package io.github.xtonousou.soundboardx;

import android.app.Activity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;

class Particle {

    private final View mView;
    private Activity mActivity;

    Particle(View view) {
        this.mView = view;
        this.mActivity = (Activity) view.getContext();
    }

    //TODO add seekbar to change scaling (in drawer)
    //TODO add seekbar to change speed (in drawer)
    void burstRandomly(ArrayList<Integer> drawables, int particles) {
        for (byte i = 0; i < drawables.size(); i++) {
            new ParticleSystem(mActivity, particles, drawables.get(i), 2500)
                    .setSpeedRange(0.125f, 0.325f)
                    .setFadeOut(750)
                    .setScaleRange(1, 1.65f)
                    .setRotationSpeedRange(0, 200)
                    .oneShot(mView, particles, new DecelerateInterpolator());
        }
    }

    void burstScaleRandomly(ArrayList<Integer> drawables, int particles) {
        for (byte i = 0; i < drawables.size(); i++) {
            new ParticleSystem(mActivity, particles, drawables.get(i), 2500)
                    .setSpeedRange(0.125f, 0.325f)
                    .setFadeOut(750)
                    .setScaleRange(1, 2.25f)
                    .oneShot(mView, particles, new DecelerateInterpolator());
        }
    }
}
