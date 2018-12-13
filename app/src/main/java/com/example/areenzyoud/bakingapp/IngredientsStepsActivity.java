package com.example.areenzyoud.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.areenzyoud.bakingapp.BakingAppWidget.BakingAppWidget;
import com.example.areenzyoud.bakingapp.BakingAppWidget.BakingAppWidgetService;
import com.example.areenzyoud.bakingapp.Fragments.RecipeIngredientsFragment;
import com.example.areenzyoud.bakingapp.Fragments.RecipeStepsFragment;
import com.example.areenzyoud.bakingapp.Fragments.StepDetailViewFragment;
import com.example.areenzyoud.bakingapp.Utils.JsonUtils;
import com.example.areenzyoud.bakingapp.models.Ingredients;
import com.example.areenzyoud.bakingapp.models.Recipe;
import com.example.areenzyoud.bakingapp.models.Steps;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class IngredientsStepsActivity extends AppCompatActivity {

    RecipeIngredientsFragment ingredientsFragment;
    RecipeStepsFragment stepsFragment;
    StepDetailViewFragment stepDetailViewFragment;
    public final static String RECIPES_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private int recipeId;
    private Intent intent;
    private Recipe recipe;
    private boolean isTablet = false;
    public static  FragmentManager fragmentManager;
    private SimpleExoPlayer exoPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_steps);


        if(savedInstanceState != null) {
            intent = savedInstanceState.getParcelable("intent");
            System.out.println("onSavedInstance !!!!!!!!!");
        }
        else
            intent = getIntent();

        recipeId = intent.getIntExtra("RECIPE_ID", -1);
        recipe = intent.getParcelableExtra("recipe");

        if(findViewById(R.id.tablet_layout) != null) {
            isTablet = true;
            getTabletDetails();
        }
        else{
            isTablet = false;
            getPhoneDetails();
        }



    }

    private void getPhoneDetails(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, RECIPES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Ingredients> ingredients = null;
                List<Steps> steps = null;

                ingredients = JsonUtils.parseIngredientsJson(recipeId, response);
                BakingAppWidgetService.startActionUpdateWidgets(IngredientsStepsActivity.this , (ArrayList<Ingredients>) ingredients, recipe);
                steps = JsonUtils.parseStepsJson(recipeId, response);
                ingredientsFragment = new RecipeIngredientsFragment();
                ingredientsFragment.setIngredients(ingredients);

                stepsFragment = new RecipeStepsFragment();
                stepsFragment.setSteps(steps);
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.ingredients, ingredientsFragment)
                        .add(R.id.steps, stepsFragment)
                        .commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void getTabletDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, RECIPES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Ingredients> ingredients = null;
                List<Steps> steps = null;

                ingredients = JsonUtils.parseIngredientsJson(recipeId, response);
                BakingAppWidgetService.startActionUpdateWidgets(IngredientsStepsActivity.this , (ArrayList<Ingredients>) ingredients, recipe);
                steps = JsonUtils.parseStepsJson(recipeId, response);
                ingredientsFragment = new RecipeIngredientsFragment();
                ingredientsFragment.setIngredients(ingredients);

                stepsFragment = new RecipeStepsFragment();
                stepsFragment.setSteps(steps);
                stepsFragment.setTablet(isTablet);

                stepDetailViewFragment = new StepDetailViewFragment();
                stepDetailViewFragment.setStep(steps.get(0));
                exoPlayer = stepDetailViewFragment.exoPlayer;

                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.ingredients, ingredientsFragment)
                        .add(R.id.steps, stepsFragment)
                        .add(R.id.stepDetails, stepDetailViewFragment)
                        .commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("intent", getIntent());
    }



}
