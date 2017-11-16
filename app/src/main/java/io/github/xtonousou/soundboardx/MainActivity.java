package io.github.xtonousou.soundboardx;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    boolean withAnimations = true;

    SoundPlayer soundPlayer;
    Toolbar mToolbar;

    static InputMethodManager mInputManager;
    static String colorTitle = "#b71c1c";

    ColorPicker colorPicker;
    RecyclerView mView;
    Drawer mDrawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SharedPrefs.init(getPreferences(Context.MODE_PRIVATE));

        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mView = findViewById(R.id.grid_view);
        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                .getInteger(R.integer.num_cols),
                StaggeredGridLayoutManager.VERTICAL));
        mView.setAdapter(new SoundAdapter(SoundStore.getAllSounds(this), withAnimations));
        ((SoundAdapter) mView.getAdapter()).showAllSounds(getApplicationContext());

        beautifyToolbar();
        initFAB();
        initDrawer(savedInstanceState);

        if (SharedPrefs.getInstance().isFirstTime()) {
            SharedPrefs.getInstance().setNotFirstTime(false);
            SharedPrefs.getInstance().setSelectedColor(Color.RED);
        }
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

    private void beautifyToolbar() {
        final ShimmerTextView shimmerTextView = findViewById(R.id.shimmerTitle);
        final Typeface font = Typeface.createFromAsset(shimmerTextView.getContext().getAssets(),
                "fonts/CaviarDreams.ttf");
        shimmerTextView.setTypeface(font);
        new Utils().paintThis(shimmerTextView);
        Shimmer shimmer = new Shimmer();
        if (new Utils().isGreenMode(MainActivity.this)) {
            shimmer.cancel();
        } else {
            if (shimmer.isAnimating()) {
                shimmer.cancel();
            } else {
                shimmer = new Shimmer();
                shimmer.start(shimmerTextView);
                shimmer.setDuration(3500)
                        .setStartDelay(1000)
                        .setDirection(Shimmer.ANIMATION_DIRECTION_LTR);
            }
        }
    }

    public void initDrawer(Bundle instance) {
        mDrawer = new DrawerBuilder()
                .withActivity(MainActivity.this)
                .withDisplayBelowStatusBar(true)
                .withScrollToTopAfterClick(true)
                .withDrawerWidthPx((new Utils().getScreenWidth(MainActivity.this)) - 225)
                .withSliderBackgroundColorRes(R.color.primary_dark)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.categories)
                                .withDivider(false)
                                .withTextColor(new Utils().getSelectedColor()),
                        new PrimaryDrawerItem().withName(R.string.all)
                                .withSetSelected(true)
                                .withIcon(FontAwesome.Icon.faw_music)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDark))
                                .withSelectedTextColor(new Utils()
                                        .getSelectedColor())
                                .withSelectedIconColor(new Utils()
                                        .getSelectedColor()),
                        new PrimaryDrawerItem().withName(R.string.animals)
                                .withIcon(R.drawable.ic_pets_white_24dp)
                                .withIconTintingEnabled(true)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDark))
                                .withSelectedTextColor(new Utils()
                                        .getSelectedColor())
                                .withSelectedIconColor(new Utils()
                                        .getSelectedColor()),
                        new PrimaryDrawerItem().withName(R.string.funny)
                                .withIcon(R.drawable.ic_sentiment_very_satisfied_white_24dp)
                                .withIconTintingEnabled(true)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDark))
                                .withSelectedTextColor(new Utils()
                                        .getSelectedColor())
                                .withSelectedIconColor(new Utils()
                                        .getSelectedColor()),
                        new PrimaryDrawerItem().withName(R.string.games)
                                .withIcon(FontAwesome.Icon.faw_gamepad)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDark))
                                .withSelectedTextColor(new Utils()
                                        .getSelectedColor())
                                .withSelectedIconColor(new Utils()
                                        .getSelectedColor()),
                        new PrimaryDrawerItem().withName(R.string.movies)
                                .withIcon(FontAwesome.Icon.faw_video_camera)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDark))
                                .withSelectedTextColor(new Utils()
                                        .getSelectedColor())
                                .withSelectedIconColor(new Utils()
                                        .getSelectedColor()),
                        new PrimaryDrawerItem().withName(R.string.nsfw)
                                .withIcon(R.drawable.ic_wc_white_24dp)
                                .withIconTintingEnabled(true)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDark))
                                .withSelectedTextColor(new Utils()
                                        .getSelectedColor())
                                .withSelectedIconColor(new Utils()
                                        .getSelectedColor()),
                        new PrimaryDrawerItem().withName(R.string.personal)
                                .withIcon(R.drawable.ic_person_white_24dp)
                                .withIconTintingEnabled(true)
                                .withSelectedColor(ContextCompat.getColor(getApplicationContext(),
                                        R.color.colorPrimaryDark))
                                .withSelectedTextColor(new Utils()
                                        .getSelectedColor())
                                .withSelectedIconColor(new Utils()
                                        .getSelectedColor()),
                        new SectionDrawerItem().withName(R.string.options)
                                .withDivider(false)
                                .withTextColor(new Utils().getSelectedColor()),
                        new SecondaryDrawerItem().withName(R.string.favorites)
                                .withIcon(FontAwesome.Icon.faw_star)
                                .withSelectable(false),
                        new SecondarySwitchDrawerItem().withName(R.string.particles)
                                .withIcon(FontAwesome.Icon.faw_eye)
                                .withSelectable(false)
                                .withChecked(true)
                                .withOnCheckedChangeListener(onCheckedChangeListener),
                        new SectionDrawerItem().withName(R.string.misc)
                                .withDivider(false)
                                .withTextColor(new Utils().getSelectedColor()),
                        new SecondaryDrawerItem().withName(R.string.color)
                                .withIcon(FontAwesome.Icon.faw_paint_brush)
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
                                    .getAnimalsSounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showAnimalsSounds(MainActivity.this);
                            break;
                        case 3:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(SoundStore
                                    .getFunnySounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showFunnySounds(MainActivity.this);
                            break;
                        case 4:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(SoundStore
                                    .getGamesSounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showGamesSounds(MainActivity.this);
                            break;
                        case 5:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(SoundStore
                                    .getMoviesSounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showMoviesSounds(MainActivity.this);
                            break;
                        case 6:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(SoundStore
                                    .getNSFWSounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showNSFWSounds(MainActivity.this);
                            break;
                        case 7:
                            mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                    .getInteger(R.integer.num_cols),
                                    StaggeredGridLayoutManager.VERTICAL));
                            mView.setAdapter(new SoundAdapter(SoundStore
                                    .getPersonalSounds(MainActivity.this), withAnimations));
                            ((SoundAdapter) mView.getAdapter()).showPersonalSounds(MainActivity.this);
                            break;
                        case 9:
                            if (!((SoundAdapter) mView.getAdapter()).isFavoritesOnly())
                                ((SoundAdapter) mView.getAdapter()).onlyShowFavorites();
                            else
                                normalize((SoundAdapter) mView.getAdapter());
                            break;
                        case 12:
                            colorPicker = new ColorPicker(MainActivity.this);
                            colorPicker.setTitle("Current color code: " + colorTitle);
                            colorPicker.setColors(R.array.rainbow);
                            colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                                @Override
                                public void onChooseColor(int position, int color) {
                                    if (position == -1)
                                        return;
                                    colorTitle = String.format("#%06X", 0xFFFFFF & color);
                                    SharedPrefs.getInstance().setSelectedColor(color);
                                    new Utils().restartActivity(MainActivity.this);
                                }

                                @Override
                                public void onCancel() {
                                    SharedPrefs.getInstance().setSelectedColor(SharedPrefs.getInstance().getSelectedColor());
                                    new Utils().restartActivity(MainActivity.this);
                                }

                            }).setRoundColorButton(true).show();
                            break;
                    }
                    return false;
                })
                .withSelectedItemByPosition(1)
                .withSavedInstance(instance)
                .build();

        if (new Utils().isGreenMode(MainActivity.this)) {
            ((SoundAdapter) mView.getAdapter()).setShowAnimations(false);
            mDrawer.removeItemByPosition(11);
        }

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawer.getDrawerLayout(),
                mToolbar, R.string.drawer_open, R.string.drawer_close) {

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

    private void initSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        SearchView.SearchAutoComplete searchViewText = searchView.findViewById(R.id.search_src_text);
        new Utils().paintThis(searchViewText);

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

    private void initFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);

        //fab.setAlpha(0.8f);
        new Utils().paintThis(fab);

        fab.setOnClickListener(view -> {
            soundPlayer.release();
            soundPlayer = new SoundPlayer(MainActivity.this);
        });
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
                adapter.showAnimalsSounds(getApplicationContext());
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
                adapter.showNSFWSounds(getApplicationContext());
                break;
            case 6:
                adapter.showPersonalSounds(getApplicationContext());
                break;
        }
    }
}
