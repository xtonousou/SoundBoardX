package com.xtonousou.soundboard;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;

import com.plattysoft.leonids.ParticleSystem;

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> {
	private static final String TAG = "SoundAdapter";
	private static final Integer WRITE_EXST = 0x1;

	private ArrayList<Sound> sounds;
	private boolean favoritesOnly = false;
	private boolean showAnimations = true;

	public SoundAdapter(ArrayList<Sound> soundArray) {
		sounds = soundArray;
	}

	public void onlyShowFavorites() {
		favoritesOnly = true;
		for (Sound sound : new ArrayList<>(sounds)) {
			if (!sound.getFavorite()) {
				notifyItemRemoved(sounds.indexOf(sound));
				sounds.remove(sound);
			}
		}
	}

	public void showAllSounds(Context context) {
		favoritesOnly = false;
		sounds = SoundStore.getAllSounds(context);
		notifyDataSetChanged();
	}

	public void setShowAnimations(boolean anim) {
		showAnimations = anim;
	}

	public boolean isShowAnimations() {
		return showAnimations;
	}

	public boolean isFavoritesOnly() {
		return favoritesOnly;
	}


	@Override
	public SoundAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Create a new view
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_card, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		holder.title.setText(sounds.get(position).getName());
		holder.itemView.setOnClickListener(new View.OnClickListener() {

			/**
			 *  Animates each holder. Checks name and decides.
			 *  @param holder The Viewholder.
             */
			public void animate(ViewHolder holder) {
				switch (holder.title.getText().toString()) {
					default:
						Log.e(TAG, "No animation is set for " + holder.title.getText().toString());
						break;
					case "Nigger's satisfaction":
						holder.setAnimationNigga();
						break;
					case "Hitmarker":
						holder.setAnimationHitmarkers();
						break;
					case "It' s 04:20 meng…":
						holder.setAnimationWeed();
						break;
					case "420 blaze it faggot":
						holder.setAnimationWeed();
						break;
					case "Blurp":
						holder.setAnimationWaterDrops();
						break;
					case "Adam!?!":
						holder.setAnimationSmoke();
						break;
					case "Allahu Akbar":
						holder.setAnimationISIS();
						break;
					case "Damn son!":
						holder.setAnimationMLG();
						break;
					case "Bane: Darude Sandstorm":
						holder.setAnimationBane();
						break;
					case "Bearcum!!!":
						holder.setAnimationMLG();
						break;
					case "What is going on here?":
						holder.setAnimationRetard();
						break;
					case "Πετράκης: Fuck her right in the pussy":
						holder.setAnimationBalls();
						break;
					case "Black kid on fire":
						holder.setAnimationNigga();
						break;
					case "Σα τον Κάτμαν μ' έκανες!":
						holder.setAnimationBart();
						break;
					case "Hmm, it's dick o'clock":
						holder.setAnimationDickClock();
						break;
					case "Russian father":
						holder.setAnimationRussian();
						break;
					case "Fichtl's Lied":
						holder.setAnimationFluteWithMusicNote();
						break;
					case "Buffed Blowjob":
						holder.setAnimationBananas();
						break;
					case "Hey that' s pretty good!":
						holder.setAnimationDubz();
						break;
					case "xT: Γαμώ τον Χριστό σου!":
						holder.setAnimationWeed();
						break;
					case "I' m your father!":
						holder.setAnimationVader();
						break;
					case "Give it to me!":
						holder.setAnimationBananas();
						break;
					case "Κοτσολάρη: Πίπα":
						holder.setAnimationGollum();
						break;
					case "Fun":
						holder.setAnimationKazoo();
						break;
					case "Kazoo":
						holder.setAnimationKazoo();
						break;
					case "Immigrants cause cancer":
						holder.setAnimationTrump();
						break;
					case "Maybe we could invite some women?":
						holder.setAnimationSkyrimPoop();
						break;
					case "Trap Theme":
						holder.setAnimationTrap();
						break;
					case "Γαμώ τα καντήλια μου όλα!":
						holder.setAnimationBart();
						break;
					case "Weird laugh":
						holder.setAnimation4chan();
						break;
					case "Thank you…":
						holder.setAnimationTrap();
						break;
					case "Make your dreams come true!":
						holder.setAnimationMan();
						break;
					case "LOTR MLG":
						holder.setAnimationMLG();
						break;
					case "Come here my pet Russian…":
						holder.setAnimationRussian();
						break;
					case "This is my masterpiece!":
						holder.setAnimationCaptcha();
						break;
					case "Do you want my milk?":
						holder.setAnimationMilk();
						break;
					case "My mooscles are getting bigger!":
						holder.setAnimationMan();
						break;
					case "I'm growing stronker!":
						holder.setAnimationMan();
						break;
					case "Yiss":
						holder.setAnimationMan();
						break;
					case "STFU":
						holder.setAnimationSTFU();
						break;
					case "Nigga is cumming…":
						holder.setAnimationNigga();
						break;
					case "Sup nigga, wanna talk some shit?":
						holder.setAnimationNigga();
						break;
					case "Surprise motherfucker!":
						holder.setAnimationNigga();
						break;
					case "Πετρόπουλος: Action!":
						holder.setAnimationGabe();
						break;
					case "Shaved pussy…":
						holder.setAnimationCats();
						break;
					case "Seven vaginias…":
						holder.setAnimationCats();
						break;
					case "Oh my!":
						holder.setAnimationTrap();
						break;
					case "Oh Yeaaaah!":
						holder.setAnimationParticles();
						break;
					case "Penis, penis and penis":
						holder.setAnimationTrap();
						break;
					case "Αγγούρι στον κώλο σου…":
						holder.setAnimationPokemon();
						break;
					case "Excuse me, I have some pussyhair on me!":
						holder.setAnimationCats();
						break;
					case "Retardation Theme":
						holder.setAnimationRetard();
						break;
					case "Cyka Blyat's Middlefinger":
						holder.setAnimationRussian();
						break;
					case "I'm the scatman…":
						holder.setAnimationScat();
						break;
					case "Why don't you show us that fine pussy?":
						holder.setAnimationSkyrimPoop();
						break;
					case "Shutdown, glitched…":
						holder.setAnimationRetard();
						break;
					case "What manner of sissyness, is this?":
						holder.setAnimationSkyrimPoop();
						break;
					case "You spin me round":
						holder.setAnimationTrap();
						break;
					case "Squeaker":
						holder.setAnimationRetard();
						break;
					case "Our courage will pull us through":
						holder.setAnimationPokemon();
						break;
					case "That's a 10!":
						holder.setAnimationSpoon();
						break;
					case "Illuminati":
						holder.setAnimationIlluminati();
						break;
					case "WOW":
						holder.setAnimationMLG();
						break;
					case "Richard Stallman":
						holder.setAnimationGNU();
						break;
					case "Τσικλίδης: Λες και είναι τσόντα!":
						holder.setAnimationCows();
						break;
					case "What else?":
						holder.setAnimationRetard();
						break;
					case "Zodiac":
						holder.setAnimation4chan();
						break;
					case "Shabadaa shabadabadabaa…":
						holder.setAnimationCats();
						break;
					case "xT: Ρε καριόλη;":
						holder.setAnimationWeed();
						break;
					case "Κιάμος: Ε!":
						holder.setAnimationSeagulls();
						break;
					case "Κιάμος: Χατζάρα":
						holder.setAnimationSeagulls();
						break;
					case "Κιάμος: Τί κοιτάς ρε μαλάκα":
						holder.setAnimationSeagulls();
						break;
					case "U WOT M8":
						holder.setAnimationFluteWithMusicNote();
						break;
					case "Cow":
						holder.setAnimationCows();
						break;
//                    case "":
//                        holder.setAnimation();
//                        break;
				}
			}

			/**
			 *  Another event for EventBus.
			 *  @param event Whatever event.
             */
			public void onEvent(String event) {
				EventBus.getDefault().unregister(this);
				notifyItemChanged(position);
			}

			@Override
			public void onClick(View view) {
				try {
					if (EventBus.getDefault().isRegistered(this)) {
						EventBus.getDefault().unregister(this);
					} else {
						EventBus.getDefault().register(this);
						if (showAnimations) {
							animate(holder);
						}
						EventBus.getDefault().post(sounds.get(holder.getAdapterPosition()));
						EventBus.getDefault().unregister(this);
						notifyItemChanged(position);
					}
				} catch (EventBusException e) {
					notifyDataSetChanged();
					System.err.println(e.getMessage());
				}
			}
		});

		boolean isFavorite = sounds.get(position).getFavorite();
		holder.favButton.setImageResource(isFavorite
				? R.drawable.ic_favorite_white_24dp
				: R.drawable.ic_favorite_outline_white_24dp);

		holder.favButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean newFavStatus = !sounds.get(holder.getAdapterPosition()).getFavorite();
				sounds.get(holder.getAdapterPosition()).setFavorite(newFavStatus);

				if (newFavStatus) {
					((ImageButton) v).setImageResource(R.drawable.ic_favorite_white_24dp);
					v.setContentDescription(v.getContext().getString(R.string.fav_desc));
				} else {
					((ImageButton) v).setImageResource(R.drawable.ic_favorite_outline_white_24dp);
					v.setContentDescription(v.getContext().getString(R.string.not_fav_desc));
				}

				if (favoritesOnly) {
					// Remove from the list.
					sounds.remove(sounds.get(holder.getAdapterPosition()));
					notifyItemRemoved(holder.getAdapterPosition());
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return sounds.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,
			MenuItem.OnMenuItemClickListener {
		public TextView title;
		public ImageButton favButton;

		public ViewHolder(View v) {
			super(v);
			title = (TextView) v.findViewById(R.id.title);
			favButton = (ImageButton) v.findViewById(R.id.fav_button);

			itemView.setBackgroundColor((new DayColor(itemView.getContext())).getDayColor());

			v.setOnCreateContextMenuListener(this);

			Typeface font = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Roboto-Regular.ttf");
			title.setTypeface(font);
		}

        @Override
		public void onCreateContextMenu(final ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
			contextMenu.setHeaderTitle("Select action");

			MenuItem setRingtone = contextMenu.add("Set as ringtone");
			MenuItem setNotification = contextMenu.add("Set as notification");
			MenuItem setAlarm = contextMenu.add("Set as alarm");

			setRingtone.setOnMenuItemClickListener(this);
			setNotification.setOnMenuItemClickListener(this);
			setAlarm.setOnMenuItemClickListener(this);
		}

		@Override
		public boolean onMenuItemClick(MenuItem menuItem) {
            String itemName;
            switch (menuItem.getTitle().toString()) {
                default:
                    Log.e(TAG, "onMenuItemClick: menuItem.getTitle().toString()");
                case "Set as ringtone":

                    // Ask for permissions gently, senpai ^_^
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ask(itemView);
                    }

                    createDirIfNotExists("/xSoundBoardHD/Ringtones");

                    itemName = title.getText().toString();

                    switch (itemName) {
                        default:
                            Log.e(TAG, "onMenuItemClick: setAsRingtone()");
                            break;
                        case "Nigga's satisfaction":
                            setAsRingtone(R.raw.a);
                            break;
                        case "Hitmarker":
                            setAsRingtone(R.raw.hitmarker);
                            break;
                        case "It' s 04:20 meng…":
                            setAsRingtone(R.raw.a420);
                            break;
                        case "420 blaze it faggot":
                            setAsRingtone(R.raw.a420blazeit);
                            break;
                        case "Blurp":
                            setAsRingtone(R.raw.blurp);
                            break;
                        case "Adam!?!":
                            setAsRingtone(R.raw.adam);
                            break;
                        case "Allahu Akbar":
                            setAsRingtone(R.raw.allahuakbar);
                            break;
                        case "Damn son!":
                            setAsRingtone(R.raw.damnson);
                            break;
                        case "Bane: Darude Sandstorm":
                            setAsRingtone(R.raw.banesandstorm);
                            break;
                        case "Bearcum!!!":
                            setAsRingtone(R.raw.bearcum);
                            break;
                        case "What is going on here?":
                            setAsRingtone(R.raw.bekfast);
                            break;
                        case "Πετράκης: Fuck her right in the pussy":
                            setAsRingtone(R.raw.fuckherrightinthepussy);
                            break;
                        case "Black kid on fire":
                            setAsRingtone(R.raw.blackkidonfire);
                            break;
                        case "Σα τον Κάτμαν μ' έκανες!":
                            setAsRingtone(R.raw.catman);
                            break;
                        case "Hmm, it's dick o'clock":
                            setAsRingtone(R.raw.dickoclock);
                            break;
                        case "Russian father":
                            setAsRingtone(R.raw.fathercatishungry);
                            break;
                        case "Fichtl's Lied":
                            setAsRingtone(R.raw.fichtlslied);
                            break;
                        case "Buffed Blowjob":
                            setAsRingtone(R.raw.fuckyeah);
                            break;
                        case "Hey that' s pretty good!":
                            setAsRingtone(R.raw.prettygood);
                            break;
                        case "xT: Γαμώ τον Χριστό σου!":
                            setAsRingtone(R.raw.gamwtoxristosou);
                            break;
                        case "I' m your father!":
                            setAsRingtone(R.raw.imyourfather);
                            break;
                        case "Give it to me!":
                            setAsRingtone(R.raw.giveittome);
                            break;
                        case "Κοτσολάρη: Πίπα":
                            setAsRingtone(R.raw.eleni);
                            break;
                        case "Fun":
                            setAsRingtone(R.raw.fun);
                            break;
                        case "Kazoo":
                            setAsRingtone(R.raw.kazoo);
                            break;
                        case "Immigrants cause cancer":
                            setAsRingtone(R.raw.immigrants);
                            break;
                        case "Maybe we could invite some women?":
                            setAsRingtone(R.raw.invitewomen);
                            break;
                        case "Trap Theme":
                            setAsRingtone(R.raw.itsatrap);
                            break;
                        case "Γαμώ τα καντήλια μου όλα!":
                            setAsRingtone(R.raw.kantilia);
                            break;
                        case "Weird laugh":
                            setAsRingtone(R.raw.laugh);
                            break;
                        case "Thank you…":
                            setAsRingtone(R.raw.thankyou);
                            break;
                        case "Make your dreams come true!":
                            setAsRingtone(R.raw.lol);
                            break;
                        case "LOTR MLG":
                            setAsRingtone(R.raw.lotr);
                            break;
                        case "Come here my pet Russian…":
                            setAsRingtone(R.raw.petrussian);
                            break;
                        case "This is my masterpiece!":
                            setAsRingtone(R.raw.masterpiece);
                            break;
                        case "Do you want my milk?":
                            setAsRingtone(R.raw.milkinyourmot);
                            break;
                        case "My mooscles are getting bigger!":
                            setAsRingtone(R.raw.mooscles);
                            break;
                        case "I'm growing stronker!":
                            setAsRingtone(R.raw.stronker);
                            break;
                        case "Yiss":
                            setAsRingtone(R.raw.yiiisss);
                            break;
                        case "STFU":
                            setAsRingtone(R.raw.stfu);
                            break;
                        case "Nigga is cumming…":
                            setAsRingtone(R.raw.niggacum);
                            break;
                        case "Sup nigga, wanna talk some shit?":
                            setAsRingtone(R.raw.niggers);
                            break;
                        case "Surprise motherfucker!":
                            setAsRingtone(R.raw.surprise);
                            break;
                        case "Πετρόπουλος: Action!":
                            setAsRingtone(R.raw.nikos);
                            break;
                        case "Shaved pussy…":
                            setAsRingtone(R.raw.shaved);
                            break;
                        case "Seven vaginias…":
                            setAsRingtone(R.raw.sevenvaginas);
                            break;
                        case "Oh my!":
                            setAsRingtone(R.raw.ohmy);
                            break;
                        case "Oh Yeaaaah!":
                            setAsRingtone(R.raw.ohyeaah);
                            break;
                        case "Penis, penis and penis":
                            setAsRingtone(R.raw.penis);
                            break;
                        case "Our courage will pull us through":
                            setAsRingtone(R.raw.pokemonaggouri);
                            break;
                        case "Excuse me, I have some pussyhair on me!":
                            setAsRingtone(R.raw.pussyhair);
                            break;
                        case "Retardation Theme":
                            setAsRingtone(R.raw.retardation);
                            break;
                        case "Cyka Blyat's Middlefinger":
                            setAsRingtone(R.raw.russianmiddlefinger);
                            break;
                        case "I'm the scatman…":
                            setAsRingtone(R.raw.scatman);
                            break;
                        case "Why don't you show us that fine pussy?":
                            setAsRingtone(R.raw.showthepussy);
                            break;
                        case "Shutdown, glitched…":
                            setAsRingtone(R.raw.shutdownglitched);
                            break;
                        case "What manner of sissyness, is this?":
                            setAsRingtone(R.raw.sissiness);
                            break;
                        case "You spin me round":
                            setAsRingtone(R.raw.spinme);
                            break;
                        case "Squeaker":
                            setAsRingtone(R.raw.squeeek);
                            break;
                        case "That's a 10!":
                            setAsRingtone(R.raw.thatsa10);
                            break;
                        case "Illuminati":
                            setAsRingtone(R.raw.illuminati);
                            break;
                        case "WOW":
                            setAsRingtone(R.raw.wow);
                            break;
                        case "Richard Stallman":
                            setAsRingtone(R.raw.windows);
                            break;
                        case "Τσικλίδης: Λες και είναι τσόντα!":
                            setAsRingtone(R.raw.tsonta);
                            break;
                        case "What else?":
                            setAsRingtone(R.raw.whatelse);
                            break;
                        case "Zodiac":
                            setAsRingtone(R.raw.zodiac);
                            break;
                        case "Shabadaa shabadabadabaa…":
                            setAsRingtone(R.raw.sabada);
                            break;
                        case "xT: Ρε καριόλη;":
                            setAsRingtone(R.raw.karioli);
                            break;
                        case "Κιάμος: Ε!":
                            setAsRingtone(R.raw.e);
                            break;
                        case "Κιάμος: Χατζάρα":
                            setAsRingtone(R.raw.tigelasre);
                            break;
                        case "Κιάμος: Τί κοιτάς ρε μαλάκα":
                            setAsRingtone(R.raw.tikoitasremlk);
                            break;
                        case "U WOT M8":
                            setAsRingtone(R.raw.uwotmate);
                            break;
                        case "Cow":
                            setAsRingtone(R.raw.cow);
                            break;
//                    case "":
//                        setAsRingtone(R.raw.);
//                        break;
                    }
                    ringtoneToast();
                    break;
                case "Set as notification":

                    // Ask for permissions gently, senpai ^_^
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ask(itemView);
                    }

                    createDirIfNotExists("/xSoundBoardHD/Notifications");

                    itemName = title.getText().toString();

                    switch (itemName) {
                        default:
                            Log.e(TAG, "onMenuItemClick: setAsNotification()");
                            break;
                        case "Nigga's satisfaction":
                            setAsNotification(R.raw.a);
                            break;
                        case "Hitmarker":
                            setAsNotification(R.raw.hitmarker);
                            break;
                        case "It' s 04:20 meng…":
                            setAsNotification(R.raw.a420);
                            break;
                        case "420 blaze it faggot":
                            setAsNotification(R.raw.a420blazeit);
                            break;
                        case "Blurp":
                            setAsNotification(R.raw.blurp);
                            break;
                        case "Adam!?!":
                            setAsNotification(R.raw.adam);
                            break;
                        case "Allahu Akbar":
                            setAsNotification(R.raw.allahuakbar);
                            break;
                        case "Damn son!":
                            setAsNotification(R.raw.damnson);
                            break;
                        case "Bane: Darude Sandstorm":
                            setAsNotification(R.raw.banesandstorm);
                            break;
                        case "Bearcum!!!":
                            setAsNotification(R.raw.bearcum);
                            break;
                        case "What is going on here?":
                            setAsNotification(R.raw.bekfast);
                            break;
                        case "Πετράκης: Fuck her right in the pussy":
                            setAsNotification(R.raw.fuckherrightinthepussy);
                            break;
                        case "Black kid on fire":
                            setAsNotification(R.raw.blackkidonfire);
                            break;
                        case "Σα τον Κάτμαν μ' έκανες!":
                            setAsNotification(R.raw.catman);
                            break;
                        case "Hmm, it's dick o'clock":
                            setAsNotification(R.raw.dickoclock);
                            break;
                        case "Russian father":
                            setAsNotification(R.raw.fathercatishungry);
                            break;
                        case "Fichtl's Lied":
                            setAsNotification(R.raw.fichtlslied);
                            break;
                        case "Buffed Blowjob":
                            setAsNotification(R.raw.fuckyeah);
                            break;
                        case "Hey that' s pretty good!":
                            setAsNotification(R.raw.prettygood);
                            break;
                        case "xT: Γαμώ τον Χριστό σου!":
                            setAsNotification(R.raw.gamwtoxristosou);
                            break;
                        case "I' m your father!":
                            setAsNotification(R.raw.imyourfather);
                            break;
                        case "Give it to me!":
                            setAsNotification(R.raw.giveittome);
                            break;
                        case "Κοτσολάρη: Πίπα":
                            setAsNotification(R.raw.eleni);
                            break;
                        case "Fun":
                            setAsNotification(R.raw.fun);
                            break;
                        case "Kazoo":
                            setAsNotification(R.raw.kazoo);
                            break;
                        case "Immigrants cause cancer":
                            setAsNotification(R.raw.immigrants);
                            break;
                        case "Maybe we could invite some women?":
                            setAsNotification(R.raw.invitewomen);
                            break;
                        case "Trap Theme":
                            setAsNotification(R.raw.itsatrap);
                            break;
                        case "Γαμώ τα καντήλια μου όλα!":
                            setAsNotification(R.raw.kantilia);
                            break;
                        case "Weird laugh":
                            setAsNotification(R.raw.laugh);
                            break;
                        case "Thank you…":
                            setAsNotification(R.raw.thankyou);
                            break;
                        case "Make your dreams come true!":
                            setAsNotification(R.raw.lol);
                            break;
                        case "LOTR MLG":
                            setAsNotification(R.raw.lotr);
                            break;
                        case "Come here my pet Russian…":
                            setAsNotification(R.raw.petrussian);
                            break;
                        case "This is my masterpiece!":
                            setAsNotification(R.raw.masterpiece);
                            break;
                        case "Do you want my milk?":
                            setAsNotification(R.raw.milkinyourmot);
                            break;
                        case "My mooscles are getting bigger!":
                            setAsNotification(R.raw.mooscles);
                            break;
                        case "I'm growing stronker!":
                            setAsNotification(R.raw.stronker);
                            break;
                        case "Yiss":
                            setAsNotification(R.raw.yiiisss);
                            break;
                        case "STFU":
                            setAsNotification(R.raw.stfu);
                            break;
                        case "Nigga is cumming…":
                            setAsNotification(R.raw.niggacum);
                            break;
                        case "Sup nigga, wanna talk some shit?":
                            setAsNotification(R.raw.niggers);
                            break;
                        case "Surprise motherfucker!":
                            setAsNotification(R.raw.surprise);
                            break;
                        case "Πετρόπουλος: Action!":
                            setAsNotification(R.raw.nikos);
                            break;
                        case "Shaved pussy…":
                            setAsNotification(R.raw.shaved);
                            break;
                        case "Seven vaginias…":
                            setAsNotification(R.raw.sevenvaginas);
                            break;
                        case "Oh my!":
                            setAsNotification(R.raw.ohmy);
                            break;
                        case "Oh Yeaaaah!":
                            setAsNotification(R.raw.ohyeaah);
                            break;
                        case "Penis, penis and penis":
                            setAsNotification(R.raw.penis);
                            break;
                        case "Our courage will pull us through":
                            setAsNotification(R.raw.pokemonaggouri);
                            break;
                        case "Excuse me, I have some pussyhair on me!":
                            setAsNotification(R.raw.pussyhair);
                            break;
                        case "Retardation Theme":
                            setAsNotification(R.raw.retardation);
                            break;
                        case "Cyka Blyat's Middlefinger":
                            setAsNotification(R.raw.russianmiddlefinger);
                            break;
                        case "I'm the scatman…":
                            setAsNotification(R.raw.scatman);
                            break;
                        case "Why don't you show us that fine pussy?":
                            setAsNotification(R.raw.showthepussy);
                            break;
                        case "Shutdown, glitched…":
                            setAsNotification(R.raw.shutdownglitched);
                            break;
                        case "What manner of sissyness, is this?":
                            setAsNotification(R.raw.sissiness);
                            break;
                        case "You spin me round":
                            setAsNotification(R.raw.spinme);
                            break;
                        case "Squeaker":
                            setAsNotification(R.raw.squeeek);
                            break;
                        case "That's a 10!":
                            setAsNotification(R.raw.thatsa10);
                            break;
                        case "Illuminati":
                            setAsNotification(R.raw.illuminati);
                            break;
                        case "WOW":
                            setAsNotification(R.raw.wow);
                            break;
                        case "Richard Stallman":
                            setAsNotification(R.raw.windows);
                            break;
                        case "Τσικλίδης: Λες και είναι τσόντα!":
                            setAsNotification(R.raw.tsonta);
                            break;
                        case "What else?":
                            setAsNotification(R.raw.whatelse);
                            break;
                        case "Zodiac":
                            setAsNotification(R.raw.zodiac);
                            break;
                        case "Shabadaa shabadabadabaa…":
                            setAsNotification(R.raw.sabada);
                            break;
                        case "xT: Ρε καριόλη;":
                            setAsNotification(R.raw.karioli);
                            break;
                        case "Κιάμος: Ε!":
                            setAsNotification(R.raw.e);
                            break;
                        case "Κιάμος: Χατζάρα":
                            setAsNotification(R.raw.tigelasre);
                            break;
                        case "Κιάμος: Τί κοιτάς ρε μαλάκα":
                            setAsNotification(R.raw.tikoitasremlk);
                            break;
                        case "U WOT M8":
                            setAsNotification(R.raw.uwotmate);
                            break;
                        case "Cow":
                            setAsNotification(R.raw.cow);
                            break;
//                    case "":
//                        setAsNotification(R.raw.);
//                        break;
                    }
                    notificationToast();
                    break;
                case "Set as alarm":

                    // Ask for permissions gently, senpai ^_^
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ask(itemView);
                    }

                    createDirIfNotExists("/xSoundBoardHD/Alarms");

                    itemName = title.getText().toString();

                    switch (itemName) {
                        default:
                            Log.e(TAG, "onMenuItemClick: setAsAlarm()");
                            break;
                        case "Nigga's satisfaction":
                            setAsAlarm(R.raw.a);
                            break;
                        case "Hitmarker":
                            setAsAlarm(R.raw.hitmarker);
                            break;
                        case "It' s 04:20 meng…":
                            setAsAlarm(R.raw.a420);
                            break;
                        case "420 blaze it faggot":
                            setAsAlarm(R.raw.a420blazeit);
                            break;
                        case "Blurp":
                            setAsAlarm(R.raw.blurp);
                            break;
                        case "Adam!?!":
                            setAsAlarm(R.raw.adam);
                            break;
                        case "Allahu Akbar":
                            setAsAlarm(R.raw.allahuakbar);
                            break;
                        case "Damn son!":
                            setAsAlarm(R.raw.damnson);
                            break;
                        case "Bane: Darude Sandstorm":
                            setAsAlarm(R.raw.banesandstorm);
                            break;
                        case "Bearcum!!!":
                            setAsAlarm(R.raw.bearcum);
                            break;
                        case "What is going on here?":
                            setAsAlarm(R.raw.bekfast);
                            break;
                        case "Πετράκης: Fuck her right in the pussy":
                            setAsAlarm(R.raw.fuckherrightinthepussy);
                            break;
                        case "Black kid on fire":
                            setAsAlarm(R.raw.blackkidonfire);
                            break;
                        case "Σα τον Κάτμαν μ' έκανες!":
                            setAsAlarm(R.raw.catman);
                            break;
                        case "Hmm, it's dick o'clock":
                            setAsAlarm(R.raw.dickoclock);
                            break;
                        case "Russian father":
                            setAsAlarm(R.raw.fathercatishungry);
                            break;
                        case "Fichtl's Lied":
                            setAsAlarm(R.raw.fichtlslied);
                            break;
                        case "Buffed Blowjob":
                            setAsAlarm(R.raw.fuckyeah);
                            break;
                        case "Hey that' s pretty good!":
                            setAsAlarm(R.raw.prettygood);
                            break;
                        case "xT: Γαμώ τον Χριστό σου!":
                            setAsAlarm(R.raw.gamwtoxristosou);
                            break;
                        case "I' m your father!":
                            setAsAlarm(R.raw.imyourfather);
                            break;
                        case "Give it to me!":
                            setAsAlarm(R.raw.giveittome);
                            break;
                        case "Κοτσολάρη: Πίπα":
                            setAsAlarm(R.raw.eleni);
                            break;
                        case "Fun":
                            setAsAlarm(R.raw.fun);
                            break;
                        case "Kazoo":
                            setAsAlarm(R.raw.kazoo);
                            break;
                        case "Immigrants cause cancer":
                            setAsAlarm(R.raw.immigrants);
                            break;
                        case "Maybe we could invite some women?":
                            setAsAlarm(R.raw.invitewomen);
                            break;
                        case "Trap Theme":
                            setAsAlarm(R.raw.itsatrap);
                            break;
                        case "Γαμώ τα καντήλια μου όλα!":
                            setAsAlarm(R.raw.kantilia);
                            break;
                        case "Weird laugh":
                            setAsAlarm(R.raw.laugh);
                            break;
                        case "Thank you…":
                            setAsAlarm(R.raw.thankyou);
                            break;
                        case "Make your dreams come true!":
                            setAsAlarm(R.raw.lol);
                            break;
                        case "LOTR MLG":
                            setAsAlarm(R.raw.lotr);
                            break;
                        case "Come here my pet Russian…":
                            setAsAlarm(R.raw.petrussian);
                            break;
                        case "This is my masterpiece!":
                            setAsAlarm(R.raw.masterpiece);
                            break;
                        case "Do you want my milk?":
                            setAsAlarm(R.raw.milkinyourmot);
                            break;
                        case "My mooscles are getting bigger!":
                            setAsAlarm(R.raw.mooscles);
                            break;
                        case "I'm growing stronker!":
                            setAsAlarm(R.raw.stronker);
                            break;
                        case "Yiss":
                            setAsAlarm(R.raw.yiiisss);
                            break;
                        case "STFU":
                            setAsAlarm(R.raw.stfu);
                            break;
                        case "Nigga is cumming…":
                            setAsAlarm(R.raw.niggacum);
                            break;
                        case "Sup nigga, wanna talk some shit?":
                            setAsAlarm(R.raw.niggers);
                            break;
                        case "Surprise motherfucker!":
                            setAsAlarm(R.raw.surprise);
                            break;
                        case "Πετρόπουλος: Action!":
                            setAsAlarm(R.raw.nikos);
                            break;
                        case "Shaved pussy…":
                            setAsAlarm(R.raw.shaved);
                            break;
                        case "Seven vaginias…":
                            setAsAlarm(R.raw.sevenvaginas);
                            break;
                        case "Oh my!":
                            setAsAlarm(R.raw.ohmy);
                            break;
                        case "Oh Yeaaaah!":
                            setAsAlarm(R.raw.ohyeaah);
                            break;
                        case "Penis, penis and penis":
                            setAsAlarm(R.raw.penis);
                            break;
                        case "Our courage will pull us through":
                            setAsAlarm(R.raw.pokemonaggouri);
                            break;
                        case "Excuse me, I have some pussyhair on me!":
                            setAsAlarm(R.raw.pussyhair);
                            break;
                        case "Retardation Theme":
                            setAsAlarm(R.raw.retardation);
                            break;
                        case "Cyka Blyat's Middlefinger":
                            setAsAlarm(R.raw.russianmiddlefinger);
                            break;
                        case "I'm the scatman…":
                            setAsAlarm(R.raw.scatman);
                            break;
                        case "Why don't you show us that fine pussy?":
                            setAsAlarm(R.raw.showthepussy);
                            break;
                        case "Shutdown, glitched…":
                            setAsAlarm(R.raw.shutdownglitched);
                            break;
                        case "What manner of sissyness, is this?":
                            setAsAlarm(R.raw.sissiness);
                            break;
                        case "You spin me round":
                            setAsAlarm(R.raw.spinme);
                            break;
                        case "Squeaker":
                            setAsAlarm(R.raw.squeeek);
                            break;
                        case "That's a 10!":
                            setAsAlarm(R.raw.thatsa10);
                            break;
                        case "Illuminati":
                            setAsAlarm(R.raw.illuminati);
                            break;
                        case "WOW":
                            setAsAlarm(R.raw.wow);
                            break;
                        case "Richard Stallman":
                            setAsAlarm(R.raw.windows);
                            break;
                        case "Τσικλίδης: Λες και είναι τσόντα!":
                            setAsAlarm(R.raw.tsonta);
                            break;
                        case "What else?":
                            setAsAlarm(R.raw.whatelse);
                            break;
                        case "Zodiac":
                            setAsAlarm(R.raw.zodiac);
                            break;
                        case "Shabadaa shabadabadabaa…":
                            setAsAlarm(R.raw.sabada);
                            break;
                        case "xT: Ρε καριόλη;":
                            setAsAlarm(R.raw.karioli);
                            break;
                        case "Κιάμος: Ε!":
                            setAsAlarm(R.raw.e);
                            break;
                        case "Κιάμος: Χατζάρα":
                            setAsAlarm(R.raw.tigelasre);
                            break;
                        case "Κιάμος: Τί κοιτάς ρε μαλάκα":
                            setAsAlarm(R.raw.tikoitasremlk);
                            break;
                        case "U WOT M8":
                            setAsAlarm(R.raw.uwotmate);
                            break;
                        case "Cow":
                            setAsAlarm(R.raw.cow);
                            break;
//                    case "":
//                        setAsAlarm(R.raw.);
//                        break;
                    }
                    alarmToast();
                    break;
            }
			return true;
		}

		/**
		 * Asks user for permission using the new permission model.
		 * @param permission	Manifest.permission.SOME_PERMISSION.
		 * @param requestCode	private static final Integer SOME_PERMISSION = 0xSOME_INTEGER;.
         * @param context		Context from view.
         */
		private void askForPermission(String permission, Integer requestCode, Context context) {
			if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
				// Should we show an explanation?
				if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
					/**
					 *  This is called if user has denied the permission before
					 *  In this case I am just asking the permission again
 					 */
					ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, requestCode);
				} else {
					ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, requestCode);
				}
			}
		}

		/**
		 *  Calls askForPermission(String, Integer, Context) and decides.
		 *  Calls onRequestPermissionsResult(Context, View, String)
		 *  @param v	View.
         */
		public void ask(View v){
			askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXST, v.getContext());
			onRequestPermissionsResult(v.getContext(), v, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		}

		/**
		 *  Checks user permission decision and notifies him with a snackbar.
		 *  @param context		The Context.
		 *  @param view			The View.
         *  @param permission	Manifest.permission.SOME_PERMISSION
         */
		public void onRequestPermissionsResult(Context context, View view, String permission) {
			if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
				Snackbar.make(view, "Permission granted", Snackbar.LENGTH_SHORT).show();
			} else {
				Snackbar.make(view, "Permission denied", Snackbar.LENGTH_SHORT).show();
			}
		}

		/**
		 *  Makes a toast.
		 *  Method called if user set a sound as ringtone.
		 */
		public void ringtoneToast() {
			Toast.makeText(itemView.getContext(), "Ringtone Saved", Toast.LENGTH_SHORT).show();
		}

		/**
		 *  Makes a toast.
		 *  Method called if user set a sound as notification.
		 */
		public void notificationToast() {
			Toast.makeText(itemView.getContext(), "Notification Saved", Toast.LENGTH_SHORT).show();
		}

        /**
         *  Makes a toast.
         *  Method called if user set a sound as alarm.
         */
        public void alarmToast() {
            Toast.makeText(itemView.getContext(), "Alarm Saved", Toast.LENGTH_SHORT).show();
        }

		/**
		 *  All methods below use Leonids library and make particles with drawables (R.drawable.something)
		 *  Methods used in animate() method located at SoundAdapter.java. See onClick.
		 */
		public void setAnimationHitmarkers() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 10, R.drawable.hitmarker, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
		}

		public void setAnimationSTFU() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 10, R.drawable.stfu, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
		}

		public void setAnimationDubz() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 10, R.drawable.dubz, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
		}

		public void setAnimationSmoke() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 25, R.drawable.smokeone, 5000)
					.setAcceleration(0.001f, 80)
					.setScaleRange(0.5f, 1.3f)
					.setSpeedRange(0.05f, 0.8f)
					.setFadeOut(250, new AnticipateOvershootInterpolator())
					.oneShot(itemView, 25);
		}

		public void setAnimationParticles() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 25, R.drawable.particle, 5000)
					.setAcceleration(0.001f, 80)
					.setScaleRange(-1,2)
					.setSpeedRange(0.05f, 0.8f)
					.setFadeOut(250, new AnticipateOvershootInterpolator())
					.oneShot(itemView, 25);
		}

		public void setAnimationBananas() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 25, R.drawable.banana, 5000)
					.setAcceleration(0.001f, 80)
					.setScaleRange(-1,2)
					.setSpeedRange(0.05f, 0.8f)
					.setFadeOut(250, new AnticipateOvershootInterpolator())
					.oneShot(itemView, 25);
		}

		public void setAnimationSeagulls() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 15, R.drawable.seagull, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setFadeOut(500)
					.oneShot(itemView, 15);
		}

		public void setAnimationTrump() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 15, R.drawable.trump, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setFadeOut(500)
					.oneShot(itemView, 15);
		}

		public void setAnimationMan() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 15, R.drawable.man, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setFadeOut(500)
					.oneShot(itemView, 15);
		}

		public void setAnimationBalls() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.ball, 5000)
					.setSpeedRange(0.2f, 0.4f)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		public void setAnimation4chan() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.fourchan, 5000)
					.setSpeedRange(0.2f, 0.4f)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		public void setAnimationBane() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.bane, 5000)
					.setSpeedRange(0.2f, 0.4f)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		public void setAnimationWeed() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 5, R.drawable.smokeone, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 5);
			new ParticleSystem(act, 10, R.drawable.weed, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
		}

		public void setAnimationTrap() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 5, R.drawable.girl, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 5);
			new ParticleSystem(act, 10, R.drawable.banana, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
		}

		public void setAnimationDickClock() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 5, R.drawable.banana, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 5);
			new ParticleSystem(act, 10, R.drawable.clock, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
		}

		public void setAnimationVader() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.vader, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setRotationSpeed(150)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		public void setAnimationKazoo() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.kazoo, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setRotationSpeed(150)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		public void setAnimationMilk() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.milk, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setRotationSpeed(125)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		public void setAnimationScat() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.poop, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setRotationSpeed(150)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		public void setAnimationCaptcha() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.captcha, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setRotationSpeed(150)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		public void setAnimationFluteWithMusicNote() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 5, R.drawable.musicnote, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 5);
			new ParticleSystem(act, 10, R.drawable.flute, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
		}

		public void setAnimationMLG() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 10, R.drawable.dwi, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
			new ParticleSystem(act, 35, R.drawable.illuminati, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setFadeOut(500)
					.setRotationSpeed(300)
					.oneShot(itemView, 10);
			new ParticleSystem(act, 35, R.drawable.joint, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setFadeOut(500)
					.setRotationSpeed(100)
					.oneShot(itemView, 20);
			new ParticleSystem(act, 35, R.drawable.mtdew, 5000)
					.setSpeedRange(0.1f, 0.2f)
					.setFadeOut(500)
					.setRotationSpeed(50)
					.oneShot(itemView, 5);
		}

		public void setAnimationPokemon() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 10, R.drawable.bulbasaur, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
			new ParticleSystem(act, 35, R.drawable.charmander, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setFadeOut(500)
					.setRotationSpeed(300)
					.oneShot(itemView, 10);
			new ParticleSystem(act, 35, R.drawable.pikachu, 5000)
					.setSpeedRange(0.1f, 0.3f)
					.setFadeOut(500)
					.setRotationSpeed(100)
					.oneShot(itemView, 20);
			new ParticleSystem(act, 35, R.drawable.squirtle, 5000)
					.setSpeedRange(0.1f, 0.2f)
					.setFadeOut(500)
					.setRotationSpeed(50)
					.oneShot(itemView, 5);
		}

		public void setAnimationNigga() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 10, R.drawable.watermelon, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
			new ParticleSystem(act, 30, R.drawable.kfc, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 30);
			new ParticleSystem(act, 20, R.drawable.bike, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 20);
		}

		public void setAnimationISIS() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 10, R.drawable.isis, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
			new ParticleSystem(act, 10, R.drawable.knife, 5000)
					.setAcceleration(0.001f, 45)
					.setSpeedRange(0.1f, 2)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
			new ParticleSystem(act, 30, R.drawable.bomb, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 30);
			new ParticleSystem(act, 20, R.drawable.cfour, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 20);
		}

		public void setAnimationSkyrimPoop() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 30, R.drawable.skyrim, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setScaleRange(1, 2)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 30);
			new ParticleSystem(act, 10, R.drawable.poop, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
		}

		public void setAnimationRussian() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 10, R.drawable.vodka, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
			new ParticleSystem(act, 30, R.drawable.csgo, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 30);
			new ParticleSystem(act, 20, R.drawable.adidas, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 20);
		}

		public void setAnimationGNU() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.gnu, 7000)
					.setSpeedRange(0.05f, 0.3f)
					.setFadeOut(500)
					.setRotationSpeed(150)
					.oneShot(itemView, 35);
		}

		public void setAnimationGabe() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.gabe, 7000)
					.setSpeedRange(0.05f, 0.3f)
					.setFadeOut(500)
					.setRotationSpeed(150)
					.oneShot(itemView, 35);
		}

		public void setAnimationCats() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 20, R.drawable.cat, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 20);
		}

		public void setAnimationSpoon() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 20, R.drawable.spoon, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setRotationSpeed(100)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 20);
		}

		public void setAnimationGollum() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.gollum, 2500)
					.setAcceleration(0.001f, 90)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 35);
		}

		public void setAnimationIlluminati() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.illuminati, 5000)
					.setSpeedRange(0.2f, 0.4f)
					.setFadeOut(500)
					.setRotationSpeed(30)
					.oneShot(itemView, 35);
		}

		public void setAnimationCows() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.cowsay, 5000)
					.setSpeedRange(0.05f, 0.3f)
					.setFadeOut(500)
					.setRotationSpeed(150)
					.oneShot(itemView, 35);
		}

		public void setAnimationWaterDrops() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 10, R.drawable.blurp, 5000)
					.setAcceleration(0.001f, 90)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(200, new AccelerateInterpolator())
					.oneShot(itemView, 10);
			new ParticleSystem(act, 15, R.drawable.blurptwo, 5000)
					.setAcceleration(0.001f, 50)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(200, new AccelerateInterpolator())
					.oneShot(itemView, 15);
			new ParticleSystem(act, 10, R.drawable.blurpthree, 5000)
					.setAcceleration(0.001f, 80)
					.setSpeedRange(0.1f, 1)
					.setFadeOut(250, new AccelerateInterpolator())
					.oneShot(itemView, 10);
		}

		public void setAnimationBart() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.bart, 5000)
					.setSpeedRange(0.1f, 0.25f)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		public void setAnimationRetard() {
			Activity act = (Activity) itemView.getContext();
			new ParticleSystem(act, 35, R.drawable.retard, 5000)
					.setSpeedRange(0.1f, 0.25f)
					.setFadeOut(500)
					.oneShot(itemView, 35);
		}

		/**
		 *  Saves sound selected by user in /xSoundBoardHD/Ringtones/, cleans filename and sets it as ringtone
		 *  @param ressound	R.raw.resourcesound
         *  @return	true
         */
		public boolean setAsRingtone(int ressound){
			byte[] buffer;
			InputStream fIn = itemView.getContext().getResources().openRawResource(ressound);
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

			String string = title.getText().toString();
			String cleanString = FileNameCleaner.cleanFileName(string);
			cleanString = cleanString.replaceAll(" ", "_");

			String filename = cleanString.toLowerCase() + ".mp3";

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
			} catch (Throwable t) {
				System.err.println(t.getMessage());
			}

			return true;
		}

		/**
		 *  Saves sound selected by user in /xSoundBoardHD/Notifications/, cleans filename and sets it as notification
		 *  @param ressound	R.raw.resourcesound
		 *  @return	true
		 */
		public boolean setAsNotification(int ressound){
			byte[] buffer;
			InputStream fIn = itemView.getContext().getResources().openRawResource(ressound);
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

			String string = title.getText().toString();
			String cleanString = FileNameCleaner.cleanFileName(string);
			cleanString = cleanString.replaceAll(" ", "_");

			String filename = cleanString.toLowerCase() + ".mp3";

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
			} catch (Throwable t) {
				System.err.println(t.getMessage());
			}
			return true;
		}

        /**
         *  Saves sound selected by user in /xSoundBoardHD/Alarms/, cleans filename and sets it as alarm
         *  @param ressound	R.raw.resourcesound
         *  @return	true
         */
        public boolean setAsAlarm(int ressound){
            byte[] buffer;
            InputStream fIn = itemView.getContext().getResources().openRawResource(ressound);
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

            String string = title.getText().toString();
            String cleanString = FileNameCleaner.cleanFileName(string);
            cleanString = cleanString.replaceAll(" ", "_");

            String filename = cleanString.toLowerCase() + ".mp3";

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
            } catch (Throwable t) {
                System.err.println(t.getMessage());
            }
            return true;
        }
	}

	/**
	 *  Creates working directory if does not exist.
	 *  @param path	String.
	 *  @return	true for successful directory creation or false if there was problem while creating folder
     */
	public static boolean createDirIfNotExists(String path) {
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
}