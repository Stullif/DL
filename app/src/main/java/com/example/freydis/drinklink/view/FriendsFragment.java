package com.example.freydis.drinklink.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.freydis.drinklink.R;
import com.example.freydis.drinklink.control.GETAsyncTask;
import com.example.freydis.drinklink.control.POSTAsyncTask;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Freydis on 2/10/2016.
 */
public class FriendsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_friends, container, false);

        /* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        handleFriendResponse(view, response);
                    }
                }
        ).executeAsync();

        // adapter which populate the friends in listview
        new GETAsyncTask().execute("SELECT * FROM users");
        //new POSTAsyncTask().execute("insert into users (user_id, firstname, lastname) values(10205511203474921, 'balli', 'halli')", "insert");
        //new POSTAsyncTask().execute("CREATE TABLE transactions (userFrom INT, userTo INT, note VARCHAR(255),\n" +
        //S        "    transactionID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(transactionID))");
        return view;

    }

    private void populateFriends(View view, ArrayList<String> friends) {
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
    }
}
