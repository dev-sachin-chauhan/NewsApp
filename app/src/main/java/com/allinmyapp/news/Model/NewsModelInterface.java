package com.allinmyapp.news.Model;

import android.content.Context;

/**
 * Created by Sachin on 03/05/17.
 */

public interface NewsModelInterface {

    void getNews(Context context, String newsType, NewsModel.Callback callback);
}
