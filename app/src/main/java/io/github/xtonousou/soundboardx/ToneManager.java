package io.github.xtonousou.soundboardx;

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

public class ToneManager {
    public static final String TAG = "ToneManager";

    private Particle particle;
    private String   itemName;
    private View     itemView;

    public ToneManager(String itemName, View itemView) {
        this.itemName = itemName;
        this.itemView = itemView;
    }

    public ToneManager(Particle particle, String itemName) {
        this.particle = particle;
        this.itemName = itemName;
    }

    public void ringtone() {
        createDirIfNotExists("/SoundBoardX/Ringtones");

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
            case "Oh baby a triple":
                setAsRingtone(R.raw.funny_babytriple);
                break;
            case "Badum Tss":
                setAsRingtone(R.raw.funny_badumtss);
                break;
            case "Beep":
                setAsRingtone(R.raw.funny_beep);
                break;
            case "Boxeo":
                setAsRingtone(R.raw.funny_boxeo);
                break;
            case "Chan":
                setAsRingtone(R.raw.funny_chan);
                break;
            case "Chan chan":
                setAsRingtone(R.raw.funny_chanchan);
                break;
            case "Combo breaker":
                setAsRingtone(R.raw.funny_combobreaker);
                break;
            case "De dotaded wam":
                setAsRingtone(R.raw.funny_dedotadedwam);
                break;
            case "Duck toy":
                setAsRingtone(R.raw.funny_ducktoy);
                break;
            case "Get noscoped":
                setAsRingtone(R.raw.funny_getnoscoped);
                break;
            case "Mom get the camera":
                setAsRingtone(R.raw.funny_getthecamera);
                break;
            case "Headshot":
                setAsRingtone(R.raw.funny_headshot);
                break;
            case "Inception":
                setAsRingtone(R.raw.funny_inception);
                break;
            case "Holy Shit":
                setAsRingtone(R.raw.funny_holyshit);
                break;
            case "Just Do It":
                setAsRingtone(R.raw.funny_justdoit);
                break;
            case "John Cena":
                setAsRingtone(R.raw.funny_johncena);
                break;
            case "Never do that":
                setAsRingtone(R.raw.funny_neverdonethat);
                break;
            case "Nein Nein":
                setAsRingtone(R.raw.funny_nein);
                break;
            case "Over 9000":
                setAsRingtone(R.raw.funny_overninek);
                break;
            case "OMG":
                setAsRingtone(R.raw.funny_omg);
                break;
            case "Shots fired":
                setAsRingtone(R.raw.funny_shotsfired);
                break;
            case "Penault Jelly":
                setAsRingtone(R.raw.funny_penaultjelly);
                break;
            case "Whatcha say":
                setAsRingtone(R.raw.funny_whatcha_say);
                break;
            case "Smoke weed everyday":
                setAsRingtone(R.raw.funny_smokeweedeveryday);
                break;
            case "Wombo Combo":
                setAsRingtone(R.raw.funny_wombo_combo);
                break;
            case "WTF BOOM":
                setAsRingtone(R.raw.funny_wtf_boom);
                break;
            case "Aku Aku":
                setAsRingtone(R.raw.games_akuaku);
                break;
            case "Balls of steel":
                setAsRingtone(R.raw.games_ballsofsteel);
                break;
            case "EA Games":
                setAsRingtone(R.raw.games_eagames);
                break;
            case "EA Sports":
                setAsRingtone(R.raw.games_easports);
                break;
            case "Excellent":
                setAsRingtone(R.raw.games_excellent);
                break;
            case "Fus Ro Dah":
                setAsRingtone(R.raw.games_fusrodah);
                break;
            case "Gameboy":
                setAsRingtone(R.raw.games_gameboy);
                break;
            case "Hadouken":
                setAsRingtone(R.raw.games_hadouken);
                break;
            case "Inspector Gadget":
                setAsRingtone(R.raw.games_inspectorgadget);
                break;
            case "Jigglypuff":
                setAsRingtone(R.raw.games_jigglypuff);
                break;
            case "Mario":
                setAsRingtone(R.raw.games_mario);
                break;
            case "Mario Coin":
                setAsRingtone(R.raw.games_mariocoin);
                break;
            case "Mario Fanfare":
                setAsRingtone(R.raw.games_mariofanfare);
                break;
            case "Mario Grow":
                setAsRingtone(R.raw.games_mariogrow);
                break;
            case "Mario Jump":
                setAsRingtone(R.raw.games_mariojump);
                break;
            case "Mario Lose":
                setAsRingtone(R.raw.games_mariolose);
                break;
            case "Mario Underground":
                setAsRingtone(R.raw.games_mariounderground);
                break;
            case "Nuke":
                setAsRingtone(R.raw.games_nuke);
                break;
            case "Playstation":
                setAsRingtone(R.raw.games_playstation);
                break;
            case "Pacman Death":
                setAsRingtone(R.raw.games_pacmandeath);
                break;
            case "Pokemon Heal":
                setAsRingtone(R.raw.games_pokemonheal);
                break;
            case "Tetris":
                setAsRingtone(R.raw.games_tetris);
                break;
            case "Sniper shot":
                setAsRingtone(R.raw.games_snipershot);
                break;
            case "Badadipoopi":
                setAsRingtone(R.raw.movies_badadipoopi);
                break;
            case "Back to the future":
                setAsRingtone(R.raw.movies_backtothefuture);
                break;
            case "Belair":
                setAsRingtone(R.raw.movies_belair);
                break;
            case "Bazinga":
                setAsRingtone(R.raw.movies_bazinga);
                break;
            case "Darth Vader":
                setAsRingtone(R.raw.movies_darthvader);
                break;
            case "Chewbacca":
                setAsRingtone(R.raw.movies_chewbacca);
                break;
            case "Fast n Furious":
                setAsRingtone(R.raw.movies_fastnfurious);
                break;
            case "Doh":
                setAsRingtone(R.raw.movies_doh);
                break;
            case "Nazgul":
                setAsRingtone(R.raw.movies_nazgul);
                break;
            case "Godzilla":
                setAsRingtone(R.raw.movies_godzilla);
                break;
            case "Two and a Half Men":
                setAsRingtone(R.raw.movies_twohalfmen);
                break;
            case "Oompaloompa":
                setAsRingtone(R.raw.movies_oompaloompa);
                break;
            case "Dr. Dre The Next Episode":
                setAsRingtone(R.raw.thug_drdre_snoop);
                break;
            case "Wazzup":
                setAsRingtone(R.raw.movies_wazzup);
                break;
            case "GTA":
                setAsRingtone(R.raw.thug_gta);
                break;
        }
    }

    public void notification() {
        createDirIfNotExists("/SoundBoardX/Notifications");

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
            case "Oh baby a triple":
                setAsNotification(R.raw.funny_babytriple);
                break;
            case "Badum Tss":
                setAsNotification(R.raw.funny_badumtss);
                break;
            case "Beep":
                setAsNotification(R.raw.funny_beep);
                break;
            case "Boxeo":
                setAsNotification(R.raw.funny_boxeo);
                break;
            case "Chan":
                setAsNotification(R.raw.funny_chan);
                break;
            case "Chan chan":
                setAsNotification(R.raw.funny_chanchan);
                break;
            case "Combo breaker":
                setAsNotification(R.raw.funny_combobreaker);
                break;
            case "De dotaded wam":
                setAsNotification(R.raw.funny_dedotadedwam);
                break;
            case "Duck toy":
                setAsNotification(R.raw.funny_ducktoy);
                break;
            case "Get noscoped":
                setAsNotification(R.raw.funny_getnoscoped);
                break;
            case "Mom get the camera":
                setAsNotification(R.raw.funny_getthecamera);
                break;
            case "Headshot":
                setAsNotification(R.raw.funny_headshot);
                break;
            case "Inception":
                setAsNotification(R.raw.funny_inception);
                break;
            case "Holy Shit":
                setAsNotification(R.raw.funny_holyshit);
                break;
            case "Just Do It":
                setAsNotification(R.raw.funny_justdoit);
                break;
            case "John Cena":
                setAsNotification(R.raw.funny_johncena);
                break;
            case "Never do that":
                setAsNotification(R.raw.funny_neverdonethat);
                break;
            case "Nein Nein":
                setAsNotification(R.raw.funny_nein);
                break;
            case "Over 9000":
                setAsNotification(R.raw.funny_overninek);
                break;
            case "OMG":
                setAsNotification(R.raw.funny_omg);
                break;
            case "Shots fired":
                setAsNotification(R.raw.funny_shotsfired);
                break;
            case "Penault Jelly":
                setAsNotification(R.raw.funny_penaultjelly);
                break;
            case "Whatcha say":
                setAsNotification(R.raw.funny_whatcha_say);
                break;
            case "Smoke weed everyday":
                setAsNotification(R.raw.funny_smokeweedeveryday);
                break;
            case "Wombo Combo":
                setAsNotification(R.raw.funny_wombo_combo);
                break;
            case "WTF BOOM":
                setAsNotification(R.raw.funny_wtf_boom);
                break;
            case "Aku Aku":
                setAsNotification(R.raw.games_akuaku);
                break;
            case "Balls of steel":
                setAsNotification(R.raw.games_ballsofsteel);
                break;
            case "EA Games":
                setAsNotification(R.raw.games_eagames);
                break;
            case "EA Sports":
                setAsNotification(R.raw.games_easports);
                break;
            case "Excellent":
                setAsNotification(R.raw.games_excellent);
                break;
            case "Fus Ro Dah":
                setAsNotification(R.raw.games_fusrodah);
                break;
            case "Gameboy":
                setAsNotification(R.raw.games_gameboy);
                break;
            case "Hadouken":
                setAsNotification(R.raw.games_hadouken);
                break;
            case "Inspector Gadget":
                setAsNotification(R.raw.games_inspectorgadget);
                break;
            case "Jigglypuff":
                setAsNotification(R.raw.games_jigglypuff);
                break;
            case "Mario":
                setAsNotification(R.raw.games_mario);
                break;
            case "Mario Coin":
                setAsNotification(R.raw.games_mariocoin);
                break;
            case "Mario Fanfare":
                setAsNotification(R.raw.games_mariofanfare);
                break;
            case "Mario Grow":
                setAsNotification(R.raw.games_mariogrow);
                break;
            case "Mario Jump":
                setAsNotification(R.raw.games_mariojump);
                break;
            case "Mario Lose":
                setAsNotification(R.raw.games_mariolose);
                break;
            case "Mario Underground":
                setAsNotification(R.raw.games_mariounderground);
                break;
            case "Nuke":
                setAsNotification(R.raw.games_nuke);
                break;
            case "Playstation":
                setAsNotification(R.raw.games_playstation);
                break;
            case "Pacman Death":
                setAsNotification(R.raw.games_pacmandeath);
                break;
            case "Pokemon Heal":
                setAsNotification(R.raw.games_pokemonheal);
                break;
            case "Tetris":
                setAsNotification(R.raw.games_tetris);
                break;
            case "Sniper shot":
                setAsNotification(R.raw.games_snipershot);
                break;
            case "Badadipoopi":
                setAsNotification(R.raw.movies_badadipoopi);
                break;
            case "Back to the future":
                setAsNotification(R.raw.movies_backtothefuture);
                break;
            case "Belair":
                setAsNotification(R.raw.movies_belair);
                break;
            case "Bazinga":
                setAsNotification(R.raw.movies_bazinga);
                break;
            case "Darth Vader":
                setAsNotification(R.raw.movies_darthvader);
                break;
            case "Chewbacca":
                setAsNotification(R.raw.movies_chewbacca);
                break;
            case "Fast n Furious":
                setAsNotification(R.raw.movies_fastnfurious);
                break;
            case "Doh":
                setAsNotification(R.raw.movies_doh);
                break;
            case "Nazgul":
                setAsNotification(R.raw.movies_nazgul);
                break;
            case "Godzilla":
                setAsNotification(R.raw.movies_godzilla);
                break;
            case "Two and a Half Men":
                setAsNotification(R.raw.movies_twohalfmen);
                break;
            case "Oompaloompa":
                setAsNotification(R.raw.movies_oompaloompa);
                break;
            case "Dr. Dre The Next Episode":
                setAsNotification(R.raw.thug_drdre_snoop);
                break;
            case "Wazzup":
                setAsNotification(R.raw.movies_wazzup);
                break;
            case "GTA":
                setAsNotification(R.raw.thug_gta);
                break;
        }
    }

    public void alarm() {
        createDirIfNotExists("/SoundBoardX/Alarms");

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
            case "Oh baby a triple":
                setAsAlarm(R.raw.funny_babytriple);
                break;
            case "Badum Tss":
                setAsAlarm(R.raw.funny_badumtss);
                break;
            case "Beep":
                setAsAlarm(R.raw.funny_beep);
                break;
            case "Boxeo":
                setAsAlarm(R.raw.funny_boxeo);
                break;
            case "Chan":
                setAsAlarm(R.raw.funny_chan);
                break;
            case "Chan chan":
                setAsAlarm(R.raw.funny_chanchan);
                break;
            case "Combo breaker":
                setAsAlarm(R.raw.funny_combobreaker);
                break;
            case "De dotaded wam":
                setAsAlarm(R.raw.funny_dedotadedwam);
                break;
            case "Duck toy":
                setAsAlarm(R.raw.funny_ducktoy);
                break;
            case "Get noscoped":
                setAsAlarm(R.raw.funny_getnoscoped);
                break;
            case "Mom get the camera":
                setAsAlarm(R.raw.funny_getthecamera);
                break;
            case "Headshot":
                setAsAlarm(R.raw.funny_headshot);
                break;
            case "Inception":
                setAsAlarm(R.raw.funny_inception);
                break;
            case "Holy Shit":
                setAsAlarm(R.raw.funny_holyshit);
                break;
            case "Just Do It":
                setAsAlarm(R.raw.funny_justdoit);
                break;
            case "John Cena":
                setAsAlarm(R.raw.funny_johncena);
                break;
            case "Never do that":
                setAsAlarm(R.raw.funny_neverdonethat);
                break;
            case "Nein Nein":
                setAsAlarm(R.raw.funny_nein);
                break;
            case "Over 9000":
                setAsAlarm(R.raw.funny_overninek);
                break;
            case "OMG":
                setAsAlarm(R.raw.funny_omg);
                break;
            case "Shots fired":
                setAsAlarm(R.raw.funny_shotsfired);
                break;
            case "Penault Jelly":
                setAsAlarm(R.raw.funny_penaultjelly);
                break;
            case "Whatcha say":
                setAsAlarm(R.raw.funny_whatcha_say);
                break;
            case "Smoke weed everyday":
                setAsAlarm(R.raw.funny_smokeweedeveryday);
                break;
            case "Wombo Combo":
                setAsAlarm(R.raw.funny_wombo_combo);
                break;
            case "WTF BOOM":
                setAsAlarm(R.raw.funny_wtf_boom);
                break;
            case "Aku Aku":
                setAsAlarm(R.raw.games_akuaku);
                break;
            case "Balls of steel":
                setAsAlarm(R.raw.games_ballsofsteel);
                break;
            case "EA Games":
                setAsAlarm(R.raw.games_eagames);
                break;
            case "EA Sports":
                setAsAlarm(R.raw.games_easports);
                break;
            case "Excellent":
                setAsAlarm(R.raw.games_excellent);
                break;
            case "Fus Ro Dah":
                setAsAlarm(R.raw.games_fusrodah);
                break;
            case "Gameboy":
                setAsAlarm(R.raw.games_gameboy);
                break;
            case "Hadouken":
                setAsAlarm(R.raw.games_hadouken);
                break;
            case "Inspector Gadget":
                setAsAlarm(R.raw.games_inspectorgadget);
                break;
            case "Jigglypuff":
                setAsAlarm(R.raw.games_jigglypuff);
                break;
            case "Mario":
                setAsAlarm(R.raw.games_mario);
                break;
            case "Mario Coin":
                setAsAlarm(R.raw.games_mariocoin);
                break;
            case "Mario Fanfare":
                setAsAlarm(R.raw.games_mariofanfare);
                break;
            case "Mario Grow":
                setAsAlarm(R.raw.games_mariogrow);
                break;
            case "Mario Jump":
                setAsAlarm(R.raw.games_mariojump);
                break;
            case "Mario Lose":
                setAsAlarm(R.raw.games_mariolose);
                break;
            case "Mario Underground":
                setAsAlarm(R.raw.games_mariounderground);
                break;
            case "Nuke":
                setAsAlarm(R.raw.games_nuke);
                break;
            case "Playstation":
                setAsAlarm(R.raw.games_playstation);
                break;
            case "Pacman Death":
                setAsAlarm(R.raw.games_pacmandeath);
                break;
            case "Pokemon Heal":
                setAsAlarm(R.raw.games_pokemonheal);
                break;
            case "Tetris":
                setAsAlarm(R.raw.games_tetris);
                break;
            case "Sniper shot":
                setAsAlarm(R.raw.games_snipershot);
                break;
            case "Badadipoopi":
                setAsAlarm(R.raw.movies_badadipoopi);
                break;
            case "Back to the future":
                setAsAlarm(R.raw.movies_backtothefuture);
                break;
            case "Belair":
                setAsAlarm(R.raw.movies_belair);
                break;
            case "Bazinga":
                setAsAlarm(R.raw.movies_bazinga);
                break;
            case "Darth Vader":
                setAsAlarm(R.raw.movies_darthvader);
                break;
            case "Chewbacca":
                setAsAlarm(R.raw.movies_chewbacca);
                break;
            case "Fast n Furious":
                setAsAlarm(R.raw.movies_fastnfurious);
                break;
            case "Doh":
                setAsAlarm(R.raw.movies_doh);
                break;
            case "Nazgul":
                setAsAlarm(R.raw.movies_nazgul);
                break;
            case "Godzilla":
                setAsAlarm(R.raw.movies_godzilla);
                break;
            case "Two and a Half Men":
                setAsAlarm(R.raw.movies_twohalfmen);
                break;
            case "Oompaloompa":
                setAsAlarm(R.raw.movies_oompaloompa);
                break;
            case "Dr. Dre The Next Episode":
                setAsAlarm(R.raw.thug_drdre_snoop);
                break;
            case "Wazzup":
                setAsAlarm(R.raw.movies_wazzup);
                break;
            case "GTA":
                setAsAlarm(R.raw.thug_gta);
                break;
        }
    }

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

        String path = Environment.getExternalStorageDirectory().getPath() + "/SoundBoardX/Ringtones/";

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

        String path = Environment.getExternalStorageDirectory().getPath() + "/SoundBoardX/Notifications/";

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

        String path = Environment.getExternalStorageDirectory().getPath() + "/SoundBoardX/Alarms/";

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
                .make(itemView.getRootView().findViewById(R.id.coordinator), "Ringtone saved", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(sbView.getContext(), R.color.colorPrimaryDark));
        TextView snackTV = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        new Utils().paintThis(snackTV);
        snackbar.show();
    }

    private void notificationSnack() {
        Snackbar snackbar = Snackbar
                .make(itemView.getRootView().findViewById(R.id.coordinator), "Ringtone saved", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(sbView.getContext(), R.color.colorPrimaryDark));
        TextView snackTV = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        new Utils().paintThis(snackTV);
        snackbar.show();
    }

    private void alarmSnack() {
        Snackbar snackbar = Snackbar
                .make(itemView.getRootView().findViewById(R.id.coordinator), "Ringtone saved", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(sbView.getContext(), R.color.colorPrimaryDark));
        TextView snackTV = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        new Utils().paintThis(snackTV);
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
            case "Dr. Dre The Next Episode":
                particle.setAnimationCows();
                break;
            case "Wazzup":
                particle.setAnimationCows();
                break;
            case "GTA":
                particle.setAnimationCows();
                break;
//                    case "":
//                        particle.setAnimation();
//                        break;
        }
    }
}
