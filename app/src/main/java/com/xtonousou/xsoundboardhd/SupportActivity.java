package com.xtonousou.xsoundboardhd;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class SupportActivity extends AppCompatActivity {

    TextView  donateTV, subTV, sourceTV, versionTV;
    ImageView donateIm, subIm, sourceIm;
    int       selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbarSupport);
        setSupportActionBar(mToolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Portrait only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        selectedColor = SharedPrefs.getInstance().getSelectedColor();

        beautifyToolbar();

        donateTV = (TextView)  findViewById(R.id.donateTV);
        subTV    = (TextView)  findViewById(R.id.subscribeTV);
        sourceTV = (TextView)  findViewById(R.id.sourceTV);
        donateIm = (ImageView) findViewById(R.id.donateIm);
        subIm    = (ImageView) findViewById(R.id.subscribeIm);
        sourceIm = (ImageView) findViewById(R.id.sourceIm);

        versionTV = (TextView) findViewById(R.id.version);

        donateIm.setImageDrawable(
                new IconicsDrawable(getApplicationContext())
                        .icon(FontAwesome.Icon.faw_paypal)
                        .color(Color.WHITE)
                        .sizeDp(24)
        );

        subIm.setImageDrawable(
                new IconicsDrawable(getApplicationContext())
                        .icon(FontAwesome.Icon.faw_youtube)
                        .color(Color.WHITE)
                        .sizeDp(24)
        );

        sourceIm.setImageDrawable(
                new IconicsDrawable(getApplicationContext())
                        .icon(FontAwesome.Icon.faw_github)
                        .color(Color.WHITE)
                        .sizeDp(24)
        );

        donateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=68KTBN3PE9U46";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        subTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/subscription_center?add_user=TheToNouSou96";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        sourceTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://github.com/xtonousou/xSoundBoardHD";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        try {
            versionTV.setText((getPackageManager().getPackageInfo(getPackageName(), 0)).versionName);
            if (selectedColor == 0)
                versionTV.setTextColor(SharedPrefs.getInstance().getSelectedColor());
            else
                versionTV.setTextColor(ContextCompat.getColor(versionTV.getContext(),
                        R.color.lavaRed));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void beautifyToolbar() {
        ShimmerTextView shimmerTextView = (ShimmerTextView) findViewById(R.id.shimmerTitleSupport);
        Typeface font = Typeface.createFromAsset(shimmerTextView.getContext().getAssets(),
                "fonts/CaviarDreams.ttf");
        shimmerTextView.setTypeface(font);
        if (selectedColor == 0)
            shimmerTextView.setTextColor(SharedPrefs.getInstance().getSelectedColor());
        else
            shimmerTextView.setTextColor(ContextCompat.getColor(shimmerTextView.getContext(),
                    R.color.lavaRed));
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

    public boolean isGreenMode() {
        boolean mode = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                ((PowerManager) this.getSystemService(Context.POWER_SERVICE))
                        .isPowerSaveMode()) {
            mode = true;
        }
        return mode;
    }
}
