package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Tag {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
