package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class NuagePost extends AbstractPost {
    public NuagePost(String title, String text, Optional<Drawable> image) {
        super(title, text, image);
    }
}