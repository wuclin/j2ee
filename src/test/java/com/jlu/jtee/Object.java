package com.jlu.jtee;

public class Object {
    Object Key;
    Object Value;

    public Object getKey() {
        return Key;
    }

    public void setKey(Object key) {
        Key = key;
    }

    public Object getValue() {
        return Value;
    }

    public void setValue(Object value) {
        Value = value;
    }

    public Object(Object key, Object value) {
        Key = key;
        Value = value;
    }

    @Override
    public String toString() {
        return " {Key=" + Key +
                ", Value=" + Value +
                '}';
    }
}
