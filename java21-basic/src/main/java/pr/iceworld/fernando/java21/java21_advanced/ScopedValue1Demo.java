package pr.iceworld.fernando.java21.java21_advanced;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

/**
 * ThreadLocal使用的时候，需要remove参数，但是ScopedValue在使用时，是不需要remove参数的，
 * ScopedValue 具备 ThreadLocal 的核心特征，也就是每个线程只有一个值。
 * 与 ThreadLocal 不同的是，ScopedValue 是不可变的，并且有确定的作用域，
 * 但是同样的，ThreadLocal也能在线程中使用
 */
public class ScopedValue1Demo {

    private static ScopedValue<String> stringScopedValue = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(stringScopedValue, say()).run(() -> {
            String result = stringScopedValue.get();
            System.out.println(result);
            hello();
        });

        ScopedValue.runWhere(stringScopedValue, "BBB", () -> {
                    System.out.println(stringScopedValue.get());
                }
        );

        String result = null;
        try {
            result = ScopedValue.callWhere(stringScopedValue, say(), ()->{
                System.out.println(12);
                System.out.println("--> " + stringScopedValue.get());
                return "HHH";
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);

        // 线程传值
        ScopedValue.runWhere(stringScopedValue, "aaaa", () -> {
            try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                StructuredTaskScope.Subtask<String> user = scope.fork(() -> {
                    System.out.println("hello:" + stringScopedValue.get());
                    return "hello";
                });
                StructuredTaskScope.Subtask<Integer> order = scope.fork(() -> {
                    System.out.println("say():" + stringScopedValue.get());
                    return 13;
                });
                try {
                    scope.join().throwIfFailed();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });




        System.out.println(stringScopedValue.get());
    }

    public static int i = 0;

    public static String say() {
        System.out.println("============say()");
        return i++ % 2 == 0 ? "Hello" : "World";
    }


    public static void hello() {
        System.out.println(stringScopedValue.get());
    }
}
