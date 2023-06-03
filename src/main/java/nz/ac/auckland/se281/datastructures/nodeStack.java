package nz.ac.auckland.se281.datastructures;

public class nodeStack<T> implements Stack<T> {
    // List<T> stack = new ArrayList<T>();
    Node<T> head = null;
    Node<T> tail = null;

    @Override
    public void push(T item) {
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

    @Override
    public T pop() {
        if (tail == null) {
            return null;
        }

        T temp = tail.getData();
        tail = tail.getPrev();
        tail.setNext(null);
        return temp;
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

    @Override
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
