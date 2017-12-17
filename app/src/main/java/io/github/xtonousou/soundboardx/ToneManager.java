package io.github.xtonousou.soundboardx;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class ToneManager {
    private static final String TAG = "ToneManager";

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
