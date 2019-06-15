package com.warner.audiodemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button playBtn;
    private Button pauseBtn;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private SeekBar volumeControl;
    private SeekBar scrubSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.button);
        pauseBtn = findViewById(R.id.button2);
        mediaPlayer = MediaPlayer.create(this, R.raw.marbles);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        volumeControl = findViewById(R.id.volumeSeekBar);
        scrubSeekBar = findViewById(R.id.scrubSeekBar);

        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mediaPlayer.start();
            }
        } );

        pauseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mediaPlayer.pause();
            }
        } );


        setVolumeSeekBarListener(volumeControl);
        setScrubSeekBarListener();
    }

    public void setVolumeSeekBarListener(SeekBar volumeControl) {

        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volumeControl.setMax(maxVol);
        volumeControl.setProgress(currVol);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setScrubSeekBarListener(){
        //Get the length of the audio
        scrubSeekBar.setMax(mediaPlayer.getDuration());

        scrubSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 300);
    }

}
