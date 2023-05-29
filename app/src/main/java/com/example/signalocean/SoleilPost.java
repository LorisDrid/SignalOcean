package com.example.signalocean;

import android.graphics.drawable.Drawable;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class SoleilPost extends AbstractPost {
    public SoleilPost(String type, String title, String text, Optional<Drawable> image, GeoPoint location) {
        super(type, title, text, ,location);
    }
}

