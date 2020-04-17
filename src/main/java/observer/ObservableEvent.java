package observer;

public interface ObservableEvent<T> {
    /**
     * @param observer to add
     */
    void addEventObserver(EventObserver<T> observer);
    /**
     * @param observer to remove
     */
    void removeEventObserver(EventObserver<T> observer);
    /**
     * @param event to send to observers
     */
    void notifyEventObservers(T event);
}
