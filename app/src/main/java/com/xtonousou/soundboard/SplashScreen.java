package com.xtonousou.soundboard;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

public class SplashScreen extends AppCompatActivity {

    public final static String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CircularProgressView progressView = (CircularProgressView) findViewById(R.id.splash_progress_view);
        ImageView logo = (ImageView) findViewById(R.id.splash_logo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int[][] textState = new int[][] {
                    new int[] {-android.R.attr.state_enabled}, // disabled
                    new int[] { android.R.attr.state_enabled}, // enabled
                    new int[] {-android.R.attr.state_checked}, // unchecked
                    new int[] { android.R.attr.state_pressed}  // pressed
            };

            int[] textStateColor = new int[] {
                    new DayColor(logo.getContext()).getDayColor(),// disabled
                    new DayColor(logo.getContext()).getDayColor(),// enabled
                    new DayColor(logo.getContext()).getDayColor(),// unchecked
                    new DayColor(logo.getContext()).getDayColor() // pressed
            };
            logo.setImageTintList(new ColorStateList(textState, textStateColor));
        } else {
            logo.setEnabled(false);
        }

        progressView.setColor((new DayColor(progressView.getContext())).getDayColor());

        final Thread timerThread = new Thread() {
            public void run() {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                            (!((PowerManager) SplashScreen.this.getSystemService(Context.POWER_SERVICE))
                                    .isPowerSaveMode())) {
                        sleep(1600);
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