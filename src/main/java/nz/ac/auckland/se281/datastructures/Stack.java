package nz.ac.auckland.se281.datastructures;

public interface Stack<T> {
    public void push(T item);

    public T pop();

    public boolean isEmpty();

    public int size();
}
