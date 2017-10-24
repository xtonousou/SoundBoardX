package io.github.xtonousou.soundboardx;

import android.app.Activity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;

import com.plattysoft.leonids.ParticleSystem;

class Particle {

    private final View view;

    public Particle(View view) {
        this.view = view;
    }

    /**
     *  All methods below use Leonids library and make particles with drawables (R.drawable.something)
     *  Methods used in animate() method located at SoundAdapter.java. See onClick.
     */
    public void setAnimationHitmarkers() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.hitmarker, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    public void setAnimationSTFU() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.stfu, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    public void setAnimationDubz() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 10, R.drawable.dubz, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    public void setAnimationSmoke() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 25, R.drawable.smokeone, 5000)
                .setAcceleration(0.001f, 80)
                .setScaleRange(0.5f, 1.3f)
                .setSpeedRange(0.05f, 0.8f)
                .setFadeOut(250, new AnticipateOvershootInterpolator())
                .oneShot(view, 25);
    }

    public void setAnimationParticles() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 25, R.drawable.particle, 5000)
                .setAcceleration(0.001f, 80)
                .setScaleRange(-1,2)
                .setSpeedRange(0.05f, 0.8f)
                .setFadeOut(250, new AnticipateOvershootInterpolator())
                .oneShot(view, 25);
    }

    public void setAnimationBananas() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 25, R.drawable.banana, 5000)
                .setAcceleration(0.001f, 80)
                .setScaleRange(-1,2)
                .setSpeedRange(0.05f, 0.8f)
                .setFadeOut(250, new AnticipateOvershootInterpolator())
                .oneShot(view, 25);
    }

    public void setAnimationSeagulls() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 15, R.drawable.seagull, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .oneShot(view, 15);
    }

    public void setAnimationTrump() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 15, R.drawable.trump, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .oneShot(view, 15);
    }

    public void setAnimationMan() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 15, R.drawable.man, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setFadeOut(500)
                .oneShot(view, 15);
    }

    public void setAnimationBalls() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.ball, 5000)
                .setSpeedRange(0.2f, 0.4f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    public void setAnimation4chan() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.fourchan, 5000)
                .setSpeedRange(0.2f, 0.4f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    public void setAnimationBane() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.bane, 5000)
                .setSpeedRange(0.2f, 0.4f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    public void setAnimationWeed() {
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

    public void setAnimationTrap() {
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

    public void setAnimationDickClock() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 5, R.drawable.banana, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 5);
        new ParticleSystem(act, 10, R.drawable.clock, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 10);
    }

    public void setAnimationVader() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.vader, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setRotationSpeed(150)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    public void setAnimationKazoo() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.kazoo, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setRotationSpeed(150)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    public void setAnimationMilk() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.milk, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setRotationSpeed(125)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    public void setAnimationScat() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.poop, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setRotationSpeed(150)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    public void setAnimationCaptcha() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.captcha, 5000)
                .setSpeedRange(0.1f, 0.3f)
                .setRotationSpeed(150)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    public void setAnimationFluteWithMusicNote() {
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

    public void setAnimationMLG() {
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

    public void setAnimationPokemon() {
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

    public void setAnimationNigga() {
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

    public void setAnimationISIS() {
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

    public void setAnimationSkyrimPoop() {
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

    public void setAnimationSkyrim() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 30, R.drawable.skyrim, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setScaleRange(1, 2)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 30);
    }

    public void setAnimationRussian() {
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

    public void setAnimationGNU() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.gnu, 7000)
                .setSpeedRange(0.05f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(150)
                .oneShot(view, 35);
    }

    public void setAnimationGabe() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.gabe, 7000)
                .setSpeedRange(0.05f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(150)
                .oneShot(view, 35);
    }

    public void setAnimationCats() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 20, R.drawable.cat, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 20);
    }

    public void setAnimationSpoon() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 20, R.drawable.spoon, 5000)
                .setAcceleration(0.001f, 80)
                .setSpeedRange(0.1f, 1)
                .setRotationSpeed(100)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 20);
    }

    public void setAnimationGollum() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.gollum, 2500)
                .setAcceleration(0.001f, 90)
                .setSpeedRange(0.1f, 1)
                .setFadeOut(250, new AccelerateInterpolator())
                .oneShot(view, 35);
    }

    public void setAnimationIlluminati() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.illuminati, 5000)
                .setSpeedRange(0.2f, 0.4f)
                .setFadeOut(500)
                .setRotationSpeed(30)
                .oneShot(view, 35);
    }

    public void setAnimationCows() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.cowsay, 5000)
                .setSpeedRange(0.05f, 0.3f)
                .setFadeOut(500)
                .setRotationSpeed(150)
                .oneShot(view, 35);
    }

    public void setAnimationWaterDrops() {
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

    public void setAnimationBart() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.bart, 5000)
                .setSpeedRange(0.1f, 0.25f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }

    public void setAnimationRetard() {
        Activity act = (Activity) view.getContext();
        new ParticleSystem(act, 35, R.drawable.retard, 5000)
                .setSpeedRange(0.1f, 0.25f)
                .setFadeOut(500)
                .oneShot(view, 35);
    }
}

