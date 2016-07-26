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
	private ArrayList<Sound> orig;
	private boolean favoritesOnly = false;
	private boolean showAnimations = true;
    private String query;
    private String nameOf;

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

	public void showAnimalsSounds(Context context) {
		favoritesOnly = false;
		sounds = SoundStore.getAnimalsSounds(context);
		notifyDataSetChanged();
	}

	public void showFunnySounds(Context context) {
		favoritesOnly = false;
		sounds = SoundStore.getFunnySounds(context);
		notifyDataSetChanged();
	}

	public void showGamesSounds(Context context) {
		favoritesOnly = false;
		sounds = SoundStore.getGamesSounds(context);
		notifyDataSetChanged();
	}

	public void showMoviesSounds(Context context) {
		favoritesOnly = false;
		sounds = SoundStore.getMoviesSounds(context);
		notifyDataSetChanged();
	}

	public void showNSFWSounds(Context context) {
		favoritesOnly = false;
		sounds = SoundStore.getNSFWSounds(context);
		notifyDataSetChanged();
	}

	public void showPersonalSounds(Context context) {
		favoritesOnly = false;
		sounds = SoundStore.getPersonalSounds(context);
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
	public int getItemCount() {
		return sounds.size();
	}

	public int getFilteredItemsCount() {
		return orig.size();
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
				Log.d(TAG, "**** PERFORM FILTERING for: " + charSequence);
                final FilterResults filtered = new FilterResults();
                if (charSequence == null || charSequence.length() == 0) {
                    filtered.values = sounds;
					filtered.count = sounds.size();
                } else {
					ArrayList<Sound> filterResultsData = new ArrayList<Sound>();
					for (final Sound sound : orig) {
						nameOf = sound.getName().toLowerCase();
						nameOf = Normalizer.normalize(nameOf, Normalizer.Form.NFD);
						nameOf = nameOf.replaceAll("\\p{M}", "");
						query = charSequence.toString();
						if (nameOf.contains(query)) {
							filterResultsData.add(sound);
						}
					}

					filtered.values = filterResultsData;
					filtered.count = filterResultsData.size();
				}

                return filtered;
            }

			@SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
				Log.d(TAG, "**** PUBLISHING RESULTS for: " + charSequence);
                sounds = (ArrayList<Sound>) filterResults.values;
                SoundAdapter.this.notifyDataSetChanged();
            }
        };
    }

	@Override
	public SoundAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// Create a new view
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_card, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		holder.title.setText(sounds.get(position).getName());
		final ToneManager tone = new ToneManager(new Particle(holder.itemView), holder.title.getText().toString());
		holder.itemView.setOnClickListener(new View.OnClickListener() {

			/**
			 *  Another event for EventBus.
			 *  @param event Whatever event.
             */
			public void onEvent(String event) {
				notifyItemChanged(holder.getAdapterPosition());
			}

			@Override
			public void onClick(View view) {
				if (EventBus.getDefault().isRegistered(this)) {
					return;
				}
				if (showAnimations) {
					tone.makeItShine();
				}
				EventBus.getDefault().register(this);
				EventBus.getDefault().post(sounds.get(holder.getAdapterPosition()));
				EventBus.getDefault().unregister(this);
			}
		});

		/**
		 *  Listens and sets favorite icon on each item.
		 */
		boolean isFavorite = sounds.get(position).getFavorite();
		holder.favButton.setImageResource(isFavorite
				? R.drawable.ic_star_white_24dp
				: R.drawable.ic_star_border_white_24dp);

		holder.favButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
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
			}
		});
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements
			View.OnCreateContextMenuListener,
			MenuItem.OnMenuItemClickListener {
		public final TextView title;
		public final ImageButton favButton;

		public ViewHolder(View v) {
			super(v);
			title = (TextView) v.findViewById(R.id.title);
			favButton = (ImageButton) v.findViewById(R.id.fav_button);

			itemView.setBackgroundColor((new DayColor(itemView.getContext())).getDayColor());

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
}