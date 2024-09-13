package pr.iceworld.fernando.java21.java21_advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerformanceTest {
 
 
    private static final int REQUEST_NUM = 10000;
 
 
    public static void main(String[] args) {
        long vir = 0, p1 = 0, p2 = 0, p3 = 0, p4 = 0;
        for (int i = 0; i < 3; i++) {
            vir += testVirtualThread();
            p1 += testPlatformThread(200);
            p2 += testPlatformThread(500);
            p3 += testPlatformThread(800);
            p4 += testPlatformThread(1000);
            System.out.println("--------------");
        }
        System.out.println("虚拟线程平均耗时：" + vir / 3 + "ms");
        System.out.println("平台线程[200]平均耗时：" + p1 / 3 + "ms");
        System.out.println("平台线程[500]平均耗时：" + p2 / 3 + "ms");
        System.out.println("平台线程[800]平均耗时：" + p3 / 3 + "ms");
        System.out.println("平台线程[1000]平均耗时：" + p4 / 3 + "ms");
    }
 
 
    private static long testVirtualThread() {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < REQUEST_NUM; i++) {
            executorService.submit(PerformanceTest::handleRequest);
        }
        executorService.close();
        long useTime = System.currentTimeMillis() - startTime;
        System.out.println("虚拟线程耗时：" + useTime + "ms");
        return useTime;
    }
 
 
    private static long testPlatformThread(int poolSize) {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < REQUEST_NUM; i++) {
            executorService.submit(PerformanceTest::handleRequest);
        }
        executorService.close();
        long useTime = System.currentTimeMillis() - startTime;
        System.out.printf("平台线程[%d]耗时：%dms\n", poolSize, useTime);
        return useTime;
    }
 
 
    private static void handleRequest() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}