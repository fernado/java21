package pr.iceworld.fernando.java21.java21_advanced;

import java.util.concurrent.*;
import java.util.*;

public class ParallelTaskManagement {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var task1 = scope.fork(() -> performTask1());
            var task2 = scope.fork(() -> performTask2());
            var task3 = scope.fork(() -> performTask3());

            // 等待所有任务完成
            scope.join();

            // 合并结果并处理
            int result1 = task1.get();
            String result2 = task2.get();
            double result3 = task3.get();

            System.out.printf("Result1: %d, Result2: %s, Result3: %.2f%n", result1, result2, result3);
            System.out.println(STR."Result1: \{result1}, Result2: \{result2}, Result3: \{result3}");
        } catch (Exception e) {
            System.err.println("Failed to complete tasks: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    private static int performTask1() throws InterruptedException {
        Thread.sleep(1000);
        return 42;
    }

    private static String performTask2() throws InterruptedException {
        Thread.sleep(1500);
        return "Task 2 Completed";
    }

    private static double performTask3() throws InterruptedException {
        Thread.sleep(2000);
        return 3.14;
    }
}
