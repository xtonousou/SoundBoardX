package io.github.xtonousou.soundboardx;

import android.content.SharedPreferences;

public class SharedPrefs {
    private static SharedPrefs       instance;
    private final  SharedPreferences prefs;

    public static void init(SharedPreferences prefs) {
        instance = new SharedPrefs(prefs);
    }

    private SharedPrefs(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public static SharedPrefs getInstance() {
        return instance;
    }

    public void setSoundFavorited(String soundName, boolean shouldFavorite) {
        prefs.edit().putBoolean(soundName, shouldFavorite).apply();
    }

    public boolean isSoundFavorited(String soundName) {
        return prefs.getBoolean(soundName, false);
    }

    public void setSelectedColor(String colorName, int color) {
        prefs.edit().putInt(colorName, color).apply();
    }

    public int getSelectedColor() {
        return prefs.getInt("color", -1);
    }

    public boolean isFirstTime() {
        return prefs.getBoolean("virgin", true);
    }

    public void setNotFirstTime(String answer, boolean shouldNotFirstTime) {
        prefs.edit().putBoolean(answer, shouldNotFirstTime).apply();
    }
}
