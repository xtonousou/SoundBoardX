package io.github.xtonousou.soundboardx;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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

	private ArrayList<Sound> sounds;
	private Particle    	 particle;
	private String       	 itemName;
	private View     		 itemView;
	private int      		 position;

    ToneManager(View itemView, String itemName, int position) {
		this.itemView = itemView;
		this.itemName = itemName;
		this.position = position;
		this.sounds = Utils.getSelectedList(itemView.getContext(), SharedPrefs
				.getInstance().getSelectedList());
    }

	ToneManager(Particle particle, String itemName) {
		this.particle = particle;
		this.itemName = itemName;
	}

    void ringtone() {
    	Resources res = itemView.getResources();
    	String fileName = res.getResourceEntryName(sounds.get(position).getResourceId());
		int soundId = res.getIdentifier(fileName, "raw",
				itemView.getContext().getPackageName());
		setAsRingtone(fileName, soundId);
    }

    void notification() {
		setAsNotification(sounds.get(position).getResourceId());
    }

    void alarm() {
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
            showToast("'" + itemName + "' has been set as ringtone!", R.drawable.ic_ringtone_white_24dp);
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
            showToast("'" + itemName + "' has been set as notification sound!", R.drawable.ic_notification_white_24dp);
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
            showToast("'" + itemName + "' has been set as alarm tone!", R.drawable.ic_alarm_white_24dp);
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

    /**
     *  Animation setter.
     */
    void makeItShine() {
        switch (itemName) {
            default:
                Log.e(TAG, "No animation is set for " + itemName);
                break;
            case "Nigger's satisfaction":
                particle.setAnimationNigga();
                break;
            case "Hitmarker":
                particle.setAnimationHitmarkers();
                break;
            case "It' s 04:20 meng…":
                particle.setAnimationWeed();
                break;
            case "420 blaze it faggot":
                particle.setAnimationWeed();
                break;
            case "Blurp":
                particle.setAnimationWaterDrops();
                break;
            case "Adam!?!":
                particle.setAnimationSmoke();
                break;
            case "Allahu Akbar":
                particle.setAnimationISIS();
                break;
            case "Damn son!":
                particle.setAnimationMLG();
                break;
            case "Bane: Darude Sandstorm":
                particle.setAnimationBane();
                break;
            case "Bearcum!!!":
                particle.setAnimationMLG();
                break;
            case "What is going on here?":
                particle.setAnimationRetard();
                break;
            case "Πετράκης: Fuck her right in the pussy":
                particle.setAnimationBalls();
                break;
            case "Black kid on fire":
                particle.setAnimationNigga();
                break;
            case "Σα τον Κάτμαν μ' έκανες!":
                particle.setAnimationBart();
                break;
            case "Hmm, it's dick o'clock":
                particle.setAnimationDickClock();
                break;
            case "Russian father":
                particle.setAnimationRussian();
                break;
            case "Fichtl's Lied":
                particle.setAnimationFluteWithMusicNote();
                break;
            case "Buffed Blowjob":
                particle.setAnimationBananas();
                break;
            case "Hey that' s pretty good!":
                particle.setAnimationDubz();
                break;
            case "xT: Γαμώ τον Χριστό σου!":
                particle.setAnimationWeed();
                break;
            case "I' m your father!":
                particle.setAnimationVader();
                break;
            case "Give it to me!":
                particle.setAnimationBananas();
                break;
            case "Κοτσολάρη: Πίπα":
                particle.setAnimationGollum();
                break;
            case "Fun":
                particle.setAnimationKazoo();
                break;
            case "Kazoo":
                particle.setAnimationKazoo();
                break;
            case "Immigrants cause cancer":
                particle.setAnimationTrump();
                break;
            case "Maybe we could invite some women?":
                particle.setAnimationSkyrimPoop();
                break;
            case "Trap Theme":
                particle.setAnimationTrap();
                break;
            case "Γαμώ τα καντήλια μου όλα!":
                particle.setAnimationBart();
                break;
            case "Weird laugh":
                particle.setAnimation4chan();
                break;
            case "Thank you…":
                particle.setAnimationTrap();
                break;
            case "Make your dreams come true!":
                particle.setAnimationMan();
                break;
            case "LOTR MLG":
                particle.setAnimationMLG();
                break;
            case "Come here my pet Russian…":
                particle.setAnimationRussian();
                break;
            case "This is my masterpiece!":
                particle.setAnimationCaptcha();
                break;
            case "Do you want my milk?":
                particle.setAnimationMilk();
                break;
            case "My mooscles are getting bigger!":
                particle.setAnimationMan();
                break;
            case "I'm growing stronker!":
                particle.setAnimationMan();
                break;
            case "Yiss":
                particle.setAnimationMan();
                break;
            case "STFU":
                particle.setAnimationSTFU();
                break;
            case "Nigga is cumming…":
                particle.setAnimationNigga();
                break;
            case "Sup nigga, wanna talk some shit?":
                particle.setAnimationNigga();
                break;
            case "Surprise motherfucker!":
                particle.setAnimationNigga();
                break;
            case "Πετρόπουλος: Action!":
                particle.setAnimationGabe();
                break;
            case "Shaved pussy…":
                particle.setAnimationCats();
                break;
            case "Seven vaginias…":
                particle.setAnimationCats();
                break;
            case "Oh my!":
                particle.setAnimationTrap();
                break;
            case "Oh Yeaaaah!":
                particle.setAnimationParticles();
                break;
            case "Penis, penis and penis":
                particle.setAnimationTrap();
                break;
            case "Αγγούρι στον κώλο σου…":
                particle.setAnimationPokemon();
                break;
            case "Excuse me, I have some pussyhair on me!":
                particle.setAnimationCats();
                break;
            case "Retardation Theme":
                particle.setAnimationRetard();
                break;
            case "Cyka Blyat's Middlefinger":
                particle.setAnimationRussian();
                break;
            case "I'm the scatman…":
                particle.setAnimationScat();
                break;
            case "Why don't you show us that fine pussy?":
                particle.setAnimationSkyrimPoop();
                break;
            case "Shutdown, glitched…":
                particle.setAnimationRetard();
                break;
            case "What manner of sissyness, is this?":
                particle.setAnimationSkyrimPoop();
                break;
            case "You spin me round":
                particle.setAnimationTrap();
                break;
            case "Squeaker":
                particle.setAnimationRetard();
                break;
            case "Our courage will pull us through":
                particle.setAnimationPokemon();
                break;
            case "That's a 10!":
                particle.setAnimationSpoon();
                break;
            case "Illuminati":
                particle.setAnimationIlluminati();
                break;
            case "WOW":
                particle.setAnimationMLG();
                break;
            case "Richard Stallman":
                particle.setAnimationGNU();
                break;
            case "Τσικλίδης: Λες και είναι τσόντα!":
                particle.setAnimationCows();
                break;
            case "What else?":
                particle.setAnimationRetard();
                break;
            case "Zodiac":
                particle.setAnimation4chan();
                break;
            case "Yuri Kuma Arashi":
                particle.setAnimationCats();
                break;
            case "xT: Ρε καριόλη;":
                particle.setAnimationWeed();
                break;
            case "Κιάμος: Ε!":
                particle.setAnimationSeagulls();
                break;
            case "Κιάμος: Χατζάρα":
                particle.setAnimationSeagulls();
                break;
            case "Κιάμος: Τί κοιτάς ρε μαλάκα":
                particle.setAnimationSeagulls();
                break;
            case "U WOT M8":
                particle.setAnimationFluteWithMusicNote();
                break;
            case "Cow":
                particle.setAnimationCows();
                break;
            // TODO fix particles
            case "Oh baby a triple":
                particle.setAnimationCows();
                break;
            case "Badum Tss":
                particle.setAnimationCows();
                break;
            case "Beep":
                particle.setAnimationCows();
                break;
            case "Boxeo":
                particle.setAnimationCows();
                break;
            case "Chan":
                particle.setAnimationCows();
                break;
            case "Chan chan":
                particle.setAnimationCows();
                break;
            case "Combo breaker":
                particle.setAnimationCows();
                break;
            case "De dotaded wam":
                particle.setAnimationCows();
                break;
            case "Duck toy":
                particle.setAnimationCows();
                break;
            case "Get noscoped":
                particle.setAnimationCows();
                break;
            case "Mom get the camera":
                particle.setAnimationCows();
                break;
            case "Headshot":
                particle.setAnimationCows();
                break;
            case "Inception":
                particle.setAnimationCows();
                break;
            case "Holy Shit":
                particle.setAnimationCows();
                break;
            case "Just Do It":
                particle.setAnimationCows();
                break;
            case "John Cena":
                particle.setAnimationCows();
                break;
            case "Never do that":
                particle.setAnimationCows();
                break;
            case "Nein Nein":
                particle.setAnimationCows();
                break;
            case "Over 9000":
                particle.setAnimationCows();
                break;
            case "OMG":
                particle.setAnimationCows();
                break;
            case "Shots fired":
                particle.setAnimationCows();
                break;
            case "Penault Jelly":
                particle.setAnimationCows();
                break;
            case "Whatcha say":
                particle.setAnimationCows();
                break;
            case "Smoke weed everyday":
                particle.setAnimationCows();
                break;
            case "Wombo Combo":
                particle.setAnimationCows();
                break;
            case "WTF BOOM":
                particle.setAnimationCows();
                break;
            case "Aku Aku":
                particle.setAnimationCows();
                break;
            case "Balls of steel":
                particle.setAnimationCows();
                break;
            case "EA Games":
                particle.setAnimationCows();
                break;
            case "EA Sports":
                particle.setAnimationCows();
                break;
            case "Excellent":
                particle.setAnimationCows();
                break;
            case "Fus Ro Dah":
                particle.setAnimationSkyrim();
                break;
            case "Gameboy":
                particle.setAnimationCows();
                break;
            case "Hadouken":
                particle.setAnimationCows();
                break;
            case "Inspector Gadget":
                particle.setAnimationCows();
                break;
            case "Jigglypuff":
                particle.setAnimationCows();
                break;
            case "Mario":
                particle.setAnimationCows();
                break;
            case "Mario Coin":
                particle.setAnimationCows();
                break;
            case "Mario Fanfare":
                particle.setAnimationCows();
                break;
            case "Mario Grow":
                particle.setAnimationCows();
                break;
            case "Mario Jump":
                particle.setAnimationCows();
                break;
            case "Mario Lose":
                particle.setAnimationCows();
                break;
            case "Mario Underground":
                particle.setAnimationCows();
                break;
            case "Nuke":
                particle.setAnimationCows();
                break;
            case "Playstation":
                particle.setAnimationCows();
                break;
            case "Pacman Death":
                particle.setAnimationCows();
                break;
            case "Pokemon Heal":
                particle.setAnimationPokemon();
                break;
            case "Tetris":
                particle.setAnimationCows();
                break;
            case "Sniper shot":
                particle.setAnimationCows();
                break;
            case "Badadipoopi":
                particle.setAnimationCows();
                break;
            case "Back to the future":
                particle.setAnimationCows();
                break;
            case "Belair":
                particle.setAnimationCows();
                break;
            case "Bazinga":
                particle.setAnimationCows();
                break;
            case "Darth Vader":
                particle.setAnimationVader();
                break;
            case "Chewbacca":
                particle.setAnimationCows();
                break;
            case "Fast n Furious":
                particle.setAnimationCows();
                break;
            case "Doh":
                particle.setAnimationCows();
                break;
            case "Nazgul":
                particle.setAnimationCows();
                break;
            case "Godzilla":
                particle.setAnimationCows();
                break;
            case "Two and a Half Men":
                particle.setAnimationCows();
                break;
            case "Oompaloompa":
                particle.setAnimationCows();
                break;
            case "Wazzup":
                particle.setAnimationCows();
                break;
//                    case "":
//                        particle.setAnimation();
//                        break;
        }
    }
}
