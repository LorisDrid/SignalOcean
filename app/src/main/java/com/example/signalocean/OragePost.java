package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class OragePost extends AbstractPost {
    public OragePost(String title, String text, Optional<Drawable> image) {
        super(title, text, image);
    }
}