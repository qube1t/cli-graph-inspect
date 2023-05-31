package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.List;

public class nodeQueue<T> implements Queue<T> {
    List<T> queue = new ArrayList<T>();

    @Override
    public void enqueue(T item) {
        if (queue.size() == 0) {
            queue.add(item);
            return;
        }

        T tempLast = queue.get(queue.size() - 1);

        for (int i = queue.size() - 1; i > 0; i--) {
            queue.set(i, queue.get(i - 1));
        }

        queue.add(tempLast);
        queue.add(0, item);
    }

    @Override
    public T dequeue() {
        if (queue.size() == 0) {
            return null;
        }
        T tempLast = queue.get(queue.size() - 1);
        queue.remove(queue.size() - 1);

        return tempLast;
    }

    @Override
    public boolean isEmpty() {
        if (queue.size() == 0)
            return true;
        return false;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        return queue.toString();

    }

}
