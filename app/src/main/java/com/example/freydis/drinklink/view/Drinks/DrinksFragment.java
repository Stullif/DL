package com.example.freydis.drinklink.view.Drinks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.freydis.drinklink.R;

/**
 * Created by Freydis on 3/30/2016.
 */
public class DrinksFragment extends Fragment {

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
                Intent intent = new Intent(getActivity(), AssignActivity.class);
                intent.putExtra("title", "halo");
                startActivity(intent);
            }
        });
    }
}
