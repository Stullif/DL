package com.example.freydis.drinklink.control;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Freydis on 3/16/2016.
 */
public class LoginAsyncTask extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {

        String user_id = params[0];
        String firstname = params[1];
        String lastname = params[2];

        String strurl = "http://spheric-alcove-124715.appspot.com/DatabaseAccess";
        HttpURLConnection urlConnection = null;
        URL url;

        try {
            url = new URL(strurl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            String urlParameters = "user_id="+ URLEncoder.encode(user_id, "UTF-8") + "&firstname="+URLEncoder.encode(firstname, "UTF-8") + "&lastname="+URLEncoder.encode(lastname, "UTF-8");

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
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "catch: "+e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

    }
}
