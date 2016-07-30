package com.xtonousou.xsoundboardhd;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    public static final String TAG = "MainActivity";

    private static final int RC_WRITE_SETNGS_PERM_AFTER_M = 0x0;
    private static final int RC_WRITE_SETNGS_PERM = 0x1;
    private static final int RC_WRITE_EXST_PERM = 0x2;

    private static SoundPlayer soundPlayer;
    private RecyclerView mView;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    private FloatingActionButton animationToggle;
    private FloatingActionButton favoritesToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Portrait only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        FavStore.init(getPreferences(Context.MODE_PRIVATE));

        mView = (RecyclerView) findViewById(R.id.grid_view);

        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                .getInteger(R.integer.num_cols),
                StaggeredGridLayoutManager.VERTICAL));
        mView.setAdapter(new SoundAdapter(SoundStore.getAllSounds(this)));

        beautifyStatusBar(MainActivity.this);
        beautifyToolbar();
        initNavigationDrawer();
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

    /**
     *  If Build Version is equal or greater than LOLLIPOP, it changes status bar's color.
     *  See getAccentColor() method for color details.
     */
    private void beautifyStatusBar(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor((new DayColor(context)).getDayColor());
        }
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
                favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
                ((SoundAdapter) mView.getAdapter()).getFilter().filter(newText);
                return true;
            }
        });

    }

    /**
     *  Initializes Floating Action Buttons, and specifically FloatingActionsMenu with three FloatingActionButtons.
     *  @see FloatingActionsMenu  fab_menu        ---> onClick, expands FloatingActionButton/s.
     *  @see FloatingActionButton stopButton      ---> onClick, utilizes onPause() and onResume() to prevent a sound from playing.
     *  @see FloatingActionButton animationToggle ---> onClick, checks if animations are present  , decides, toggles.
     *  @see FloatingActionButton favoritesToggle ---> onClick, checks if favorites are only shown, decides, toggles.
     *  @see SoundAdapter         adapter         ---> adapter used.
     *  Depends on 'com.getbase:floatingactionbutton:1.10.1'.
     */
    private void initFloatingButtons() {
        final FloatingActionsMenu fabMenu = (FloatingActionsMenu) findViewById(R.id.fab_menu);
        final FloatingActionButton stopButton = (FloatingActionButton) findViewById(R.id.fab_stop);
        animationToggle = (FloatingActionButton) findViewById(R.id.fab_anim);
        favoritesToggle = (FloatingActionButton) findViewById(R.id.fab_favs);

        fabMenu.setAlpha(0.75f);

        stopButton.setIcon(R.drawable.ic_volume_off_white_24dp);
        animationToggle.setIcon(R.drawable.ic_visibility_white_24dp);
        favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);

        favoritesToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!((SoundAdapter) mView.getAdapter()).isFavoritesOnly()) {
                    ((SoundAdapter) mView.getAdapter()).onlyShowFavorites();
                    favoritesToggle.setIcon(R.drawable.ic_star_white_24dp);
                } else {
                    normalize(((SoundAdapter) mView.getAdapter()));
                    favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
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
                        animationToggle.setIcon(R.drawable.ic_visibility_off_white_24dp);
                    } else if (!((SoundAdapter) mView.getAdapter()).areAnimationsShown()) {
                        ((SoundAdapter) mView.getAdapter()).setShowAnimations(true);
                        animationToggle.setIcon(R.drawable.ic_visibility_white_24dp);
                    }
                }
            });
        }

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(40);
                soundPlayer.release();
                soundPlayer = new SoundPlayer(MainActivity.this);
            }
        });
    }

    /**
     *  Initializes Navigation Drawer.
     *  Uses onNavigationItemSelected() to handle each item,
     *          onDrawerClosed()           to handle close event and
     *          onDrawerOpened()           to handle open event.
     *  Applies custom typeface to Navigation Drawer's title.
     *  GOTO res/layout/nav_header.xml to customize header (ImageView, TextView).
     *  GOTO res/menu/menu_navigation.xml to add, delete or change Navigation Drawer's items.
     */
    @SuppressLint ("SetTextI18n")
    private void initNavigationDrawer() {
        // for navigation view item text color
        int[][] textState = new int[][] {
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed
        };

        int[] textStateColor = new int[] {
                Color.argb(75, 0, 0, 0), // disabled
                Color.BLACK,             // enabled
                Color.TRANSPARENT,       // unchecked
                Color.WHITE              // pressed
        };

        // for navigation view item icon color
        int[][] iconState = new int[][] {
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed
        };

        int[] iconStateColor = new int[] {
                Color.argb(75, 0, 0, 0), // disabled
                Color.BLACK,             // enabled
                Color.TRANSPARENT,       // unchecked
                Color.WHITE              // pressed
        };

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        final View header = navigationView.getHeaderView(0);
        final TextView mDrawerTitle = (TextView) header.findViewById(R.id.drawer_title);
        final TextView mVersion = (TextView) findViewById(R.id.drawer_version);

        ColorStateList textColorStateList = new ColorStateList(textState, textStateColor);
        ColorStateList iconColorStateList = new ColorStateList(iconState, iconStateColor);
        Typeface fontRobotoRegular = Typeface.createFromAsset(mDrawerTitle.getContext().getAssets(),
                "fonts/Roboto-Regular.ttf");

        try {
            mVersion.setText((getPackageManager().getPackageInfo(getPackageName(), 0)).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        navigationView.setBackgroundColor((new DayColor(navigationView.getContext()).getDayColor()));
        navigationView.setCheckedItem(R.id.drawer_all);
        navigationView.setItemTextColor(textColorStateList);
        navigationView.setItemIconTintList(iconColorStateList);

        mDrawerTitle.setTypeface(fontRobotoRegular);
        mDrawerTitle.setText(R.string.categories);
        mDrawerTitle.setTextColor((new DayColor(mDrawerTitle.getContext()).getDayColor()));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    default:
                        finish();
                        break;
                    case R.id.drawer_all:
                        favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
                        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                .getInteger(R.integer.num_cols),
                                StaggeredGridLayoutManager.VERTICAL));
                        mView.swapAdapter((new SoundAdapter(SoundStore
                                .getAllSounds(MainActivity.this))), true);
                        ((SoundAdapter) mView.getAdapter()).showAllSounds(MainActivity.this);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_animals:
                        favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
                        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                .getInteger(R.integer.num_cols),
                                StaggeredGridLayoutManager.VERTICAL));
                        mView.swapAdapter((new SoundAdapter(SoundStore
                                .getAnimalsSounds(MainActivity.this))), true);
                        ((SoundAdapter) mView.getAdapter()).showAnimalsSounds(MainActivity.this);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_funny:
                        favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
                        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                .getInteger(R.integer.num_cols),
                                StaggeredGridLayoutManager.VERTICAL));
                        mView.swapAdapter((new SoundAdapter(SoundStore
                                .getFunnySounds(MainActivity.this))), true);
                        ((SoundAdapter) mView.getAdapter()).showFunnySounds(MainActivity.this);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_games:
                        favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
                        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                .getInteger(R.integer.num_cols),
                                StaggeredGridLayoutManager.VERTICAL));
                        mView.swapAdapter((new SoundAdapter(SoundStore
                                .getGamesSounds(MainActivity.this))), true);
                        ((SoundAdapter) mView.getAdapter()).showGamesSounds(MainActivity.this);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_movies:
                        favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
                        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                .getInteger(R.integer.num_cols),
                                StaggeredGridLayoutManager.VERTICAL));
                        mView.swapAdapter((new SoundAdapter(SoundStore
                                .getMoviesSounds(MainActivity.this))), true);
                        ((SoundAdapter) mView.getAdapter()).showMoviesSounds(MainActivity.this);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_nsfw:
                        favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
                        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                .getInteger(R.integer.num_cols),
                                StaggeredGridLayoutManager.VERTICAL));
                        mView.swapAdapter((new SoundAdapter(SoundStore
                                .getNSFWSounds(MainActivity.this))), true);
                        ((SoundAdapter) mView.getAdapter()).showNSFWSounds(MainActivity.this);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_personal:
                        favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
                        mView.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                .getInteger(R.integer.num_cols),
                                StaggeredGridLayoutManager.VERTICAL));
                        mView.swapAdapter((new SoundAdapter(SoundStore
                                .getPersonalSounds(MainActivity.this))), true);
                        ((SoundAdapter) mView.getAdapter()).showPersonalSounds(MainActivity.this);
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
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

    // PERMISSIONS
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
        }
    }

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
                        Uri.parse("package:" + getPackageName()));
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