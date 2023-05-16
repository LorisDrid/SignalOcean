package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.util.Optional;

public interface Post {
    String getTitle();

    String getCategory();

    String getText();

    Optional<Drawable> getImage();
}

