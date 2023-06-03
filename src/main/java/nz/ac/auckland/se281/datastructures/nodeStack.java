package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.List;

public class nodeStack<T> implements Stack<T> {
    List<T> stack = new ArrayList<T>();

    @Override
    public void push(T item) {
        stack.add(item);
    }

    @Override
    public T pop() {

        if (stack.size() == 0) {
            return null;
        }

        return stack.remove(stack.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

}
