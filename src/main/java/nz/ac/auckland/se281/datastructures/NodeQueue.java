package nz.ac.auckland.se281.datastructures;

/**
 * A node queue data structure extending LinkedList and implementing Queue.
 *
 * @param <T> The type of each item.
 */
public class NodeQueue<T> extends LinkedList<T> implements Queue<T> {

  @Override
  public void enqueue(T item) {
    // call parent append method
    append(item);
  }

  @Override
  public T dequeue() {
    // call parent removeFirst method
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

    // iterate through the queue for size
    while (temp != null) {
      queueSize++;
      temp = temp.getNext();
    }

    return queueSize;
  }

}
