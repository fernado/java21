package pr.iceworld.fernando.listenerevent.one.event;

/**
 * 事件监听器
 *
 * @param <E> 当前监听器感兴趣的事件类型
 */
public interface EventListener<E extends AbstractEvent> {
    /**
     * 此方法负责处理事件
     *
     * @param event 要响应的事件对象
     */
    void processEvent(E event);
}