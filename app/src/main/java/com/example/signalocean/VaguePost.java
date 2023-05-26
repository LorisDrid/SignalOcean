package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public class VaguePost extends AbstractPost {
    public VaguePost(String type, String title, String text, Optional<Drawable> image) {
        super(type, title, text, image);
    }
}