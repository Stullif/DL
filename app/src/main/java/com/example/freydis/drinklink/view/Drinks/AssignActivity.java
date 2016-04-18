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
import com.example.freydis.drinklink.control.POSTAsyncTask;
import com.example.freydis.drinklink.view.OnTaskCompleted;
import com.example.freydis.drinklink.view.MainActivity;
import com.facebook.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Created by Freydis on 3/26/2016.
 */
public class AssignActivity extends AppCompatActivity implements OnTaskCompleted {

    private int beerCount;
    private int shotCount;
    private int cockCount;
    private HashMap<Long,Integer> beerAssignments = new HashMap<Long,Integer>();
    private HashMap<Long,Integer> shotAssignments = new HashMap<Long,Integer>();
    private HashMap<Long,Integer> cockAssignments = new HashMap<Long,Integer>();
    private ArrayList<Long> userIdTransactions = new ArrayList<Long>();
    private ArrayList<Integer> tranIdTransactions = new ArrayList<Integer>();
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
                Profile profile = Profile.getCurrentProfile();
                Long currentUserId = Long.parseLong(profile.getId());
                Log.d("usertransaction","currentUserId: "+currentUserId);
                Toast.makeText(v.getContext(), "send notifications ... ", Toast.LENGTH_SHORT).show();
                /*Log.d("insertionValues", "about to get users");
                HashSet<Long> userIds = new HashSet<Long>();
                for ( HashMap.Entry<Long, Integer> entry : beerAssignments.entrySet()) {
                    userIds.add(entry.getKey());
                }
                for ( HashMap.Entry<Long, Integer> entry : shotAssignments.entrySet()) {
                    userIds.add(entry.getKey());
                }
                for ( HashMap.Entry<Long, Integer> entry : cockAssignments.entrySet()) {
                    userIds.add(entry.getKey());
                }
                Log.d("insertionValues", "about to make transactions");
                for( Long userId : userIds) {
                    userIdTransactions.add(userId);
                    String sql = "insert into transactions (userFrom, userTo, note) values(" + currentUserId + ", " + userId + ", 'tester')";
                    Log.d("transactionPost", sql);
                    new POSTAsyncTask(AssignActivity.this).execute(sql, "insert");
                    Log.d("insertionValues", "made Transaction");
                }*/
                /*Log.d("insertionValues", "done making transactions, length: "+tranIdTransactions.size());

                HashMap<Long,Integer> userTransactions = new HashMap<Long, Integer>();
                for(int i = 0; i < tranIdTransactions.size(); i++) {
                    userTransactions.put(userIdTransactions.get(i),tranIdTransactions.get(i));
                    Log.d("insertionValues", "id: "+ userIdTransactions.get(i)+ "tranId: "+ tranIdTransactions.get(i));
                }*/
                for ( HashMap.Entry<Long, Integer> entry : beerAssignments.entrySet()) {
                    Long key = entry.getKey();
                    Integer value = entry.getValue();
                    Log.d("usertransaction", "Beers: userId: " + key + " amount: " + value);
                    for(int i = 0; i < value; i++) {
                        String sql = "insert into drinks (drinkType, drinkName, userFrom, userTo ) values ('beer','beer','"+currentUserId+"','"+key+"')";
                        Log.d("drinkPost",sql);
                        new POSTAsyncTask(AssignActivity.this).execute(sql,"insert");//+userTransactions.get(key) +")","insert");
                    }
                }
                for ( HashMap.Entry<Long, Integer> entry : shotAssignments.entrySet()) {
                    Long key = entry.getKey();
                    Integer value = entry.getValue();
                    Log.d("usertransaction", "Shots: userId: " + key + " amount: " + value);
                    for(int i = 0; i < value; i++) {
                        String sql = "insert into drinks (drinkType, drinkName, userFrom, userTo ) values ('shot','shot','"+currentUserId+"','"+key+"')";
                        Log.d("drinkPost",sql);
                        new POSTAsyncTask(AssignActivity.this).execute(sql,"insert");//userTransactions.get(key) +")","insert");
                    }
                }
                for ( HashMap.Entry<Long, Integer> entry : cockAssignments.entrySet()) {
                    Long key = entry.getKey();
                    Integer value = entry.getValue();
                    Log.d("usertransaction","Cocks: userId: " + key + " amount: " + value);
                    for(int i = 0; i < value; i++) {
                        String sql = "insert into drinks (drinkType, drinkName, userFrom, userTo ) values ('cocktail','cocktail','"+currentUserId+"','"+key+"')";
                        Log.d("drinkPost",sql);
                        new POSTAsyncTask(AssignActivity.this).execute(sql,"insert");//+userTransactions.get(key) +")","insert");
                    }
                }
                Log.d("done","done making insertions");
                Intent intent = new Intent(AssignActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }//((AssignActivity) getActivity()).reduceDrinksLeftCount(v,user_ids.get(position), friends.get(position));

    public void reduceDrinksLeftCount(View v, Long userId, String userName) {


        TextView vi =(TextView) findViewById(R.id.assignFragDrinksLeftCount);

        TextView nextDrinkTextValue = (TextView) findViewById(R.id.nextDrinkTextValue);
        if( totalDrinks == 0) {
            findViewById(R.id.drinksLeftText).setVisibility(View.GONE);
            nextDrinkTextValue.setText("All out of beer");
            Toast.makeText(v.getContext(), "Nothing more to assign" , Toast.LENGTH_SHORT).show();
        }else if(totalDrinks <= beerCount) {
            if(beerAssignments.get(userId)== null) {
                beerAssignments.put(userId, 1);
            }else{
                beerAssignments.put(userId, beerAssignments.get(userName) + 1);
            }
            nextDrinkTextValue.setText("Beer");
            totalDrinks--;
            Toast.makeText(v.getContext(), "Assigned beer to "+userName , Toast.LENGTH_SHORT).show();
        }else if((totalDrinks <= shotCount + beerCount)&&(totalDrinks > beerCount)){
            if(shotAssignments.get(userId)== null) {
                shotAssignments.put(userId, 1);
            }else{
                shotAssignments.put(userId, shotAssignments.get(userName) + 1);
            }
            nextDrinkTextValue.setText("Shot");
            totalDrinks--;
            Toast.makeText(v.getContext(), "Assigned shot to "+userName , Toast.LENGTH_SHORT).show();
        }else if(totalDrinks > (shotCount + beerCount)){
            if(cockAssignments.get(userId)== null) {
                cockAssignments.put(userId, 1);
            }else{
                cockAssignments.put(userId, cockAssignments.get(userName) + 1);
            }
            nextDrinkTextValue.setText("Cocktail");
            totalDrinks--;
            Toast.makeText(v.getContext(), "Assigned cocktail to "+userName , Toast.LENGTH_SHORT).show();
        }
        vi.setText(totalDrinks + "");
    }

    public void onGETTaskCompleted(String result) {
        Log.d("onTaskComplete", "get: " + result);
    }
    public void onPOSTTaskCompleted(String result) {
        Log.d("onTaskComplete", "postdrink: " + result);
        //tranIdTransactions.add(Integer.parseInt(result));
        //Log.d("onTaskComplete", "transactionsize: "+tranIdTransactions.size());
    }
}
