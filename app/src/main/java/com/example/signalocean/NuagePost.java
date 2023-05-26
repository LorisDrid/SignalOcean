package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class NuagePost extends AbstractPost {
    public NuagePost(String type, String title, String text, Optional<Drawable> image) {
        super(type, title, text, image);
    }
}