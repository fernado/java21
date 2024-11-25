package pr.iceworld.fernando.java21.java21_advanced;

/**
 * 生命在于思考，我们来想想ThreadLocal有什么缺点：
 *
 * 第一个就是权限问题，也许我们只需要在main()方法中给ThreadLocal赋值，在其他方法中获取值就可以了，
 * 而上述代码中 methodA()、methodB()方法都有权限给ThreadLocal赋值，ThreadLocal不能做权限控制。
 *
 * 第二个就是内存问题，ThreadLocal需要手动强制remove，也就是在用完ThreadLocal之后，比如 methodB()方法中，
 * 应该调用其remove()方法，但是我们很容易忘记调用remove()，从而造成内存浪费。
 */
public class ThreadLocalDemo {
    public final static ThreadLocal<String> NAME = new ThreadLocal();


    void main(String[] args) {
        NAME.set("fernando");
        methodA();
        System.out.println("main() " + NAME.get());
    }

    private static void methodA() {
        System.out.println("methodA() " + NAME.get());
        methodB();
    }

    private static void methodB() {
        System.out.println("methodB() " + NAME.get());
        NAME.remove();
    }

}
