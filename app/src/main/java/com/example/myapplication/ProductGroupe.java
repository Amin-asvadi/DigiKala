package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class ProductGroupe {
    @SerializedName("title")
    private String Name;

    public ProductGroupe(String name) {
        this.Name = name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }
}
