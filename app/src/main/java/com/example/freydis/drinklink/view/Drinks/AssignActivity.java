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

    private int beerCount;
    private int shotCount;
    private int cockCount;
    private int totalDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
<<<<<<< HEAD
            beerCount = extras.getString("beerCount");
            shotCount = extras.getString("shotCount");
            cockCount = extras.getString("cockCount");
            totalDrinks = beerCount + shotCount + cockCount;
=======
            beerCount = extras.getInt("beerCount");
            shotCount = extras.getInt("shotCount");
            cockCount = extras.getInt("cockCount");
            Log.d("extractIntent", beerCount + " " + shotCount + " " + cockCount);
>>>>>>> dc2c4ec4e20c25451b86d639b4fb339a211453da
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
