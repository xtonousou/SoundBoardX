package com.xtonousou.soundboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;

abstract class SoundStore {

    /**
     *  Gets all sounds.
     *  @param context  The Context.
     *  @return         ArrayList<Sound> with all sounds included.
     */
    public static ArrayList<Sound> getAllSounds(Context context) {
        Resources res = context.getApplicationContext().getResources();

        TypedArray labels = res.obtainTypedArray(R.array.labels);
        TypedArray ids = res.obtainTypedArray(R.array.ids);

        ArrayList<Sound> sounds = new ArrayList<>();

        for (int i = 0; i < labels.length(); i++) {
            sounds.add(new Sound(labels.getString(i), ids.getResourceId(i, -1)));
        }

        labels.recycle();
        ids.recycle();

        return sounds;
    }
}
