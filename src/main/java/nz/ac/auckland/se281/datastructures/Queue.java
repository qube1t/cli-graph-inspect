package nz.ac.auckland.se281.datastructures;

public interface Queue<T> {
  public void enqueue(T item);

  public T dequeue();

  public abstract boolean isEmpty();

  public abstract int size();
}
