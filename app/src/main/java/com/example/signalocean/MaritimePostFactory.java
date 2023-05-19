package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class MaritimePostFactory extends AbstractPostFactory {
    public Post createPost(String type, String title, String text, Optional<Drawable> image) {
        switch (type) {
            case "Vent":
                return new VentPost(title, text, image);
            case "Vague":
                return new VaguePost(title, text, image);
            default:
                throw new IllegalArgumentException("Type de post inconnu : " + type);
        }
    }
}
