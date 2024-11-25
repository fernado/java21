package pr.iceworld.fernando.java21.java21_advanced;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomClassLoaderExample1 {

    // 公共类加载器，所有线程共享
    static class SharedClassLoader extends URLClassLoader {
        public SharedClassLoader(URL[] urls) {
            super(urls);
        }
    }

    // 独立类加载器，线程独享
    static class IsolatedClassLoader extends URLClassLoader {
        public IsolatedClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }
    }

    public static void main(String[] args) throws Exception {
        CustomClassLoaderExample1 customClassLoaderExample1 = new CustomClassLoaderExample1();
        customClassLoaderExample1.doAction();
    }

    public void doAction() throws Exception {
        // 定义公共类路径
        File sharedClassPath = new File("D:\\GitHub\\java21-learning\\java21-basic\\temp_for_custom_classloader\\shared");
        URL sharedUrl = sharedClassPath.toURI().toURL();

        // 定义每个线程的独立类路径
        File thread1ClassPath = new File("D:\\GitHub\\java21-learning\\java21-basic\\temp_for_custom_classloader\\thread1");
        File thread2ClassPath = new File("D:\\GitHub\\java21-learning\\java21-basic\\temp_for_custom_classloader\\thread2");
        URL thread1Url = thread1ClassPath.toURI().toURL();
        URL thread2Url = thread2ClassPath.toURI().toURL();

        // 创建共享的类加载器
        SharedClassLoader sharedClassLoader = new SharedClassLoader(new URL[]{sharedUrl});

        //
        Class<?> shared1 = sharedClassLoader.loadClass("pr.iceworld.fernando.java21.java21_advanced.shared.ShareClass");
//        Class<?> shared2 = this.getClass().getClassLoader().loadClass("pr.iceworld.fernando.java21.java21_advanced.shared.ShareClass");
//        Object sharedInstance1 = shared1.getDeclaredConstructor().newInstance();
//        Object sharedInstance2 = shared2.getDeclaredConstructor().newInstance();
        System.out.println("Shared loaded class: " + shared1.getName());
        System.out.println("Shared loaded class: " + shared1.getClassLoader().getParent().getName());

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 线程1任务
        executor.submit(() -> {
            try {
                // 线程1独立类加载器
                IsolatedClassLoader classLoader1 = new IsolatedClassLoader(new URL[]{thread1Url}, sharedClassLoader);
                // 加载类并实例化
                Class<?> clazz1 = classLoader1.loadClass("pr.iceworld.fernando.java21.java21_advanced.thread1.Thread1Class");
//                Object instance1 = clazz1.getDeclaredConstructor().newInstance();
                System.out.println("Thread 1 loaded class: " + clazz1.getName());
                System.out.println("Thread 1 loaded class: " + clazz1.getClassLoader().getParent().getName());
            } catch (Exception e) {
                System.err.println(e);
            }
        });

        // 线程2任务
        executor.submit(() -> {
            try {
                // 线程2独立类加载器
                IsolatedClassLoader classLoader2 = new IsolatedClassLoader(new URL[]{thread2Url}, sharedClassLoader);
                // 加载类并实例化
                Class<?> clazz2 = classLoader2.loadClass("pr.iceworld.fernando.java21.java21_advanced.thread2.Thread2Class");
//                Object instance2 = clazz2.getDeclaredConstructor().newInstance();
                System.out.println("Thread 2 loaded class: " + clazz2.getName());
                System.out.println("Thread 2 loaded class: " + clazz2.getClassLoader().getParent().getName());
            } catch (Exception e) {
                System.err.println(e);
            }
        });

        // 关闭线程池
        executor.shutdownNow();
    }
}
