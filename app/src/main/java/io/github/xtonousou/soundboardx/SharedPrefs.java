package io.github.xtonousou.soundboardx;

import android.content.SharedPreferences;

class SharedPrefs {
    private static SharedPrefs       instance;
    private final  SharedPreferences prefs;

    static void init(SharedPreferences prefs) {
        instance = new SharedPrefs(prefs);
    }

    private SharedPrefs(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    static SharedPrefs getInstance() {
        return instance;
    }

    void setSoundFavorited(String soundName, boolean shouldFavorite) {
        prefs.edit().putBoolean(soundName, shouldFavorite).apply();
    }

    boolean isSoundFavorited(String soundName) {
        return prefs.getBoolean(soundName, false);
    }

    void setSelectedList(String soundLIst) {
        prefs.edit().putString("list", soundLIst).apply();
    }

    String getSelectedList() {
        return prefs.getString("list", "allSounds");
    }

    void setSelectedColor(int color) {
        prefs.edit().putInt("color", color).apply();
    }

    int getSelectedColor() {
        return prefs.getInt("color", -1);
    }

    boolean isFirstTime() {
        return prefs.getBoolean("newbie", true);
    }

    void setFirstTime(boolean firstTime) {
        prefs.edit().putBoolean("newbie", firstTime).apply();
    }
}
