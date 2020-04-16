package observer;

public interface ObservableEvent<T> {
    /**
     * @param observer to add
     */
    void addEObserver(EventObserver<T> observer);
    /**
     * @param observer to remove
     */
    void removeEObserver(EventObserver<T> observer);
    /**
     * @param arg to send to observers
     */
    void notifyEventObservers(T arg);
}
