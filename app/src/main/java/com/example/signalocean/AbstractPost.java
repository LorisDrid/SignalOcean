package com.example.signalocean;

import android.graphics.drawable.Drawable;

import org.osmdroid.util.GeoPoint;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public abstract class AbstractPost implements Post, Serializable {
    private String title;
    private String text;
    private Optional<Drawable> image;
    private GeoPoint location;
    private LocalDateTime creationTime;

    public AbstractPost(String title, String text, Optional<Drawable> image, GeoPoint location) {
        this.title = title;
        this.text = text;
        this.image = image;
        this.creationTime = LocalDateTime.now();
        this.location= location;
    }

    public abstract String getPostDetails();

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Optional<Drawable> getImage() {
        return image;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = getCreationTime().format(formatter);
        return getTitle() + " - " + formattedDate;
    }

}

