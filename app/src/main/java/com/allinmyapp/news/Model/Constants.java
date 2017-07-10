package com.allinmyapp.news.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sachin on 04/05/17.
 */

public interface Constants {
    String BASE_LINK = "https://news.google.com/news?cf=all&hl=en";
    String OUTPUT = "output";
    String RSS = "rss";
    String TOPIC = "topic";
    String EDITION = "ned";

    String TOP_HEADLINE = "h";//specifies the top headlines topic
    String WORLD_TOPICS = "w"; //specifies the world topic
    String BUSINESS = "b"; //specifies the business topic
    String NATION = "n"; //specifices the nation topic
    String ELECTIONS = "el";
    String POLOTICS = "p";
    String ENTERTAINMENT = "e";
    String SPORTS = "s";
    String HEALTH = "m";
    String TECHNOLOGY = "tc";

    Map<String,String> localeMap= new HashMap<>();
}
