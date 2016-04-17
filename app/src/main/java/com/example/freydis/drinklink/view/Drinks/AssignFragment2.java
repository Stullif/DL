package com.example.freydis.drinklink.view.Drinks;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freydis.drinklink.R;
import com.example.freydis.drinklink.view.DataAdapter;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Freydis on 3/31/2016.
 */
public class AssignFragment2 extends Fragment {

    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_assign_frag2, container, false);
        gridView = (GridView) rootView.findViewById(R.id.friendsGridview);

        new GraphRequest(AccessToken.getCurrentAccessToken(),"/me/friends", null, HttpMethod.GET,
            new GraphRequest.Callback() {
                public void onCompleted(GraphResponse response) {
                    handleFriendResponse(rootView, response);
                }
            }
        ).executeAsync();

        return rootView;
    }

    private void populateFriends(final View view, final ArrayList<String> friends, ArrayList<String> friends_pic) {
        DataAdapter adapter = new DataAdapter(view.getContext(), friends, friends_pic);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                /*AssignActivity.totalDrinks = AssignActivity.totalDrinks-1;
                Log.d("stulli", AssignActivity.totalDrinks + "");
                TextView vi =(TextView) getView().findViewById(R.id.assignFragDrinksLeftCount);
                vi.setText(""+AssignActivity.totalDrinks);*/
                Toast.makeText(v.getContext(), friends.get(position)+" " , Toast.LENGTH_SHORT).show();
                ((AssignActivity) getActivity()).reduceDrinksLeftCount();
                // DO something
                //Toast.makeText(v.getContext(), "send notifications ... ", Toast.LENGTH_SHORT).show();

            }
        });
        //gridView.setOnItemClickListener(this);
    }

    public void handleFriendResponse(View view, GraphResponse response) {
        try {
            JSONObject jsonObject = response.getJSONObject();

            ArrayList<String> friends = new ArrayList<String>();
            ArrayList<String> friends_pic = new ArrayList<String>();
            JSONArray data = (JSONArray)jsonObject.get("data");
            int length = data.length();

            for(int i = 0; i < length; i++) {
                JSONObject friend = data.getJSONObject(i);
                String name = friend.getString("name");
                String id = friend.getString("id");
                URL profile_pic = new URL("https://graph.facebook.com/"+id+"/picture?width=200&height=200");

                friends.add(name);
                friends_pic.add(profile_pic.toString());
            }
            populateFriends(view, friends, friends_pic);
        } catch ( Throwable t ) {
            t.printStackTrace();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //listener = null;
    }

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(view.getContext(), "send notifications ... ", Toast.LENGTH_SHORT).show();
        if (listener != null) {
            // notify interface that an item has been selected
            listener.onItemSelected(id);
        }
    }*/
}
