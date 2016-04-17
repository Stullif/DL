package com.example.freydis.drinklink.view.Drinks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freydis.drinklink.R;

/**
 * Created by Freydis on 3/26/2016.
 */
public class AssignActivity extends AppCompatActivity {
    private String beerCount;
    private String shotCount;
    private String cockCount;
    private int totalDrinks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            beerCount = extras.getString("beerCount");
            shotCount = extras.getString("shotCount");
            cockCount = extras.getString("cockCount");
            totalDrinks = beerCount + shotCount + cockCount;
        }

        Button continueButton = (Button) findViewById(R.id.continueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView v = findViewById(R.id.assignFragDrinksLeftCount);
                Log.d("Stulli", v.getText()+"");
                Toast.makeText(v.getContext(), "send notifications ... ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
