package com.example.freydis.drinklink.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freydis.drinklink.R;
import com.example.freydis.drinklink.control.DrinksPagerAdapter;

/**
 * Created by Freydis on 2/1/2016.
 */
public class DrinksFragment extends Fragment {

    ViewPager pager;
    DrinksPagerAdapter pagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {





        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        return view;

    }
}