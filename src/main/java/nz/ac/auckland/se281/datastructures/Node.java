package nz.ac.auckland.se281.datastructures;

/**
 * A node data structure.
 *
 * @param <T> The type of each item.
 */
public class Node<T> {
  private T data;
  private Node<T> next = null;
  private Node<T> prev = null;

  public Node(T data) {
    this.data = data;
  }

  /**
   * Returns the next node.
   *
   * @return the next node.
   */
  public Node<T> getNext() {
    return next;
  }

  /**
   * Returns the previous node.
   *
   * @return the previous node.
   */
  public Node<T> getPrev() {
    return prev;
  }

  /**
   * Returns the data of the node.
   *
   * @return the data of the node.
   */
  public T getData() {
    return data;
  }

  /**
   * Sets the data of the next node.
   *
   * @param next the data of the next node.
   */
  public void setNext(Node<T> next) {
    this.next = next;
  }

  /**
   * Sets the data of the previous node.
   *
   * @param prev the data of the previous node.
   */
  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }
}
