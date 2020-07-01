package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductItem {
    @SerializedName("id")
    private int Id;

    @SerializedName("imgUrl")
    private String imgname;

    @SerializedName("nameE")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("tagValues")
    private List<tagValue> tagValues;

    @SerializedName("nameE")
    private String nameE;



    public ProductItem(String imgname, String name, String price, int id) {
        this.imgname = imgname;
        this.name = name;
        this.price = price;
        this.Id = id;
    }
    public String getNameE() {
        return nameE;
    }
    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String  getImgname() {
        return imgname;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public List<tagValue> getTagValues() {
        return tagValues;
    }
}
