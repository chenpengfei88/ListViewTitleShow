package com.chenpengfei.listviewtitleshow;

import java.util.ArrayList;

/**
 * Created by Chenpengfei on 2015/5/27.
 */
public class Parent {

    private String name;

    private ArrayList<Child> childArrayList = new ArrayList<Child>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Child> getChildArrayList() {
        return childArrayList;
    }

    public void setChildArrayList(ArrayList<Child> childArrayList) {
        this.childArrayList = childArrayList;
    }
}
