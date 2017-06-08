package com.allinmyapp.news.Model;

import android.content.Context;
import android.os.Handler;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Sachin on 03/05/17.
 */

public class NewsModel implements NewsModelInterface{
    private final Locale locale;

    public interface Callback {
        void response(List<NewsEntity> newsEntityListsList);
    }

    public static NewsModelInterface getInstance(Locale locale) {
        return new NewsModel(locale);
    }

    private NewsModel(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void getNews(Context context, String newsType, final Callback callback) {
        final Handler handler = new Handler();
        new NetworkCall(context, new URLBuilder().setTopic(newsType).build() , new NetworkCall.Callback() {

            @Override
            public void onResponse(final List<NewsEntity> newsEntities) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.response(newsEntities);
                    }
                });
            }
        });
    }

}
