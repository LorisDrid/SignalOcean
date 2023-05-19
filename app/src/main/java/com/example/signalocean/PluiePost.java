package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class PluiePost extends AbstractPost {
    public PluiePost(String title, String text, Optional<Drawable> image) {
        super(title, text, image);
    }
}