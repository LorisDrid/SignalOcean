package com.example.signalocean;

import android.graphics.drawable.Drawable;

import java.time.LocalDateTime;
import java.util.Optional;

public abstract class AbstractPost implements Post {
    private String title;
    private String text;
    private Optional<Drawable> image;
    private LocalDateTime creationTime;

    public AbstractPost(String title, String text, Optional<Drawable> image) {
        this.title = title;
        this.text = text;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Optional<Drawable> getImage() {
        return image;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}

