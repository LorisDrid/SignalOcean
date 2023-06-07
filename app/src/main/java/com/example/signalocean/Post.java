package com.example.signalocean;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

public interface Post{
    String getTitle();

    String getText();

    Optional<Uri> getImage();

    LocalDateTime getCreationTime();

    String getPostDetails();

}

