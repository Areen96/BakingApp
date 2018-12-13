package com.example.areenzyoud.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.areenzyoud.bakingapp.BakingAppWidget.BakingAppWidgetService;
import com.example.areenzyoud.bakingapp.IngredientsStepsActivity;
import com.example.areenzyoud.bakingapp.R;
import com.example.areenzyoud.bakingapp.models.Ingredients;
import com.example.areenzyoud.bakingapp.models.Recipe;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeAdapterViewHolder> {

    private Context mContext;
    private List<Recipe> recipeList;

    public RecipesAdapter(Context mContext, List<Recipe> recipeList) {
        this.mContext = mContext;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_card_view, viewGroup, false);
        RecipeAdapterViewHolder viewHolder = new RecipeAdapterViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterViewHolder holder, int i) {

        holder.mRecipeName.setText(recipeList.get(i).getName());
        System.out.println(recipeList.get(i).getName() + "  --------");
        holder.mRecipeServing.setText(String.valueOf(recipeList.get(i).getServings()));
        if (recipeList.get(i).getImage() == 0) {
            switch (recipeList.get(i).getName()) {

                case "Nutella Pie":
                    holder.mCardImage.setImageResource(R.drawable.nutellapie);
                    break;

                case "Brownies":
                    holder.mCardImage.setImageResource(R.drawable.brownies);
                    break;

                case "Yellow Cake":
                    holder.mCardImage.setImageResource(R.drawable.yellowcake);
                    break;

                case "Cheesecake":
                    holder.mCardImage.setImageResource(R.drawable.cheesecake);
                    break;
            }
        } else {
            holder.mCardImage.setImageResource(recipeList.get(i).getImage());

        }
    }

    @Override
    public int getItemCount() {
        if(recipeList == null)
        return 0;
        return recipeList.size();
    }

    public void setRecipeData(List<Recipe> recipes) {
        recipeList = recipes;
    }


    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder  {

        public final ImageView mCardImage, mServingLogo;
        public final TextView mRecipeName, mRecipeServing;

        public RecipeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardImage = itemView.findViewById(R.id.card_image);
            mServingLogo = itemView.findViewById(R.id.serving_logo);
            mRecipeName = itemView.findViewById(R.id.recipe_name);
            mRecipeServing = itemView.findViewById(R.id.recipe_servings);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(mContext, IngredientsStepsActivity.class);
                        intent.putExtra("RECIPE_ID", recipeList.get(position).getId());
                        intent.putExtra("recipe", (Parcelable) recipeList.get(position));
                        mContext.startActivity(intent);
                    }
                }
            });


        }
    }
}



