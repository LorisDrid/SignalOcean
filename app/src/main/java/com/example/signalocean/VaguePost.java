package com.example.signalocean;

import android.graphics.drawable.Drawable;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class VaguePost extends AbstractPost {
    public VaguePost(String title, String text, Optional<Drawable> image, GeoPoint location) {
        super(title, text, image,location);
    }

    @Override
    public String getPostDetails() {
        return null;
    }
}