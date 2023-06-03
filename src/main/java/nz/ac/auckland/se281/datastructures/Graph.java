package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>
 * You must NOT change the signature of the existing methods or constructor of
 * this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {
  private Set<Edge<T>> edges;
  private Set<T> verticies;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    // edges.toArray(this.edges);
    // verticies.toArray(this.verticies);

    this.edges = edges;
    this.verticies = verticies;
    // Collections.sort(this.verticies);

  }

  // private void bubbleSortEdges() {
  // ArrayList<Edge<T>> edges_array = new ArrayList<Edge<T>>(edges.size());
  // for (Edge<T> edge : edges) {
  // edges_array.add(edge);
  // }

  // // System.out.println(edges_array);

  // for (Object edge : edges_array) {
  // System.out.println(((Edge<T>)edge).getDestination());
  // }

  // // for (int i = 0; i < edges_array.size() - 1; i++) {
  // // for (int j = 0; j < edges_array.size() - i - 1; j++) {
  // // if (edges_array[j].getDestination().compareTo(edges_array[j +
  // 1]).getDestination()) > 0) {
  // // Edge<T> temp = edges[j];
  // // edges.set(j, edges.get(j + 1));
  // // edges.set(j + 1, temp);
  // // }
  // // }
  // // }
  // }

  private boolean isNodeReflexive(T vertex) {
    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex) && edge.getDestination().equals(vertex)) {
        return true;
      }
    }
    return false;
  }

  private boolean doesEdgeSymmetryExist(Edge<T> edge) {
    for (Edge<T> _edge : edges) {
      if (_edge.getSource().equals(edge.getDestination()) && _edge.getDestination().equals(edge.getSource())) {
        return true;
      }
    }
    return false;
  }

  private boolean doesEdgeTransitivityExist(Edge<T> edge) {

    if (edge.getSource().equals(edge.getDestination())) {
      return true;
    }

    for (Edge<T> _edge : edges) {
      // excuse self loops
      if (_edge.getSource().equals(_edge.getDestination())) {
        continue;
      }

      if (_edge.getSource().equals(edge.getSource()) &&
          _edge.getDestination().equals(edge.getDestination()))
        continue;

      // check if a case requiring transitivity exists
      if (_edge.getSource().equals(edge.getDestination()) &&
          !_edge.getDestination().equals(edge.getSource())) {

        // check transitivity
        for (Edge<T> __edge : edges) {
          if (__edge.getSource().equals(edge.getSource()) && __edge.getDestination().equals(_edge.getDestination())) {
            // System.out.println(edge.getSource() + " -> " + edge.getDestination());
            // System.out.println(_edge.getSource() + " -> " + _edge.getDestination());
            // System.out.println(__edge.getSource() + " -> " + __edge.getDestination());
            // System.out.println();
            return true;
          }
        }
      }
    }
    return false;
  }

  public Set<T> getRoots() {
    // TODO: Task 1.
    // bubbleSortEdges();

    // TODO MAKE THIS AN ARRAY !!!!
    Set<T> roots = new HashSet<T>();

    if (!isEquivalence()) {
      throw new UnsupportedOperationException();
    }

    for (Set<T> equivalenceClass : getEquivalenceClasses()) {
      T lowest = null;
      for (T vertex : equivalenceClass) {
        if (lowest == null) {
          lowest = vertex;
        } else if (vertex.compareTo(lowest) < 0) {
          lowest = vertex;
        }
      }
      roots.add(lowest);
    }

    // }
    // throw new UnsupportedOperationException();
    return roots;
    // TODO return in order??
  }

  public boolean isReflexive() {
    // check reflexivity
    // TODO make sure there are no ghost verticies
    for (T vertex : verticies) {
      if (!isNodeReflexive(vertex)) {
        return false;
      }
    }
    return true;
  }

  public boolean isSymmetric() {
    // check symmetry
    for (Edge<T> edge : edges) {
      if (!doesEdgeSymmetryExist(edge)) {
        return false;
      }
    }

    return true;
  }

  public boolean isTransitive() {
    // check transitivity
    for (Edge<T> edge : edges) {
      if (!doesEdgeTransitivityExist(edge)) {
        return false;
      }
    }

    return true;
  }

  public boolean isAntiSymmetric() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isEquivalence() {
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    }
    return false;
  }

  public Set<Set<T>> getEquivalenceClasses() {

    if (!isEquivalence()) {
      throw new UnsupportedOperationException();
    }

    Set<T> done = new HashSet<T>();
    Set<Set<T>> equivalenceClasses = new HashSet<Set<T>>();

    for (Edge<T> edge : edges) {
      if (done.contains(edge.getSource()) && done.contains(edge.getDestination())) {
        continue;
      }

      Set<T> equivalenceClass = new HashSet<T>();
      equivalenceClass.add(edge.getSource());
      equivalenceClass.add(edge.getDestination());

      for (Edge<T> _edge : edges) {
        if (_edge.getSource().equals(edge.getSource()) && !_edge.getDestination().equals(edge.getDestination())) {
          equivalenceClass.add(_edge.getDestination());
        }
      }

      done.addAll(equivalenceClass);
      done.add(edge.getSource());
      done.add(edge.getDestination());
      equivalenceClasses.add(equivalenceClass);
    }

    return equivalenceClasses;
  }

  public Set<T> getEquivalenceClass(T vertex) {
    Set<Set<T>> equivalenceClasses = getEquivalenceClasses();

    for (Set<T> equivalenceClass : equivalenceClasses) {
      if (equivalenceClass.contains(vertex)) {
        return equivalenceClass;
      }
    }

    return new HashSet<T>();
    // TODO: Task 1.
    // throw new UnsupportedOperationException();
  }

  public List<T> iterativeBreadthFirstSearch() {

    List<T> visited = new ArrayList<T>();
    Queue<T> queue = new nodeQueue<T>();

    T selectedNode = null;

    // get firstNode
    for (T vertex : verticies) {
      selectedNode = vertex;
      break;
    }

    int _b = 0;

    while (visited.size() < verticies.size() && _b < 100) {
      _b++;

      boolean isVisited = visited.contains(selectedNode);

      while (isVisited) {
        selectedNode = queue.dequeue();

        // if queue is empty before removeing, then get a new random
        if (selectedNode == null) {
          for (T vertex : verticies) {
            if (!visited.contains(vertex)) {
              selectedNode = vertex;
              break;
            }
          }
        }

        isVisited = visited.contains(selectedNode);
      }

      visited.add(0, selectedNode);

      // for (T visitedNode : visited) {
      // if (selectedNode == visitedNode) {
      // isVisited = true;
      // }
      // }

      // System.out.println(selectedNode);
      // System.out.println(visited);
      // System.out.println(queue);
      // System.out.println();

      // add all adjacents to queue
      for (Edge<T> edge : edges) {
        if (edge.getSource() == edge.getDestination())
          continue;

        if (edge.getSource() == selectedNode) {
          // System.out.println(edge);
          // System.out.println(queue);
          queue.enqueue(edge.getDestination());
        } else if (edge.getDestination() == selectedNode) {
          // System.out.println(edge);
          // System.out.println(queue);
          queue.enqueue(edge.getSource());
        }
      }

      // selectedNode = queue.dequeue();

      // if (selectedNode == null) {
      // for (T vertex : verticies) {
      // if (visited.contains(vertex)) {
      // continue;
      // }
      // selectedNode = vertex;
      // break;
      // }
      // }
    }
    Collections.sort(visited);
    return visited;
  }

  public List<T> iterativeDepthFirstSearch() {
    List<T> visited = new ArrayList<T>();
    Stack<T> stack = new nodeStack<T>();

    throw new UnsupportedOperationException();
  }

  public void breadthFirstSearch(List<T> visited, Queue<T> queue) {
    T selectedNode = null;
    boolean isVisited = true;

    while (isVisited) {
      selectedNode = queue.dequeue();

      // if queue is empty before removeing, then get a new random
      if (selectedNode == null) {
        for (T vertex : verticies) {
          if (!visited.contains(vertex)) {
            selectedNode = vertex;
            break;
          }
        }
      }

      isVisited = visited.contains(selectedNode);
    }

    visited.add(0, selectedNode);

    for (Edge<T> edge : edges) {
      if (edge.getSource() == edge.getDestination())
        continue;

      if (edge.getSource() == selectedNode) {
        // System.out.println(edge);
        // System.out.println(queue);
        queue.enqueue(edge.getDestination());
        // } else if (edge.getDestination() == selectedNode) {
        // // System.out.println(edge);
        // // System.out.println(queue);
        // queue.enqueue(edge.getSource());
      }
    }

    if (visited.size() < verticies.size()) {
      breadthFirstSearch(visited, queue);
    }

  }

  public List<T> recursiveBreadthFirstSearch() {
    List<T> visited = new ArrayList<T>();
    Queue<T> queue = new nodeQueue<T>();

    breadthFirstSearch(visited, queue);

    Collections.sort(visited);

    return visited;
  }

  private void depthFirstSearch(List<T> visited, Stack<T> stack) {
    T selectedNode = null;
    boolean isVisited = true;

    while (isVisited) {
      selectedNode = stack.pop();

      // if queue is empty before removeing, then get a new random
      if (selectedNode == null) {
        for (T vertex : verticies) {
          if (!visited.contains(vertex)) {
            selectedNode = vertex;
            break;
          }
        }
      }

      isVisited = visited.contains(selectedNode);
    }

    System.out.println(selectedNode);

    visited.add(0, selectedNode);

    for (Edge<T> edge : edges) {
      if (edge.getSource() == edge.getDestination())
        continue;

      if (edge.getSource() == selectedNode) {
        System.out.println(stack);
        System.out.println(edge);
        stack.push(edge.getDestination());
        // } else if (edge.getDestination() == selectedNode) {
        // System.out.println(stack);
        // System.out.println(edge);
        // stack.push(edge.getSource());
      }
    }

    System.out.println();

    if (visited.size() < verticies.size()) {
      System.out.println(stack);
      System.out.println();
      depthFirstSearch(visited, stack);
    }

  }

  public List<T> recursiveDepthFirstSearch() {
    List<T> visited = new ArrayList<T>();
    Stack<T> stack = new nodeStack<T>();

    depthFirstSearch(visited, stack);

    Collections.sort(visited);

    return visited;

  }
}
