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

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by gudkj on 4/17/2016.
 */
public class TabFragment extends Fragment implements OnTaskCompleted {
    private View view;
    public Boolean from = true;

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
                from = true;
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
                from = false;
                //populateTabList(v, new String[]{"owe 1", "owe 2"});
            }
        });
        /*ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new String[]{"entry 1", "entry 2"});
        ListView listView = (ListView) view.findViewById(R.id.tabList);
        listView.setAdapter(adapter);*/

        return view;

    }
    public void populateTabList(String[] transactions, String[] friend_pic) {
        //ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, transactions);
        ListView listView = (ListView) getActivity().findViewById(R.id.tabList);
        //listView.setAdapter(adapter);

        DataAdapter adapter = new DataAdapter(view.getContext(), new ArrayList<String>(Arrays.asList(transactions)), new ArrayList<String>(Arrays.asList(friend_pic)));
        listView.setAdapter(adapter);


        /*CustomList adapter = new
                CustomList(MainActivity.this, transactions, friend_pic);
        list=(ListView)findViewById(R.id.tabList);
                list.setAdapter(adapter);*/

    }
    public void onGETTaskCompleted(String result) {
        Log.d("tabReturnGet","now in get complete");
        if(result.trim().equals("")) {
            populateTabList(new String[] {"No results"}, new String[]{""});
            return;
        }
        //Log.d("splitResult", "before split: "+ result);
        String[] vars = new String[] {"drink","drink","userFrom","userTo"};
        int index = 1;
        Scanner scanner = new Scanner(result);
        String currentDrink = "";
        HashMap<String,Integer> drinkCounts = new HashMap<String,Integer>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //Log.d("splitResult",line);
            // process the line
            if(vars[index].equals("drink")) currentDrink = line;
            if(from) {
                if(vars[index].equals("userTo")) {
                    if(drinkCounts.get(line+"-"+currentDrink) != null) {
                        drinkCounts.put(line+"-"+currentDrink, drinkCounts.get(line+"-"+currentDrink)+1);
                    }else{
                        drinkCounts.put(line+"-"+currentDrink,1);
                    }
                }
            }else{
                if(vars[index].equals("userFrom")) {
                    if(drinkCounts.get(line+"-"+currentDrink) != null) {
                        drinkCounts.put(line+"-"+currentDrink, drinkCounts.get(line+"-"+currentDrink)+1);
                    }else{
                        drinkCounts.put(line+"-"+currentDrink,1);
                    }
                }
            }
            index++;
            index %= vars.length;
        }
        ArrayList<String> populator = new ArrayList<String>();
        ArrayList<String> friend_pic = new ArrayList<String>();
        for ( HashMap.Entry<String, Integer> entry : drinkCounts.entrySet()) {
            String key = entry.getKey();
            String[] userDrink = key.split("-");
            Integer value = entry.getValue();
            populator.add(userDrink[1] + "s : " + value);
            if(from) {
                Log.d("drinkCounts", "user:" + userDrink[0] + " drink:" + userDrink[1] + " count:" + value);
            }else{
                Log.d("drinkCounts", "user2:" + userDrink[0] + " drink:" + userDrink[1] + " count:" + value);
            }
            try{
                URL profile_pic = new URL("https://graph.facebook.com/"+userDrink[0]+"/picture?width=200&height=200");
                friend_pic.add(profile_pic.toString());
            } catch ( Throwable t ) {
                t.printStackTrace();
            }
        }

        scanner.close();
        populateTabList(populator.toArray(new String[populator.size()]),friend_pic.toArray(new String[friend_pic.size()]));
        /*String[] lines = result.split(System.getProperty("line.separator"));
        for(int i = 0; i < lines.length; i++) {
            Log.d("onTaskComplete", lines[i]);
        }*/
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
