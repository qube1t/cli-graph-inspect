package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.List;

public class nodeQueue<T> implements Queue<T> {
    // List<T> queue = new ArrayList<T>();
    Node<T> head = null;
    Node<T> tail = null;

    @Override
    public void enqueue(T item) {
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
    public T dequeue() {
        if (head == null) {
            return null;
        }

        T temp = head.getData();
        head = head.getNext();
        head.setPrev(null);
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
