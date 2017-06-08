package com.allinmyapp.news.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sachin on 04/05/17.
 */


class URLBuilder {

    Map<String,String> request = new  HashMap<>();

    private String topic = Constants.TOP_HEADLINE;
    private String language;
    private String country;
    private String sortBy;

    URLBuilder setCountry(){
        return this;
    }

    URLBuilder setTopic( String topic){
        request.put(Constants.TOPIC,topic);
        return this;
    }

    String build() {
        request.put(Constants.OUTPUT,Constants.RSS);
        StringBuilder url = new StringBuilder(Constants.BASE_LINK);
        for( String key: request.keySet()){
            url.append("&");
            url.append(key);
            url.append("=");
            url.append(request.get(key));
        }
        return url.toString();
    }
}
