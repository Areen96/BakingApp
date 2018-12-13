package com.example.areenzyoud.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {


    private int Id;
    private String ShortDescription;
    private String Description;
    private String VidepURL;
    private String ThumbnailURL;

    public Steps(int id, String shortDescription, String description, String videpURL, String thumbnailURL) {
        Id = id;
        ShortDescription = shortDescription;
        Description = description;
        VidepURL = videpURL;
        ThumbnailURL = thumbnailURL;
    }

    public Steps() {
    }

    protected Steps(Parcel in) {
        Id = in.readInt();
        ShortDescription = in.readString();
        Description = in.readString();
        VidepURL = in.readString();
        ThumbnailURL = in.readString();
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getVidepURL() {
        return VidepURL;
    }

    public void setVidepURL(String videpURL) {
        VidepURL = videpURL;
    }

    public String getThumbnailURL() {
        return ThumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        ThumbnailURL = thumbnailURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(ShortDescription);
        parcel.writeString(Description);
        parcel.writeString(VidepURL);
        parcel.writeString(ThumbnailURL);
    }
}
