package com.example.ana.busproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Ana on 3/31/2017.
 */

public class FetchJson extends AsyncTask<Void, Void, JSONObject>{

    @Override
    public JSONObject doInBackground(Void... params) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URLConnection urlConn;

        try
        {
            URL url = new URL("http://curewitz.com/get_location_android.php");
            urlConn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null)
            {
                stringBuffer.append(line);
                Log.d("JSON", line);
            }

            JSONObject jsonObject = new JSONObject(String.valueOf(stringBuffer));
            JSONArray jsonArray = jsonObject.getJSONArray("Location");
            Log.d("JSON STRING", jsonArray.toString());

            br.close();
            return jsonObject;

        }
        catch(Exception ex)
        {
            Log.e("App", "Null", ex);
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject response)
    {
        if(response != null)
        {
            try {
                Log.e("App", "Success: " + response.getString("yourJsonElement") );
            } catch (JSONException ex) {
                Log.e("App", "Failure", ex);
            }
        }
    }
}
