package com.example.signalocean;

import android.net.Uri;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class PluiePost extends AbstractPost {
    public PluiePost(String type, String title, String text, Optional<Uri> image, GeoPoint location) {
        super(type, title, text, image,location);
    }
}