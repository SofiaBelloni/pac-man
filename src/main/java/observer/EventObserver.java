package observer;

public interface EventObserver<T> {
    void update(ObservableEvent<T> source, T arg);
}
