package utils;

/**
 *
 * @param <X>
 * @param <Y>
 * 
 * this interface manages a pair of objects
 */
public interface Pair<X, Y> {
    /**
     * @return the element of type X
     */
    X getX();
    /**
     * @return the element of type Y
     */
    Y getY();
}
