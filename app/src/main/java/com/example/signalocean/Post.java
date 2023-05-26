package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

public interface Post{
    String getTitle();

    String getText();

    Optional<Drawable> getImage();

    LocalDateTime getCreationTime();

    String getPostDetails();

}

