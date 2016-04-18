package com.example.freydis.drinklink.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.freydis.drinklink.R;

/**
 * Created by gudkj on 4/17/2016.
 */
public class TabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tab, container, false);
        ArrayAdapter adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, new String[]{"entry 1","entry 2"});
        ListView listView = (ListView) view.findViewById(R.id.tabList);
        listView.setAdapter(adapter);
        return view;

    }
}
