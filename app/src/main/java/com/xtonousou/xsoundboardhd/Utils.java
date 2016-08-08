package com.xtonousou.xsoundboardhd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.romainpiel.shimmer.ShimmerTextView;

public class Utils {

    private boolean  isPainted = false;

    public int getScreenHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public int getSelectedColor() {
        return SharedPrefs.getInstance().getSelectedColor();
    }

    public void paintThis(TextView textView) {
        if (!isPainted) {
            isPainted = true;
            textView.setTextColor(SharedPrefs.getInstance().getSelectedColor());
        } else
            textView.setTextColor(ContextCompat.getColor(textView.getContext(),
                    R.color.lavaRed));
    }

    public void paintThis(ShimmerTextView shimmerTextViewtextView) {
        if (!isPainted) {
            isPainted = true;
            shimmerTextViewtextView.setTextColor(SharedPrefs.getInstance().getSelectedColor());
        } else
            shimmerTextViewtextView.setTextColor(ContextCompat.getColor(shimmerTextViewtextView.getContext(),
                    R.color.lavaRed));
    }

    public void paintThis(SearchView.SearchAutoComplete searchViewText) {
        if (!isPainted) {
            isPainted = true;
            searchViewText.setTextColor(SharedPrefs.getInstance().getSelectedColor());
        } else
            searchViewText.setTextColor(ContextCompat.getColor(searchViewText.getContext(),
                    R.color.lavaRed));
    }

    public void paintThis(FloatingActionButton fab) {
        if (!isPainted) {
            isPainted = true;
            fab.setRippleColor(SharedPrefs.getInstance().getSelectedColor());
        } else
            fab.setRippleColor(ContextCompat.getColor(fab.getContext(),
                    R.color.lavaRed));
    }

    public void initPaypal(Activity activity) {
        String url =
                "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=68KTBN3PE9U46";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

    public void initYoutube(Activity activity) {
        String url = "https://www.youtube.com/subscription_center?add_user=TheToNouSou96";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

    public void initGithub(Activity activity) {
        String url = "https://github.com/xtonousou/xSoundBoardHD";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

    public boolean isGreenMode(Activity activity) {
        boolean mode = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                ((PowerManager) activity.getSystemService(Context.POWER_SERVICE))
                        .isPowerSaveMode()) {
            mode = true;
        }
        return mode;
    }
}
