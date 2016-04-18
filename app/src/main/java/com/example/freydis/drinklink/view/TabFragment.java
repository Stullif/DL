package com.example.freydis.drinklink.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.freydis.drinklink.R;
import com.example.freydis.drinklink.control.GETAsyncTask;
import com.example.freydis.drinklink.model.Transaction;
import com.example.freydis.drinklink.view.Drinks.AssignActivity;
import com.facebook.Profile;

import java.util.ArrayList;

/**
 * Created by gudkj on 4/17/2016.
 */
public class TabFragment extends Fragment implements OnTaskCompleted {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tab, container, false);
        Button collectButton = (Button) view.findViewById(R.id.buttonCollect);
        Button oweButton = (Button) view.findViewById(R.id.buttonOwe);
        ArrayList<String> userIds = new ArrayList<String>();

        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profile = Profile.getCurrentProfile();
                Long currentUserId = Long.parseLong(profile.getId());
                Log.d("splitResult", "current user ID:" + currentUserId);
                //new GETAsyncTask(TabFragment.this).execute("SELECT * FROM transactions", "userFrom", "userTo");// WHERE userFrom="+currentUserId,"userFrom","userTo");
                new GETAsyncTask(TabFragment.this).execute("SELECT * FROM drinks WHERE userFrom='"+currentUserId+"'");
                //populateTabList(v, new String[]{"collect 1", "collect 2"});
            }
        });
        oweButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profile = Profile.getCurrentProfile();
                Long currentUserId = Long.parseLong(profile.getId());
                new GETAsyncTask(TabFragment.this).execute("SELECT * FROM drinks WHERE userTo='"+currentUserId+"'");
                //populateTabList(v, new String[]{"owe 1", "owe 2"});
            }
        });
        /*ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new String[]{"entry 1", "entry 2"});
        ListView listView = (ListView) view.findViewById(R.id.tabList);
        listView.setAdapter(adapter);*/

        return view;

    }
    public void populateTabList(String[] transactions) {
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, transactions);
        ListView listView = (ListView) getActivity().findViewById(R.id.tabList);
        listView.setAdapter(adapter);
    }
    public void onGETTaskCompleted(String result) {
        Log.d("tabReturnGet","now in get complete");
        Log.d("splitResult", "before split: "+ result);
        //String[] results = result.split("&");
        //Log.d("splitResult", "length:" + results.length);
        //populateTabList(results);


        /*for(int i = 0; i < results.length-1; i++) {
            Log.d("splitResult", results[i]);
            String[] subresult = results[i].split("||");
            results[i] = "Transaction from:"+subresult[0]+" to:"+subresult[1];
        }
        populateTabList(results);
        Log.d("onTaskComplete", result);*/
    }
    public void onPOSTTaskCompleted(String result) {
        Log.d("onTaskComplete", "post: " + result);
    }
}
