package model;

import exceptions.DictionaryException;
import exceptions.MyException;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<Key, Value> implements MyIDictionary<Key, Value> {
    private Map<Key, Value> map;

    public MyDictionary() {
        this.map = new HashMap<Key, Value>();
    }

    @Override
    public void put(Key key, Value value) {
        this.map.put(key, value);
    }

    @Override
    public Value get(Key key) throws MyException {
        if (!isDefined(key)) {
            throw new DictionaryException();
        }
        return map.get(key);
    }

    @Override
    public void remove(Key key) {
        this.map.remove(key);
    }

    @Override
    public boolean isDefined(Key key) {
        return this.map.containsKey(key);
    }

    public String toString() {
        return map.toString();
    }
}
