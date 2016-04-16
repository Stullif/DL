package com.example.freydis.drinklink.model;

import java.util.List;
import java.util.Date;


/**
 * Created by Stulli on 16/04/2016.
 */
public class Transaction {
    public List<Drink> drinks;
    public User userTo;
    public User userFrom;
    public String location;
    public String note;
    public Date timestamp;

    public Transaction(){

    }

    public List<Drink> getDrinks(){
        return this.drinks;
    }

    public void setDrinks(List<Drink> d){
        this.drinks = d;
    }

    public User getUserTo(){
        return this.userTo;
    }

    public void setUserTo(User u){
        this.userTo = u;
    }

    public User getUserFrom(){
        return this.userFrom;
    }

    public void setUserFrom(User u){
        this.userFrom = u;
    }

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String l){
        this.location = l;
    }

    public String getNote(){
        return this.note;
    }

    public void setNote(String n){
        this.note = n;
    }

    public Date getTimestamp(){
        return this.timestamp;
    }

    public void setTimestamp(Date t){
        this.timestamp = t;
    }
}
