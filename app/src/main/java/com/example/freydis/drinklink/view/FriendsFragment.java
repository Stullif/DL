package com.example.freydis.drinklink.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freydis.drinklink.R;

/**
 * Created by Freydis on 2/10/2016.
 */
public class FriendsFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        return view;

    }
}
