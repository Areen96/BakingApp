package com.example.areenzyoud.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.areenzyoud.bakingapp.Fragments.StepDetailViewFragment;
import com.example.areenzyoud.bakingapp.models.Steps;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class StepDetailsActivity extends AppCompatActivity {

    private Intent intent;
    private StepDetailViewFragment stepDetailViewFragment;
    private SimpleExoPlayer exoPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        intent = getIntent();

        Steps step = intent.getParcelableExtra("step");
        Toast.makeText(this, step.getVidepURL(), Toast.LENGTH_SHORT).show();

        stepDetailViewFragment = new StepDetailViewFragment();
        stepDetailViewFragment.setStep(step);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.details, stepDetailViewFragment)
                .commit();

        exoPlayer = stepDetailViewFragment.exoPlayer;
    }


    


}
