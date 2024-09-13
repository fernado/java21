package pr.iceworld.fernando.java21.java21_advanced;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestVirtualThread {

    public Runnable doSomethings() {

        return () -> System.out.println("--------------------------");
    }

    @Test
    public void test_01() throws InterruptedException {
        // name(String prefix, Integer start) p0:前缀 p1:计数器初始值
        Thread.Builder.OfVirtual virtualThreadBuilder = Thread.ofVirtual().name("worker-", 0);
        Thread worker0 = virtualThreadBuilder.start(this::doSomethings);
        worker0.join();
        System.out.print("finish worker-0 running");
        Thread worker1 = virtualThreadBuilder.start(this::doSomethings);
        worker1.join();
        System.out.print("finish worker-1 running");
    }

    /**
     * 虚拟线程既便宜又丰富，因此永远不应该被池化，应该为每个应用程序任务创建一个新的虚拟线程。使用 newVirtualThreadPerTaskExecutor
     * 创建的是一个没有线程数量限制的线程池（并不是一个典型的线程池，并不是为了复用线程而存在），其会为每个提交的任务创建一个新的虚拟线程进行处理。
     */

    @Test
    public void test_02() {
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<?> submit = executorService.submit(this::doSomethings);
            submit.get();
            System.out.print("finish running");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
