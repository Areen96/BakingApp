package com.example.areenzyoud.bakingapp.BakingAppWidget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.example.areenzyoud.bakingapp.models.Ingredients;
import com.example.areenzyoud.bakingapp.models.Recipe;

import java.util.ArrayList;

public class BakingAppWidgetService extends IntentService {

    public static final String ACTION_UPDATE = "com.example.alex.alex_backingapp.action.update_widget";
    private static ArrayList<Ingredients> mIngredients;
    private static Recipe mRecipe;

    public BakingAppWidgetService( ) {
        super("BakingAppWidgetService");
    }

    public static void startActionUpdateWidgets(Context context, ArrayList<Ingredients> ingredients, Recipe recipe) {

         Intent intent = new Intent(context, BakingAppWidgetService.class);
         intent.setAction(ACTION_UPDATE);
         mIngredients=ingredients;
         mRecipe = recipe;
         context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE.equals(action)) {

                handleActionUpdateWidgets(this );

            }

        }


    }

    private void handleActionUpdateWidgets(Context context) {
         AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidget.class));
        BakingAppWidget.updateAppWidgetIngredients(context, appWidgetManager,mIngredients, mRecipe,appWidgetIds);

    }


}

