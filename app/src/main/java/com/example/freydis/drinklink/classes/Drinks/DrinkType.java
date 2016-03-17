package com.example.freydis.drinklink.classes.Drinks;

/**
 * Created by Freydis on 3/10/2016.
 */
public class DrinkType {


    public class Beer {
        private int beerCtr = 1;
        public String volume;
        public int id;

        public Beer() {
            this.id = beerCtr++;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }
    }

    public class Shot {
        private int shotCtr = 1;
        public int id;

        public Shot() {
            this.id = shotCtr++;
        }
    }

    public class Cocktail {
        private int cocktailCtr = 1;
        public int id;

        public Cocktail() {
            this.id = cocktailCtr++;
        }
    }
}
