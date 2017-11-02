package com.aystub.muhbeers.data;

import com.google.firebase.firestore.IgnoreExtraProperties;

// Beer POJO
@IgnoreExtraProperties
public class Beer {

    public static final String COLLECTION_NAME = "beers";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_RATING = "rating";
    public static final String FIELD_TASTER = "taster";
    public static final String FIELD_CREATED_AT = "created_at";

    private String imageURL;
    private String name;
    private float rating;
    private String taster;
    private long createdAt;

    public Beer() {}

    public Beer(String imageURL, String name, float rating, String taster) {
        this.imageURL = imageURL;
        this.name = name;
        this.rating = rating;
        this.taster = taster;
        this.createdAt = System.currentTimeMillis();
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getTaster() {
        return taster;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
