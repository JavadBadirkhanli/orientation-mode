package com.javadbadirkhanly.orientationmode.models;

/**
 * Created by javadbadirkhanly on 12/13/16.
 */

public class GroupItem {

    private String name;
    private int id;
    private int parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
