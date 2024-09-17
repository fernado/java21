package pr.iceworld.fernando.listenerevent.one.event;

/**
 * 事件对象
 */
public abstract class AbstractEvent {
 
    //事件源
    protected final Object source;
 
    public AbstractEvent(Object source) {
        this.source = source;
    }
 
    public Object getSource() {
        return source;
    }

}