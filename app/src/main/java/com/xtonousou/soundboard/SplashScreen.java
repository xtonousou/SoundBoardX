package com.xtonousou.soundboard;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Thread timerThread = new Thread() {
            public void run() {
                try {
                    // give animation some time to finish
                    sleep(4500);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        // start thread
        timerThread.start();

        TitanicTextView tv = (TitanicTextView) findViewById(R.id.titanic_tv);

        // satisfied?
        Typeface font = Typeface.createFromAsset(tv.getContext().getAssets(), "fonts/Satisfy-Regular.ttf");
        tv.setTypeface(font);

        // start animation
        new Titanic().start(tv);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // finish activity
        finish();
        // start transition with fab's animation
        // overridePendingTransition(R.anim., R.anim.);
        // TODO add transition animation
    }
}