package com.xtonousou.xsoundboardhd;

import android.content.ContentValues;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *  TODO Rename setAsXXXX methods.
 */
public class ToneManager {
    public static final String TAG = "ToneManager";

    private Particle particle;
    private String itemName;
    private View itemView;

    public ToneManager(String itemName, View itemView) {
        this.itemName = itemName;
        this.itemView = itemView;
    }

    public ToneManager(Particle particle, String itemName) {
        this.particle = particle;
        this.itemName = itemName;
    }

    public void ringtone() {
        createDirIfNotExists("/xSoundBoardHD/Ringtones");

        switch (itemName) {
            default:
                Log.e(TAG, "onMenuItemClick: setAsRingtone()");
                break;
            case "Nigga's satisfaction":
                setAsRingtone(R.raw.funny_a);
                break;
            case "Hitmarker":
                setAsRingtone(R.raw.games_hitmarker);
                break;
            case "It' s 04:20 meng…":
                setAsRingtone(R.raw.funny_a420);
                break;
            case "420 blaze it faggot":
                setAsRingtone(R.raw.funny_a420blazeit);
                break;
            case "Blurp":
                setAsRingtone(R.raw.funny_blurp);
                break;
            case "Adam!?!":
                setAsRingtone(R.raw.funny_adam);
                break;
            case "Allahu Akbar":
                setAsRingtone(R.raw.funny_allahuakbar);
                break;
            case "Damn son!":
                setAsRingtone(R.raw.funny_damnson);
                break;
            case "Bane: Darude Sandstorm":
                setAsRingtone(R.raw.funny_banesandstorm);
                break;
            case "Bearcum!!!":
                setAsRingtone(R.raw.funny_bearcum);
                break;
            case "What is going on here?":
                setAsRingtone(R.raw.funny_bekfast);
                break;
            case "Πετράκης: Fuck her right in the pussy":
                setAsRingtone(R.raw.personal_fuckherrightinthepussy);
                break;
            case "Black kid on fire":
                setAsRingtone(R.raw.funny_blackkidonfire);
                break;
            case "Σα τον Κάτμαν μ' έκανες!":
                setAsRingtone(R.raw.personal_catman);
                break;
            case "Hmm, it's dick o'clock":
                setAsRingtone(R.raw.nsfw_dickoclock);
                break;
            case "Russian father":
                setAsRingtone(R.raw.funny_fathercatishungry);
                break;
            case "Fichtl's Lied":
                setAsRingtone(R.raw.funny_fichtlslied);
                break;
            case "Buffed Blowjob":
                setAsRingtone(R.raw.nsfw_fuckyeah);
                break;
            case "Hey that' s pretty good!":
                setAsRingtone(R.raw.funny_prettygood);
                break;
            case "xT: Γαμώ τον Χριστό σου!":
                setAsRingtone(R.raw.personal_gamwtoxristosou);
                break;
            case "I' m your father!":
                setAsRingtone(R.raw.movies_imyourfather);
                break;
            case "Give it to me!":
                setAsRingtone(R.raw.nsfw_giveittome);
                break;
            case "Κοτσολάρη: Πίπα":
                setAsRingtone(R.raw.personal_eleni);
                break;
            case "Fun":
                setAsRingtone(R.raw.funny_fun);
                break;
            case "Kazoo":
                setAsRingtone(R.raw.funny_kazoo);
                break;
            case "Immigrants cause cancer":
                setAsRingtone(R.raw.funny_immigrants);
                break;
            case "Maybe we could invite some women?":
                setAsRingtone(R.raw.funny_invitewomen);
                break;
            case "Trap Theme":
                setAsRingtone(R.raw.funny_itsatrap);
                break;
            case "Γαμώ τα καντήλια μου όλα!":
                setAsRingtone(R.raw.personal_kantilia);
                break;
            case "Weird laugh":
                setAsRingtone(R.raw.funny_laugh);
                break;
            case "Thank you…":
                setAsRingtone(R.raw.nsfw_thankyou);
                break;
            case "Make your dreams come true!":
                setAsRingtone(R.raw.funny_lol);
                break;
            case "LOTR MLG":
                setAsRingtone(R.raw.movies_lotr);
                break;
            case "Come here my pet Russian…":
                setAsRingtone(R.raw.funny_petrussian);
                break;
            case "This is my masterpiece!":
                setAsRingtone(R.raw.funny_masterpiece);
                break;
            case "Do you want my milk?":
                setAsRingtone(R.raw.nsfw_milkinyourmot);
                break;
            case "My mooscles are getting bigger!":
                setAsRingtone(R.raw.funny_mooscles);
                break;
            case "I'm growing stronker!":
                setAsRingtone(R.raw.funny_stronker);
                break;
            case "Yiss":
                setAsRingtone(R.raw.funny_yiiisss);
                break;
            case "STFU":
                setAsRingtone(R.raw.funny_stfu);
                break;
            case "Nigga is cumming…":
                setAsRingtone(R.raw.funny_niggacum);
                break;
            case "Sup nigga, wanna talk some shit?":
                setAsRingtone(R.raw.funny_niggers);
                break;
            case "Surprise motherfucker!":
                setAsRingtone(R.raw.funny_surprise);
                break;
            case "Πετρόπουλος: Action!":
                setAsRingtone(R.raw.personal_nikos);
                break;
            case "Shaved pussy…":
                setAsRingtone(R.raw.nsfw_shaved);
                break;
            case "Seven vaginias…":
                setAsRingtone(R.raw.funny_sevenvaginas);
                break;
            case "Oh my!":
                setAsRingtone(R.raw.funny_ohmy);
                break;
            case "Oh Yeaaaah!":
                setAsRingtone(R.raw.funny_ohyeaah);
                break;
            case "Penis, penis and penis":
                setAsRingtone(R.raw.nsfw_penis);
                break;
            case "Our courage will pull us through":
                setAsRingtone(R.raw.games_pokemonaggouri);
                break;
            case "Excuse me, I have some pussyhair on me!":
                setAsRingtone(R.raw.funny_pussyhair);
                break;
            case "Retardation Theme":
                setAsRingtone(R.raw.funny_retardation);
                break;
            case "Cyka Blyat's Middlefinger":
                setAsRingtone(R.raw.funny_russianmiddlefinger);
                break;
            case "I'm the scatman…":
                setAsRingtone(R.raw.nsfw_scatman);
                break;
            case "Why don't you show us that fine pussy?":
                setAsRingtone(R.raw.funny_showthepussy);
                break;
            case "Shutdown, glitched…":
                setAsRingtone(R.raw.funny_shutdownglitched);
                break;
            case "What manner of sissyness, is this?":
                setAsRingtone(R.raw.funny_sissiness);
                break;
            case "You spin me round":
                setAsRingtone(R.raw.funny_spinme);
                break;
            case "Squeaker":
                setAsRingtone(R.raw.funny_squeeek);
                break;
            case "That's a 10!":
                setAsRingtone(R.raw.funny_thatsa10);
                break;
            case "Illuminati":
                setAsRingtone(R.raw.funny_illuminati);
                break;
            case "WOW":
                setAsRingtone(R.raw.funny_wow);
                break;
            case "Richard Stallman":
                setAsRingtone(R.raw.funny_windows);
                break;
            case "Τσικλίδης: Λες και είναι τσόντα!":
                setAsRingtone(R.raw.personal_tsonta);
                break;
            case "What else?":
                setAsRingtone(R.raw.funny_whatelse);
                break;
            case "Zodiac":
                setAsRingtone(R.raw.funny_zodiac);
                break;
            case "Yuri Kuma Arashi":
                setAsRingtone(R.raw.movies_sabada);
                break;
            case "xT: Ρε καριόλη;":
                setAsRingtone(R.raw.personal_karioli);
                break;
            case "Κιάμος: Ε!":
                setAsRingtone(R.raw.personal_e);
                break;
            case "Κιάμος: Χατζάρα":
                setAsRingtone(R.raw.personal_tigelasre);
                break;
            case "Κιάμος: Τί κοιτάς ρε μαλάκα":
                setAsRingtone(R.raw.personal_tikoitasremlk);
                break;
            case "U WOT M8":
                setAsRingtone(R.raw.movies_uwotmate);
                break;
            case "Cow":
                setAsRingtone(R.raw.animals_cow);
                break;
//                    case "":
//                        setAsRingtone(R.raw.);
//                        break;
        }
    }

