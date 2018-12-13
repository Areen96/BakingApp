package com.example.areenzyoud.bakingapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.areenzyoud.bakingapp.R;
import com.example.areenzyoud.bakingapp.models.Steps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepDetailViewFragment extends Fragment {

    private Steps step;
    private SimpleExoPlayerView mPlayer;
    public SimpleExoPlayer exoPlayer;
    private long position;
    private String video_url;
    private View rootView;
    private boolean isTablet = false;

    public void setStep(Steps step) {
        this.step = step;
    }


    public StepDetailViewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_step_detail_view, container, false);
        if(step == null) return null;

        if(savedInstanceState != null){
            step = savedInstanceState.getParcelable("step");
            position = savedInstanceState.getLong("video-position");
        }else
            position = 0;
        TextView description = rootView.findViewById(R.id.step_description);
        mPlayer = rootView.findViewById(R.id.exo_player);

        String descriptionTxt = step.getDescription();
        video_url = step.getVidepURL();
        if(video_url == null)
            video_url = step.getThumbnailURL();

        description.setText(descriptionTxt);

        initializePlayer(video_url, position, rootView);
        return rootView;

    }

    private void initializePlayer(String video_url, long position, View rootView) {
        if (video_url.equals("")) {
            mPlayer.setVisibility(View.GONE);
            return;
        }

        if (exoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();


            exoPlayer = ExoPlayerFactory.newSimpleInstance(rootView.getContext(), trackSelector, loadControl);

            exoPlayer.seekTo(position);
            mPlayer.setPlayer(exoPlayer);

            String userAgent = Util.getUserAgent(rootView.getContext(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(
                    Uri.parse(video_url),
                    new DefaultDataSourceFactory(rootView.getContext(), userAgent),
                    new DefaultExtractorsFactory(), null, null);


            exoPlayer.prepare(mediaSource);

            exoPlayer.setPlayWhenReady(false);

        }
    }

    private void releasePlayer() {

        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if(exoPlayer!=null){
            exoPlayer.setPlayWhenReady(false);
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(exoPlayer!=null){
            releasePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(exoPlayer!=null){
            exoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(exoPlayer!=null)
            exoPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(exoPlayer != null)
        outState.putLong("video-position", position);
        outState.putParcelable("step", step);

        System.out.println(position + "-------" + " step");
    }
}
