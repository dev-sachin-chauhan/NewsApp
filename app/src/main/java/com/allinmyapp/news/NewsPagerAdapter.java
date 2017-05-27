package com.allinmyapp.news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.allinmyapp.news.UI.NewsFragment;
import com.allinmyapp.news.UI.NewsMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sachin on 01/05/17.
 */


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {

    ArrayList<NewsMap> newsMap;

    public NewsPagerAdapter(FragmentManager fm, ArrayList<NewsMap> map) {
        super(fm);
        this.newsMap = map;
    }

    @Override
    public Fragment getItem(int position) {
        return newsMap.get(position).fragment;
    }

    @Override
    public int getCount() {
        return newsMap.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return newsMap.get(position).title;

    }
}

