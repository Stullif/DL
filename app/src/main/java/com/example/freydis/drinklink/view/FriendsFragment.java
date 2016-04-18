package com.example.freydis.drinklink.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.freydis.drinklink.R;
import com.example.freydis.drinklink.control.GETAsyncTask;
import com.example.freydis.drinklink.control.POSTAsyncTask;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Freydis on 2/10/2016.
 */
public class FriendsFragment extends Fragment implements OnTaskCompleted {
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




        /*final View view = inflater.inflate(R.layout.fragment_friends, container, false);

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        handleFriendResponse(view, response);
                    }
                }
        ).executeAsync();*/

        // adapter which populate the friends in listview
        //new GETAsyncTask(this).execute("SELECT * FROM users", "user_id", "firstname");
        new POSTAsyncTask(this).execute("insert into drinks(drinkID,drinkType,drinkName,userFrom,userTo) values(5,'beer','beer','5','6')","insert");
        //new POSTAsyncTask(this).execute("DROP TABLE IF EXISTS drinks","");
        //new POSTAsyncTask(this).execute("CREATE TABLE drinks (drinkType VARCHAR(255), drinkName VARCHAR(255), userFrom VARCHAR(255), userTo VARCHAR(255), drinkID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(drinkID))","");
        //new POSTAsyncTask(this).execute("insert into transactions (userFrom, userTo, note) values(1234, 123, 'tester')", "insert");
        //new POSTAsyncTask(this).execute("CREATE TABLE transactions (userFrom LONG, userTo LONG, note VARCHAR(255), transactionID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(transactionID))"," ");
        //new POSTAsyncTask().execute("CREATE TABLE transactions (userFrom LONG, userTo LONG, note VARCHAR(255),\n" +
        //S        "    transactionID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(transactionID))");
        //return view;
        return rootView;

    }

    /* void populateFriends(View view, ArrayList<String> friends) {
        ArrayAdapter adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, friends);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void handleFriendResponse(View view, GraphResponse response) {
        try {
            JSONObject jsonObject = response.getJSONObject();

            Log.d(LoginActivity.class.getSimpleName(), "" + jsonObject.get("data"));
            ArrayList<String> friends = new ArrayList<String>();
            JSONArray data = (JSONArray)jsonObject.get("data");
            int length = data.length();
            for(int i = 0; i < length; i++) {
                JSONObject friend = data.getJSONObject(i);
                String name = friend.getString("name");
                friends.add(name);
            }
            populateFriends(view, friends);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }*/


    private void populateFriends(View view, final ArrayList<String> friends, ArrayList<String> friends_pic) {
        DataAdapter adapter = new DataAdapter(view.getContext(), friends, friends_pic);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(v.getContext(), friends.get(position), Toast.LENGTH_SHORT).show();
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
                Log.d("friendFragment","added friend");
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


    public void onGETTaskCompleted(String result) {
        String[] results = result.split("&&");
        for(int i = 0; i < results.length; i++) {
            Log.d("splitResult", results[i]);
        }
        Log.d("onTaskComplete", result);
    }
    public void onPOSTTaskCompleted(String result) {
        Log.d("onTaskComplete", "post: " + result);
    }
}
