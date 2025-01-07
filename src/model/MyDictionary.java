package model;

import exceptions.DictionaryException;
import exceptions.MyException;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Map<Key, Value> getContent() {
        return this.map;
    }

    @Override
    public MyIDictionary<Key, Value> deepCopy() {
        MyDictionary<Key, Value> copy = new MyDictionary<>();
        copy.map = new HashMap<>(map);
        return copy;
    }

//    @Override
//    public MyIDictionary<Key, Value> clone() {
//        Map<Key, Value> dictionaryContent = this.getContent();
//        MyDictionary<Key, Value> newDictionary = new MyDictionary<>();
//        newDictionary.put(dictionaryContent.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
//        return newDictionary;
//    }
}

