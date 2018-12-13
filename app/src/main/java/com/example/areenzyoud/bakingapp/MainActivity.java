package com.example.areenzyoud.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.areenzyoud.bakingapp.Adapters.RecipesAdapter;
import com.example.areenzyoud.bakingapp.Utils.JsonUtils;
import com.example.areenzyoud.bakingapp.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    RecipesAdapter recipesAdapter;
    public final static String RECIPES_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    public static List<Recipe> recipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recipes = new ArrayList<Recipe>();
        recipesAdapter = new RecipesAdapter(this, recipes);
        getRecipes();
    }

    public void getRecipes() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, RECIPES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                recipes = JsonUtils.parseRecipesJson(response);
                recipesAdapter.setRecipeData(recipes);
                recyclerView.setAdapter(recipesAdapter);
                System.out.println("yes -----------");
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



}
