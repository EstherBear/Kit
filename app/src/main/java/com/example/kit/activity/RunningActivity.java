//CPR_player
package com.example.kit.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.kit.R;

import java.util.concurrent.TimeUnit;

public class RunningActivity extends AppCompatActivity {

    Chronometer chronometer;
    Toolbar myToolbar;

    private MediaPlayer cprPlayer;
    private Handler myHandler = new Handler();
    private Button prCPRButton;
    private Button stopCPRButton;
    private Button foreCPRButton;
    private Button backCPRButton;
    private Button restartCPRButton;
    private SeekBar seekBar;
    private TextView curTime;
    private TextView endTime;
    private double startTime;
    private double finalTime;

    private int forwardTime = 3000;
    private int backwardTime = 3000;
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {

            startTime = cprPlayer.getCurrentPosition();
            curTime.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        prCPRButton = (Button) findViewById(R.id.CPR_pause_resume_button);
        stopCPRButton = (Button) findViewById(R.id.CPR_stop_button);
        foreCPRButton = (Button) findViewById(R.id.CPR_forward_button);
        backCPRButton = (Button) findViewById(R.id.CPR_backward_button);
        restartCPRButton = (Button) findViewById(R.id.CPR_restart_button);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        curTime = (TextView) findViewById(R.id.curTime);
        endTime = (TextView) findViewById(R.id.endTime);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (cprPlayer == null) {
            cprPlayer = MediaPlayer.create(this, R.raw.cpr_guide);
        }

        chronometer.start();
        startTime = 0;
        finalTime = cprPlayer.getDuration();
        seekBar.setMax((int) finalTime);
        endTime.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                finalTime)))
        );

        curTime.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                startTime)))
        );

        cprPlayer.start();
        myHandler.postDelayed(UpdateSongTime, 100);

        prCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cprPlayer.isPlaying()) {
                    cprPlayer.pause();
                    prCPRButton.setText(R.string.resume);
                } else {
                    cprPlayer.start();
                    prCPRButton.setText(R.string.pause);
                    //myHandler.postDelayed(UpdateSongTime,100);
                }

            }
        });

        stopCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseCPRPlayer();
                finish();

            }
        });

        foreCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startTime + forwardTime <= finalTime) {
                    startTime += forwardTime;
                    Toast.makeText(getApplicationContext(), "You have Jumped forward 3 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    startTime = finalTime;
                    Toast.makeText(getApplicationContext(), "You have Jumped to the end", Toast.LENGTH_SHORT).show();
                }
                cprPlayer.seekTo((int) startTime);
            }
        });

        backCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startTime - backwardTime >= 0) {
                    startTime -= backwardTime;
                    Toast.makeText(getApplicationContext(), "You have Jumped backward 3 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    startTime = 0;
                    Toast.makeText(getApplicationContext(), "You have Jumped to the start", Toast.LENGTH_SHORT).show();
                }
                cprPlayer.seekTo((int) startTime);
            }
        });

        restartCPRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = 0;
                Toast.makeText(getApplicationContext(), "You have Restarted", Toast.LENGTH_SHORT).show();
                cprPlayer.seekTo((int) startTime);
                cprPlayer.start();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCPRPlayer();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (cprPlayer.isPlaying()) {
            cprPlayer.pause();
            prCPRButton.setText(R.string.resume);
        }
    }


    private void releaseCPRPlayer() {
        myHandler.removeCallbacks(UpdateSongTime);
        if (cprPlayer != null) {
            cprPlayer.release();
        }
        cprPlayer = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_point:
                Intent i;

                i = new Intent(RunningActivity.this, CameraActivity.class);

                startActivity(i);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
