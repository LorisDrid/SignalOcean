package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class VaguePost extends AbstractPost {
    public VaguePost(String title, String text, Optional<Drawable> image) {
        super(title, text, image);
    }

    @Override
    public String getPostDetails() {
        return null;
    }
}