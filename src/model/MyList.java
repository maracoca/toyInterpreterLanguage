package model;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    private List<T> elements;

    public MyList() {
        this.elements = new ArrayList<T>();
    }

    @Override
    public void add(T elem) {
        elements.add(elem);
    }

    @Override
    public List getAll() {
        return elements;
    }

    @Override
    public String toString() {
        return "My list(" + elements + ")";
    }

}
