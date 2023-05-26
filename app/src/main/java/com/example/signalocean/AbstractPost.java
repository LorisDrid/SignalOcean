package com.example.signalocean;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public abstract class AbstractPost implements Post, Parcelable {
    private String title;
    private String text;
    private Optional<Drawable> image;
    private LocalDateTime creationTime;

    public AbstractPost(String title, String text, Optional<Drawable> image) {
        this.title = title;
        this.text = text;
        this.image = image;
        this.creationTime = LocalDateTime.now();
    }

    public String getPostDetails(){return this.getTitle() + '\n' + getText();}

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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = getCreationTime().format(formatter);
        return getTitle() + " - " + formattedDate;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(text);
        dest.writeValue(image.orElse(null));
        dest.writeSerializable(creationTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected AbstractPost(Parcel in) {
        title = in.readString();
        text = in.readString();
        image = Optional.ofNullable((Drawable) in.readValue(Drawable.class.getClassLoader()));
        creationTime = (LocalDateTime) in.readSerializable();
    }

    public static final Parcelable.Creator<AbstractPost> CREATOR = new Parcelable.Creator<AbstractPost>() {
        @Override
        public AbstractPost createFromParcel(Parcel in) {
            return new AbstractPost(in) {
                @Override
                public String getPostDetails() {
                    return super.getPostDetails();
                }
            };
        }

        @Override
        public AbstractPost[] newArray(int size) {
            return new AbstractPost[size];
        }
    };
}
