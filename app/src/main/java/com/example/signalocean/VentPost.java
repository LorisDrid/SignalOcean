package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class VentPost extends AbstractPost {
    public VentPost(String title, String text, Optional<Drawable> image) {
        super(title, text, image);
    }
}