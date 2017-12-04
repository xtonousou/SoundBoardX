package io.github.xtonousou.soundboardx;

import android.Manifest;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
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

import com.github.clans.fab.FloatingActionButton;
import com.hanks.htextview.base.HTextView;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import petrov.kristiyan.colorpicker.ColorPicker;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    int savedColor;
    byte easterEggCounter = 0;
    boolean withAnimations = true;

    Typeface fancyFont;
	MediaPlayer easterEggPlayer = null;
	SoundPlayer soundPlayer;
    Toolbar mToolbar;

    static InputMethodManager mInputManager;
    static String fallbackColor = "#b71c1c";

    ColorPicker colorPicker;
    RecyclerView mView;
    Drawer mDrawer = null;

    @SuppressWarnings("ConstantConditions")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefs.init(getPreferences(Context.MODE_PRIVATE));
        MainActivityPermissionsDispatcher.writeSettingsWithPermissionCheck(this);

        if (SharedPrefs.getInstance().isFirstTime()) {
            SharedPrefs.getInstance().setFirstTime(false);
            SharedPrefs.getInstance().setSelectedColor(Color.parseColor(fallbackColor));
        }
        
        savedColor = Utils.getSelectedColor();

        mToolbar = findViewById(R.id.toolbar);
		Utils.paintThis(mToolbar);

		HTextView txt = findViewById(R.id.title_view);
		Utils.paintThis(txt);
		txt.setOnClickListener((View view) -> {
			switch (easterEggCounter) {
				default:
					if (easterEggPlayer == null) {
						txt.animateText(getString(R.string.question_mark));
						easterEggCounter++;
					}
					break;
				case 4:
					if (easterEggPlayer == null) {
						txt.animateText(getString(R.string.can_you_stop));
						easterEggCounter++;
					}
					break;
				case 8:
					if (easterEggPlayer == null) {
						txt.animateText(getString(R.string.are_you_sure));
						easterEggCounter++;
					}
					break;
				case 9:
					if (easterEggPlayer == null) {
						txt.animateText(getString(R.string.lenny_face));
						easterEggPlayer = MediaPlayer.create(getApplicationContext(), R.raw.easteregg);
						easterEggPlayer.start();
						easterEggPlayer.setOnCompletionListener(mediaPlayer -> {
							txt.animateText(getString(R.string.app_name));
							easterEggPlayer = null;
						});
						easterEggCounter = 0;
					}
					break;
			}
		});
		txt.animateText(getString(R.string.app_name));
		fancyFont = Typeface.createFromAsset(getAssets(), "fonts/hacked.ttf");
		txt.setTypeface(fancyFont);

        setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mView = findViewById(R.id.grid_view);
        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                .getInteger(R.integer.num_cols),
                StaggeredGridLayoutManager.VERTICAL));

        mView.setAdapter(new SoundAdapter(SoundStore.getAllSounds(this), withAnimations));
        ((SoundAdapter) mView.getAdapter()).showAllSounds(getApplicationContext());

        mView.addItemDecoration(new BottomOffsetDecoration(225));

        initFAB();
        initDrawer(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        soundPlayer = new SoundPlayer(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        soundPlayer.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item, menu);
        initSearchView(menu);
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

    @NeedsPermission(Manifest.permission.WRITE_SETTINGS)
    void writeSettings() {
    }

    @OnShowRationale(Manifest.permission.WRITE_SETTINGS)
    void writeSettingsOnShowRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("You need to grant permission first in order to be ale to set sounds as ringtones, notifications or alarms.")
                .setPositiveButton("Grant", (dialogInterface, i) -> request.proceed())
                .setNegativeButton("Deny", (dialogInterface, i) -> request.cancel())
                .show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_SETTINGS)
    void writeSettingsOnPermissionDenied() {
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_SETTINGS)
    void writeSettingsOnNeverAskAgain() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        MainActivityPermissionsDispatcher.onActivityResult(this, requestCode);
    }

    public void normalize(SoundAdapter adapter) {
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

    private void initFAB() {
		FloatingActionButton fab = findViewById(R.id.fab);
		Utils.paintThis(fab);

		fab.setOnClickListener(view -> {
        	if (easterEggPlayer != null) {
				easterEggPlayer.release();
				easterEggPlayer = null;
				((HTextView) findViewById(R.id.title_view)).animateText(getString(R.string
						.app_name));
			}
			soundPlayer.release();
			soundPlayer = new SoundPlayer(this);
		});
    }

    private void initSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        SearchView.SearchAutoComplete searchViewText = searchView.findViewById(R.id.search_src_text);
        searchView.setIconified(true);
        searchView.setIconifiedByDefault(true);

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

    public void initDrawer(Bundle instance) {
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
                                .withTextColor(savedColor),
                        new PrimaryDrawerItem().withName(R.string.all)
                                .withSetSelected(false)
                                .withIcon(FontAwesome.Icon.faw_music)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(savedColor)
                                .withSelectedIconColor(savedColor),
                        new PrimaryDrawerItem().withName(R.string.funny)
                                .withIcon(FontAwesome.Icon.faw_smile_o)
                                .withIconTintingEnabled(false)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(savedColor)
                                .withSelectedIconColor(savedColor),
                        new PrimaryDrawerItem().withName(R.string.games)
                                .withIcon(FontAwesome.Icon.faw_gamepad)
								.withIconTintingEnabled(false)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(savedColor)
                                .withSelectedIconColor(savedColor),
                        new PrimaryDrawerItem().withName(R.string.movies)
                                .withIcon(FontAwesome.Icon.faw_video_camera)
								.withIconTintingEnabled(false)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDarker))
                                .withSelectedTextColor(savedColor)
                                .withSelectedIconColor(savedColor),
                        new SectionDrawerItem().withName(R.string.options)
                                .withDivider(false)
                                .withTextColor(savedColor),
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
                                .withTextColor(savedColor),
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
                            mView.setAdapter(new SoundAdapter(SoundStore
                                    .getAllSounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showAllSounds(MainActivity.this);
                            break;
                        case 2:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(SoundStore
                                    .getFunnySounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showFunnySounds(MainActivity.this);
                            break;
                        case 3:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(SoundStore
                                    .getGamesSounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showGamesSounds(MainActivity.this);
                            break;
                        case 4:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(SoundStore
                                    .getMoviesSounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showMoviesSounds(MainActivity.this);
                            break;
                        case 6:
                            if (!((SoundAdapter) mView.getAdapter()).isFavoritesOnly())
                                ((SoundAdapter) mView.getAdapter()).onlyShowFavorites();
                            else
                                normalize((SoundAdapter) mView.getAdapter());
                            break;
                        case 9:
                            colorPicker = new ColorPicker(MainActivity.this);
                            colorPicker.setTitle(getString(R.string.hex_code) + fallbackColor);
                            colorPicker.setColors(R.array.rainbow);
                            colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                                @Override
                                public void onChooseColor(int position, int color) {
                                    if (position == -1)
                                        return;
                                    fallbackColor = String.format("#%06X", 0xFFFFFF & color);
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
			}

			@Override
			public void onDrawerOpened(View v) {
				if ((v != null) && mInputManager.isActive()) {
					mInputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
				super.onDrawerOpened(v);
			}
		};

		mDrawer.getDrawerLayout().addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();

        if (Utils.isGreenMode(MainActivity.this)) {
            ((SoundAdapter) mView.getAdapter()).setShowAnimations(false);
            mDrawer.removeItemByPosition(8);
        }
    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem
                , CompoundButton buttonView, boolean isChecked) {
            if (((SoundAdapter) mView.getAdapter()).areAnimationsShown()) {
                withAnimations = false;
                ((SoundAdapter) mView.getAdapter()).setShowAnimations(false);
            } else {
                withAnimations = true;
                ((SoundAdapter) mView.getAdapter()).setShowAnimations(true);
            }
        }
    };
}
