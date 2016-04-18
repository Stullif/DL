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

import java.util.HashMap;

/**
 * Created by Freydis on 3/26/2016.
 */
public class AssignActivity extends AppCompatActivity {

    private int beerCount;
    private int shotCount;
    private int cockCount;
    private HashMap<Long,Integer> beerAssignments = new HashMap<Long,Integer>();
    private HashMap<Long,Integer> shotAssignments = new HashMap<Long,Integer>();
    private HashMap<Long,Integer> cockAssignments = new HashMap<Long,Integer>();
    public static int totalDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            beerCount = extras.getInt("beerCount");
            shotCount = extras.getInt("shotCount");
            cockCount = extras.getInt("cockCount");
            totalDrinks = beerCount + shotCount + cockCount;
            Log.d("extractIntent", beerCount + " " + shotCount + " " + cockCount);
        }


        TextView vi = (TextView) findViewById(R.id.assignFragDrinksLeftCount);
        vi.setText(totalDrinks + "");
        Log.d("Stulli", vi.getText() + "");

        TextView nextDrinkTextValue = (TextView) findViewById(R.id.nextDrinkTextValue);
        if( totalDrinks == 0) {
            Log.d("stulli", "supposed to disappear: "+totalDrinks);
            findViewById(R.id.drinksLeftText).setVisibility(View.GONE);
            nextDrinkTextValue.setText("All out of beer");
        }else if(totalDrinks <= beerCount) {
            nextDrinkTextValue.setText("Beer");
        }else if((totalDrinks <= shotCount + beerCount)&&(totalDrinks > beerCount)){
            nextDrinkTextValue.setText("Shot");
        }else if(totalDrinks > (shotCount + beerCount)){
            nextDrinkTextValue.setText("Cocktail");
        }

        Button continueButton = (Button) findViewById(R.id.continueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "send notifications ... ", Toast.LENGTH_SHORT).show();
                for ( HashMap.Entry<Long, Integer> entry : beerAssignments.entrySet()) {
                    Long key = entry.getKey();
                    Integer value = entry.getValue();
                    Log.d("usertransaction","Beers: userId: " + key + " amount: " + value);
                }
                for ( HashMap.Entry<Long, Integer> entry : shotAssignments.entrySet()) {
                    Long key = entry.getKey();
                    Integer value = entry.getValue();
                    Log.d("usertransaction","Shots: userId: " + key + " amount: " + value);
                }
                for ( HashMap.Entry<Long, Integer> entry : cockAssignments.entrySet()) {
                    Long key = entry.getKey();
                    Integer value = entry.getValue();
                    Log.d("usertransaction","Cocks: userId: " + key + " amount: " + value);
                }
            }
        });
    }//((AssignActivity) getActivity()).reduceDrinksLeftCount(v,user_ids.get(position), friends.get(position));

    public void reduceDrinksLeftCount(View v, Long userId, String userName) {


        TextView vi =(TextView) findViewById(R.id.assignFragDrinksLeftCount);
        vi.setText(totalDrinks + "");
        TextView nextDrinkTextValue = (TextView) findViewById(R.id.nextDrinkTextValue);
        if( totalDrinks == 0) {
            findViewById(R.id.drinksLeftText).setVisibility(View.GONE);
            nextDrinkTextValue.setText("All out of beer");
            Toast.makeText(v.getContext(), "Nothing more to assign" , Toast.LENGTH_SHORT).show();
        }else if(totalDrinks <= beerCount) {
            if(beerAssignments.get(userId)== null) {
                beerAssignments.put(userId,1);
            }else{
                beerAssignments.put(userId,beerAssignments.get(userName)+1);
            }
            nextDrinkTextValue.setText("Beer");
            totalDrinks--;
            Toast.makeText(v.getContext(), "Assigned beer to "+userName , Toast.LENGTH_SHORT).show();
        }else if((totalDrinks <= shotCount + beerCount)&&(totalDrinks > beerCount)){
            if(shotAssignments.get(userId)== null) {
                shotAssignments.put(userId, 1);
            }else{
                shotAssignments.put(userId,shotAssignments.get(userName)+1);
            }
            nextDrinkTextValue.setText("Shot");
            totalDrinks--;
            Toast.makeText(v.getContext(), "Assigned shot to "+userName , Toast.LENGTH_SHORT).show();
        }else if(totalDrinks > (shotCount + beerCount)){
            if(cockAssignments.get(userId)== null) {
                cockAssignments.put(userId,1);
            }else{
                cockAssignments.put(userId,cockAssignments.get(userName)+1);
            }
            nextDrinkTextValue.setText("Cocktail");
            totalDrinks--;
            Toast.makeText(v.getContext(), "Assigned cocktail to "+userName , Toast.LENGTH_SHORT).show();
        }
    }
}
