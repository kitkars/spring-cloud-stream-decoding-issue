package com.bug.issuereplicate.model;

public class Dog implements Pet{

    @Override
    public String getId() {
        return String.valueOf(1);
    }

    @Override
    public String getName() {
        return "dog";
    }

    @Override
    public String toString() {
        return this.getName() + " - " + this.getId();
    }

}
