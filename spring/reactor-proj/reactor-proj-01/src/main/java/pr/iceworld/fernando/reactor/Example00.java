package pr.iceworld.fernando.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Example00 {

//    subscribe();
//
//    subscribe(Consumer<? super T> consumer);
//
//    subscribe(Consumer<? super T> consumer,
//              Consumer<? super Throwable> errorConsumer);
//
//    subscribe(Consumer<? super T> consumer,
//              Consumer<? super Throwable> errorConsumer,
//              Runnable completeConsumer);
//
//    subscribe(Consumer<? super T> consumer,
//              Consumer<? super Throwable> errorConsumer,
//              Runnable completeConsumer,
//              Consumer<? super Subscription> subscriptionConsumer);

    public static void main(String[] args) {

        action13();
    }

    static void action13() {
        final Mono<String> mono = Mono.just("hello ");

        Thread t = new Thread(() -> mono
                .map(msg -> msg + "thread ")
                .subscribe(v ->
                        System.out.println(v + Thread.currentThread().getName())
                )
        );
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void action12() {
        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null)
                        sink.next(letter);
                });

        alphabet.subscribe(System.out::println);
    }

    public static String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }


    static void action11() {
        MyMessageProcessor myMessageProcessor = new MyMessageProcessor();
        Flux<String> bridge = Flux.create(sink -> {
            myMessageProcessor.register(
                    new MyMessageListener<String>() {
                        public void onMessage(List<String> messages) {
                            for (String s : messages) {
                                sink.next(s);
                            }
                        }
                    });
            sink.onRequest(n -> {
                List<String> messages = myMessageProcessor.getHistory(n);
                for (String s : messages) {
                    sink.next(s);
                }
            });
        });

        // Subscribe to the Flux and print the results
        bridge.subscribe(
                data -> System.out.println("Received: " + data),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Event processing complete!")
        );
    }

    static class MyMessageProcessor {
        private MyMessageListener<String> listener;
        List<String> messages = new ArrayList<>();

        public void register(MyMessageListener<String> listener) {
            this.listener = listener;

            // Simulate event-driven data chunk processing
            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                // Simulate emitting some data chunks
                messages.add("message 1");
                messages.add("message 2");
                messages.add("message 3");
                listener.onMessage(messages);
            }, 1, TimeUnit.SECONDS);
        }

        public List<String> getHistory(Long n) {

            return messages;
        }
    }


    interface MyMessageListener<T> {
        void onMessage(List<T> messages);
    }

    static void action10() {
        MyEventProcessor2 myEventProcessor = new MyEventProcessor2();

        Flux<String> bridge = Flux.push(sink -> {
            myEventProcessor.register(
                    new SingleThreadEventListener<>() {
                        public void onDataChunk(List<String> chunk) {
                            for (String s : chunk) {
                                sink.next(s);
                            }
                        }

                        public void processComplete() {
                            sink.complete();
                        }

                        public void processError(Throwable e) {
                            sink.error(e);
                        }
                    });
        });

        // Subscribe to the Flux and print the results
        bridge.subscribe(
                data -> System.out.println("Received: " + data),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Event processing complete!")
        );
    }

    static class MyEventProcessor2 {
        private SingleThreadEventListener<String> listener;

        // Register the event listener
        public void register(SingleThreadEventListener<String> listener) {
            this.listener = listener;

            // Simulate event-driven data chunk processing
            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                // Simulate emitting some data chunks
                List<String> dataChunk1 = new ArrayList<>();
                dataChunk1.add("event1");
                dataChunk1.add("event2");
                listener.onDataChunk(dataChunk1);

                List<String> dataChunk2 = new ArrayList<>();
                dataChunk2.add("event3");
                dataChunk2.add("event4");
                listener.onDataChunk(dataChunk2);

                listener.processError(new RuntimeException("bbbbbbbbbbbbbbb"));

                // Complete the event processing
                listener.processComplete();
            }, 1, TimeUnit.SECONDS);
        }
    }

    interface SingleThreadEventListener<T> {
        void onDataChunk(List<T> chunk);

        void processError(Throwable e);

        void processComplete();

    }

    static void action09() {
        MyEventProcessor myEventProcessor = new MyEventProcessor();
        Flux<String> bridge = Flux.create(sink -> {
            myEventProcessor.register(
                    new MyEventListener<String>() {
                        public void onDataChunk(List<String> chunk) {
                            for (String s : chunk) {
                                sink.next(s);
                            }
                        }

                        public void processComplete() {
                            sink.complete();
                        }
                    });
        });
//        bridge.subscribe(System.out::println);

        // Subscribe to the Flux and print the results
        bridge.subscribe(
                data -> System.out.println("Received: " + data),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Event processing complete!")
        );
    }

    static class MyEventProcessor {
        private MyEventListener<String> listener;

        // Register the event listener
        public void register(MyEventListener<String> listener) {
            this.listener = listener;

            // Simulate event-driven data chunk processing
            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                // Simulate emitting some data chunks
                List<String> dataChunk1 = new ArrayList<>();
                dataChunk1.add("event1");
                dataChunk1.add("event2");
                listener.onDataChunk(dataChunk1);

                List<String> dataChunk2 = new ArrayList<>();
                dataChunk2.add("event3");
                dataChunk2.add("event4");
                listener.onDataChunk(dataChunk2);

                // Complete the event processing
                listener.processComplete();
            }, 1, TimeUnit.SECONDS);
        }
    }

    interface MyEventListener<T> {
        void onDataChunk(List<T> chunk);

        void processComplete();
    }

    static void action08() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state) -> System.out.println("state: " + state));

        flux.subscribe();
    }

    static void action07() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                });
        flux.subscribe(System.out::println);
    }

    static void action06() {
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });

        flux.subscribe(System.out::println);
    }


    static void action05() {
        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    public void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    public void hookOnNext(Integer integer) {
                        System.out.println("Cancelling after having received " + integer);
                        cancel();
                    }
                });
    }

    static void action04() {
        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
        Flux<Integer> ints = Flux.range(1, 10);
        ints.subscribe(ss);
    }

    static class SampleSubscriber<T> extends BaseSubscriber<T> {

        @Override
        public void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed");
            request(1);
        }

        @Override
        public void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }

    static void action03() {
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));
    }

    static void action02() {
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error: " + error));
    }

    static void action01() {
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(i -> System.out.println(i));
    }

}
