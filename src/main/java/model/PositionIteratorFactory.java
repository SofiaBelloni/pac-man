package model;

public interface PositionIteratorFactory {
    PositionIterator pacManPositionIterator();
    PositionIterator blinkyPositionIterator();
    PositionIterator clydePositionIterator();
    PositionIterator pinkyPositionIterator();
    PositionIterator inkyPositionIterator();
}
