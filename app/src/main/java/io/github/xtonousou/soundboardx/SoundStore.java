package io.github.xtonousou.soundboardx;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;

abstract class SoundStore {

    static ArrayList<Sound> getSelectedSounds(Context context) {
        switch (SharedPrefs.getInstance().getSelectedCategory()) {
            case 1:
                return getAllSounds(context);
            case 2:
                return getFunnySounds(context);
            case 3:
                return getGamesSounds(context);
            case 4:
                return getMoviesSounds(context);
            case 5:
                return getMusicSounds(context);
        }
        return null;
    }

    static ArrayList<Sound> getAllSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray allSounds = res.obtainTypedArray(R.array.allSounds);
        TypedArray allSoundsIDs = res.obtainTypedArray(R.array.allSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        for (int i = 0; i < allSounds.length(); i++) {
            sounds.add(new Sound(allSounds.getString(i), allSoundsIDs.getResourceId(i, -1)));
        }

        allSounds.recycle();
        allSoundsIDs.recycle();

        return sounds;
    }

    static ArrayList<Sound> getFunnySounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray funnySounds = res.obtainTypedArray(R.array.funnySounds);
        TypedArray funnySoundsIDs = res.obtainTypedArray(R.array.funnySoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        final int funnySounds_length = funnySounds.length();
        for (int i = 0; i < funnySounds_length; i++) {
            sounds.add(new Sound(funnySounds.getString(i), funnySoundsIDs.getResourceId(i, -1)));
        }

        funnySounds.recycle();
        funnySoundsIDs.recycle();

        return sounds;
    }

    static ArrayList<Sound> getGamesSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray gamesSounds = res.obtainTypedArray(R.array.gamesSounds);
        TypedArray gamesSoundsIDs = res.obtainTypedArray(R.array.gamesSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        final int gamesSounds_length = gamesSounds.length();
        for (int i = 0; i < gamesSounds_length; i++) {
            sounds.add(new Sound(gamesSounds.getString(i), gamesSoundsIDs.getResourceId(i, -1)));
        }

        gamesSounds.recycle();
        gamesSoundsIDs.recycle();

        return sounds;
    }

    static ArrayList<Sound> getMoviesSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray moviesSounds = res.obtainTypedArray(R.array.moviesSounds);
        TypedArray moviesSoundsIDs = res.obtainTypedArray(R.array.moviesSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        final int moviesSounds_length = moviesSounds.length();
        for (int i = 0; i < moviesSounds_length; i++) {
            sounds.add(new Sound(moviesSounds.getString(i), moviesSoundsIDs.getResourceId(i, -1)));
        }

        moviesSounds.recycle();
        moviesSoundsIDs.recycle();

        return sounds;
    }

    static ArrayList<Sound> getMusicSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray musicSounds = res.obtainTypedArray(R.array.musicSounds);
        TypedArray musicSoundsIDs = res.obtainTypedArray(R.array.musicSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        final int musicSounds_length = musicSounds.length();
        for (int i = 0; i < musicSounds_length; i++) {
            sounds.add(new Sound(musicSounds.getString(i), musicSoundsIDs.getResourceId(i, -1)));
        }

		musicSounds.recycle();
		musicSoundsIDs.recycle();

        return sounds;
    }
}

