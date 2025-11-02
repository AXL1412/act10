package com.poutividad.act10.models;

// FINAL-FIX: The constructor for Photo MUST accept a String for the imageUrl.
public class Photo {
    private String imageUrl;
    private String title;
    private String description;

    public Photo(String imageUrl, String title, String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
