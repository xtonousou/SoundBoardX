package io.github.xtonousou.soundboardx;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

class ToneManager {
    private View itemView;
    private Context context;
    private ArrayList<Sound> sounds;
    private int position;
    private Sound sound;
    private String filename;
    private int id;

    ToneManager(View itemView, int position) {
        this.itemView = itemView;
        this.position = position;
        this.context = itemView.getContext();
        this.sounds = SoundStore.getSelectedSounds(context);
        this.sound = sounds != null ? sounds.get(position) : null;
		this.filename = itemView.getResources().getResourceEntryName(sound.getResourceId());
		this.id = sound != null ? sound.getResourceId() : 0;
    }

    void setAsRingtone() {
        String pathName = Utils.writeSoundOnInternalStorage(context, "ringtone", filename, id);
    }

    void setAsNotification() {
        String pathName = Utils.writeSoundOnInternalStorage(context, "notification", filename, id);
    }

    void setAsAlarm() {
        String pathName = Utils.writeSoundOnInternalStorage(context, "alarm", filename, id);
    }
}
