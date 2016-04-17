package com.example.freydis.drinklink.view.Drinks;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.freydis.drinklink.R;

import java.util.List;

/**
 * Created by Freydis on 3/30/2016.
 */
public class DrinksFragment extends Fragment {
    
    public static int beerCount = 0;
    public static int shotCount = 0;
    public static int cockCount = 0;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drinks, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Button assignButton = (Button) view.findViewById(R.id.assignButton);
        final Drinks_List drinksList = (Drinks_List) getFragmentManager().findFragmentById(R.id.drinksList);

        assignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("stulli", "Bjór:" + getBeerCount()+"  "+ "Skot:" + getShotCount()+"  " + "Typpi:" + getCockCount());
                Intent intent = new Intent(getActivity(), AssignActivity.class);
                intent.putExtra("beerCount", beerCount);
                intent.putExtra("shotCount", shotCount);
                intent.putExtra("cockCount", cockCount);
                startActivity(intent);
            }
        });
    }
    
    public int getBeerCount(){
        return this.beerCount;
    }

    public static void setBeerCount(int beerCount) {
        DrinksFragment.beerCount = beerCount;
    }

    public int getShotCount(){
        return this.shotCount;
    }

    public static void setShotCount(int shotCount) {
        DrinksFragment.shotCount = shotCount;
    }

    public int getCockCount(){
        return this.cockCount;
    }

    public static void setCockCount(int cockCount) {
        DrinksFragment.cockCount = cockCount;
    }
}
