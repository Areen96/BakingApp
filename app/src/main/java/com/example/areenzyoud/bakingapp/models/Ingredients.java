package com.example.areenzyoud.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {

   private int Quantity;
   private String Measure;
   private String Ingredient;

    public Ingredients(int quantity, String measure, String ingredient) {
        Quantity = quantity;
        Measure = measure;
        Ingredient = ingredient;
    }

    public Ingredients() {
    }

    protected Ingredients(Parcel in) {
        Quantity = in.readInt();
        Measure = in.readString();
        Ingredient = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    public String getIngredient() {
        return Ingredient;
    }

    public void setIngredient(String ingredient) {
        Ingredient = ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Quantity);
        parcel.writeString(Measure);
        parcel.writeString(Ingredient);
    }
}
