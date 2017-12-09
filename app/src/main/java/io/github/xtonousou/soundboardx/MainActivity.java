package io.github.xtonousou.soundboardx;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

	static InputMethodManager sInputManager;

	int mColor;
	int mDefaultColor;

	Typeface mFont;
	Drawer mDrawer;
	Toolbar mToolbar;
	RecyclerView mView;
	TextView mTitleText;
	SoundPlayer mSoundPlayer;
	ColorPicker mColorPicker;
	FloatingActionButton mFab;

	/*
	 * Do not change the order of the code.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDefaultColor = getResources().getColor(R.color.red);

		handlePreferences();
		handleReflections();
		handleFAB();
		handleView();
		handleTitle();
		handleToolbar();
		handleDrawer(savedInstanceState);
	}

	@Override
    public void onResume() {
        super.onResume();
        mSoundPlayer = new SoundPlayer(this);
    }

    @Override
    public void onPause() {
        super.onPause();
		mSoundPlayer.release();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = mDrawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private void showFavorites() {
		((SoundAdapter) mView.getAdapter()).showFavorites();
	}

    private void normalize(SoundAdapter adapter) {
        switch (SharedPrefs.getInstance().getSelectedCategory()) {
            case 1:
                adapter.showAllSounds(getApplicationContext());
                break;
            case 2:
                adapter.showFunnySounds(getApplicationContext());
                break;
            case 3:
                adapter.showGamesSounds(getApplicationContext());
                break;
            case 4:
                adapter.showMoviesSounds(getApplicationContext());
                break;
			case 5:
				adapter.showMoviesSounds(getApplicationContext());
				break;
        }
    }

    private void handlePreferences() {
		SharedPrefs.init(getPreferences(Context.MODE_PRIVATE));
		SharedPrefs.getInstance().setFavoritesShown(false);

		if (SharedPrefs.getInstance().isFirstTime()) {
			SharedPrefs.getInstance().setFirstTime(false);
			SharedPrefs.getInstance().setAnimationsShown(true);
			SharedPrefs.getInstance().setSelectedCategory(1); // all sounds
			SharedPrefs.getInstance().setSelectedColor(mDefaultColor);
		}

		mColor = Utils.getSelectedColor();
	}

	private void handleReflections() {
		mToolbar = findViewById(R.id.toolbar);
		mTitleText = findViewById(R.id.title_view);
		mView = findViewById(R.id.grid_view);
		mFab = findViewById(R.id.fab);
	}

    private void handleToolbar() {
		Utils.paintThis(mToolbar);
		sInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}

    private void handleView() {
		mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
				.getInteger(R.integer.num_cols),
				StaggeredGridLayoutManager.VERTICAL));
		mView.setAdapter(new SoundAdapter(MainActivity.this,
				SoundStore.getAllSounds(this)));
		((SoundAdapter) mView.getAdapter()).showAllSounds(getApplicationContext());
		mView.addItemDecoration(new BottomOffsetDecoration(225));
	}

    private void handleTitle() {
		Utils.paintThis(mTitleText);
		mFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-BoldCondensed.ttf");
		mTitleText.setTypeface(mFont);
		mTitleText.setOnClickListener((View view) -> mView.smoothScrollToPosition(0));
		mTitleText.setOnLongClickListener(view -> {
			showFavorites();
			return true;
		});
	}

    private void handleFAB() {
		Utils.paintThis(mFab);
		mFab.setOnClickListener(view -> mute());
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
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((SoundAdapter) mView.getAdapter()).getFilter().filter(newText);
                return true;
            }
        });
    }

    private void handleDrawer(Bundle instance) {
        int drawerSize;
        //TODO handle properly different screen resolutions
        if (getApplicationContext().getResources().getConfiguration().orientation != 1) {
            drawerSize = (Utils.getScreenWidth(this)) - 850;
        } else {
            drawerSize = (Utils.getScreenWidth(this)) - 250;
        }
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_layout)
                .withToolbar(mToolbar)
                .withTranslucentStatusBar(false)
                .withTranslucentNavigationBar(true)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggle(true)
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
                                .withSetSelected(false) //TODO hmmm rethinkkkk
                                .withIcon(FontAwesome.Icon.faw_asterisk)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.funny)
								.withIdentifier(2)
                                .withIcon(FontAwesome.Icon.faw_smile_o)
                                .withIconTintingEnabled(false)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.games)
								.withIdentifier(3)
                                .withIcon(FontAwesome.Icon.faw_gamepad)
								.withIconTintingEnabled(false)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.movies)
								.withIdentifier(4)
                                .withIcon(FontAwesome.Icon.faw_video_camera)
								.withIconTintingEnabled(false)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
						new PrimaryDrawerItem().withName(R.string.music)
								.withIdentifier(5)
								.withIcon(FontAwesome.Icon.faw_music)
								.withIconTintingEnabled(false)
								.withSelectedColor(ContextCompat.getColor(getApplicationContext(),
										R.color.colorPrimaryDarker))
								.withSelectedTextColor(mColor)
								.withSelectedIconColor(mColor),
                        new SectionDrawerItem().withName(R.string.options)
								.withIdentifier(6)
                                .withDivider(false)
                                .withTextColor(mColor),
                        new SecondarySwitchDrawerItem().withName(R.string.favorites)
								.withIdentifier(7)
                                .withIcon(FontAwesome.Icon.faw_heart)
								.withIconTintingEnabled(false)
								.withSelectable(false)
								.withChecked(SharedPrefs.getInstance().areFavoritesShown())
								.withOnCheckedChangeListener(onToggleFavoritesListener),
                        new SecondarySwitchDrawerItem().withName(R.string.particles)
								.withIdentifier(8)
                                .withIcon(FontAwesome.Icon.faw_eye)
								.withIconTintingEnabled(false)
                                .withSelectable(false)
                                .withChecked(true)
                                .withOnCheckedChangeListener(onToggleParticleListener),
                        new SectionDrawerItem().withName(R.string.misc)
								.withIdentifier(9)
                                .withDivider(false)
                                .withTextColor(mColor),
                        new SecondaryDrawerItem().withName(R.string.color)
								.withIdentifier(10)
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
                            mView.setAdapter(new SoundAdapter(MainActivity.this, SoundStore
                                    .getAllSounds(MainActivity.this)));
                            ((SoundAdapter) mView.getAdapter()).showAllSounds(MainActivity.this);
                            break;
                        case 2:
                            mView.setAdapter(new SoundAdapter(MainActivity.this, SoundStore
                                    .getFunnySounds(MainActivity.this)));
                            ((SoundAdapter) mView.getAdapter()).showFunnySounds(MainActivity.this);
                            break;
                        case 3:
                            mView.setAdapter(new SoundAdapter(MainActivity.this, SoundStore
                                    .getGamesSounds(MainActivity.this)));
                            ((SoundAdapter) mView.getAdapter()).showGamesSounds(MainActivity.this);
                            break;
                        case 4:
                            mView.setAdapter(new SoundAdapter(MainActivity.this, SoundStore
                                    .getMoviesSounds(MainActivity.this)));
                            ((SoundAdapter) mView.getAdapter()).showMoviesSounds(MainActivity.this);
                            break;
						//TODO
						/*case 5:
							mView.setAdapter(new SoundAdapter(MainActivity.this, SoundStore
									.getMusicSounds(MainActivity.this)));
							((SoundAdapter) mView.getAdapter()).showMusicSounds(MainActivity.this);
							break;*/
						/* Options title
						case 6:
							break;*/
						/* Favorites switch, handled further with listener
                        case 7:
							showFavorites();
                            break;*/
						/* Particle switch, handled further with listener
						case 8:
							break;*/
						/* Misc title
						case 9:
							break;*/
                        case 10:
                            handleColorPicker();
                            break;
                    }
                    return false;
                })
                .withSelectedItemByPosition(1)
                .withSavedInstance(instance)
                .build();

		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
				mDrawer.getDrawerLayout(),
				mToolbar, 0, 0) {

			@Override
			public void onDrawerClosed(View v) {
				super.onDrawerClosed(v);
				if ((v != null) && sInputManager.isActive()) {
					sInputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}

			@Override
			public void onDrawerOpened(View v) {
				super.onDrawerOpened(v);
				if ((v != null) && sInputManager.isActive()) {
					sInputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
		};

		mDrawer.getDrawerLayout().addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();

        if (Utils.isGreenMode(MainActivity.this)) {
			SharedPrefs.getInstance().setAnimationsShown(false);
			mDrawer.removeItemByPosition(8);
			mDrawer.setItemAtPosition(mDrawer.getDrawerItem(10), 8);
		}
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
			public void onCancel() {}

		}).setColumns(4).setRoundColorButton(true).show();
	}

    private OnCheckedChangeListener onToggleFavoritesListener = (drawerItem, buttonView, isChecked)
			-> {
		if (isChecked) {
			showFavorites();
		} else {
			normalize((SoundAdapter) mView.getAdapter());
		}
	};

	private OnCheckedChangeListener onToggleParticleListener = (drawerItem, buttonView, isChecked)
			-> SharedPrefs.getInstance().setAnimationsShown(isChecked);

	void mute() {
		mSoundPlayer.release();
		mSoundPlayer = new SoundPlayer(this);
	}
}
