package com.example.freydis.drinklink.model;

import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Freydis on 3/10/2016.
 */
public class User {

    private String userId;
    private String firstname;
    private String lastname;
    private ImageView profilePic;
    private ArrayList<Group> groups = new ArrayList<Group>();


    public User() {

    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public void removeGroup(Group group) {
        this.groups.remove(group);
    }

    public ArrayList<Group> getAllGroups() {
        return this.groups;
    }
}
