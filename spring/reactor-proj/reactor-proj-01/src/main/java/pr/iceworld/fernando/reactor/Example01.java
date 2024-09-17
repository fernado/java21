package pr.iceworld.fernando.reactor;

import reactor.core.publisher.Flux;

public class Example01 {
    public static void main(String[] args) {
        Flux<Integer> ints3 = Flux.range(1, 5);
        ints3.subscribe(System.out::println,
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(3));

    }
}
