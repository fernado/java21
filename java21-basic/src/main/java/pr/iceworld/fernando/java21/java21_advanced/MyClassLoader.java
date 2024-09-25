package pr.iceworld.fernando.java21.java21_advanced;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
 
public class MyClassLoader extends ClassLoader {
    private String classPath;
 
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }
 
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classData = loadClassData(name);
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Could not load class " + name, e);
        }
    }
 
    private byte[] loadClassData(String className) throws IOException {
        String path = classPath + "/" + className.replace('.', '/') + ".class";
        return Files.readAllBytes(Paths.get(path));
    }
 
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("D:\\GitHub\\java21-learning\\java21-basic\\temp_for_custom_classloader\\shared");
        Class<?> clazz = classLoader.loadClass("pr.iceworld.fernando.java21.java21_advanced.shared.ShareClass");

        System.out.println(clazz.getName());
        System.out.println(clazz.getClassLoader().getName());
        System.out.println(clazz.getClassLoader().getParent().getName());
    }
}