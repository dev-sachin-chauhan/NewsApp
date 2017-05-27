package com.allinmyapp.news.Model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sachin on 04/05/17.
 */

class URLBuilder {

    interface Topic {
        String TOP_HEADLINE = "h";//specifies the top headlines topic
        String WORLD_TOPICS = "w"; //specifies the world topic
        String BUSINESS = "b"; //specifies the business topic
        String NATION = "n"; //specifices the nation topic
        String ELECTIONS = "el";
        String POLOTICS = "p";
        String ENTERTAINMENT = "e";
        String SPORTS = "s";
        String HEALTH = "m";
    }


    private String topic = Topic.TOP_HEADLINE;
    private String language;
    private String country;
    private String sortBy;

    String build() {
        String url = Constants.BASE_LINK;
        return url;
    }
}
