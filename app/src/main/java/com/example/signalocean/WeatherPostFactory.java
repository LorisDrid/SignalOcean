package com.example.signalocean;

import android.graphics.drawable.Drawable;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class WeatherPostFactory extends AbstractPostFactory {
    public AbstractPost createPost(String type, String title, String text, Optional<Drawable> image, GeoPoint location) {
        switch (type) {
            case "Soleil":
                return new SoleilPost(title, text, image, location);
            case "Nuage":
                return new NuagePost(title, text, image, location);
            case "Pluie":
                return new PluiePost(title, text, image, location);
            case "Orage":
                return new OragePost(title, text, image, location);
            default:
                throw new IllegalArgumentException("Type de post inconnu : " + type);
        }
    }
}
