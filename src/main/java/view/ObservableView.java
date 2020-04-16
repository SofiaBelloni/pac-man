package view;

import java.util.HashSet;
import java.util.Set;

import observer.EventObserver;
import observer.ObservableEvent;

public abstract class ObservableView<T> implements ObservableEvent<T> {
    private final Set<EventObserver<T>> observers;

    public ObservableView() {
        this.observers = new HashSet<EventObserver<T>>();
    }

    @Override
    public void addEObserver(EventObserver<T> observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyEventObservers(T arg) {
        this.observers.forEach(x -> x.update(this, arg));
    }
}
