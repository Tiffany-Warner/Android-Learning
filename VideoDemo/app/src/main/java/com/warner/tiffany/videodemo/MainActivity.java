package com.warner.tiffany.videodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        VideoView videoView = (VideoView) findViewById(R.id.videoView2);

        //Access files stored in the file structure of your app
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.demovideo);

        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        videoView.start();
    }
}
