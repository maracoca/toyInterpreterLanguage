package model;

public interface MyIStack<T> {
    void push(T value);
    boolean isEmpty();
    T pop();
}
