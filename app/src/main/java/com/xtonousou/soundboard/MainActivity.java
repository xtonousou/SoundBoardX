package com.xtonousou.soundboard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        boolean DEVELOPER_MODE = false;
//        if (DEVELOPER_MODE) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectAll()
//                    .penaltyLog()
//                    .penaltyDialog()
//                    .build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()
//                    .penaltyLog()
//                    .build());
//        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Portrait only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        FavStore.init(getPreferences(Context.MODE_PRIVATE));

        final RecyclerView grid = (RecyclerView) findViewById(R.id.grid_view);

        grid.setLayoutManager(new StaggeredGridLayoutManager(getResources()
                .getInteger(R.integer.num_cols), StaggeredGridLayoutManager.VERTICAL));
        grid.setAdapter(new SoundAdapter(SoundStore.getAllSounds(this)));

        beautifyStatusBar(getApplicationContext());
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
            if (!Settings.System.canWrite(getApplicationContext())) {
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
     *  Shimmering effect depends on "com.romainpiel.shimmer:library:1.4.0@aar"
     */
    private void beautifyToolbar() {
        ShimmerTextView shimmerTextView = (ShimmerTextView) findViewById(R.id.shimmerTitle);
        Typeface font = Typeface.createFromAsset(shimmerTextView.getContext().getAssets(), "fonts/CaviarDreams.ttf");
        shimmerTextView.setTypeface(font);
        shimmerTextView.setTextColor((new DayColor(shimmerTextView.getContext())).getDayColor());
        Shimmer shimmer = new Shimmer();
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

    /**
     *  It lets user search sounds using query change/submit.
     *  @see SearchManager searchManager, it uses SEARCH.SERVICE to look up in a list.
     *  @see SearchView.SearchAutoComplete searchViewText, custom typeface can be applied. Fonts are located at assets/fonts.
     *  @see android.widget.Filterable, adapter filters current list using Filterable.
     *  @param menu The menu.
     */
    private void initSearchView(Menu menu) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchView.SearchAutoComplete searchViewText = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        final SoundAdapter soundAdapter = ((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter());

        searchViewText.setTextColor((new DayColor(searchViewText.getContext())).getDayColor());

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)) {
                    soundAdapter.getFilter().filter("");
                } else {
                    soundAdapter.getFilter().filter(query, new Filter.FilterListener() {
                        @Override
                        public void onFilterComplete(int i) {
                            if (soundAdapter.getSounds().isEmpty()) {
                                YoYo.with(Techniques.Shake)
                                        .duration(200)
                                        .playOn(findViewById(R.id.action_search));
                            }
                        }
                    });
                }
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    soundAdapter.getFilter().filter("");
                } else {
                    soundAdapter.getFilter().filter(newText, new Filter.FilterListener() {
                        @Override
                        public void onFilterComplete(int i) {
                            if (soundAdapter.getSounds().isEmpty()) {
                                YoYo.with(Techniques.Shake)
                                        .duration(200)
                                        .playOn(findViewById(R.id.action_search));
                            }
                        }
                    });
                }
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
     *  @see SoundAdapter         adapter         ---> used adapter.
     *  Depends on 'com.getbase:floatingactionbutton:1.10.1'.
     */
    private void initFloatingButtons() {
        FloatingActionsMenu        fab_menu        = (FloatingActionsMenu ) findViewById(R.id.fab_menu);
        FloatingActionButton       stopButton      = (FloatingActionButton) findViewById(R.id.fab_stop);
        final FloatingActionButton animationToggle = (FloatingActionButton) findViewById(R.id.fab_anim);
        final FloatingActionButton favoritesToggle = (FloatingActionButton) findViewById(R.id.fab_favs);
        final SoundAdapter         adapter         = ((SoundAdapter)((RecyclerView) findViewById(R.id.grid_view)).getAdapter());

        fab_menu.setAlpha(0.75f);

        stopButton     .setIcon(R.drawable.ic_volume_off_white_24dp      );
        animationToggle.setIcon(R.drawable.ic_visibility_white_24dp      );
        favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);

        favoritesToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(40);
                if (!adapter.isFavoritesOnly()) {
                    adapter.onlyShowFavorites();
                    favoritesToggle.setIcon(R.drawable.ic_star_white_24dp);
                } else if (adapter.isFavoritesOnly()) {
                    adapter.showAllSounds(getApplicationContext());
                    favoritesToggle.setIcon(R.drawable.ic_star_border_white_24dp);
                }
            }
        });

        animationToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(40);
                if (adapter.isShowAnimations()) {
                    adapter.setShowAnimations(false);
                    animationToggle.setIcon(R.drawable.ic_visibility_off_white_24dp);
                } else if (!adapter.isShowAnimations()) {
                    adapter.setShowAnimations(true);
                    animationToggle.setIcon(R.drawable.ic_visibility_white_24dp);
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(40);
                soundPlayer.release();
                soundPlayer = new SoundPlayer(getApplicationContext());
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
    private void initNavigationDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setBackgroundColor((new DayColor(navigationView.getContext()).getDayColor()));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    default:
                        finish();
                        break;
                    case R.id.drawer_animals:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_movies:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_funny:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_nsfw:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.drawer_personal:
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView mDrawerTitle = (TextView) header.findViewById(R.id.drawer_title);
        Typeface font = Typeface.createFromAsset(mDrawerTitle.getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        mDrawerTitle.setTypeface(font);
        mDrawerTitle.setText(R.string.categories);
//        ListView listView = (ListView) findViewById(R.id.)
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

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
}