package observer;

public interface EventObserver<T> {
    /**
     * @param source of th event
     * @param arg to send to observer
     */
    void update(ObservableEvent<T> source, T arg);
}
