package com.xtonousou.soundboard;

import java.util.Calendar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.StrictMode;
import android.os.Vibrator;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.SearchManager;
import android.graphics.Typeface;
import android.widget.Toast;
import android.net.Uri;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.software.shell.fab.ActionButton;

public class MainActivity extends AppCompatActivity {

    boolean DEVELOPER_MODE = false; // If debug set true
    static SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (DEVELOPER_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDialog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll()
                    .penaltyLog()
                    .build());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
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

        beautifyStatusBar(this.getApplicationContext());
        beautifyToolbar(mToolbar);
        createStopButton();
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

        createSearchView(menu);

        menu.add(1, Menu.FIRST    , Menu.FIRST    , "Disable Animations"  ); // 1
        menu.add(1, Menu.FIRST + 1, Menu.FIRST + 1, "Toggle Favorites"    ); // 2
        menu.add(1, Menu.FIRST + 2, Menu.FIRST + 2, "Donate"              ); // 3
        menu.add(1, Menu.FIRST + 3, Menu.FIRST + 3, "Subscribe on YouTube"); // 4

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter())
                .isShowAnimations()) {
            menu.getItem(Menu.FIRST).setTitle("Disable Animations");
        } else if (!((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view))
                .getAdapter())
                .isShowAnimations()) {
            menu.getItem(Menu.FIRST).setTitle("Enable Animations");
        }

        if (((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter())
                .isFavoritesOnly()) {
            menu.getItem(Menu.FIRST + 1).setTitle("Show All Sounds");
        } else if (!((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter())
                .isFavoritesOnly()) {
            menu.getItem(Menu.FIRST + 1).setTitle("Show Favorites Only");
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                if(((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter())
                        .isShowAnimations()) {
                    ((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter())
                            .setShowAnimations(false);
                    Toast msg = Toast.makeText(MainActivity.this, "Animations Disabled", Toast.LENGTH_SHORT);
                    msg.show();
                } else {
                    ((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter())
                            .setShowAnimations(true);
                    Toast msg = Toast.makeText(MainActivity.this, "Animations Enabled", Toast.LENGTH_SHORT);
                    msg.show();
                }
                return true;
            case 2:
                if(!((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter())
                        .isFavoritesOnly()) {
                    ((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter())
                            .onlyShowFavorites();
                    Toast msg = Toast.makeText(MainActivity.this, "Favorites Only", Toast.LENGTH_SHORT);
                    msg.show();
                } else {
                    ((SoundAdapter) ((RecyclerView) findViewById(R.id.grid_view)).getAdapter())
                            .showAllSounds(getApplicationContext());
                    Toast msg = Toast.makeText(MainActivity.this, "All Sounds", Toast.LENGTH_SHORT);
                    msg.show();
                }
                return true;
            case 3:
                playThankYou();
                String url = "https://www.paypal.com/PAYPAL_DONATION_LINK_HERE";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return true;
            case 4:
                playThankYou();
                String url2 = "https://www.youtube.com/user/YOUTUBE_CHANNEL_NAME_HERE";
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse(url2));
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *  If Build Version is equal or greater than LOLLIPOP, it changes status bar's color.
     *  See getAccentColor() method for color details.
     */
    public void beautifyStatusBar(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor((new DayColor(context)).getDayColor());
        }
    }

    /**
     *  Generates custom text view with shimmering effect and custom typeface and applies them to the toolbar.
     *  Font is located at /assets/fonts/
     *  Shimmering effect depends on "com.romainpiel.shimmer:library:1.4.0@aar"
     *  @param     toolbar     the toolbar.
     */
    public void beautifyToolbar(Toolbar toolbar) {
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
        toolbar.setTitle("");
    }

    /**
     *  Creates a floating action button that mutes app's sound.
     *  onClick vibrates and mutes. [onPause(), onResume()]
     *  ActionButton depends on "com.github.shell-software:fab:1.1.2"
     */
    public void createStopButton() {
        final ActionButton actionButton = (ActionButton) findViewById(R.id.stopButton);
        actionButton.setImageResource(R.drawable.mute);
        actionButton.setImageSize(36);
        actionButton.setAlpha(0.6f);
        actionButton.setRippleEffectEnabled(true);
        actionButton.setButtonColor(getResources().getColor(R.color.fab_material_black));
        actionButton.setButtonColorPressed(getResources().getColor(R.color.fab_material_black));
        actionButton.setButtonColorRipple((new DayColor(actionButton.getContext())).getDayColor());
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(40);
                onPause();
                onResume();
            }
        });
    }

    /**
     *  Creates a search bar that filters user's input onQueryTextSubmit and onQueryTextChange
     *  and attaches it to toolbar as a menu.
     *  Checks if adapters' titles contains user's input characters.
     *  @param menu The menu.
     */
    public void createSearchView(Menu menu) {
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);

        // Fixes soft input popping up
        searchView.clearFocus();

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        SearchView.SearchAutoComplete searchViewText = (SearchView.SearchAutoComplete)searchView
                .findViewById(R.id.search_src_text);
        searchViewText.setTextColor((new DayColor(searchViewText.getContext())).getDayColor());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((RecyclerView) findViewById(R.id.grid_view))
                        .setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                .getInteger(R.integer.num_cols), StaggeredGridLayoutManager.VERTICAL));

                ((RecyclerView) findViewById(R.id.grid_view))
                        .setAdapter(new SoundAdapter(SoundStore.getSearchResults(MainActivity.this, query)));

                SoundAdapter mAdapter = new SoundAdapter(SoundStore.getSearchResults(MainActivity.this, query));
                ((RecyclerView) findViewById(R.id.grid_view)).setAdapter(mAdapter);

                mAdapter.notifyDataSetChanged(); // data set changed

                if ((SoundStore.getSearchResults(MainActivity.this, query)).isEmpty()) {
                    YoYo.with(Techniques.Shake)
                            .duration(300)
                            .playOn(findViewById(R.id.action_search));
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((RecyclerView) findViewById(R.id.grid_view))
                        .setLayoutManager(new StaggeredGridLayoutManager(getResources()
                                .getInteger(R.integer.num_cols), StaggeredGridLayoutManager.VERTICAL));

                ((RecyclerView) findViewById(R.id.grid_view))
                        .setAdapter(new SoundAdapter(SoundStore.getSearchResults(MainActivity.this, newText)));

                SoundAdapter mAdapter = new SoundAdapter(SoundStore.getSearchResults(MainActivity.this, newText));
                ((RecyclerView) findViewById(R.id.grid_view)).setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged(); // data set changed

                if ((SoundStore.getSearchResults(MainActivity.this, newText)).isEmpty()) {
                    YoYo.with(Techniques.Shake)
                            .duration(300)
                            .playOn(findViewById(R.id.action_search));
                }
                return true;
            }
        });
    }

    public void playThankYou() {
        final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.thankyou);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.release();
            }
        });
    }
}


