package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public abstract class AbstractPostFactory  {
    public abstract AbstractPost createPost(String type, String title, String text, Optional<Drawable> image);

    public static AbstractPostFactory getFactory(String type) {
        if (type.equals("Soleil") || type.equals("Nuage") || type.equals("Pluie") || type.equals("Orage")) {
            return new WeatherPostFactory();
        } else if (type.equals("Vague") || type.equals("Vent")) {
            return new MaritimePostFactory();
        }
        return null;
    }
}

