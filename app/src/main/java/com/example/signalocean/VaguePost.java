package com.example.signalocean;

import android.graphics.drawable.Drawable;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class VaguePost extends AbstractPost {
    public VaguePost(String type, String title, String text, Optional<Drawable> image, GeoPoint location) {
        super(type, title, text, image,location);
    }
}