    public void notification() {
        createDirIfNotExists("/xSoundBoardHD/Notifications");

        switch (itemName) {
            default:
                Log.e(TAG, "onMenuItemClick: setAsNotification()");
                break;
            case "Nigga's satisfaction":
                setAsNotification(R.raw.funny_a);
                break;
            case "Hitmarker":
                setAsNotification(R.raw.games_hitmarker);
                break;
            case "It' s 04:20 meng…":
                setAsNotification(R.raw.funny_a420);
                break;
            case "420 blaze it faggot":
                setAsNotification(R.raw.funny_a420blazeit);
                break;
            case "Blurp":
                setAsNotification(R.raw.funny_blurp);
                break;
            case "Adam!?!":
                setAsNotification(R.raw.funny_adam);
                break;
            case "Allahu Akbar":
                setAsNotification(R.raw.funny_allahuakbar);
                break;
            case "Damn son!":
                setAsNotification(R.raw.funny_damnson);
                break;
            case "Bane: Darude Sandstorm":
                setAsNotification(R.raw.funny_banesandstorm);
                break;
            case "Bearcum!!!":
                setAsNotification(R.raw.funny_bearcum);
                break;
            case "What is going on here?":
                setAsNotification(R.raw.funny_bekfast);
                break;
            case "Πετράκης: Fuck her right in the pussy":
                setAsNotification(R.raw.personal_fuckherrightinthepussy);
                break;
            case "Black kid on fire":
                setAsNotification(R.raw.funny_blackkidonfire);
                break;
            case "Σα τον Κάτμαν μ' έκανες!":
                setAsNotification(R.raw.personal_catman);
                break;
            case "Hmm, it's dick o'clock":
                setAsNotification(R.raw.nsfw_dickoclock);
                break;
            case "Russian father":
                setAsNotification(R.raw.funny_fathercatishungry);
                break;
            case "Fichtl's Lied":
                setAsNotification(R.raw.funny_fichtlslied);
                break;
            case "Buffed Blowjob":
                setAsNotification(R.raw.nsfw_fuckyeah);
                break;
            case "Hey that' s pretty good!":
                setAsNotification(R.raw.funny_prettygood);
                break;
            case "xT: Γαμώ τον Χριστό σου!":
                setAsNotification(R.raw.personal_gamwtoxristosou);
                break;
            case "I' m your father!":
                setAsNotification(R.raw.movies_imyourfather);
                break;
            case "Give it to me!":
                setAsNotification(R.raw.nsfw_giveittome);
                break;
            case "Κοτσολάρη: Πίπα":
                setAsNotification(R.raw.personal_eleni);
                break;
            case "Fun":
                setAsNotification(R.raw.funny_fun);
                break;
            case "Kazoo":
                setAsNotification(R.raw.funny_kazoo);
                break;
            case "Immigrants cause cancer":
                setAsNotification(R.raw.funny_immigrants);
                break;
            case "Maybe we could invite some women?":
                setAsNotification(R.raw.funny_invitewomen);
                break;
            case "Trap Theme":
                setAsNotification(R.raw.funny_itsatrap);
                break;
            case "Γαμώ τα καντήλια μου όλα!":
                setAsNotification(R.raw.personal_kantilia);
                break;
            case "Weird laugh":
                setAsNotification(R.raw.funny_laugh);
                break;
            case "Thank you…":
                setAsNotification(R.raw.nsfw_thankyou);
                break;
            case "Make your dreams come true!":
                setAsNotification(R.raw.funny_lol);
                break;
            case "LOTR MLG":
                setAsNotification(R.raw.movies_lotr);
                break;
            case "Come here my pet Russian…":
                setAsNotification(R.raw.funny_petrussian);
                break;
            case "This is my masterpiece!":
                setAsNotification(R.raw.funny_masterpiece);
                break;
            case "Do you want my milk?":
                setAsNotification(R.raw.nsfw_milkinyourmot);
                break;
            case "My mooscles are getting bigger!":
                setAsNotification(R.raw.funny_mooscles);
                break;
            case "I'm growing stronker!":
                setAsNotification(R.raw.funny_stronker);
                break;
            case "Yiss":
                setAsNotification(R.raw.funny_yiiisss);
                break;
            case "STFU":
                setAsNotification(R.raw.funny_stfu);
                break;
            case "Nigga is cumming…":
                setAsNotification(R.raw.funny_niggacum);
                break;
            case "Sup nigga, wanna talk some shit?":
                setAsNotification(R.raw.funny_niggers);
                break;
            case "Surprise motherfucker!":
                setAsNotification(R.raw.funny_surprise);
                break;
            case "Πετρόπουλος: Action!":
                setAsNotification(R.raw.personal_nikos);
                break;
            case "Shaved pussy…":
                setAsNotification(R.raw.nsfw_shaved);
                break;
            case "Seven vaginias…":
                setAsNotification(R.raw.funny_sevenvaginas);
                break;
            case "Oh my!":
                setAsNotification(R.raw.funny_ohmy);
                break;
            case "Oh Yeaaaah!":
                setAsNotification(R.raw.funny_ohyeaah);
                break;
            case "Penis, penis and penis":
                setAsNotification(R.raw.nsfw_penis);
                break;
            case "Our courage will pull us through":
                setAsNotification(R.raw.games_pokemonaggouri);
                break;
            case "Excuse me, I have some pussyhair on me!":
                setAsNotification(R.raw.funny_pussyhair);
                break;
            case "Retardation Theme":
                setAsNotification(R.raw.funny_retardation);
                break;
            case "Cyka Blyat's Middlefinger":
                setAsNotification(R.raw.funny_russianmiddlefinger);
                break;
            case "I'm the scatman…":
                setAsNotification(R.raw.nsfw_scatman);
                break;
            case "Why don't you show us that fine pussy?":
                setAsNotification(R.raw.funny_showthepussy);
                break;
            case "Shutdown, glitched…":
                setAsNotification(R.raw.funny_shutdownglitched);
                break;
            case "What manner of sissyness, is this?":
                setAsNotification(R.raw.funny_sissiness);
                break;
            case "You spin me round":
                setAsNotification(R.raw.funny_spinme);
                break;
            case "Squeaker":
                setAsNotification(R.raw.funny_squeeek);
                break;
            case "That's a 10!":
                setAsNotification(R.raw.funny_thatsa10);
                break;
            case "Illuminati":
                setAsNotification(R.raw.funny_illuminati);
                break;
            case "WOW":
                setAsNotification(R.raw.funny_wow);
                break;
            case "Richard Stallman":
                setAsNotification(R.raw.funny_windows);
                break;
            case "Τσικλίδης: Λες και είναι τσόντα!":
                setAsNotification(R.raw.personal_tsonta);
                break;
            case "What else?":
                setAsNotification(R.raw.funny_whatelse);
                break;
            case "Zodiac":
                setAsNotification(R.raw.funny_zodiac);
                break;
            case "Yuri Kuma Arashi":
                setAsNotification(R.raw.movies_sabada);
                break;
            case "xT: Ρε καριόλη;":
                setAsNotification(R.raw.personal_karioli);
                break;
            case "Κιάμος: Ε!":
                setAsNotification(R.raw.personal_e);
                break;
            case "Κιάμος: Χατζάρα":
                setAsNotification(R.raw.personal_tigelasre);
                break;
            case "Κιάμος: Τί κοιτάς ρε μαλάκα":
                setAsNotification(R.raw.personal_tikoitasremlk);
                break;
            case "U WOT M8":
                setAsNotification(R.raw.movies_uwotmate);
                break;
            case "Cow":
                setAsNotification(R.raw.animals_cow);
                break;
//                    case "":
//                        setAsNotification(R.raw.);
//                        break;
        }
    }

