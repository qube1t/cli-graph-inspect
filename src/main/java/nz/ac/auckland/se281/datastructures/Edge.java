package nz.ac.auckland.se281.datastructures;

/**
 * An edge in a graph that connects two verticies.
 *
 * <p>
 * You must NOT change the signature of the constructor of this class.
 *
 * @param <T> The type of each vertex.
 */
public class Edge<T> {
  private T source;
  private T destination;

  public Edge(T source, T destination) {
    this.source = source;
    this.destination = destination;
  }

  /**
   * Returns source of the edge.
   * 
   * @return source of the edge.
   */
  public T getSource() {
    return source;
  }

  /**
   * Returns destination of the edge.
   * 
   * @return destination of the edge.
   */
  public T getDestination() {
    return destination;
  }

  /**
   * Checks equality of two edges.
   * 
   * @param edge Edge object to check equality with.
   * @return Boolean showing equality.
   */
  // TODO
  public boolean equals(T s, T d) {
    return (s.equals(source) && d.equals(destination));
  }

  public String toString() {
    return getSource() + "->" + getDestination();
  }
}
