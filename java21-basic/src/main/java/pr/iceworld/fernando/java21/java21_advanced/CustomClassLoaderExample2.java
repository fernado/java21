package pr.iceworld.fernando.java21.java21_advanced;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoaderExample2 {

    // 共享类加载器，多个应用共享
    static class SharedClassLoader extends URLClassLoader {
        public SharedClassLoader(URL[] urls) {
            super(urls);
        }

        // 重写 loadClass 方法，确保只加载共享类，不加载特定应用的类
        @Override
        public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // 检查类是否已经被加载
                Class<?> loadedClass = findLoadedClass(name);
                if (loadedClass == null) {
                    try {
                        // 只尝试加载共享类路径上的类
                        loadedClass = findClass(name);
                    } catch (ClassNotFoundException e) {
                        // 如果找不到类，委托给父类加载器（系统类加载器）
                        loadedClass = super.loadClass(name, resolve);
                    }
                }
                if (resolve) {
                    resolveClass(loadedClass);
                }
                return loadedClass;
            }
        }
    }

    // 应用类加载器，加载应用专属类
    static class AppClassLoader extends URLClassLoader {

        public AppClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        @Override
        public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // 首先检查类是否已经被加载
                Class<?> loadedClass = findLoadedClass(name);
                if (loadedClass == null) {
                    try {
                        // 尝试加载应用特有的类
                        loadedClass = findClass(name);
                    } catch (ClassNotFoundException e) {
                        // 如果找不到，则委托给父类加载器（共享类加载器）
                        loadedClass = super.loadClass(name, resolve);
                    }
                }
                if (resolve) {
                    resolveClass(loadedClass);
                }
                return loadedClass;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // 定义共享类路径
//        File sharedClassPath = new File("path/to/shared/libs");
        File sharedClassPath = new File("D:\\GitHub\\java21-learning\\java21-basic\\temp_for_custom_classloader\\shared");
        URL sharedUrl = sharedClassPath.toURI().toURL();

        // 定义每个应用的类路径
//        File app1ClassPath = new File("path/to/app1/classes");
        File app1ClassPath = new File("D:\\GitHub\\java21-learning\\java21-basic\\temp_for_custom_classloader\\thread1");
//        File app2ClassPath = new File("path/to/app2/classes");
        File app2ClassPath = new File("D:\\GitHub\\java21-learning\\java21-basic\\temp_for_custom_classloader\\thread2");
        URL app1Url = app1ClassPath.toURI().toURL();
        URL app2Url = app2ClassPath.toURI().toURL();

        // 创建共享的类加载器
        SharedClassLoader sharedClassLoader = new SharedClassLoader(new URL[]{sharedUrl});

        // 为应用1创建自己的类加载器，父类加载器是共享类加载器
        AppClassLoader app1ClassLoader = new AppClassLoader(new URL[]{app1Url}, sharedClassLoader);

        // 为应用2创建自己的类加载器，父类加载器是共享类加载器
        AppClassLoader app2ClassLoader = new AppClassLoader(new URL[]{app2Url}, sharedClassLoader);

        // 模拟应用1加载类
        Class<?> app1Class = app1ClassLoader.loadClass("pr.iceworld.fernando.java21.java21_advanced.thread1.Thread1Class");
        System.out.println("App 1 loaded class: " + app1Class.getName());

        Class<?> sharedClass1 = app1ClassLoader.loadClass("pr.iceworld.fernando.java21.java21_advanced.shared.ShareClass");
        System.out.println("Shared class loaded:  " + sharedClass1.getName());

        // 模拟应用2加载类
        Class<?> app2Class = app2ClassLoader.loadClass("pr.iceworld.fernando.java21.java21_advanced.thread2.Thread2Class");
        System.out.println("App 2 loaded class: " + app2Class.getName());

        Class<?> sharedClass2 = app2ClassLoader.loadClass("pr.iceworld.fernando.java21.java21_advanced.shared.ShareClass");
        System.out.println("Shared class loaded:  " + sharedClass2.getName());

        // 共享类加载器加载共享类
        Class<?> sharedClass = sharedClassLoader.loadClass("pr.iceworld.fernando.java21.java21_advanced.shared.ShareClass");
        System.out.println("Shared class loaded: " + sharedClass.getName());

        // ----
        try {
            Class<?> app1Class2 = sharedClassLoader.loadClass("pr.iceworld.fernando.java21.java21_advanced.thread1.Thread1Class");
            System.out.println("sharedClassLoader loaded class: " + app1Class2.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("sharedClassLoader loaded class: pr.iceworld.fernando.java21.java21_advanced.thread1.Thread1Class not found");
        }
    }
}
