package com.example.areenzyoud.bakingapp.Utils;


import com.example.areenzyoud.bakingapp.models.Ingredients;
import com.example.areenzyoud.bakingapp.models.Recipe;
import com.example.areenzyoud.bakingapp.models.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {



    public static List<Recipe> parseRecipesJson(String json)  {

            List<Recipe> recipes = new ArrayList<>();
            try {
                JSONArray objects = new JSONArray(json);
                for (int i = 0; i < objects.length(); i++) {

                    JSONObject object = (JSONObject) objects.get(i);
                    String name = object.getString("name");
                    int Id = object.getInt("id");
                    int servings = object.getInt("servings");
                    String image = object.getString("image");
                    recipes.add(new Recipe(Id, name, servings, 0));
                }
                return recipes;

            } catch (JSONException e) {
                e.printStackTrace();
            }


        return null;
    }


    public static List<Ingredients> parseIngredientsJson(int id, String json) {

        List<Ingredients> ingredients = new ArrayList<Ingredients>();
        try {
                JSONArray results = new JSONArray(json);
                for (int i = 0; i < results.length(); i++) {
                    JSONObject ingrediant = (JSONObject) results.get(i);
                    if (ingrediant.getInt("id") == id) {
                        JSONArray IngrediantsArray = ingrediant.getJSONArray("ingredients");
                        for (int j = 0; j < IngrediantsArray.length(); j++) {
                            JSONObject ingredientDetails = IngrediantsArray.getJSONObject(j);
                            int quentity = ingredientDetails.getInt("quantity");
                            String mesure = ingredientDetails.getString("measure");
                            String ingredient = ingredientDetails.getString("ingredient");
                            ingredients.add(new Ingredients(quentity, mesure, ingredient));
                        }
                    }
                }
                return ingredients;

            } catch (JSONException e) {
                e.printStackTrace();
            }

        return null;
    }



    public static List<Steps> parseStepsJson(int id, String json)  {

        List<Steps> steps = new ArrayList<>();
        try {
            JSONArray results = new JSONArray(json);
            for (int i = 0; i < results.length(); i++) {
                JSONObject step = (JSONObject) results.get(i);
                if (step.getInt("id") == id) {
                    JSONArray stepsArray = step.getJSONArray("steps");
                    for (int j = 0; j < stepsArray.length(); j++) {
                        JSONObject stepsDetails = stepsArray.getJSONObject(j);
                        int stepId = stepsDetails.getInt("id");
                        String shortDescription = stepsDetails.getString("shortDescription");
                        String description = stepsDetails.getString("description");
                        String videoURL = stepsDetails.getString("videoURL");
                        String thumbnailURL = stepsDetails.getString("thumbnailURL");
                        steps.add(new Steps(stepId, shortDescription, description, videoURL, thumbnailURL));
                    }
                }
            }
            return steps;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

