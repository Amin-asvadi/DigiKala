package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class tagValue {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("tag")
    private Tag tag;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Tag getTag() {
        return tag;
    }
}
