package com.example.areenzyoud.bakingapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.areenzyoud.bakingapp.IngredientsStepsActivity;
import com.example.areenzyoud.bakingapp.R;
import com.example.areenzyoud.bakingapp.StepDetailsActivity;
import com.example.areenzyoud.bakingapp.models.Steps;

import java.util.List;

public class RecipeStepsFragment extends Fragment {

    List<Steps> steps;
    private boolean isTablet;

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }
    public void setTablet(boolean tablet) {
        isTablet = tablet;
    }


    public RecipeStepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        ButterKnife.bind(this,rootView);
        ListView listView = rootView.findViewById(R.id.steps_list);
        if(steps==null)
            return null;

        String [] stepsArray = new String[steps.size()];
        for(int i=0; i<steps.size(); i++)
            stepsArray[i] = steps.get(i).getShortDescription();

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1, stepsArray);
        listView.setAdapter(adapter);

        if(isTablet){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Steps step = steps.get(position);
                    StepDetailViewFragment stepDetailViewFragment = new StepDetailViewFragment();
                    stepDetailViewFragment.setStep(step);
                    Toast.makeText(rootView.getContext(), "Tablet Mode !!", Toast.LENGTH_SHORT).show();
                    IngredientsStepsActivity.fragmentManager.beginTransaction()
                            .replace(R.id.stepDetails, stepDetailViewFragment)
                            .commit();
                }
            });

            System.out.println("Tablet !!!!!");
        }else{
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(rootView.getContext(), StepDetailsActivity.class);
                    intent.putExtra("step", (Parcelable) steps.get(position));
                    startActivity(intent);
                }
            });
        }


        System.out.println("Hello @@@");
        return rootView;
    }

}
