package observer;

public interface EventObserver<T> {
    /**
     * @param source of the event
     * @param event to send to observer
     */
    void update(ObservableEvent<T> source, T event);
}
