package nz.ac.auckland.se281.datastructures;

public interface Stack<T> {
    public void append(T item);

    public T pop();

    public abstract boolean isEmpty();

    public abstract int size();
}
