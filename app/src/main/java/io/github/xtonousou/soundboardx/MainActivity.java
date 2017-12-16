package io.github.xtonousou.soundboardx;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {
	private int mColor;

	private Drawer mDrawer;
	private Toolbar mToolbar;
	private RecyclerView mView;
	private TextView mTitleText;
	private SoundPlayer mSoundPlayer;
	private ColorPicker mColorPicker;
	private FloatingActionButton mFabMute;
	private MaterialFavoriteButton mFavButton;
	private BroadcastReceiver mPowerSaverChangeReceiver;

	/*
	 * Do not change the order of the code.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		handlePreferences();
		handleReflections();

		handleFAB();
		handleView();
		handleTitle();
		handleToolbar();
		handleDrawer();

		handlePowerSaverMode();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mPowerSaverChangeReceiver);
		if (mSoundPlayer != null) {
			mSoundPlayer.release();
			mSoundPlayer = null;
		}
	}

	@Override
    public void onResume() {
        super.onResume();
		if (mSoundPlayer == null) mSoundPlayer = new SoundPlayer(MainActivity.this);
    }

    @Override
    public void onPause() {
        super.onPause();
		if (mSoundPlayer != null) {
			mSoundPlayer.release();
			mSoundPlayer = null;
		}
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);
		handleSearchView(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer != null && mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void handlePowerSaverMode() {
		if (Utils.isGreenMode(MainActivity.this)) {
			if (SharedPrefs.getInstance().areAnimationsShown())
				SharedPrefs.getInstance().setAnimationsShown(false);

			mDrawer.removeItem(7);
			mDrawer.setItemAtPosition(
					new SecondaryDrawerItem().withName("Particles Disabled")
							.withIdentifier(7)
							.withIcon(FontAwesome.Icon.faw_exclamation_circle)
							.withIconTintingEnabled(false)
							.withSelectable(false),
					7);

			Toast.makeText(getApplicationContext(), R.string.battery_saver_on, Toast
					.LENGTH_LONG).show();
		}

		mPowerSaverChangeReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				if (SharedPrefs.getInstance().areAnimationsShown()) {
					SharedPrefs.getInstance().setAnimationsShown(false);

					mDrawer.removeItem(7);
					mDrawer.setItemAtPosition(
							new SecondaryDrawerItem().withName(R.string.particles_disabled)
									.withIdentifier(7)
									.withIcon(FontAwesome.Icon.faw_exclamation_circle)
									.withIconTintingEnabled(false)
									.withSelectable(false),
							7);

					Toast.makeText(getApplicationContext(), R.string.battery_saver_on, Toast
							.LENGTH_LONG).show();
				} else {
					Utils.restartActivity(MainActivity.this);
				}
			}
		};

		IntentFilter intentFilterilter = new IntentFilter();
		intentFilterilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
		registerReceiver(mPowerSaverChangeReceiver, intentFilterilter);
	}

    private void handlePreferences() {
		SharedPrefs.init(getPreferences(Context.MODE_PRIVATE));
		SharedPrefs.getInstance().setFavoritesShown(false);
		SharedPrefs.getInstance().setAnimationsShown(true);

		if (SharedPrefs.getInstance().isFirstTime()) {
			SharedPrefs.getInstance().setFirstTime(false);
			SharedPrefs.getInstance().setSelectedCategory(1);
			SharedPrefs.getInstance().setSelectedColor(R.color.red);
		}

		mColor = Utils.getSelectedColor();
	}

	private void handleReflections() {
		mView      = findViewById(R.id.grid_view);
		mToolbar   = findViewById(R.id.toolbar);
		mFabMute   = findViewById(R.id.fab);
		mFavButton = findViewById(R.id.fav_button);
		mTitleText = findViewById(R.id.title_view);
	}

    private void handleToolbar() {
		Utils.paintThis(mToolbar);

		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);

		mFavButton.setOnFavoriteChangeListener((buttonView, favorite) -> {
			if (!SharedPrefs.getInstance().areFavoritesShown()) {
				((SoundAdapter) mView.getAdapter()).showFavorites();
			} else {
				((SoundAdapter) mView.getAdapter()).showPrevious();
			}
		});
	}

    private void handleView() {
		mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
				.getInteger(R.integer.num_cols),
				StaggeredGridLayoutManager.VERTICAL));
		mView.setAdapter(new SoundAdapter(MainActivity.this));
		((SoundAdapter) mView.getAdapter()).showPrevious();
		mView.addItemDecoration(new BottomOffsetDecoration(225));
	}

    private void handleTitle() {
		Utils.paintThis(mTitleText);
		Typeface mFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-BoldCondensed.ttf");
		mTitleText.setTypeface(mFont);
		mTitleText.setOnClickListener(view -> mView.smoothScrollToPosition(0));
	}

    private void handleFAB() {
		Utils.paintThis(mFabMute);
		mFabMute.setOnClickListener(view -> {
			mSoundPlayer.release();
			mSoundPlayer = new SoundPlayer(MainActivity.this);
		});
	}

    private void handleSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        SearchView.SearchAutoComplete searchViewText = searchView.findViewById(R.id.search_src_text);

        Utils.paintThis(searchViewText);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            	if (mDrawer.isDrawerOpen()) mDrawer.closeDrawer();
				return true;
        	}

            @Override
            public boolean onQueryTextChange(String newText) {
                ((SoundAdapter) mView.getAdapter()).getFilter().filter(newText);
                return true;
            }
        });
    }

    private void handleDrawer() {
        int drawerSize;
		int selectedColor = ContextCompat.getColor(getApplicationContext(),
				R.color.colorPrimaryDarker);

		/* HORIZONTAL ORIENTATION
		//TODO handle properly different screen resolutions
        if (getApplicationContext().getResources().getConfiguration().orientation != 1) {
            drawerSize = (Utils.getScreenWidth(MainActivity.this)) - 850;
        } else {
            drawerSize = (Utils.getScreenWidth(MainActivity.this)) - 250;
        }
        */

		drawerSize = (Utils.getScreenWidth(MainActivity.this)) - 250;

        mDrawer = new DrawerBuilder()
                .withActivity(MainActivity.this)
                .withRootView(R.id.drawer_layout)
                .withToolbar(mToolbar)
                .withTranslucentStatusBar(false) // keyboard fix
                .withTranslucentNavigationBar(true)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withScrollToTopAfterClick(true)
                .withDrawerWidthPx(drawerSize)
                .withSliderBackgroundColorRes(R.color.colorPrimaryDark)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.categories)
								.withIdentifier(0)
								.withDivider(false)
                                .withTextColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.all)
								.withIdentifier(1)
                                .withSetSelected(false)
                                .withIcon(FontAwesome.Icon.faw_asterisk)
                                .withSelectedColor(selectedColor)
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.funny)
								.withIdentifier(2)
                                .withIcon(FontAwesome.Icon.faw_smile_o)
                                .withIconTintingEnabled(false)
                                .withSelectedColor(selectedColor)
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.games)
								.withIdentifier(3)
                                .withIcon(FontAwesome.Icon.faw_gamepad)
								.withIconTintingEnabled(false)
                                .withSelectedColor(selectedColor)
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.movies)
								.withIdentifier(4)
                                .withIcon(FontAwesome.Icon.faw_video_camera)
								.withIconTintingEnabled(false)
                                .withSelectedColor(selectedColor)
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
						new PrimaryDrawerItem().withName(R.string.music)
								.withIdentifier(5)
								.withIcon(FontAwesome.Icon.faw_music)
								.withIconTintingEnabled(false)
								.withSelectedColor(selectedColor)
								.withSelectedTextColor(mColor)
								.withSelectedIconColor(mColor),
                        new SectionDrawerItem().withName(R.string.options)
								.withIdentifier(6)
                                .withDivider(false)
                                .withTextColor(mColor),
                        new SecondarySwitchDrawerItem().withName(R.string.particles)
								.withIdentifier(7)
                                .withIcon(FontAwesome.Icon.faw_magic)
								.withIconTintingEnabled(false)
                                .withSelectable(false)
                                .withChecked(true)
                                .withOnCheckedChangeListener(onToggleParticleListener),
                        new SecondaryDrawerItem().withName(R.string.color)
								.withIdentifier(8)
                                .withIcon(FontAwesome.Icon.faw_paint_brush)
								.withIconTintingEnabled(false)
                                .withSelectable(false)
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
					mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
							.getInteger(R.integer.num_cols),
							StaggeredGridLayoutManager.VERTICAL));
                    switch (position) {
                        case 1:
                            mView.setAdapter(new SoundAdapter(MainActivity.this));
							if (SharedPrefs.getInstance().areFavoritesShown())
								mFavButton.toggleFavorite();
                            ((SoundAdapter) mView.getAdapter())
									.showAllSounds(MainActivity.this);
                            break;
                        case 2:
                            mView.setAdapter(new SoundAdapter(MainActivity.this));
							if (SharedPrefs.getInstance().areFavoritesShown())
								mFavButton.toggleFavorite();
                            ((SoundAdapter) mView.getAdapter())
									.showFunnySounds(MainActivity.this);
                            break;
                        case 3:
                            mView.setAdapter(new SoundAdapter(MainActivity.this));
							if (SharedPrefs.getInstance().areFavoritesShown())
								mFavButton.toggleFavorite();
                            ((SoundAdapter) mView.getAdapter())
									.showGamesSounds(MainActivity.this);
                            break;
                        case 4:
                            mView.setAdapter(new SoundAdapter(MainActivity.this));
							if (SharedPrefs.getInstance().areFavoritesShown())
								mFavButton.toggleFavorite();
                            ((SoundAdapter) mView.getAdapter())
									.showMoviesSounds(MainActivity.this);
                            break;
						case 5:
							mView.setAdapter(new SoundAdapter(MainActivity.this));
							if (SharedPrefs.getInstance().areFavoritesShown())
								mFavButton.toggleFavorite();
							((SoundAdapter) mView.getAdapter())
									.showMusicSounds(MainActivity.this);
							break;
						/* Options title
						case 7:
							break;*/
						/* Particle switch, handled by listener
						case 7:
							break;*/
						case 8:
							handleColorPicker();
							break;
                    }
                    return false;
                })
                .withSelectedItemByPosition(SharedPrefs.getInstance().getSelectedCategory())
                .build();

		mDrawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
    }

    private void handleColorPicker() {
		mColorPicker = new ColorPicker(MainActivity.this);
		mColorPicker.setTitle(getString(R.string.palette));
		mColorPicker.setColors(R.array.rainbow);
		mColorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
			@Override
			public void onChooseColor(int position, int color) {
				if (position == -1)
					return;
				SharedPrefs.getInstance().setSelectedColor(color);
				Utils.restartActivity(MainActivity.this);
			}

			@Override
			public void onCancel() {
				mColorPicker.dismissDialog();
			}

		}).setColumns(4).setRoundColorButton(true).show();
	}

	private OnCheckedChangeListener onToggleParticleListener = (drawerItem, buttonView, isChecked)
			-> SharedPrefs.getInstance().setAnimationsShown(isChecked);
}
