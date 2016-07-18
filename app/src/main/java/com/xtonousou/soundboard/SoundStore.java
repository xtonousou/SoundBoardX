package com.xtonousou.soundboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import java.text.Normalizer;
import java.util.ArrayList;

public abstract class  SoundStore {

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

    /**
     *  Takes userInput from SearchView and filters results.
     *  @param context  The Context.
     *  @param userText User input from SearchView.
     *  @return         Filtered ArrayList<Sound>
     */
    public static ArrayList<Sound> getSearchResults(Context context, String userText) {
        Resources res = context.getApplicationContext().getResources();
        TypedArray labels = res.obtainTypedArray(R.array.labels);
        TypedArray ids = res.obtainTypedArray(R.array.ids);

        final ArrayList<Sound> newSounds = new ArrayList<>();
        ArrayList<Sound> list = (SoundStore.getAllSounds(context));

        for (int i = 0; i < labels.length(); i++) {
            String text = list.get(i).getName();
            text = Normalizer.normalize(text, Normalizer.Form.NFD);
            text = text.toLowerCase();
            text = text.replaceAll("\\p{M}", "");
            Sound sound = new Sound(labels.getString(i), ids.getResourceId(i, -1));
            if (text.contains(userText)) {
                newSounds.add(sound);
            }
        }

        labels.recycle();
        ids.recycle();

        return newSounds;
    }
}
