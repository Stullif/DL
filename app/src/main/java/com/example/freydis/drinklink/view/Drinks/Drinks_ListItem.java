package com.example.freydis.drinklink.view.Drinks;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Freydis on 3/26/2016.
 */
public class Drinks_ListItem {

    private Drawable icon;
    private String title;
    private Button minusButton;
    private TextView textView;
    private Button plusButton;

    private int count = 0;

    /*private Callbacks callbacks;

    public interface Callbacks {
        void increaseCount();
        void decreaseCount();
    }*/

    public Drinks_ListItem(Drawable icon, String title, Button minusButton, TextView textView, Button plusButton) {
        this.icon = icon;
        this.title = title;
        this.minusButton = minusButton;
        this.textView = textView;
        this.plusButton = plusButton;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public String getTitle() {
        return this.title;
    }
}
