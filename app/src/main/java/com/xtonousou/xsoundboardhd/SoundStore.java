package com.xtonousou.xsoundboardhd;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;

abstract class SoundStore {

    public static ArrayList<Sound> getAllSounds(Context context) {
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

    public static ArrayList<Sound> getAnimalsSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray animalSounds = res.obtainTypedArray(R.array.animalSounds);
        TypedArray animalSoundsIDs = res.obtainTypedArray(R.array.animalSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        for (int i = 0; i < animalSounds.length(); i++) {
            sounds.add(new Sound(animalSounds.getString(i), animalSoundsIDs.getResourceId(i, -1)));
        }

        animalSounds.recycle();
        animalSoundsIDs.recycle();

        return sounds;
    }

    public static ArrayList<Sound> getFunnySounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray funnySounds = res.obtainTypedArray(R.array.funnySounds);
        TypedArray funnySoundsIDs = res.obtainTypedArray(R.array.funnySoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        for (int i = 0; i < funnySounds.length(); i++) {
            sounds.add(new Sound(funnySounds.getString(i), funnySoundsIDs.getResourceId(i, -1)));
        }

        funnySounds.recycle();
        funnySoundsIDs.recycle();

        return sounds;
    }

    public static ArrayList<Sound> getGamesSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray gamesSounds = res.obtainTypedArray(R.array.gamesSounds);
        TypedArray gamesSoundsIDs = res.obtainTypedArray(R.array.gamesSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        for (int i = 0; i < gamesSounds.length(); i++) {
            sounds.add(new Sound(gamesSounds.getString(i), gamesSoundsIDs.getResourceId(i, -1)));
        }

        gamesSounds.recycle();
        gamesSoundsIDs.recycle();

        return sounds;
    }

    public static ArrayList<Sound> getMoviesSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray moviesSounds = res.obtainTypedArray(R.array.moviesSounds);
        TypedArray moviesSoundsIDs = res.obtainTypedArray(R.array.moviesSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        for (int i = 0; i < moviesSounds.length(); i++) {
            sounds.add(new Sound(moviesSounds.getString(i), moviesSoundsIDs.getResourceId(i, -1)));
        }

        moviesSounds.recycle();
        moviesSoundsIDs.recycle();

        return sounds;
    }

    public static ArrayList<Sound> getNSFWSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray nsfwSounds = res.obtainTypedArray(R.array.nsfwSounds);
        TypedArray nsfwSoundsIDs = res.obtainTypedArray(R.array.nsfwSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        for (int i = 0; i < nsfwSounds.length(); i++) {
            sounds.add(new Sound(nsfwSounds.getString(i), nsfwSoundsIDs.getResourceId(i, -1)));
        }

        nsfwSounds.recycle();
        nsfwSoundsIDs.recycle();

        return sounds;
    }

    public static ArrayList<Sound> getPersonalSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray personalSounds = res.obtainTypedArray(R.array.personalSounds);
        TypedArray personalSoundsIDs = res.obtainTypedArray(R.array.personalSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        for (int i = 0; i < personalSounds.length(); i++) {
            sounds.add(new Sound(personalSounds.getString(i), personalSoundsIDs.getResourceId(i, -1)));
        }

        personalSounds.recycle();
        personalSoundsIDs.recycle();

        return sounds;
    }

    public static ArrayList<Sound> getThugSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray thugSounds = res.obtainTypedArray(R.array.thugSounds);
        TypedArray thugSoundsIDs = res.obtainTypedArray(R.array.thugSoundsIDs);

        ArrayList<Sound> sounds = new ArrayList<>();

        for (int i = 0; i < thugSounds.length(); i++) {
            sounds.add(new Sound(thugSounds.getString(i), thugSoundsIDs.getResourceId(i, -1)));
        }

        thugSounds.recycle();
        thugSoundsIDs.recycle();

        return sounds;
    }
}
