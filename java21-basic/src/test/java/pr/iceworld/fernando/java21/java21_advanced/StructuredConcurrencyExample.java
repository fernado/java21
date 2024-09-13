package pr.iceworld.fernando.java21.java21_advanced;

import java.util.List;
import java.util.concurrent.*;

public class StructuredConcurrencyExample {
    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            List<Callable<String>> tasks = List.of(
                () -> fetchDataFromSource1(),
                () -> fetchDataFromSource2(),
                () -> fetchDataFromSource3()
            );

            // 提交任务
            List<StructuredTaskScope.Subtask<String>> futures = tasks.stream()
                .map(task -> scope.fork(task))
                .toList();
            
            // 等待所有任务完成
            scope.join();

            // 处理结果
            futures.stream()
                .map(StructuredTaskScope.Subtask::get)
                .forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Failed to complete tasks: " + e.getMessage());
        }
    }

    private static String fetchDataFromSource1() throws InterruptedException {
        Thread.sleep(1000);
        return "Data from Source 1";
    }

    private static String fetchDataFromSource2() throws InterruptedException {
        Thread.sleep(1200);
        return "Data from Source 2";
    }

    private static String fetchDataFromSource3() throws InterruptedException {
        Thread.sleep(1500);
        return "Data from Source 3";
    }
}