    public void alarm() {
        createDirIfNotExists("/xSoundBoardHD/Alarms");

        switch (itemName) {
            default:
                Log.e(TAG, "onMenuItemClick: setAsAlarm()");
                break;
            case "Nigga's satisfaction":
                setAsAlarm(R.raw.funny_a);
                break;
            case "Hitmarker":
                setAsAlarm(R.raw.games_hitmarker);
                break;
            case "It' s 04:20 meng…":
                setAsAlarm(R.raw.funny_a420);
                break;
            case "420 blaze it faggot":
                setAsAlarm(R.raw.funny_a420blazeit);
                break;
            case "Blurp":
                setAsAlarm(R.raw.funny_blurp);
                break;
            case "Adam!?!":
                setAsAlarm(R.raw.funny_adam);
                break;
            case "Allahu Akbar":
                setAsAlarm(R.raw.funny_allahuakbar);
                break;
            case "Damn son!":
                setAsAlarm(R.raw.funny_damnson);
                break;
            case "Bane: Darude Sandstorm":
                setAsAlarm(R.raw.funny_banesandstorm);
                break;
            case "Bearcum!!!":
                setAsAlarm(R.raw.funny_bearcum);
                break;
            case "What is going on here?":
                setAsAlarm(R.raw.funny_bekfast);
                break;
            case "Πετράκης: Fuck her right in the pussy":
                setAsAlarm(R.raw.personal_fuckherrightinthepussy);
                break;
            case "Black kid on fire":
                setAsAlarm(R.raw.funny_blackkidonfire);
                break;
            case "Σα τον Κάτμαν μ' έκανες!":
                setAsAlarm(R.raw.personal_catman);
                break;
            case "Hmm, it's dick o'clock":
                setAsAlarm(R.raw.nsfw_dickoclock);
                break;
            case "Russian father":
                setAsAlarm(R.raw.funny_fathercatishungry);
                break;
            case "Fichtl's Lied":
                setAsAlarm(R.raw.funny_fichtlslied);
                break;
            case "Buffed Blowjob":
                setAsAlarm(R.raw.nsfw_fuckyeah);
                break;
            case "Hey that' s pretty good!":
                setAsAlarm(R.raw.funny_prettygood);
                break;
            case "xT: Γαμώ τον Χριστό σου!":
                setAsAlarm(R.raw.personal_gamwtoxristosou);
                break;
            case "I' m your father!":
                setAsAlarm(R.raw.movies_imyourfather);
                break;
            case "Give it to me!":
                setAsAlarm(R.raw.nsfw_giveittome);
                break;
            case "Κοτσολάρη: Πίπα":
                setAsAlarm(R.raw.personal_eleni);
                break;
            case "Fun":
                setAsAlarm(R.raw.funny_fun);
                break;
            case "Kazoo":
                setAsAlarm(R.raw.funny_kazoo);
                break;
            case "Immigrants cause cancer":
                setAsAlarm(R.raw.funny_immigrants);
                break;
            case "Maybe we could invite some women?":
                setAsAlarm(R.raw.funny_invitewomen);
                break;
            case "Trap Theme":
                setAsAlarm(R.raw.funny_itsatrap);
                break;
            case "Γαμώ τα καντήλια μου όλα!":
                setAsAlarm(R.raw.personal_kantilia);
                break;
            case "Weird laugh":
                setAsAlarm(R.raw.funny_laugh);
                break;
            case "Thank you…":
                setAsAlarm(R.raw.nsfw_thankyou);
                break;
            case "Make your dreams come true!":
                setAsAlarm(R.raw.funny_lol);
                break;
            case "LOTR MLG":
                setAsAlarm(R.raw.movies_lotr);
                break;
            case "Come here my pet Russian…":
                setAsAlarm(R.raw.funny_petrussian);
                break;
            case "This is my masterpiece!":
                setAsAlarm(R.raw.funny_masterpiece);
                break;
            case "Do you want my milk?":
                setAsAlarm(R.raw.nsfw_milkinyourmot);
                break;
            case "My mooscles are getting bigger!":
                setAsAlarm(R.raw.funny_mooscles);
                break;
            case "I'm growing stronker!":
                setAsAlarm(R.raw.funny_stronker);
                break;
            case "Yiss":
                setAsAlarm(R.raw.funny_yiiisss);
                break;
            case "STFU":
                setAsAlarm(R.raw.funny_stfu);
                break;
            case "Nigga is cumming…":
                setAsAlarm(R.raw.funny_niggacum);
                break;
            case "Sup nigga, wanna talk some shit?":
                setAsAlarm(R.raw.funny_niggers);
                break;
            case "Surprise motherfucker!":
                setAsAlarm(R.raw.funny_surprise);
                break;
            case "Πετρόπουλος: Action!":
                setAsAlarm(R.raw.personal_nikos);
                break;
            case "Shaved pussy…":
                setAsAlarm(R.raw.nsfw_shaved);
                break;
            case "Seven vaginias…":
                setAsAlarm(R.raw.funny_sevenvaginas);
                break;
            case "Oh my!":
                setAsAlarm(R.raw.funny_ohmy);
                break;
            case "Oh Yeaaaah!":
                setAsAlarm(R.raw.funny_ohyeaah);
                break;
            case "Penis, penis and penis":
                setAsAlarm(R.raw.nsfw_penis);
                break;
            case "Our courage will pull us through":
                setAsAlarm(R.raw.games_pokemonaggouri);
                break;
            case "Excuse me, I have some pussyhair on me!":
                setAsAlarm(R.raw.funny_pussyhair);
                break;
            case "Retardation Theme":
                setAsAlarm(R.raw.funny_retardation);
                break;
            case "Cyka Blyat's Middlefinger":
                setAsAlarm(R.raw.funny_russianmiddlefinger);
                break;
            case "I'm the scatman…":
                setAsAlarm(R.raw.nsfw_scatman);
                break;
            case "Why don't you show us that fine pussy?":
                setAsAlarm(R.raw.funny_showthepussy);
                break;
            case "Shutdown, glitched…":
                setAsAlarm(R.raw.funny_shutdownglitched);
                break;
            case "What manner of sissyness, is this?":
                setAsAlarm(R.raw.funny_sissiness);
                break;
            case "You spin me round":
                setAsAlarm(R.raw.funny_spinme);
                break;
            case "Squeaker":
                setAsAlarm(R.raw.funny_squeeek);
                break;
            case "That's a 10!":
                setAsAlarm(R.raw.funny_thatsa10);
                break;
            case "Illuminati":
                setAsAlarm(R.raw.funny_illuminati);
                break;
            case "WOW":
                setAsAlarm(R.raw.funny_wow);
                break;
            case "Richard Stallman":
                setAsAlarm(R.raw.funny_windows);
                break;
            case "Τσικλίδης: Λες και είναι τσόντα!":
                setAsAlarm(R.raw.personal_tsonta);
                break;
            case "What else?":
                setAsAlarm(R.raw.funny_whatelse);
                break;
            case "Zodiac":
                setAsAlarm(R.raw.funny_zodiac);
                break;
            case "Yuri Kuma Arashi":
                setAsAlarm(R.raw.movies_sabada);
                break;
            case "xT: Ρε καριόλη;":
                setAsAlarm(R.raw.personal_karioli);
                break;
            case "Κιάμος: Ε!":
                setAsAlarm(R.raw.personal_e);
                break;
            case "Κιάμος: Χατζάρα":
                setAsAlarm(R.raw.personal_tigelasre);
                break;
            case "Κιάμος: Τί κοιτάς ρε μαλάκα":
                setAsAlarm(R.raw.personal_tikoitasremlk);
                break;
            case "U WOT M8":
                setAsAlarm(R.raw.movies_uwotmate);
                break;
            case "Cow":
                setAsAlarm(R.raw.animals_cow);
                break;
//                    case "":
//                        setAsAlarm(R.raw.);
//                        break;
        }
    }

