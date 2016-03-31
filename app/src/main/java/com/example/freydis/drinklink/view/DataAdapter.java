package com.example.freydis.drinklink.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.freydis.drinklink.R;
import com.example.freydis.drinklink.control.DownloadImage;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Freydis on 3/31/2016.
 */
public class DataAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> friends;
    private ArrayList<String> friends_pic;
    private ImageView profilePicture;
    private TextView friendName;

    public DataAdapter(Context context, ArrayList<String> friends, ArrayList<String> friends_pic) {
        this.context = context;
        this.friends = friends;
        this.friends_pic = friends_pic;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.item_gridview, null);
            //gridView = new GridView(context);
            //gridView = (GridView) convertView.findViewById(R.id.friendsGridview);


            //profilePicture = (ImageView) gridView.findViewById(R.id.friendPic);
            /*profilePicture = new ImageView(context);
            profilePicture.setLayoutParams(new GridView.LayoutParams(50, 50));
            profilePicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
            profilePicture.setPadding(8, 8, 8, 8);*/
            profilePicture = (ImageView) gridView.findViewById(R.id.friendPic);
            friendName = (TextView) gridView.findViewById(R.id.friendName);

            new DownloadImage(profilePicture).execute(friends_pic.get(position));
            friendName.setText(friends.get(position));

        } else {
            gridView = convertView;
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
