package com.xtonousou.xsoundboardhd;

import android.content.Context;
import android.graphics.Typeface;
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

import java.text.Normalizer;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> implements Filterable {
	public static final String TAG = "SoundAdapter";

	private ArrayList<Sound> sounds;
	private ArrayList<Sound> soundsCopy;
	private boolean favoritesOnly = false;
	private boolean animationsShown = true;
	private boolean allSoundsOnly = true; // default
	private boolean animalsSoundsOnly = false;
	private boolean funnySoundsOnly = false;
	private boolean gamesSoundsOnly = false;
	private boolean moviesSoundsOnly = false;
	private boolean nsfwSoundsOnly = false;
	private boolean personalSoundsOnly = false;

	public SoundAdapter(ArrayList<Sound> soundArray) {
		sounds     = soundArray;
		soundsCopy = soundArray;
	}

	public boolean areAnimationsShown() {
		return animationsShown;
	}

	public boolean isFavoritesOnly() {
		return favoritesOnly;
	}

	/**
	 *  returns  0 if allSoundsOnly,
	 *  returns  1 if animalsSoundsOnly,
	 *  returns  2 if funnySoundsOnly,
	 *  returns  3 if gamesSoundsOnly,
	 *  returns  4 if moviesSoundsOnly,
	 *  returns  5 if nsfwSoundsOnly,
	 *  returns  6 if personalSoundsOnly,
	 *  returns -1 if unexpected occurrence.
     */
	public byte getCategory() {
		byte category;
		if      (allSoundsOnly)      category =  0;
		else if (animalsSoundsOnly)  category =  1;
		else if (funnySoundsOnly)    category =  2;
		else if (gamesSoundsOnly)    category =  3;
		else if (moviesSoundsOnly)   category =  4;
		else if (nsfwSoundsOnly)     category =  5;
		else if (personalSoundsOnly) category =  6;
		else                         category = -1;
		return category;
	}

	public void setShowAnimations(boolean anim) {
		animationsShown = anim;
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

		allSoundsOnly = true;
		animalsSoundsOnly = false;
		funnySoundsOnly = false;
		gamesSoundsOnly = false;
		moviesSoundsOnly = false;
		nsfwSoundsOnly = false;
		personalSoundsOnly = false;

		sounds = SoundStore.getAllSounds(context);
		notifyDataSetChanged();
	}

	public void showAnimalsSounds(Context context) {
		favoritesOnly = false;

		allSoundsOnly = false;
		animalsSoundsOnly = true;
		funnySoundsOnly = false;
		gamesSoundsOnly = false;
		moviesSoundsOnly = false;
		nsfwSoundsOnly = false;
		personalSoundsOnly = false;

		sounds = SoundStore.getAnimalsSounds(context);
		notifyDataSetChanged();
	}

	public void showFunnySounds(Context context) {
		favoritesOnly = false;

		allSoundsOnly = false;
		animalsSoundsOnly = false;
		funnySoundsOnly = true;
		gamesSoundsOnly = false;
		moviesSoundsOnly = false;
		nsfwSoundsOnly = false;
		personalSoundsOnly = false;

		sounds = SoundStore.getFunnySounds(context);
		notifyDataSetChanged();
	}

	public void showGamesSounds(Context context) {
		favoritesOnly = false;

		allSoundsOnly = false;
		animalsSoundsOnly = false;
		funnySoundsOnly = false;
		gamesSoundsOnly = true;
		moviesSoundsOnly = false;
		nsfwSoundsOnly = false;
		personalSoundsOnly = false;

		sounds = SoundStore.getGamesSounds(context);
		notifyDataSetChanged();
	}

	public void showMoviesSounds(Context context) {
		favoritesOnly = false;

		allSoundsOnly = false;
		animalsSoundsOnly = false;
		funnySoundsOnly = false;
		gamesSoundsOnly = false;
		moviesSoundsOnly = true;
		nsfwSoundsOnly = false;
		personalSoundsOnly = false;

		sounds = SoundStore.getMoviesSounds(context);
		notifyDataSetChanged();
	}

	public void showNSFWSounds(Context context) {
		favoritesOnly = false;


		allSoundsOnly = false;
		animalsSoundsOnly = false;
		funnySoundsOnly = false;
		gamesSoundsOnly = false;
		moviesSoundsOnly = false;
		nsfwSoundsOnly = true;
		personalSoundsOnly = false;

		sounds = SoundStore.getNSFWSounds(context);
		notifyDataSetChanged();
	}

	public void showPersonalSounds(Context context) {
		favoritesOnly = false;

		allSoundsOnly = false;
		animalsSoundsOnly = false;
		funnySoundsOnly = false;
		gamesSoundsOnly = false;
		moviesSoundsOnly = false;
		nsfwSoundsOnly = false;
		personalSoundsOnly = true;

		sounds = SoundStore.getPersonalSounds(context);
		notifyDataSetChanged();
	}

	@Override
	public SoundAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_card, parent, false);
		return new ViewHolder(v);
	}

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnCreateContextMenuListener,
            MenuItem.OnMenuItemClickListener {
        public final TextView title;
        public final ImageButton favButton;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            favButton = (ImageButton) v.findViewById(R.id.fav_button);

            //final ToneManager tone = new ToneManager(new Particle(itemView), title.getText().toString());
            itemView.setBackgroundColor((new DayColor(itemView.getContext())).getDayColor());
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onEvent(String event) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        if (EventBus.getDefault().isRegistered(this)) {
                            EventBus.getDefault().unregister(this);
                        }
                        notifyItemChanged(getAdapterPosition());
                    }
                }

                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        if (EventBus.getDefault().isRegistered(this)) {
                            return;
                        }
                        if (animationsShown) {
                            new ToneManager(new Particle(itemView), title.getText().toString()).makeItShine();
                        }
                        EventBus.getDefault().register(this);
                        EventBus.getDefault().post(sounds.get(getAdapterPosition()));
                        EventBus.getDefault().unregister(this);
                    }
                }
            });

            v.setOnCreateContextMenuListener(this);

            Typeface font = Typeface.createFromAsset(itemView.getContext().getAssets(),
                    "fonts/Roboto-Regular.ttf");
            title.setTypeface(font);
        }

        @Override
        public void onCreateContextMenu(final ContextMenu contextMenu, View view,
                                        ContextMenu.ContextMenuInfo contextMenuInfo) {
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
            ToneManager toneSetAs = new ToneManager(title.getText().toString(), itemView);
            switch (menuItem.getTitle().toString()) {
                default:
                    Log.e(TAG, "onMenuItemClick: menuItem.getTitle().toString()");
                case "Set as ringtone":
                    toneSetAs.ringtone();
                    break;
                case "Set as notification":
                    toneSetAs.notification();
                    break;
                case "Set as alarm":
                    toneSetAs.alarm();
                    break;
            }
            return true;
        }
    }

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		holder.title.setText(sounds.get(position).getName());

		boolean isFavorite = sounds.get(position).getFavorite();
		holder.favButton.setImageResource(isFavorite
				? R.drawable.ic_star_white_24dp
				: R.drawable.ic_star_border_white_24dp);

		holder.favButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					boolean newFavStatus = !sounds.get(holder.getAdapterPosition()).getFavorite();
					sounds.get(holder.getAdapterPosition()).setFavorite(newFavStatus);

					if (newFavStatus) {
						((ImageButton) v).setImageResource(R.drawable.ic_star_white_24dp);
						v.setContentDescription(v.getContext().getString(R.string.fav_desc));
					} else {
						((ImageButton) v).setImageResource(R.drawable.ic_star_border_white_24dp);
						v.setContentDescription(v.getContext().getString(R.string.not_fav_desc));
					}

					if (favoritesOnly) {
						// Remove from the list.
						sounds.remove(sounds.get(holder.getAdapterPosition()));
						notifyItemRemoved(holder.getAdapterPosition());
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					Log.e(TAG, e.getMessage());
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return sounds.size();
	}

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            ArrayList<Sound> tempList = new ArrayList<Sound>();
            favoritesOnly = false;
            if (constraint != null && soundsCopy != null) {
                for (Sound item : new ArrayList<>(soundsCopy)) {
                    String userInput = striptease(constraint.toString().toLowerCase());
                    String itemName = striptease(item.getName().toLowerCase());
                    if (itemName.contains(userInput)) {
                        tempList.add(item);
                    }
                }
                filterResults.values = tempList;
                filterResults.count = tempList.size();
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            sounds = (ArrayList<Sound>) results.values;
            notifyDataSetChanged();
        }
    };

    private static String striptease(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll(" ", "")
                .replaceAll("\'", "");
    }
}