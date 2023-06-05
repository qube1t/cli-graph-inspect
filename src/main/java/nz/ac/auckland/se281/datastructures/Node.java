package nz.ac.auckland.se281.datastructures;

public class Node<T> {
  T data;
  Node<T> next = null;
  Node<T> prev = null;

  public Node(T data) {
    this.data = data;
  }

  public Node<T> getNext() {
    return next;
  }

  public Node<T> getPrev() {
    return prev;
  }

  public T getData() {
    return data;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }
}
