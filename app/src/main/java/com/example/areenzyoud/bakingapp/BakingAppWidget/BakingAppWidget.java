package com.example.areenzyoud.bakingapp.BakingAppWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.areenzyoud.bakingapp.R;
import com.example.areenzyoud.bakingapp.models.Ingredients;
import com.example.areenzyoud.bakingapp.models.Recipe;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, ArrayList<Ingredients> mIngredients, Recipe recipe,
                                int appWidgetId) {
        int i=1;
        StringBuilder ingredient =new StringBuilder();
        ingredient.append(recipe.getName() + "\n\n\n");
        for (Ingredients ingredients : mIngredients){
            ingredient.append( (i) + ")  " + ingredients.getQuantity() +" "
                                         + ingredients.getMeasure() + " "
                                         + ingredients.getIngredient()+  "\n");
            i++;
        }

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
        views.setTextViewText(R.id._ingredient_appwidget_text, ingredient);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }

    public static void updateAppWidgetIngredients(Context context, AppWidgetManager appWidgetManager, ArrayList<Ingredients> mIngredients, Recipe recipe, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager,mIngredients, recipe, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

