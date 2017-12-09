package io.github.xtonousou.soundboardx;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

	int mColor;
	boolean mAnimations = true;
	static String sDefaultColor = "#b71c1c";

	static InputMethodManager sInputManager;

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

		handlePreferences();

		mToolbar = findViewById(R.id.toolbar);
		mTitleText = findViewById(R.id.title_view);
		mView = findViewById(R.id.grid_view);
		mFab = findViewById(R.id.fab);

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

    private void normalize(SoundAdapter adapter) {
        switch (adapter.getCategory()) {
            default:
                Log.e(TAG, "Something went completely wrong. Check normalize() or its calls.");
                break;
            case 0:
                adapter.showAllSounds(getApplicationContext());
                break;
            case 1:
                adapter.showFunnySounds(getApplicationContext());
                break;
            case 2:
                adapter.showGamesSounds(getApplicationContext());
                break;
            case 3:
                adapter.showMoviesSounds(getApplicationContext());
                break;
        }
    }

    private void showFavs() {
		if (!((SoundAdapter) mView.getAdapter()).isFavoritesOnly())
			((SoundAdapter) mView.getAdapter()).onlyShowFavorites();
		else
			normalize((SoundAdapter) mView.getAdapter());
	}

    private void handlePreferences() {
		SharedPrefs.init(getPreferences(Context.MODE_PRIVATE));

		if (SharedPrefs.getInstance().isFirstTime()) {
			SharedPrefs.getInstance().setFirstTime(false);
			SharedPrefs.getInstance().setSelectedColor(Color.parseColor(sDefaultColor));
		}

		mColor = Utils.getSelectedColor();
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
				SoundStore.getAllSounds(this), mAnimations));
		((SoundAdapter) mView.getAdapter()).showAllSounds(getApplicationContext());
		mView.addItemDecoration(new BottomOffsetDecoration(225));
	}

    private void handleTitle() {
		Utils.paintThis(mTitleText);
		mFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-BoldCondensed.ttf");
		mTitleText.setTypeface(mFont);
		mTitleText.setOnClickListener((View view) -> mView.smoothScrollToPosition(0));
		mTitleText.setOnLongClickListener(view -> {
			showFavs();
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
								.withDivider(false)
                                .withTextColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.all)
                                .withSetSelected(false)
                                .withIcon(FontAwesome.Icon.faw_music)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.funny)
                                .withIcon(FontAwesome.Icon.faw_smile_o)
                                .withIconTintingEnabled(false)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.games)
                                .withIcon(FontAwesome.Icon.faw_gamepad)
								.withIconTintingEnabled(false)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new PrimaryDrawerItem().withName(R.string.movies)
                                .withIcon(FontAwesome.Icon.faw_video_camera)
								.withIconTintingEnabled(false)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(mColor)
                                .withSelectedIconColor(mColor),
                        new SectionDrawerItem().withName(R.string.options)
                                .withDivider(false)
                                .withTextColor(mColor),
                        new SecondaryDrawerItem().withName(R.string.favorites)
                                .withIcon(FontAwesome.Icon.faw_heart)
                                .withSelectable(false),
                        new SecondarySwitchDrawerItem().withName(R.string.particles)
                                .withIcon(FontAwesome.Icon.faw_eye)
								.withIconTintingEnabled(false)
                                .withSelectable(false)
                                .withChecked(true)
                                .withOnCheckedChangeListener(onCheckedChangeListener),
                        new SectionDrawerItem().withName(R.string.misc)
                                .withDivider(false)
                                .withTextColor(mColor),
                        new SecondaryDrawerItem().withName(R.string.color)
                                .withIcon(FontAwesome.Icon.faw_paint_brush)
								.withIconTintingEnabled(false)
                                .withSelectable(false)
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    switch (position) {
                        case 1:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(MainActivity.this, SoundStore
                                    .getAllSounds(MainActivity.this), mAnimations));
                            ((SoundAdapter) mView.getAdapter()).showAllSounds(MainActivity.this);
                            break;
                        case 2:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(MainActivity.this, SoundStore
                                    .getFunnySounds(MainActivity.this), mAnimations));
                            ((SoundAdapter) mView.getAdapter()).showFunnySounds(MainActivity.this);
                            break;
                        case 3:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(MainActivity.this, SoundStore
                                    .getGamesSounds(MainActivity.this), mAnimations));
                            ((SoundAdapter) mView.getAdapter()).showGamesSounds(MainActivity.this);
                            break;
                        case 4:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(MainActivity.this, SoundStore
                                    .getMoviesSounds(MainActivity.this), mAnimations));
                            ((SoundAdapter) mView.getAdapter()).showMoviesSounds(MainActivity.this);
                            break;
                        case 6:
							showFavs();
                            break;
                        case 9:
                            mColorPicker = new ColorPicker(MainActivity.this);
							mColorPicker.setTitle(getString(R.string.hex_code) + sDefaultColor);
							mColorPicker.setColors(R.array.rainbow);
							mColorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                                @Override
                                public void onChooseColor(int position, int color) {
                                    if (position == -1)
                                        return;
                                    sDefaultColor = String.format("#%06X", 0xFFFFFF & color);
                                    SharedPrefs.getInstance().setSelectedColor(color);
                                    Utils.restartActivity(MainActivity.this);
                                }

                                @Override
                                public void onCancel() {
                                    SharedPrefs.getInstance().setSelectedColor(SharedPrefs.getInstance().getSelectedColor());
                                    Utils.restartActivity(MainActivity.this);
                                }

                            }).setRoundColorButton(true).show();
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

		/*
		 * Tweak it.....
		 */
        if (Utils.isGreenMode(MainActivity.this)) {
            ((SoundAdapter) mView.getAdapter()).setShowAnimations(false);
            mDrawer.removeItemByPosition(8);
        }
    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
			if (((SoundAdapter) mView.getAdapter()).areAnimationsShown()) {
				mAnimations = false;
				((SoundAdapter) mView.getAdapter()).setShowAnimations(false);
			} else {
				mAnimations = true;
				((SoundAdapter) mView.getAdapter()).setShowAnimations(true);
			}
		}
    };

	void mute() {
		mSoundPlayer.release();
		mSoundPlayer = new SoundPlayer(this);
	}
}
