package com.bug.issuereplicate.model;

import java.util.UUID;

public class Cat implements Pet{

    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getName() {
        return "cat";
    }

    @Override
    public String toString() {
        return this.getName() + " - " + this.getId();
    }
}
