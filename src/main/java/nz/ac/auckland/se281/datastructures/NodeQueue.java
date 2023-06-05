package nz.ac.auckland.se281.datastructures;

public class NodeQueue<T> extends LinkedList<T> implements Queue<T> {

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
    int queueSize = 0;
    while (temp != null) {
      queueSize++;
      temp = temp.getNext();
    }

    return queueSize;
  }

}
