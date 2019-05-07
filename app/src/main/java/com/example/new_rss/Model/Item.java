package com.example.new_rss.Model;

public class Item {

    //instance variables
    public String title;
    public String pubDate;
    public String link;
    public String content;

    //constructor
    public Item(String title, String pubDate, String link, String content) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
        this.content = content;
    }

    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
