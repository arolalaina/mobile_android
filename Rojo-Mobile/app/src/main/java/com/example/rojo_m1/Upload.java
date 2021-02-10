package com.example.rojo_m1;

import com.google.firebase.database.Exclude;
public class Upload {
    private String mName;
    private String mImageUrl;
    private String mKey;
    private String description;
    private int category;
    public Upload() {
        //empty constructor needed
    }
    public Upload(String name, String imageUrl,String description, int value) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
        this.description = description;
        this.category = value;
    }
    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key) {
        mKey = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}