package com.example.fortest.business;

import android.util.Log;

import com.example.fortest.ListItem;
import com.example.fortest.helper.JasonHelper;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ebiz_asc1 on 10/24/13.
 */
public class BuUserdata {
    public static ArrayList<HashMap<String, String>> getusermemberbyfacebookid(String fbid) {
        String url = "http://developer.zoaish.com/webservice/getusermemberbyfacebookid/";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("fbid",fbid));
        final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        try {
            JSONArray data = new JSONArray(JasonHelper.getJSONUrl(url, params));
            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("id", c.getString("id"));
                map.put("facebook_id", c.getString("facebook_id"));
                map.put("name", c.getString("name"));
                map.put("email", c.getString("email"));
                map.put("location", c.getString("location"));
                map.put("about", c.getString("about"));
                MyArrList.add(map);

            }
        } catch (JSONException e)

        {
            e.printStackTrace();
        }
        return MyArrList;
    }

}
