package com.example.new_rss.Model;

import java.util.List;

public class RSSObject {

    //instance variables
    public String status;
    public List<Item> items;

    //constructor
    public RSSObject(String status, List<Item> items) {
        this.status = status;
        this.items = items;
    }

    //getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
