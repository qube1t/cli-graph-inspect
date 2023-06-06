package nz.ac.auckland.se281.datastructures;

/**
 * A node stack data structure extending LinkedList and implementing Stack.
 *
 * @param <T> The type of each item.
 */
public class NodeStack<T> extends LinkedList<T> implements Stack<T> {

  @Override
  public T pop() {
    // call parent removeLast method
    return removeLast();
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public int size() {
    Node<T> temp = head;
    int stackSize = 0;

    // iterate through the stack for size
    while (temp != null) {
      stackSize++;
      temp = temp.getNext();
    }

    return stackSize;
  }

}
