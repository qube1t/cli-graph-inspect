package nz.ac.auckland.se281.datastructures;

public class NodeStack<T> extends LinkedList<T> implements Stack<T> {

  @Override
  public T pop() {
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
    while (temp != null) {
      stackSize++;
      temp = temp.getNext();
    }

    return stackSize;
  }

}
