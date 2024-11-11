package model;

import java.util.Dictionary;
import java.util.List;

public interface MyIDictionary<Key, Value> {
    void put(Key key, Value value);
    Value get(Key key);
    void remove(Key key);
    boolean isDefined(Key key);
}
