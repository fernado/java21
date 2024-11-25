package pr.iceworld.fernando.java21.java21_advanced;


/**
 * 首先需要通过ScopedValue.newInstance()生成一个ScopedValue对象，然后通过ScopedValue.runWhere()
 * 方法给ScopedValue对象赋值，runWhere()的第三个参数是一个lambda表达式，表示作用域，比如上面代码就表示：
 * 给NAME绑定值为"dadudu"，但是仅在调用 methodA()方法时才生效，并且在执行runWhere()方法时就会执行lambda表达式。
 */
public class ScopedValueDemo {
    public final static ScopedValue<String> NAME = ScopedValue.newInstance();


    void main(String[] args) {
        // 执行main()方法时，main线程执行过程中会执行runWhere()方法三次，而每次执行runWhere()时都会生成一个Snapshot对象，
        // Snapshot对象中记录了所绑定的值，而Snapshot对象有一个prev属性指向上一次所生成的Snapshot对象，
        // 并且在Thread类中新增了一个属性scopedValueBindings，专门用来记录当前线程对应的Snapshot对象。
        ScopedValue.runWhere(NAME, "fernando", ScopedValueDemo::methodA);
        System.out.println(NAME.isBound());
        System.out.println("main() " + NAME.get());

    }

    private static void methodA() {
        System.out.println("methodA() " + NAME.get());
        // ScopedValue也支持在某个方法中重新开启新的作用域并绑定值，比如：
        ScopedValue.runWhere(NAME, "John", ScopedValueDemo::methodC);
        methodB();
        methodC();
    }


    private static void methodC() {
        System.out.println("methodC() " + NAME.get());
    }

    /**
     * ScopedValue也不需要手动remove
     */
    private static void methodB() {
        System.out.println("methodB() " + NAME.get());
    }

}
