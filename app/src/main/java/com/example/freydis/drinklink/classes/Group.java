package com.example.freydis.drinklink.classes;

import com.example.freydis.drinklink.classes.UserData.User;

import java.util.ArrayList;

/**
 * Created by Freydis on 3/10/2016.
 */
public class Group {

    //private User user;
    private String groupName;
    private ArrayList<User> groupMembers;

    public Group(String groupName) {
        this.groupName = groupName;

    }

    public void addMember(User user) {
        this.groupMembers.add(user);
    }

    public void deleteMember(User user) {
        this.groupMembers.remove(user);
    }

    public void editName(String name) {
        this.groupName = name;
    }

    public ArrayList<User> getAllMembers() {
        return this.groupMembers;
    }

}
