package com.allinmyapp.news.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * NetworkCall class will hit backend to get the JSONArray for "http://starlord.hackerearth.com/kickstarter"
 * Created by Sachin on 07/05/17.
 */

class NetworkCall {
    private static final String TAG = "NetworkCall";


    interface Callback {
        void onResponse(List<NewsEntity> newsEntities);
    }

    /**
     * NetworkCall constructor should be used to initiate the call to kickstart backend
     *
     * @param context  Application context to initialize volley.
     * @param callback callback should be provided to get the response.
     */
    NetworkCall(@NonNull Context context, @NonNull String url, @NonNull final Callback callback) {
        Request req = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // we got the response, now our job is to handle it
                callback.onResponse(parseXmlResponse(response));
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //something happened, treat the error.
            }
        });

        // Adding request to request queue
        Volley.newRequestQueue(context).add(req);
    }

    /**
     * Ref: http://www.androidauthority.com/simple-rss-reader-full-tutorial-733245/
     *
     * @param response
     * @return
     */
    private List<NewsEntity> parseXmlResponse(String response) {
        List<NewsEntity> list = new ArrayList<>();

        String title = null;
        String link = null;
        String description = null;
        String pubDate = null;
        boolean isItem = false;

        try {
            XmlPullParser xmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(new ByteArrayInputStream(response.getBytes(Charset.defaultCharset())), null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if (name == null)
                    continue;

                if (eventType == XmlPullParser.END_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if (name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                Log.d("MyXmlParser", "Parsing name ==> " + name);
                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")) {
                    link = result;
                } else if (name.equalsIgnoreCase("description")) {
                    description = result;
                } else if (name.equalsIgnoreCase("pubDate")) {
                    pubDate = result;
                }

                if (title != null && link != null && description != null && pubDate != null) {
                    if (isItem) {
                        int index = description.indexOf("img src=");
                        String subString = description.substring(index + 11);
                        int index2 = subString.indexOf("\"");
                        String imgLink = subString.substring(0, index2);
                        Log.i("Img Link", imgLink);
                        NewsEntity item = new NewsEntity(title, "http://" + imgLink, pubDate);
                        list.add(item);
                    }

                    title = null;
                    link = null;
                    pubDate = null;
                    description = null;
                    isItem = false;
                }
            }
        } catch (XmlPullParserException e) {


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        return list;
    }
}
