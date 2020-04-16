package view;

import java.util.HashSet;
import java.util.Set;

import observer.EventObserver;
import observer.ObservableEvent;

public abstract class ObservableViewImpl<T> implements ObservableEvent<T> {
    private final Set<EventObserver<T>> observers;

    public ObservableViewImpl() {
        this.observers = new HashSet<EventObserver<T>>();
    }

    @Override
    public final void removeEObserver(final EventObserver<T> observer) {
        this.observers.remove(observer);
    }

    @Override
    public final void addEObserver(final EventObserver<T> observer) {
        this.observers.add(observer);
    }

    @Override
    public final void notifyEventObservers(final T arg) {
        this.observers.forEach(x -> x.update(this, arg));
    }
}
