package io.github.xtonousou.soundboardx;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import java.util.ArrayList;

class ToneManager {

    private Context context;
    private String filename;
    private int id;

    private ContentValues contentValues;
    private ContentResolver contentResolver;

    ToneManager(View itemView, int position) {
        this.context = itemView.getContext();

        ArrayList<Sound> sounds = SoundStore.getSelectedSounds(context);
        Sound sound = sounds != null ? sounds.get(position) : null;
        
		this.filename = itemView.getResources()
                .getResourceEntryName(sound != null ? sound.getResourceId() : 0);
		this.id = sound != null ? sound.getResourceId() : 0;

		this.contentValues = new ContentValues();
        this.contentValues.put(MediaStore.MediaColumns.TITLE, sound != null ? sound.getName() : null);
        this.contentValues.put(MediaStore.MediaColumns.MIME_TYPE, context.getString(R.string.audio));
        this.contentValues.put(MediaStore.Audio.Media.ARTIST, context.getString(R.string.author));
        this.contentValues.put(MediaStore.Audio.Media.IS_MUSIC, false);
        this.contentResolver = context.getContentResolver();
    }

    void setAsRingtone() {
        String pathName = Utils.writeSoundOnInternalStorage(context, "ringtone", filename, id);

        contentValues.put(MediaStore.MediaColumns.DATA, pathName);
        contentValues.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        contentValues.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
        contentValues.put(MediaStore.Audio.Media.IS_ALARM, false);

        Uri url = MediaStore.Audio.Media.getContentUriForPath(pathName);
        Uri addedUri = contentResolver.insert(url, contentValues);

        RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE,
                addedUri);
    }

    void setAsNotification() {
        String pathName = Utils.writeSoundOnInternalStorage(context, "notification", filename, id);

        contentValues.put(MediaStore.MediaColumns.DATA, pathName);
        contentValues.put(MediaStore.Audio.Media.IS_RINGTONE, false);
        contentValues.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        contentValues.put(MediaStore.Audio.Media.IS_ALARM, false);

        Uri url = MediaStore.Audio.Media.getContentUriForPath(pathName);
        Uri addedUri = contentResolver.insert(url, contentValues);

        RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION,
                addedUri);
    }

    void setAsAlarm() {
        String pathName = Utils.writeSoundOnInternalStorage(context, "alarm", filename, id);

        contentValues.put(MediaStore.MediaColumns.DATA, pathName);
        contentValues.put(MediaStore.Audio.Media.IS_RINGTONE, false);
        contentValues.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
        contentValues.put(MediaStore.Audio.Media.IS_ALARM, true);

        Uri url = MediaStore.Audio.Media.getContentUriForPath(pathName);
        Uri addedUri = contentResolver.insert(url, contentValues);

        RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALARM, addedUri);
    }
}
