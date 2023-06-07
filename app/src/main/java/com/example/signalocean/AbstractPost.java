package com.example.signalocean;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import org.osmdroid.util.GeoPoint;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public abstract class AbstractPost implements Post, Parcelable {
    private String title;
    private String text;
    private String type;
    private Optional<Uri> image;
    private GeoPoint location;
    private LocalDateTime creationTime;

    public AbstractPost(String type, String title, String text, Optional<Uri> image, GeoPoint location) {
        this.type = type;
        this.title = title;
        this.text = text;
        this.image = image;
        this.creationTime = LocalDateTime.now();
        this.location = location;
    }

    public String getPostDetails() {
        return getTitle() + '&' + getText();
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public Optional<Uri> getImage() {
        return image;
    }

    public void setImage(Optional<Uri> image) {
        this.image = image;
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
        dest.writeString(type);
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
        type = in.readString();
        title = in.readString();
        text = in.readString();
        image = Optional.ofNullable((Uri) in.readValue(Uri.class.getClassLoader()));
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
