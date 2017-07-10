package com.allinmyapp.news.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sachin on 29/06/17.
 */

public class Preference {

    private final Context mContext;

    public static final String NEWS_PREFERENCES = "news_prefs" ;
    public static final String LOCALE = "locale";

    private static Preference instance = null;
    private final SharedPreferences mSharedPreference;

    private Preference(Context context) {
       this.mContext = context;
        mSharedPreference = mContext.getApplicationContext().getSharedPreferences(NEWS_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static Preference getInstance(Context context) {
        if (null == instance) {
            synchronized (Preference.class) {
                if (null == instance) {
                    instance = new Preference(context);
                }
            }
        }
        return instance;
    }

    public void setNewsLocale(String locale) {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(LOCALE, locale);
        editor.apply();
        editor.commit();
    }

    public String getNewsLocale() {
       return mSharedPreference.getString(LOCALE, "in");
    }

}
