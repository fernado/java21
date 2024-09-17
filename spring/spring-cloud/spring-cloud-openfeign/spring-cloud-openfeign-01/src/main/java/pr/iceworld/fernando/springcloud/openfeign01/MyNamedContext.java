package pr.iceworld.fernando.springcloud.openfeign01;

import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义的命名上下文
 */
class MyNamedContext extends NamedContextFactory {
    public MyNamedContext() {
        // MyDefaultConfig 是公共的默认配置
        super(MyDefaultConfig.class, "kuku", "kuku.name");
    }

    /**
     * 给 name 中的容器，注入类型
     * @param name
     * @param clazz
     */
    public void registerBean(String name, Class clazz) {
        GenericApplicationContext context = getContext(name);
        context.registerBean(clazz);
    }
}