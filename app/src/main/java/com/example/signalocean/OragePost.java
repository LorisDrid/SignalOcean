package com.example.signalocean;

import android.net.Uri;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class OragePost extends AbstractPost {
    public OragePost(String type, String title, String text, Optional<Uri> image, GeoPoint location) {
        super(type, title, text, image,location);
    }
}