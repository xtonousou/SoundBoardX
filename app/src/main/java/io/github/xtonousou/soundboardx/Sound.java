package io.github.xtonousou.soundboardx;

class Sound {

    private String name;
    private final int resourceId;
    private boolean favorite;

    Sound(String name, int resourceId) {
        this.name = name;
        this.resourceId = resourceId;
        this.favorite = SharedPrefs.getInstance().isSoundFavorited(name);
    }

    int getResourceId() {
        return resourceId;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    boolean getFavorite() {
        return favorite;
    }

    void setFavorite(boolean favorite) {
        this.favorite = favorite;
        SharedPrefs.getInstance().setSoundFavorited(name, favorite);
    }

    @Override
    public String toString() {
        return name;
    }
}
