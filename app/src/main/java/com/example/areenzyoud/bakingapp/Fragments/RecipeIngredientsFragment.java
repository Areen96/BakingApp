package com.example.areenzyoud.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.areenzyoud.bakingapp.R;
import com.example.areenzyoud.bakingapp.models.Ingredients;

import java.util.List;

public class RecipeIngredientsFragment extends Fragment {

    List<Ingredients> ingredients;


    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }


    public RecipeIngredientsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View roorView = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);
        LinearLayout linearLayout = roorView.findViewById(R.id.ingredients_layout);
        if(ingredients==null)
            return null;
        for (int i = 0; i < ingredients.size(); i++) {
            TextView textView = new TextView(roorView.getContext());
            textView.setTextSize(16);
            String ingredient = (i+1) + ")  " + ingredients.get(i).getQuantity() + " "
                                         + ingredients.get(i).getMeasure() + " "
                                         + ingredients.get(i).getIngredient();
            textView.setText(ingredient);
            linearLayout.addView(textView);
        }
        return roorView;
    }
}
