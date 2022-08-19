package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // @TODO add notifications after you learn to add notifications

    TextView timerTextView;
    MediaPlayer endTimer;
    SeekBar timerSeekBar;
    boolean goButtonStart = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void goTimer(View view) {

        if (goButtonStart) {
                timerTextView.setText("0:30");
                timerSeekBar.setProgress(30);
                timerSeekBar.setEnabled(true);
                countDownTimer.cancel();
                goButton.setText("GO !");
                goButtonStart = false;
        } else {

            goButtonStart = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP !");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    endTimer.start();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft/60;
        int seconds = secondsLeft%60;
        if(seconds < 10)
            timerTextView.setText(String.valueOf(minutes)+":0"+String.valueOf(seconds));
        else
            timerTextView.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        goButton = findViewById(R.id.goButton);
        endTimer = MediaPlayer.create(this,R.raw.timer_end);
        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}