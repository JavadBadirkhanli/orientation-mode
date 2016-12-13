package com.javadbadirkhanly.orientationmode.models;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by javadbadirkhanly on 12/13/16.
 */

public class Group implements ParentListItem{

    private String name;
    private int id;
    List<GroupItem> itemList;

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

    public List<GroupItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<GroupItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public List<GroupItem> getChildItemList() {
        return itemList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
