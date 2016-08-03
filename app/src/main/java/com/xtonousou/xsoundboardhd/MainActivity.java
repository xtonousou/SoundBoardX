package com.xtonousou.xsoundboardhd;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    public static final String TAG = "MainActivity";

    static final int RC_WRITE_SETNGS_PERM_AFTER_M = 0x0;
    static final int RC_WRITE_SETNGS_PERM         = 0x1;
    static final int RC_WRITE_EXST_PERM           = 0x2;
    static final int SUPPORT_ACTIVITY             = 0x3;

    int drawerSelectionPosition = 1;

    static SoundPlayer        soundPlayer;
    static InputMethodManager mInputManager;

    Toolbar      mToolbar;
    RecyclerView mView;
    Drawer       mDrawer = null;

    FloatingActionsMenu  fabMenu;
    FloatingActionButton animationToggle;
    FloatingActionButton favoritesToggle;
    FloatingActionButton muteToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Portrait only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        FavStore.init(getPreferences(Context.MODE_PRIVATE));

        mView = (RecyclerView) findViewById(R.id.grid_view);
        mInputManager = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);

        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                .getInteger(R.integer.num_cols),
                StaggeredGridLayoutManager.VERTICAL));
        mView.setAdapter(new SoundAdapter(SoundStore.getAllSounds(this)));

        beautifyToolbar();
        initDrawer(savedInstanceState);
        initFloatingButtons();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            writeSettingsPermissionAfterM();
            writeExternalStoragePermission();
        } else {
            writeSettingsPermission();
            writeExternalStoragePermission();
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
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (mDrawer != null && mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = mDrawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
     *  Generates custom text view with shimmering effect and custom typeface and applies them to the toolbar.
     *  Fonts are located at assets/fonts.
     *  Shimmering effect depends on 'com.romainpiel.shimmer:library:1.4.0@aar'
     */
    private void beautifyToolbar() {
        ShimmerTextView shimmerTextView = (ShimmerTextView) findViewById(R.id.shimmerTitle);
        Typeface font = Typeface.createFromAsset(shimmerTextView.getContext().getAssets(),
                "fonts/CaviarDreams.ttf");
        shimmerTextView.setTypeface(font);
        shimmerTextView.setTextColor((new DayColor(shimmerTextView.getContext())).getDayColor());
        Shimmer shimmer = new Shimmer();
        if (isGreenMode()) {
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
                .withActivity(this)
                .withDisplayBelowStatusBar(true)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.categories)
                                .withDivider(false),
                        new PrimaryDrawerItem().withName(R.string.all)
                                .withSetSelected(true)
                                .withIcon(FontAwesome.Icon.faw_music),
                        new PrimaryDrawerItem().withName(R.string.animals)
                                .withIcon(R.drawable.ic_pets_white_24dp)
                                .withIconTintingEnabled(true),
                        new PrimaryDrawerItem().withName(R.string.funny)
                                .withIcon(R.drawable.ic_sentiment_very_satisfied_white_24dp)
                                .withIconTintingEnabled(true),
                        new PrimaryDrawerItem().withName(R.string.games)
                                .withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem().withName(R.string.movies)
                                .withIcon(FontAwesome.Icon.faw_video_camera),
                        new PrimaryDrawerItem().withName(R.string.nsfw)
                                .withIcon(FontAwesome.Icon.faw_exclamation_circle),
                        new PrimaryDrawerItem().withName(R.string.personal)
                                .withIcon(R.drawable.ic_person_white_24dp)
                                .withIconTintingEnabled(true),
                        new SectionDrawerItem().withName(R.string.extra).withDivider(false),
                        new PrimaryDrawerItem().withName(R.string.support)
                                .withIcon(FontAwesome.Icon.faw_hand_peace_o)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                favoritesToggle.setIconDrawable(
                                        new IconicsDrawable(getApplicationContext())
                                                .icon(FontAwesome.Icon.faw_star_o)
                                                .color(Color.WHITE)
                                                .sizeDp(24)
                                );
                                drawerSelectionPosition = mDrawer.getCurrentSelectedPosition();
                                mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                        .getInteger(R.integer.num_cols),
                                        StaggeredGridLayoutManager.VERTICAL));
                                mView.swapAdapter((new SoundAdapter(SoundStore
                                        .getAllSounds(MainActivity.this))), true);
                                ((SoundAdapter) mView.getAdapter()).showAllSounds(MainActivity.this);
                                break;
                            case 2:
                                favoritesToggle.setIconDrawable(
                                        new IconicsDrawable(getApplicationContext())
                                                .icon(FontAwesome.Icon.faw_star_o)
                                                .color(Color.WHITE)
                                                .sizeDp(24)
                                );
                                drawerSelectionPosition = mDrawer.getCurrentSelectedPosition();
                                mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                        .getInteger(R.integer.num_cols),
                                        StaggeredGridLayoutManager.VERTICAL));
                                mView.swapAdapter((new SoundAdapter(SoundStore
                                        .getAnimalsSounds(MainActivity.this))), true);
                                ((SoundAdapter) mView.getAdapter()).showAnimalsSounds(MainActivity.this);
                                break;
                            case 3:
                                favoritesToggle.setIconDrawable(
                                        new IconicsDrawable(getApplicationContext())
                                                .icon(FontAwesome.Icon.faw_star_o)
                                                .color(Color.WHITE)
                                                .sizeDp(24)
                                );
                                drawerSelectionPosition = mDrawer.getCurrentSelectedPosition();
                                mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                        .getInteger(R.integer.num_cols),
                                        StaggeredGridLayoutManager.VERTICAL));
                                mView.swapAdapter((new SoundAdapter(SoundStore
                                        .getFunnySounds(MainActivity.this))), true);
                                ((SoundAdapter) mView.getAdapter()).showFunnySounds(MainActivity.this);
                                break;
                            case 4:
                                favoritesToggle.setIconDrawable(
                                        new IconicsDrawable(getApplicationContext())
                                                .icon(FontAwesome.Icon.faw_star_o)
                                                .color(Color.WHITE)
                                                .sizeDp(24)
                                );
                                drawerSelectionPosition = mDrawer.getCurrentSelectedPosition();
                                mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                        .getInteger(R.integer.num_cols),
                                        StaggeredGridLayoutManager.VERTICAL));
                                mView.swapAdapter((new SoundAdapter(SoundStore
                                        .getGamesSounds(MainActivity.this))), true);
                                ((SoundAdapter) mView.getAdapter()).showGamesSounds(MainActivity.this);
                                break;
                            case 5:
                                favoritesToggle.setIconDrawable(
                                        new IconicsDrawable(getApplicationContext())
                                                .icon(FontAwesome.Icon.faw_star_o)
                                                .color(Color.WHITE)
                                                .sizeDp(24)
                                );
                                drawerSelectionPosition = mDrawer.getCurrentSelectedPosition();
                                mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                        .getInteger(R.integer.num_cols),
                                        StaggeredGridLayoutManager.VERTICAL));
                                mView.swapAdapter((new SoundAdapter(SoundStore
                                        .getMoviesSounds(MainActivity.this))), true);
                                ((SoundAdapter) mView.getAdapter()).showMoviesSounds(MainActivity.this);
                                break;
                            case 6:
                                favoritesToggle.setIconDrawable(
                                        new IconicsDrawable(getApplicationContext())
                                                .icon(FontAwesome.Icon.faw_star_o)
                                                .color(Color.WHITE)
                                                .sizeDp(24)
                                );
                                drawerSelectionPosition = mDrawer.getCurrentSelectedPosition();
                                mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                        .getInteger(R.integer.num_cols),
                                        StaggeredGridLayoutManager.VERTICAL));
                                mView.swapAdapter((new SoundAdapter(SoundStore
                                        .getNSFWSounds(MainActivity.this))), true);
                                ((SoundAdapter) mView.getAdapter()).showNSFWSounds(MainActivity.this);
                                break;
                            case 7:
                                favoritesToggle.setIconDrawable(
                                        new IconicsDrawable(getApplicationContext())
                                                .icon(FontAwesome.Icon.faw_star_o)
                                                .color(Color.WHITE)
                                                .sizeDp(24)
                                );
                                drawerSelectionPosition = mDrawer.getCurrentSelectedPosition();
                                mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                        .getInteger(R.integer.num_cols),
                                        StaggeredGridLayoutManager.VERTICAL));
                                mView.swapAdapter((new SoundAdapter(SoundStore
                                        .getPersonalSounds(MainActivity.this))), true);
                                ((SoundAdapter) mView.getAdapter()).showPersonalSounds(MainActivity.this);
                                break;
                            // skip 8 cause there is no 8 in drawer
                            case 9:
                                mDrawer.deselect();
                                favoritesToggle.setIconDrawable(
                                        new IconicsDrawable(getApplicationContext())
                                                .icon(FontAwesome.Icon.faw_star_o)
                                                .color(Color.WHITE)
                                                .sizeDp(24)
                                );
                                Intent intent = new Intent(MainActivity.this, SupportActivity.class);
                                startActivityForResult(intent, SUPPORT_ACTIVITY);
                                break;
                        }
                        return false;
                    }
                })
                .withSelectedItemByPosition(1) // Default All Sounds
                .withSavedInstance(instance)
                .build();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this
                , mDrawer.getDrawerLayout(),
                mToolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                if (v != null && mInputManager.isActive()) {
                    mInputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                fabMenu.collapse();
                super.onDrawerOpened(v);
            }
        };

        mDrawer.getDrawerLayout().addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    /**
     *  It lets user search sounds using query change/submit.
     *  @see SearchManager searchManager, it uses SEARCH.SERVICE to look up in a list.
     *  @see SearchView.SearchAutoComplete searchViewText, custom typeface can be applied.
     *       Fonts are located at assets/fonts.
     *  @see android.widget.Filterable, adapter filters current list using Filterable.
     *  @param menu The menu.
     */
    private void initSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        SearchView.SearchAutoComplete searchViewText = (SearchView.SearchAutoComplete) searchView
                .findViewById(R.id.search_src_text);
        searchViewText.setTextColor((new DayColor(searchViewText.getContext())).getDayColor());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                favoritesToggle.setIconDrawable(
                        new IconicsDrawable(getApplicationContext())
                                .icon(FontAwesome.Icon.faw_star_o)
                                .color(Color.WHITE)
                                .sizeDp(24)
                );
                ((SoundAdapter) mView.getAdapter()).getFilter().filter(newText);
                return true;
            }
        });

    }

    /**
     *  Initializes Floating Action Buttons, and specifically FloatingActionsMenu with three FloatingActionButtons.
     *  @see FloatingActionsMenu   fab_menu        ---> onClick, expands FloatingActionButton/s.
     *  @see FloatingActionButton stopButton      ---> onClick, utilizes onPause() and onResume() to prevent a sound from playing.
     *  @see FloatingActionButton animationToggle ---> onClick, checks if animations are present  , decides, toggles.
     *  @see FloatingActionButton favoritesToggle ---> onClick, checks if favorites are only shown, decides, toggles.
     *  @see SoundAdapter         adapter         ---> adapter used.
     *  Depends on 'com.getbase:floatingactionbutton:1.10.1'.
     */
    private void initFloatingButtons() {
        fabMenu = (FloatingActionsMenu) findViewById(R.id.fab_menu);
        favoritesToggle = (FloatingActionButton) findViewById(R.id.fab_fav);
        animationToggle = (FloatingActionButton) findViewById(R.id.fab_anim);
        muteToggle = (FloatingActionButton) findViewById(R.id.fab_mute);

        fabMenu.setAlpha(0.85f);

        animationToggle.setColorPressed((new DayColor(animationToggle.getContext())).getDayColor());
        favoritesToggle.setColorPressed((new DayColor(favoritesToggle.getContext())).getDayColor());
        muteToggle.setColorPressed((new DayColor(muteToggle.getContext())).getDayColor());

        muteToggle.setIcon(R.drawable.ic_volume_off_white_24dp);
        animationToggle.setIconDrawable(
                new IconicsDrawable(getApplicationContext())
                        .icon(FontAwesome.Icon.faw_eye)
                        .color(Color.WHITE)
                        .sizeDp(24)
        );

        favoritesToggle.setIconDrawable(
                new IconicsDrawable(getApplicationContext())
                .icon(FontAwesome.Icon.faw_star_o)
                .color(Color.WHITE)
                .sizeDp(24)
        );

        favoritesToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!((SoundAdapter) mView.getAdapter()).isFavoritesOnly()) {
                    ((SoundAdapter) mView.getAdapter()).onlyShowFavorites();
                    favoritesToggle.setIconDrawable(
                            new IconicsDrawable(getApplicationContext())
                                    .icon(FontAwesome.Icon.faw_star)
                                    .color(Color.WHITE)
                                    .sizeDp(24)
                    );
                } else {
                    normalize(((SoundAdapter) mView.getAdapter()));
                    favoritesToggle.setIconDrawable(
                            new IconicsDrawable(getApplicationContext())
                            .icon(FontAwesome.Icon.faw_star_o)
                            .color(Color.WHITE)
                            .sizeDp(24)
                    );
                }
            }
        });

        if (isGreenMode()) {
            ((SoundAdapter) mView.getAdapter()).setShowAnimations(false);
            fabMenu.removeButton(animationToggle);
        } else {
            animationToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((SoundAdapter) mView.getAdapter()).areAnimationsShown()) {
                        ((SoundAdapter) mView.getAdapter()).setShowAnimations(false);
                        animationToggle.setIconDrawable(
                                new IconicsDrawable(animationToggle.getContext())
                                .icon(FontAwesome.Icon.faw_eye_slash)
                                .color(Color.WHITE)
                                .sizeDp(24)
                        );
                    } else if (!((SoundAdapter) mView.getAdapter()).areAnimationsShown()) {
                        ((SoundAdapter) mView.getAdapter()).setShowAnimations(true);
                        animationToggle.setIconDrawable(
                                new IconicsDrawable(animationToggle.getContext())
                                        .icon(FontAwesome.Icon.faw_eye)
                                        .color(Color.WHITE)
                                        .sizeDp(24)
                        );
                    }
                }
            });
        }

        muteToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(40);
                soundPlayer.release();
                soundPlayer = new SoundPlayer(MainActivity.this);
            }
        });
    }

    public void normalize(SoundAdapter adapter) {
        switch (adapter.getCategory()) {
            default:
                Log.e(TAG, "Something went completely wrong. Check normalize() or its calls.");
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

    public boolean isGreenMode() {
        boolean mode = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                ((PowerManager) this.getSystemService(Context.POWER_SERVICE))
                        .isPowerSaveMode()) {
            mode = true;
        }
        return mode;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            default:
                break;
            case EasyPermissions.SETTINGS_REQ_CODE:
                Toast.makeText(this, R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT)
                        .show();
                break;
            case RC_WRITE_SETNGS_PERM_AFTER_M:
                Toast.makeText(this, R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT)
                        .show();
                break;
            case SUPPORT_ACTIVITY:
                mDrawer.setSelectionAtPosition(drawerSelectionPosition);
                break;
        }
    }

    // PERMISSIONS
    @AfterPermissionGranted (RC_WRITE_EXST_PERM)
    public void writeExternalStoragePermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Have permission, do the thing!
            Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_exst),
                    RC_WRITE_EXST_PERM, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @AfterPermissionGranted(RC_WRITE_SETNGS_PERM)
    public void writeSettingsPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_SETTINGS)) {
            // Have permissions, do the thing!
            Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_settings),
                    RC_WRITE_SETNGS_PERM, Manifest.permission.WRITE_SETTINGS);
        }
    }

    @AfterPermissionGranted(RC_WRITE_SETNGS_PERM_AFTER_M)
    public void writeSettingsPermissionAfterM() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(MainActivity.this)) {
                final Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                        Uri.parse("package:" + getPackageName())); //dont change string in uri parse
                new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.DialogTheme))
                        .setTitle("Need permission")
                        .setMessage(R.string.rationale_settings)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @SuppressLint ("NewApi")
                            public void onClick(DialogInterface dialog, int which) {
                                startActivityForResult(intent, RC_WRITE_SETNGS_PERM_AFTER_M);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied permissions and checked NEVER ASK AGAIN.
        // This will display a dialog directing them to enable the permission in app settings.
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.rationale_ask_again),
                R.string.setting, R.string.cancel, null, perms);
    }
}