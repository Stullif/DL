package com.example.freydis.drinklink.model;

/**
 * Created by Freydis on 3/10/2016.
 */
public class Drink {

    public enum DrinkType {
        BEER,
        SHOT,
        COCKTAIL
    }

    public Drink(DrinkType type) {
        //this.drinkId = type.id;
        this.type = type;
    }

    private int drinkId;
    private DrinkType type;
    private String name;
    private double price;

    public DrinkType getType() {
        return this.type;
    }
    public int getDrinkId() {
        return this.drinkId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return this.price;
    }
}
