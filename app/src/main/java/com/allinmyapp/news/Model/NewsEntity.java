package com.allinmyapp.news.Model;

/**
 * Created by Sachin on 03/05/17.
 */

public class NewsEntity {
    private String content;
    private String imageUrl;
    private String title;

    public NewsEntity(String title, String link, String description) {
        setTitle(title);
        setImageUrl(link);
        setContent(description);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
