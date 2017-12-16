package io.github.xtonousou.soundboardx;

import android.app.Activity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.LinearInterpolator;

import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;

class Particle {

    private final View view;

    Particle(View view) {
        this.view = view;
    }

    void animateAccelerateRandomly(ArrayList<Integer> drawables, int particles) {
        Activity act = (Activity) view.getContext();
        for (byte i = 0; i < drawables.size(); i++) {
            new ParticleSystem(act, particles, drawables.get(i), 3750)
                    .setAcceleration(0.0025f, 90)
                    .setSpeedRange(0.1f, 0.25f)
                    .oneShot(view, particles, new AccelerateInterpolator());
        }
    }

    void animateAnticipateRandomly(ArrayList<Integer> drawables, int particles) {
        Activity act = (Activity) view.getContext();
        for (byte i = 0; i < drawables.size(); i++) {
            new ParticleSystem(act, particles, drawables.get(i), 3750)
                    .setAcceleration(0.0025f, 90)
                    .setSpeedRange(0.1f, 0.25f)
                    .oneShot(view, particles, new AnticipateInterpolator());
        }
    }

    void animateLinearRandomly(ArrayList<Integer> drawables, int particles) {
        Activity act = (Activity) view.getContext();
        for (byte i = 0; i < drawables.size(); i++) {
            new ParticleSystem(act, particles, drawables.get(i), 3750)
                    .setAcceleration(0.0025f, 90)
                    .setSpeedRange(0.1f, 0.25f)
                    .oneShot(view, particles, new LinearInterpolator());
        }
    }

    void animateAccelerateCircular(ArrayList<Integer> drawables, int particles) {
        Activity act = (Activity) view.getContext();
        for (byte i = 0; i < drawables.size(); i++) {
            new ParticleSystem(act, particles, drawables.get(i), 5000)
                    .setSpeedRange(0.1f, 0.25f)
                    .setRotationSpeedRange(0, 360)
                    .setInitialRotationRange(0, 360)
                    .oneShot(view, particles, new AccelerateInterpolator());
        }
    }

    void animateAnticipateCircular(ArrayList<Integer> drawables, int particles) {
        Activity act = (Activity) view.getContext();
        for (byte i = 0; i < drawables.size(); i++) {
            new ParticleSystem(act, particles, drawables.get(i), 5000)
                    .setSpeedRange(0.1f, 0.25f)
                    .setRotationSpeedRange(0, 360)
                    .setInitialRotationRange(0, 360)
                    .oneShot(view, particles, new AnticipateInterpolator());
        }
    }

    void animateLinearCircular(ArrayList<Integer> drawables, int particles) {
        Activity act = (Activity) view.getContext();
        for (byte i = 0; i < drawables.size(); i++) {
            new ParticleSystem(act, particles, drawables.get(i), 5000)
                    .setSpeedRange(0.1f, 0.25f)
                    .setRotationSpeedRange(0, 360)
                    .setInitialRotationRange(0, 360)
                    .oneShot(view, particles, new LinearInterpolator());
        }
    }
}
