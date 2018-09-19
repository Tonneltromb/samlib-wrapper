package com.ymatin.samlib.common;

public interface Identifiable<T> {

    T getIdentifier();
    void setIdentifier(T identifier);
    boolean hasIdentifier();
}
