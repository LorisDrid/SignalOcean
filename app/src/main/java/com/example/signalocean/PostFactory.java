package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class PostFactory {
    public static  Post createPost(String type, String title, String category, String text, Optional<Drawable> image) {
        switch (type) {
            case "Soleil":
                return new SoleilPost(title, category, text, image);
            case "Nuage":
                return new NuagePost(title, category, text, image);
            case "Pluie":
                return new PluiePost(title, category, text, image);
            case "Vent":
                return new VentPost(title, category, text, image);
            case "Orage":
                return new OragePost(title, category, text, image);
            case "Température":
                return new TemperaturePost(title, category, text, image);
            default:
                throw new IllegalArgumentException("Type de post inconnu : " + type);
        }
    }
}

