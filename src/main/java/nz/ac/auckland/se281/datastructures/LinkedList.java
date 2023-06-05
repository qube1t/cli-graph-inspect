package nz.ac.auckland.se281.datastructures;

public abstract class LinkedList<T> {
  protected Node<T> head = null;
  protected Node<T> tail = null;

  public void append(T item) {
    if (head == null) {
      head = new Node<T>(item);
      tail = head;
      return;
    }

    Node<T> newitem = new Node<T>(item);
    newitem.setPrev(tail);
    tail.setNext(newitem);
    tail = newitem;
  }

  public T removeFirst() {
    // empty queue returns null
    if (head == null) {
      return null;
    }

    T temp = head.getData();
    head = head.getNext();

    if (head != null)
      head.setPrev(null);
    else
      tail = null;

    return temp;
  }

  public T removeLast() {
    if (tail == null) {
      return null;
    }

    T temp = tail.getData();
    tail = tail.getPrev();

    if (tail != null)
      tail.setNext(null);
    else
      head = null;

    return temp;
  }

  public abstract boolean isEmpty();

  public abstract int size();

  public String toString() {
    Node<T> temp = head;
    StringBuilder str = new StringBuilder();

    str.append("[");
    while (temp != null) {
      str.append(temp.getData());
      str.append(", ");
      temp = temp.getNext();
    }
    str.append("]");

    return str.toString();

  }

}
