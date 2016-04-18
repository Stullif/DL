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

import com.example.freydis.drinklink.view.OnTaskCompleted;

/**
 * Created by Freydis on 3/16/2016.
 */
public class POSTAsyncTask extends AsyncTask<String, Void, String> {

    OnTaskCompleted listener;

    public POSTAsyncTask(OnTaskCompleted onTaskCompleted) {
        this.listener = onTaskCompleted;
    }
    @Override
    protected String doInBackground(String... params) {
        String query = params[0];
        String insert = params[1];
        /*String user_id = params[0];
        String firstname = params[1];
        String lastname = params[2];*/

        String strurl = "http://spheric-alcove-124715.appspot.com/DatabaseAccess";
        HttpURLConnection urlConnection = null;
        URL url;

        try {
            url = new URL(strurl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            String urlParameters = "query="+ URLEncoder.encode(query, "UTF-8") + "&insert="+URLEncoder.encode(insert, "UTF-8");

            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            InputStream is = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            //Log.d("post",response.toString());
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "catch: "+e.getMessage();
        }finally{
            //urlConnection.disconnect();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onPOSTTaskCompleted(result);
    }
}
