package com.example.freydis.drinklink.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stulli on 16/04/2016.
 */
public class Tab {
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    public User owner = null;

    public Tab(){

    }

    public void updateTab(List<Transaction> tl){

    }

    public void removeTransaction(Transaction t){

    }

    public Transaction getMostRecentTransaction(){
        return new Transaction();
    }

    public Transaction getSpecificTransaction(Transaction t){
        return new Transaction();
    }

    public void clear(){

    }
}
