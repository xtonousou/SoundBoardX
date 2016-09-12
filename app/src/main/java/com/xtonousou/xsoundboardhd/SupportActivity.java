package com.xtonousou.xsoundboardhd;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbarSupport);
        setSupportActionBar(mToolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        beautifyToolbar();

        donateTV  = (TextView)  findViewById(R.id.donateTV   );
        subTV     = (TextView)  findViewById(R.id.subscribeTV);
        sourceTV  = (TextView)  findViewById(R.id.sourceTV   );
        versionTV = (TextView)  findViewById(R.id.version    );
        donateIm  = (ImageView) findViewById(R.id.donateIm   );
        subIm     = (ImageView) findViewById(R.id.subscribeIm);
        sourceIm  = (ImageView) findViewById(R.id.sourceIm   );

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
                new Utils().initPaypal(SupportActivity.this);
            }
        });

        subTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Utils().initYoutube(SupportActivity.this);
            }
        });

        sourceTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Utils().initGithub(SupportActivity.this);
            }
        });

        try {
            versionTV.setText((getPackageManager().getPackageInfo(getPackageName(), 0)).versionName);
            new Utils().paintThis(versionTV);
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
        new Utils().paintThis(shimmerTextView);
        Shimmer shimmer = new Shimmer();
        if (new Utils().isGreenMode(SupportActivity.this)) {
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
}
