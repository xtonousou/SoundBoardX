package io.github.xtonousou.soundboardx;

import android.app.Activity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;

import com.plattysoft.leonids.ParticleSystem;

class Particle {

    private final View view;

    Particle(View view) {
        this.view = view;
    }

    /**
     *  All methods below use Leonids library and make particles with drawables (R.drawable.something)
     *  Methods used in animate() method located at SoundAdapter.java. See onClick.
     */
    void setAnimationHitmarkers() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.hitmarker, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    void setAnimationSTFU() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.stfu, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    void setAnimationDubz() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.dubz, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    void setAnimationSmoke() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 25, R.drawable.smokeone, 5000)
                .setAcceleration(0.001f, 80)
                .setScaleRange(0.5f, 1.3f)
                .setSpeedRange(0.05f, 0.8f)
                .setFadeOut(250, new AnticipateOvershootInterpolator())
                .oneShot(view, 25);
    }

    void setAnimationParticles() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 25, R.drawable.particle, 5000)
                .setAcceleration(0.001f, 80)
                .setScaleRange(-1,2)
                .setSpeedRange(0.05f, 0.8f)
                .setFadeOut(250, new AnticipateOvershootInterpolator())
                .oneShot(view, 25);
    }

    void setAnimationBananas() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 25, R.drawable.banana, 5000)
                .setAcceleration(0.001f, 80)
                .setScaleRange(-1,2)
                .setSpeedRange(0.05f, 0.8f)
                .setFadeOut(250, new AnticipateOvershootInterpolator())
                .oneShot(view, 25);
    }

    void setAnimationSeagulls() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 15, R.drawable.seagull, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .oneShot(view, 15);
    }

    void setAnimationTrump() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 15, R.drawable.trump, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .oneShot(view, 15);
    }

    void setAnimationMan() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 15, R.drawable.man, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .oneShot(view, 15);
    }

    void setAnimationBalls() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.ball, 5000)
                .setSpeedRange(0.2f, 0.4f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    void setAnimation4chan() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.fourchan, 5000)
                .setSpeedRange(0.2f, 0.4f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    void setAnimationBane() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.banesandstorm, 5000)
                .setSpeedRange(0.2f, 0.4f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    void setAnimationWeed() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 5, R.drawable.smokeone, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 5);
        new ParticleSystem(act, 10, R.drawable.weed, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    void setAnimationTrap() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 5, R.drawable.girl, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 5);
        new ParticleSystem(act, 10, R.drawable.banana, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    void setAnimationVader() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.vader, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setRotationSpeed(150)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    void setAnimationKazoo() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.kazoo, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setRotationSpeed(150)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    void setAnimationCaptcha() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.captcha, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setRotationSpeed(150)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    void setAnimationFluteWithMusicNote() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 5, R.drawable.musicnote, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 5);
        new ParticleSystem(act, 10, R.drawable.flute, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    void setAnimationMLG() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.dwi, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
        new ParticleSystem(act, 35, R.drawable.illuminati, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(300)
                .oneShot(view, 10);
        new ParticleSystem(act, 35, R.drawable.joint, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(100)
                .oneShot(view, 20);
        new ParticleSystem(act, 35, R.drawable.mtdew, 5000)
                .setSpeedRange(0.1f, 0.2f)
                .setFadeOut(500)
                .setRotationSpeed(50)
                .oneShot(view, 5);
    }

    void setAnimationPokemon() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.bulbasaur, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
        new ParticleSystem(act, 35, R.drawable.charmander, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(300)
                .oneShot(view, 10);
        new ParticleSystem(act, 35, R.drawable.pikachu, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(100)
                .oneShot(view, 20);
        new ParticleSystem(act, 35, R.drawable.squirtle, 5000)
                .setSpeedRange(0.1f, 0.2f)
                .setFadeOut(500)
                .setRotationSpeed(50)
                .oneShot(view, 5);
    }

    void setAnimationNigga() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.watermelon, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
        new ParticleSystem(act, 30, R.drawable.kfc, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 30);
        new ParticleSystem(act, 20, R.drawable.bike, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 20);
    }

    void setAnimationISIS() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.isis, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
        new ParticleSystem(act, 10, R.drawable.knife, 5000)
                .setAcceleration(0.001f, 45)
                .setSpeedRange(0.1f, 2)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
        new ParticleSystem(act, 30, R.drawable.bomb, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 30);
        new ParticleSystem(act, 20, R.drawable.cfour, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 20);
    }

    void setAnimationSkyrimPoop() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 30, R.drawable.skyrim, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setScaleRange(1, 2)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 30);
        new ParticleSystem(act, 10, R.drawable.poop, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    void setAnimationSkyrim() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 30, R.drawable.skyrim, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setScaleRange(1, 2)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 30);
    }

    void setAnimationRussian() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.vodka, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
        new ParticleSystem(act, 30, R.drawable.csgo, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 30);
        new ParticleSystem(act, 20, R.drawable.adidas, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 20);
    }

    void setAnimationGNU() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.gnu, 7000)
                .setSpeedRange(0.05f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(150)
                .oneShot(view, 35);
    }

    void setAnimationGabe() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.gabe, 7000)
                .setSpeedRange(0.05f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(150)
                .oneShot(view, 35);
    }

    void setAnimationCats() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 20, R.drawable.cat, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 20);
    }

    void setAnimationSpoon() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 20, R.drawable.spoon, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 20);
    }

    void setAnimationGollum() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.gollum, 2500)
                .setAcceleration(0.001f, 90)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 35);
    }

    void setAnimationIlluminati() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.illuminati, 5000)
                .setSpeedRange(0.2f, 0.4f)
                .setFadeOut(500)
                .setRotationSpeed(30)
                .oneShot(view, 35);
    }

    void setAnimationCows() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.cowsay, 5000)
                .setSpeedRange(0.05f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(150)
                .oneShot(view, 35);
    }

    void setAnimationWaterDrops() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.blurp, 5000)
                .setAcceleration(0.001f, 90)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(200, new AccelerateInterpolator())
                .oneShot(view, 10);
        new ParticleSystem(act, 15, R.drawable.blurptwo, 5000)
                .setAcceleration(0.001f, 50)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(200, new AccelerateInterpolator())
                .oneShot(view, 15);
        new ParticleSystem(act, 10, R.drawable.blurpthree, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    void setAnimationBart() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.bart, 5000)
                .setSpeedRange(0.1f, 0.25f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    void setAnimationRetard() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.retard, 5000)
                .setSpeedRange(0.1f, 0.25f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }
}

