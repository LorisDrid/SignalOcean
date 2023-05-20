package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class WeatherPostFactory extends AbstractPostFactory {
    public AbstractPost createPost(String type, String title, String text, Optional<Drawable> image) {
        switch (type) {
            case "Soleil":
                return new SoleilPost(title, text, image);
            case "Nuage":
                return new NuagePost(title, text, image);
            case "Pluie":
                return new PluiePost(title, text, image);
            case "Orage":
                return new OragePost(title, text, image);
            default:
                throw new IllegalArgumentException("Type de post inconnu : " + type);
        }
    }
}
