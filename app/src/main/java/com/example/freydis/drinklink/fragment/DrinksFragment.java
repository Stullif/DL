package com.example.freydis.drinklink.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freydis.drinklink.R;

/**
 * Created by Freydis on 2/1/2016.
 */
public class DrinksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        return view;

    }
}
