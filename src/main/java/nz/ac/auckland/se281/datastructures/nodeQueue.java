package nz.ac.auckland.se281.datastructures;

public class nodeQueue<T> extends LinkedList<T> implements Queue<T> {
    // List<T> queue = new ArrayList<T>();

    @Override
    public void enqueue(T item) {
        append(item);
    }

    @Override
    public T dequeue() {
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

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        Node<T> temp = head;
        int queue_size = 0;
        while (temp != null) {
            queue_size++;
            temp = temp.getNext();
        }

        return queue_size;
    }

}
