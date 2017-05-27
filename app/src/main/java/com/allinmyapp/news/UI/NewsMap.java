package com.allinmyapp.news.UI;

import android.support.v4.app.Fragment;

/**
 * Created by Sachin on 26/05/17.
 */

public class NewsMap {

    public String title;
    public Fragment fragment;

    public NewsMap(String title, Fragment fragment){
        this.title = title;
        this.fragment = fragment;
    }
}