    /**
     *  Saves sound selected by user in /xSoundBoardHD/Ringtones/,
     *  cleans filename and sets it as ringtone.
     *  @param resource	R.raw.resourcesound
     */
    private boolean setAsRingtone(int resource) {
        byte[] buffer;
        InputStream fIn = itemView.getContext().getResources().openRawResource(resource);
        int size;

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            return false;
        }

        String path = Environment.getExternalStorageDirectory().getPath() + "/xSoundBoardHD/Ringtones/";

        String filename = FileNameCleaner.cleanFileName(itemName);
        filename = filename.replaceAll(" ", "_");
        filename = filename.toLowerCase() + ".mp3";

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
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
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
            ringtoneSnack();
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }

        return true;
    }

    /**
     *  Saves sound selected by user in /xSoundBoardHD/Notifications/, cleans filename and sets it as notification
     *  @param ressound	R.raw.resourcesound
     */
    private boolean setAsNotification(int resource) {
        byte[] buffer;
        InputStream fIn = itemView.getContext().getResources().openRawResource(resource);
        int size;

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            return false;
        }

        String path = Environment.getExternalStorageDirectory().getPath() + "/xSoundBoardHD/Notifications/";

        String filename = FileNameCleaner.cleanFileName(itemName);
        filename = filename.replaceAll(" ", "_");
        filename = filename.toLowerCase() + ".mp3";

        System.out.println(filename);

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
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
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
            notificationSnack();
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
        return true;
    }

    /**
     *  Saves sound selected by user in /xSoundBoardHD/Alarms/, cleans filename and sets it as alarm
     *  @param resource	R.raw.resourcesound
     */
    private boolean setAsAlarm(int resource) {
        byte[] buffer;
        InputStream fIn = itemView.getContext().getResources().openRawResource(resource);
        int size;

        try {
            size = fIn.available();
            buffer = new byte[size];
            fIn.read(buffer);
            fIn.close();
        } catch (IOException e) {
            return false;
        }

        String path = Environment.getExternalStorageDirectory().getPath() + "/xSoundBoardHD/Alarms/";

        String filename = FileNameCleaner.cleanFileName(itemName);
        filename = filename.replaceAll(" ", "_");
        filename = filename.toLowerCase() + ".mp3";

        System.out.println(filename);

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
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
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
            alarmSnack();
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
        return true;
    }

    /**
     *  Creates working directory if does not exist.
     *  @param path	String.
     *  @return	true for successful directory creation or false if there was problem while creating folder
     */
    private static boolean createDirIfNotExists(String path) {
        boolean ret = true;
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e(TAG, "Method: createDirIfNotExists(String path).");
                ret = false;
            }
        }
        return ret;
    }

    private void ringtoneSnack() {
        Snackbar snackbar = Snackbar
                .make(itemView.getRootView(), "Ringtone saved", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(sbView.getContext(), R.color.colorPrimaryDark));
        TextView snackTV = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        snackTV.setTextColor((new DayColor(itemView.getContext()).getDayColor()));
        snackbar.show();
    }

    private void notificationSnack() {
        Snackbar snackbar = Snackbar
                .make(itemView.getRootView(), "Notification saved", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(sbView.getContext(), R.color.colorPrimaryDark));
        TextView snackTV = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        snackTV.setTextColor((new DayColor(itemView.getContext()).getDayColor()));
        snackbar.show();
    }

    private void alarmSnack() {
        Snackbar snackbar = Snackbar
                .make(itemView.getRootView(), "Alarm saved", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(sbView.getContext(), R.color.colorPrimaryDark));
        TextView snackTV = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        snackTV.setTextColor((new DayColor(itemView.getContext()).getDayColor()));
        snackbar.show();
    }

    /**
     *  Animation setter.
     */
    public void makeItShine() {
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
//                    case "":
//                        particle.setAnimation();
//                        break;
        }
    }
}
