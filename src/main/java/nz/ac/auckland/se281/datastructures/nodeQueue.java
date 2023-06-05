package nz.ac.auckland.se281.datastructures;

public class nodeQueue<T> extends LinkedList<T> implements Queue<T> {

  @Override
  public void enqueue(T item) {
    append(item);
  }

  @Override
  public T dequeue() {
    return removeFirst();
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public int size() {
    Node<T> temp = head;
    int queue_size = 0;
    while (temp != null) {
      queue_size++;
      temp = temp.getNext();
    }

    return queue_size;
  }

}
