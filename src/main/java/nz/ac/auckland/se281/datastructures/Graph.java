package nz.ac.auckland.se281.datastructures;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.TreeSet;

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
  private ArrayList<T> verticies;
  private ArrayList<Edge<T>> edges;

  /**
   * Constructs a graph with the given verticies and edges.
   *
   * @param verticies The verticies of the graph.
   * @param edges     The edges of the graph.
   */
  public Graph(Set<T> verticies, Set<Edge<T>> edges) {

    ArrayList<T> verticiesArrayList = new ArrayList<T>(verticies);
    ArrayList<Edge<T>> edgesArrayList = new ArrayList<Edge<T>>(edges);

    Collections.sort(verticiesArrayList, new Comparator<T>() {
      @Override
      public int compare(T arg0, T arg1) {
        return ((Integer) Integer.parseInt((String) arg0)).compareTo(Integer.parseInt((String) arg1));
      }
    });

    Collections.sort(edgesArrayList, new Comparator<Edge<T>>() {
      @Override
      public int compare(Edge<T> arg0, Edge<T> arg1) {
        int c;

        // ASSUMING T IS AN INTEGER

        c = ((Integer) Integer.parseInt((String) arg0.getSource()))
            .compareTo(Integer.parseInt((String) arg1.getSource()));
        if (c == 0) {
          c = ((Integer) Integer.parseInt((String) arg0.getDestination()))
              .compareTo(Integer.parseInt((String) arg1.getDestination()));
        }
        return c;
      }
    });

    this.edges = edgesArrayList;
    this.verticies = verticiesArrayList;

  }

  /**
   * Checks if a node is reflexive.
   *
   *
   * @param vertex to check has a reflexive relationship.
   *
   * @return Boolean showing reflexivity of the node.
   */

  private boolean isNodeReflexive(T vertex) {
    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex) && edge.getDestination().equals(vertex)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the particular edge has symmetry.
   *
   *
   * @param edge Edge object to check symmetry for.
   *
   * @return Boolean showing symmetry.
   */
  private boolean doesEdgeSymmetryExist(Edge<T> edge) {
    for (Edge<T> edge1 : edges) {
      if (edge1.getSource().equals(edge.getDestination())
          && edge1.getDestination().equals(edge.getSource())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the particular edge has transitivity.
   *
   *
   * @param edge Edge object to check transitivity for.
   *
   * @return Boolean showing transitivity.
   */
  private boolean doesEdgeTransitivityExist(Edge<T> edge) {

    if (edge.getSource().equals(edge.getDestination())) {
      return true;
    }

    for (Edge<T> edge1 : edges) {

      // check if the edge is the same
      if (edge1.getSource().equals(edge.getSource()) &&
          edge1.getDestination().equals(edge.getDestination())) {
        continue;
      }

      // check if a case requiring transitivity exists
      if (edge1.getSource().equals(edge.getDestination()) &&
          !edge1.getDestination().equals(edge.getSource())) {

        // check transitivity
        for (Edge<T> edge2 : edges) {
          if (edge2.getSource().equals(edge.getSource())
              && edge2.getDestination().equals(edge1.getDestination())) {
            return true;
          }
        }
        return false;
      }
    }
    return false;
  }

  /**
   * Get set of verticies with no in degree, but at least one out degree.
   *
   *
   * @return Set of verticies with no in degree, but at least one out degree.
   */
  private Set<T> getNoInDegreeVerticies() {
    Set<T> noInDegreeVerticies = new TreeSet<T>();

    // iterate through all verticies
    for (T vertex : verticies) {
      int indegree = 0;
      int outdegree = 0;

      // iterate through all edges
      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(vertex)) {
          // if source of edge is vertex, increment outdegree
          outdegree++;
        }
        if (edge.getDestination().equals(vertex)) {
          // if destination of edge is vertex, increment indegree
          indegree++;
        }
      }

      // check requirements
      if (indegree == 0 && outdegree >= 1) {
        noInDegreeVerticies.add(vertex);
      }
    }
    return noInDegreeVerticies;
  }

  /**
   * Get set of roots of the graph.
   *
   * @return Set of roots of the graph.
   */
  public TreeSet<T> getRoots() {

    // create a set of roots with comparator
    TreeSet<T> roots = new TreeSet<T>(new Comparator<T>() {
      @Override
      public int compare(T arg0, T arg1) {
        return ((Integer) Integer.parseInt((String) arg0))
            .compareTo(Integer.parseInt((String) arg1));
      }
    });

    // add all verticies with no in degree
    roots.addAll(getNoInDegreeVerticies());

    // add lowest vertex from each equivalence class
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

    return roots;
  }

  /**
   * Check whether the graph is reflexive.
   *
   * @return Boolean showing reflexivity of the graph.
   *
   */
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

  /**
   * Check whether the graph is symmetric.
   *
   *
   * @return Boolean showing symmetry of the graph.
   */
  public boolean isSymmetric() {
    // check symmetry
    for (Edge<T> edge : edges) {
      if (!doesEdgeSymmetryExist(edge)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Check whether the graph is transitive.
   *
   * @return Boolean showing transitivity of the graph.
   */
  public boolean isTransitive() {
    // check transitivity
    for (Edge<T> edge : edges) {
      if (!doesEdgeTransitivityExist(edge)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Check whether the graph is anti-symmetric.
   *
   * @return Boolean showing anti-symmetry of the graph.
   */
  public boolean isAntiSymmetric() {
    // check anti-symmetry
    for (Edge<T> edge : edges) {
      for (Edge<T> edge1 : edges) {
        if (edge.getSource().equals(edge1.getDestination())
            && edge.getDestination().equals(edge1.getSource())) {
          if (!edge.getSource().equals(edge.getDestination())) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Check whether the graph is an equivalence relation.
   *
   * @return Boolean showing equivalence of the graph.
   */
  public boolean isEquivalence() {
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    }
    return false;
  }

  /**
   * Get set of equivalence classes of the graph.
   *
   *
   * @return TreeSet of equivalence classes of the graph sorted numerically.
   */

  public TreeSet<TreeSet<T>> getEquivalenceClasses() {

    Set<T> done = new HashSet<T>();
    TreeSet<TreeSet<T>> equivalenceClasses = new TreeSet<TreeSet<T>>(new Comparator<TreeSet<T>>() {
      @Override
      public int compare(TreeSet<T> arg0, TreeSet<T> arg1) {
        return ((Integer) Integer.parseInt((String) arg0.first())).compareTo(Integer.parseInt((String) arg1.first()));
      }
    });

    if (!isEquivalence()) {
      return equivalenceClasses;
    }

    for (Edge<T> edge : edges) {
      if (done.contains(edge.getSource()) && done.contains(edge.getDestination())) {
        continue;
      }

      // create a set of equivalence class with comparator
      TreeSet<T> equivalenceClass = new TreeSet<T>(new Comparator<T>() {
        @Override
        public int compare(T arg0, T arg1) {
          return ((Integer) Integer.parseInt((String) arg0)).compareTo(Integer.parseInt((String) arg1));
        }
      });

      equivalenceClass.add(edge.getSource());
      equivalenceClass.add(edge.getDestination());

      // add destination of all edges with the given source
      for (Edge<T> edge1 : edges) {
        if (edge1.getSource().equals(edge.getSource()) && !edge1.getDestination().equals(edge.getDestination())) {
          equivalenceClass.add(edge1.getDestination());
        }
      }

      done.addAll(equivalenceClass);
      // TODO is adding needed
      done.add(edge.getSource());
      done.add(edge.getDestination());
      equivalenceClasses.add(equivalenceClass);
    }

    return equivalenceClasses;
  }

  /**
   * Get equivalence class of a vertex.
   *
   * @param vertex
   *               Vertex to get equivalence class of.
   *
   * @return Set of equivalence class of the vertex.
   */
  public Set<T> getEquivalenceClass(T vertex) {
    TreeSet<TreeSet<T>> equivalenceClasses = getEquivalenceClasses();

    for (Set<T> equivalenceClass : equivalenceClasses) {
      if (equivalenceClass.contains(vertex)) {
        return equivalenceClass;
      }
    }

    return new TreeSet<T>();
  }

  /**
   * Perform iterative breadth first search on the graph.
   *
   * @return List of the visited verticies in the order they were visited.
   */
  public List<T> iterativeBreadthFirstSearch() {

    List<T> visited = new ArrayList<T>();
    Queue<T> queue = new NodeQueue<T>();

    // select a random node
    T selectedNode = getRoots().size() == 0 ? verticies.get(0) : ((TreeSet<T>) getRoots()).first();

    while (visited.size() < verticies.size()) {

      // checks if selectedNode has been visited
      boolean isVisited = visited.contains(selectedNode);

      // if selectedNode has been visited, get a new unvisited node
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

      // add selectedNode to visited
      visited.add(selectedNode);

      // add all adjacents to queue
      for (Edge<T> edge : edges) {
        // ignore self loops
        if (edge.getSource() == edge.getDestination()) {
          continue;
        }

        // add adjacent to queue
        if (edge.getSource() == selectedNode) {
          queue.enqueue(edge.getDestination());
        }
      }
    }
    return visited;
  }

  /**
   * Perform iterative depth first search on the graph.
   *
   * @return List of the visited verticies in the order they were visited.
   */

  public List<T> iterativeDepthFirstSearch() {
    List<T> visited = new ArrayList<T>();
    Stack<T> stack = new NodeStack<T>();

    // select a random node
    T selectedNode = getRoots().size() == 0 ? verticies.get(0) : ((TreeSet<T>) getRoots()).first();

    while (visited.size() < verticies.size()) {

      // checks if selectedNode has been visited
      boolean isVisited = visited.contains(selectedNode);

      // if selectedNode has been visited, get a new unvisited node
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

      // add selectedNode to visited
      visited.add(selectedNode);

      // reversed order edges
      Collections.reverse(edges);

      // add all adjacents to queue
      for (Edge<T> edge : edges) {
        // ignore self loops
        if (edge.getSource() == edge.getDestination()) {
          continue;
        }

        // add adjacent to queue
        if (edge.getSource() == selectedNode) {
          stack.append(edge.getDestination());
        }
      }

      Collections.reverse(edges);
    }

    return visited;
  }

  /**
   * Perform recursive depth first search on the graph.
   *
   * @params visited List of visited verticies.
   *
   * @params queue Queue of verticies to visit.
   */

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

    visited.add(selectedNode);

    for (Edge<T> edge : edges) {
      if (edge.getSource() == selectedNode) {
        if (edge.getSource() == edge.getDestination())
          continue;
        queue.enqueue(edge.getDestination());
      }
    }

    if (visited.size() < verticies.size()) {
      breadthFirstSearch(visited, queue);
    }
  }

  /**
   * Initiate recursive breadth first search on the graph.
   *
   * @return List of the visited verticies in the order they were visited.
   */

  public List<T> recursiveBreadthFirstSearch() {
    List<T> visited = new ArrayList<T>();
    Queue<T> queue = new NodeQueue<T>();

    queue.enqueue(getRoots().size() == 0 ? verticies.get(0) : (getRoots()).first());

    breadthFirstSearch(visited, queue);

    return visited;
  }

  /**
   * Perform recursive depth first search on the graph.
   *
   * @params visited List of visited verticies.
   *
   * @params stack Stack of verticies to visit.
   */

  private void depthFirstSearch(List<T> visited, Stack<T> stack) {
    T selectedNode = null;
    boolean isVisited = true;

    while (isVisited) {
      selectedNode = stack.pop();
      System.out.println(selectedNode);

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

    visited.add(selectedNode);

    // add all adjacents to stack after reversing numerical order
    Collections.reverse(edges);

    for (Edge<T> edge : edges) {
      if (edge.getSource() == selectedNode) {
        if (edge.getSource() == edge.getDestination()) {
          continue;
        }

        stack.append(edge.getDestination());
      }
    }

    Collections.reverse(edges);

    // if not all verticies have been visited, then continue
    if (visited.size() < verticies.size()) {
      depthFirstSearch(visited, stack);
    }

  }

  /**
   * Initiate recursive depth first search on the graph.
   *
   * @return List of the visited verticies in the order they were visited.
   */

  public List<T> recursiveDepthFirstSearch() {
    List<T> visited = new ArrayList<T>();
    Stack<T> stack = new NodeStack<T>();

    stack.append(getRoots().size() == 0 ? verticies.get(0) : ((TreeSet<T>) getRoots()).first());

    depthFirstSearch(visited, stack);

    return visited;

  }
}
