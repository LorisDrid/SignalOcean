package com.example.signalocean;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class VentPost extends AbstractPost {
    public VentPost(String type, String title, String text, Optional<Uri> image, GeoPoint location) {
        super(type, title, text, image,location);
    }
}