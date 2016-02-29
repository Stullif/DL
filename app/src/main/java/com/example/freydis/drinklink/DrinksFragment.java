package com.example.freydis.drinklink;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Freydis on 2/1/2016.
 */
public class DrinksFragment extends Fragment {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
        String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();
        TextView nameView = (TextView)findViewById(R.id.nameAndSurname);
        nameView.setText("" + name + " " + surname);

        new DownloadImage((ImageView)findViewById(R.id.profileImage)).execute(imageUrl);
        int screenOrientation = getResources().getConfiguration().orientation;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        return view;

    }
}
