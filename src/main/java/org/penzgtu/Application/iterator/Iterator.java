package org.penzgtu.Application.iterator;

public interface Iterator<T> {
    boolean hasNext();
    T next();
    void reset();

}