package com.alds.graph;

/**
 * Auxilary class for building graph elements
 */
public class DependencyPair<T> {

    private T independant;
    private T dependant;

    public DependencyPair(T independant, T dependant) {
        this.independant = independant;
        this.dependant = dependant;
    }

    public T getIndependant() {
        return independant;
    }

    public T getDependant() {
        return dependant;
    }
}
