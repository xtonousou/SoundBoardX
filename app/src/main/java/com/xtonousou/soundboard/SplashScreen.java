package com.xtonousou.soundboard;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

public class SplashScreen extends AppCompatActivity {

    public final static String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        CircularProgressView progressView = (CircularProgressView) findViewById(R.id.splash_progress_view);
        progressView.setColor((new DayColor(progressView.getContext())).getDayColor());

        final Thread timerThread = new Thread() {
            public void run() {
                try {
                    if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                            ((PowerManager) SplashScreen.this.getSystemService(Context.POWER_SERVICE))
                                    .isPowerSaveMode())) {
                        sleep(1600);
                    } else {
                        onPause();
                    }
                } catch(InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        // start thread
        timerThread.start();
        // start animation
        progressView.startAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // finish activity
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}