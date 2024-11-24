package model;

import exceptions.DictionaryException;
import exceptions.MyException;

import java.util.Dictionary;
import java.util.List;

public interface MyIDictionary<Key, Value> {
    void put(Key key, Value value);
    Value get(Key key) throws MyException;
    void remove(Key key);
    boolean isDefined(Key key);
}
