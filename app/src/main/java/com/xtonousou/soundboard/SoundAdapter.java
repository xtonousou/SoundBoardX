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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> implements Filterable {
	private static final String TAG = "SoundAdapter";
	private static final Integer WRITE_EXST = 0x1;
	private ArrayList<Sound> sounds;
	private ArrayList<Sound> orig;
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

	public ArrayList<Sound> getSounds() {
		return sounds;
	}

	@Override
	public SoundAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Create a new view
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_card, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		final Particle particle = new Particle(holder.itemView);
		holder.title.setText(sounds.get(position).getName());
		holder.itemView.setOnClickListener(new View.OnClickListener() {

			/**
			 *  Animates each item onClick. Checks name and decides.
             */
			public void animate() {
				switch (holder.title.getText().toString()) {
					default:
						Log.e(TAG, "No animation is set for " + holder.title.getText().toString());
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
					case "Shabadaa shabadabadabaa…":
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

			/**
			 *  Another event for EventBus.
			 *  @param event Whatever event.
             */
			public void onEvent(String event) {
				EventBus.getDefault().unregister(this);
				notifyItemChanged(holder.getAdapterPosition());
			}

			@Override
			public void onClick(View view) {
				try {
					if (EventBus.getDefault().isRegistered(this)) {
						EventBus.getDefault().unregister(this);
					} else {
						EventBus.getDefault().register(this);
						if (showAnimations) {
							animate();
						}
						EventBus.getDefault().post(sounds.get(holder.getAdapterPosition()));
						EventBus.getDefault().unregister(this);
						notifyItemChanged(holder.getAdapterPosition());
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

	/**
	 *  Receives a CharSequence as parameter and filters a list. Notifies.
	 *  @see MainActivity initSearchView()
	 *  @return filtered list.
     */
	@Override
	public Filter getFilter() {
		return new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence charSequence) {
				final FilterResults filtered = new FilterResults();
				final ArrayList<Sound> results = new ArrayList<Sound>();
				if (orig == null) {
					orig = sounds;
				}
				if (charSequence != null) {
					if (orig != null && orig.size() > 0 ) {
						for (final Sound sound : orig) {
							if (sound.getName().toLowerCase().contains(charSequence.toString()))results.add(sound);
						}
					}
					filtered.values = results;
				}
				return filtered;
			}

			@Override
			protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
				sounds = (ArrayList<Sound>) filterResults.values;
				notifyDataSetChanged();
			}
		};
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