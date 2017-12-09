package io.github.xtonousou.soundboardx;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class ToneManager {
    private static final String TAG = "ToneManager";
    private static final int PERMISSION_REQUEST_WRITE_SETTINGS = 0;
    private static final int PERMISSION_REQUEST_MODIFY_AUDIO_SETTINGS = 1;

	private ArrayList<Sound> sounds;
    private Activity         activity;
    private Context          context;
    private String       	 itemName;
    private View     		 itemView;
    private int      		 position;

    ToneManager(Activity activity, View itemView, String itemName, int position) {
        this.activity = activity;
		this.itemView = itemView;
		this.itemName = itemName;
		this.position = position;
		this.context  = itemView.getContext();
		this.sounds   = Utils.getSelectedList(itemView.getContext(),
                SharedPrefs.getInstance().getSelectedList());
    }

    private void handlePermissions() {
        Context context = itemView.getContext();
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.WRITE_SETTINGS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.WRITE_SETTINGS},
                        PERMISSION_REQUEST_WRITE_SETTINGS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

	void setToneAs(byte id) {
        handlePermissions();
        switch (id) {
            case 0:
                ringtone();
                break;
            case 1:
                notification();
                break;
            case 2:
                alarm();
                break;
        }
    }

    private void ringtone() {
    	Resources res = itemView.getResources();
    	String fileName = res.getResourceEntryName(sounds.get(position).getResourceId());
		int soundId = res.getIdentifier(fileName, "raw",
				itemView.getContext().getPackageName());
		setAsRingtone(fileName, soundId);
    }

    private void notification() {
		setAsNotification(sounds.get(position).getResourceId());
    }

    private void alarm() {
		setAsAlarm(sounds.get(position).getResourceId());
    }

    private void setAsRingtone(String filename, int resource) {
        byte[] buffer;
        InputStream fIn = itemView.getContext().getResources().openRawResource(resource);
        int size;

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            return;
        }

        String path = Environment.DIRECTORY_RINGTONES;

		filename += ".mp3";

        boolean exists = (new File(path)).exists();
        if (!exists) {
            new File(path).mkdirs();
        }

        FileOutputStream save;
        try {
            save = new FileOutputStream(path + filename);
            save.write(buffer);
            save.flush();
            save.close();
        } catch (IOException e) {
            return;
        }

        itemView.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file:/" + path + filename)));

        File k = new File(path, filename);
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, "Ringtone");
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
        values.put(MediaStore.Audio.Media.ARTIST, "xToNouSou");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        values.put(MediaStore.Audio.Media.IS_ALARM, true);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);

        //Insert it into the database
        Uri newUri = itemView.getContext().getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath()), values);

        try {
            RingtoneManager.setActualDefaultRingtoneUri(itemView.getContext(), RingtoneManager.TYPE_RINGTONE, newUri);
            //showToast("'" + itemName + "' has been set as ringtone!", R.drawable
            //        .ic_ringtone_white_24dp);
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }

    }

    private void setAsNotification(int resource) {
        byte[] buffer;
        InputStream fIn = itemView.getContext().getResources().openRawResource(resource);
        int size;

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            return;
        }

			String path = Environment.DIRECTORY_NOTIFICATIONS;

			String filename = Resources.getSystem().getResourceName(resource);

			boolean exists = (new File(path)).exists();
        if (!exists) {
            new File(path).mkdirs();
        }

        FileOutputStream save;
        try {
            save = new FileOutputStream(path + filename);
            save.write(buffer);
            save.flush();
            save.close();
        } catch (IOException e) {
            return;
        }

        itemView.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file:/" + path + filename)));

        File k = new File(path, filename);
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, "Notification");
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
        values.put(MediaStore.Audio.Media.ARTIST, "xToNouSou");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        values.put(MediaStore.Audio.Media.IS_ALARM, true);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);

        //Insert it into the database
        Uri newUri = itemView.getContext().getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath()), values);

        try {
            RingtoneManager.setActualDefaultRingtoneUri(itemView.getContext(), RingtoneManager.TYPE_NOTIFICATION, newUri);
            //showToast("'" + itemName + "' has been set as notification sound!", R.drawable
			//		.ic_notification_white_24dp);
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }

    private void setAsAlarm(int resource) {
        byte[] buffer;
        InputStream fIn = itemView.getContext().getResources().openRawResource(resource);
        int size;

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            return;
        }

			String path = Environment.DIRECTORY_ALARMS;

			String filename = Resources.getSystem().getResourceName(resource);

			boolean exists = (new File(path)).exists();
        if (!exists) {
            new File(path).mkdirs();
        }

        FileOutputStream save;
        try {
            save = new FileOutputStream(path + filename);
            save.write(buffer);
            save.flush();
            save.close();
        } catch (IOException e) {
            return;
        }

        itemView.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file:/" + path + filename)));

        File k = new File(path, filename);
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, "Alarm");
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
        values.put(MediaStore.Audio.Media.ARTIST, "xToNouSou");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
        values.put(MediaStore.Audio.Media.IS_ALARM, true);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);

        //Insert it into the database
        Uri newUri = itemView.getContext().getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath()), values);

        try {
            RingtoneManager.setActualDefaultRingtoneUri(itemView.getContext(), RingtoneManager.TYPE_ALARM, newUri);
            //showToast("'" + itemName + "' has been set as alarm tone!", R.drawable
			//		.ic_alarm_white_24dp);
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }

    private void showToast(String message, Integer resourceId) {
        SuperActivityToast.create(itemView.getContext(), new Style(), Style.TYPE_BUTTON)
                .setButtonIconResource(resourceId)
                .setText(message)
                .setDuration(Style.DURATION_MEDIUM)
                .setFrame(Style.FRAME_STANDARD)
                .setColor(SharedPrefs.getInstance().getSelectedColor())
                .setAnimations(Style.ANIMATIONS_POP).show();
    }
}
