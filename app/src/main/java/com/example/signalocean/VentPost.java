package com.example.signalocean;

import android.graphics.drawable.Drawable;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class VentPost extends AbstractPost {
    public VentPost(String title, String text, Optional<Drawable> image, GeoPoint location) {
        super(title, text, image,location);
    }

    @Override
    public String getPostDetails() {
        return null;
    }
}