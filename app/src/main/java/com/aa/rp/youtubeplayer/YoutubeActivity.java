package com.aa.rp.youtubeplayer;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyA-zkgJvSbD7sPQSkFLe7ho40sW4Z7OOfE";
    static final String YOUTUBE_VIDEO_ID ="CtgD91Ev4NU";
    static final String YOUTUBE_PLAYLIST = "RDCtgD91Ev4NU";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    setContentView(R.layout.activity_youtube);
         ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.activity_youtube);
  */  // The next two lines will work same as the two lines  commented above

      ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube,null);
        setContentView(layout);

 /*      TRIAL BUTTON
        Button button1 = new Button(this);
        button1.setLayoutParams(new ConstraintLayout.LayoutParams(300,80));
        button1.setText("BUTTON ADDED");
        layout.addView(button1);
   */

 // YOUTUBE PLAYER API

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG,"onInitializationSuccess: provider is "+ provider.getClass().toString());
        Toast.makeText(this,"Initialized UYouTube Player successfully",Toast.LENGTH_LONG).show();

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final  int REQUEST_CODE = 1;
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE);
        } else {
            String errorMessage = String.format("There was an error initilazing the youtube Player (%1$s)",youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }

    }

    private  YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this,"Good video is playing ok",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this,"Video has paused",Toast.LENGTH_LONG).show();
            }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this,"Video has stopped",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this,"Click ad now , make the video rich !!",Toast.LENGTH_LONG).show();


        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this,"Video has started !",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this,"video has ended",Toast.LENGTH_LONG).show();


        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

}
