package observer;

public interface ObservableEvent<T> {
    void addEObserver(EventObserver<T> observer);
    void notifyEventObservers(T arg);
}
