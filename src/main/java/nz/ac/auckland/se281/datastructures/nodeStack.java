package nz.ac.auckland.se281.datastructures;

public class nodeStack<T> extends LinkedList<T> implements Stack<T> {

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
    int stack_size = 0;
    while (temp != null) {
      stack_size++;
      temp = temp.getNext();
    }

    return stack_size;
  }

}
