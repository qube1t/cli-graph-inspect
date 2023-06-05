package nz.ac.auckland.se281.datastructures;

public class nodeStack<T> extends LinkedList<T> implements Stack<T> {
    // List<T> stack = new ArrayList<T>();

    // @Override
    // public void push(T item) {
    // if (head == null) {
    // head = new Node<T>(item);
    // tail = head;
    // return;
    // }

    // Node<T> newitem = new Node<T>(item);
    // newitem.setPrev(tail);
    // tail.setNext(newitem);
    // tail = newitem;
    // }

    @Override
    public T pop() {
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
