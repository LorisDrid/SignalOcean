package com.example.signalocean;

import android.net.Uri;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class MaritimePostFactory extends AbstractPostFactory {
    public AbstractPost createPost(String type, String title, String text, Optional<Uri> image, GeoPoint location) {
        switch (type) {
            case "Vent":
                return new VentPost(type, title, text, image, location);
            case "Vague":
                return new VaguePost(type, title, text, image, location);
            default:
                throw new IllegalArgumentException("Type de post inconnu : " + type);
        }
    }
}
