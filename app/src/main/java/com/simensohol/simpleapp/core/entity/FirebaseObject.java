package com.simensohol.simpleapp.core.entity;

/**
 * @author Simen Søhol
 */
public class FirebaseObject {
    private String id;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Id: %s. Name: %s", getId(), getName());
    }
}
