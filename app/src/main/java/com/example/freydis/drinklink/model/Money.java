package com.example.freydis.drinklink.model;

import java.util.Currency;

/**
 * Created by Freydis on 3/10/2016.
 */
public class Money {

    private Double amount;
    private Currency currency;

    public Money(){

    }

    public Double getAmount(){
        return this.amount;
    }

    public void setAmount(Double a){
        this.amount = a;
    }

    public Currency getCurrency(){
        return this.currency;
    }

    public void setCurrency(Currency c){
        this.currency = c;
    }
}
