package com.example.areenzyoud.bakingapp.models;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;


public class Recipe implements Parcelable {

    private int Id;
    private String Name;
    private int Servings;
    private int Image;

    public Recipe() {
    }

    public Recipe(int id, String name, int servings, int image) {
        Id = id;
        Name = name;
        Servings = servings;
        Image = image;
    }

    protected Recipe(Parcel in) {
        Id = in.readInt();
        Name = in.readString();
        Servings = in.readInt();
        Image = in.readInt();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getServings() {
        return Servings;
    }

    public void setServings(int servings) {
        Servings = servings;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(Name);
        parcel.writeInt(Servings);
        parcel.writeInt(Image);
    }
}
