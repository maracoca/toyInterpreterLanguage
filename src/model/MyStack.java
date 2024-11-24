package model;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<>();
    }

    @Override
    public void push(T stmt) {
        stack.push(stmt);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    public String toString() {
//        return stack.toString();
        if (stack.isEmpty())
            return "";
        String output = stack.getLast().toString();
        if (stack.size() > 1) {
            output += "\n";
            output += " " + stack.getFirst().toString();
        }
        return output;
    }
}
