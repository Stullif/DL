package com.example.freydis.drinklink.control;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.util.Log;

/**
 * Created by Freydis on 3/16/2016.
 */
public class GETAsyncTask extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {

        //String user_id = params[0];
        //String firstname = params[1];
        //String lastname = params[2];

        Log.d("get","In doInBackground");

        String strurl = "http://spheric-alcove-124715.appspot.com/DatabaseAccess";
        HttpURLConnection urlConnection = null;
        URL url;

        try {
            //url = new URL(strurl+"?user_id="+ URLEncoder.encode("5", "UTF-8") + "&firstname="+URLEncoder.encode("jonni bonni", "UTF-8")+ "&firstname="+URLEncoder.encode("nonni", "UTF-8") + "&lastname="+URLEncoder.encode("jons", "UTF-8"));
            url = new URL(strurl+"?query="+ URLEncoder.encode(params[0], "UTF-8"));
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);




            Log.d("get", "" + urlConnection.getResponseCode());
            Log.d("get", urlConnection.getResponseMessage());
            /*
            InputStream in = urlConnection.getInputStream();
            String encoding = urlConnection.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            */
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            Log.d("get", "Before reading output"+rd.readLine());
            while((line = rd.readLine()) != null) {
                response.append(line);
                Log.d("get", "added line");
                response.append('\r');
            }
            rd.close();


            Log.d("get", ""+urlConnection.getContent());
            Log.d("get", response.toString());
            Log.d("get", "After reading output");

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("get", "Went to catch"+e.getMessage());
            return "catch: "+e.getMessage();
        }finally{
            //urlConnection.disconnect();

            Log.d("get","In finally");
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("get","Post executing " + result);
    }
}
