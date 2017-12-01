package io.github.xtonousou.soundboardx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
class Utils {

    private boolean  isPainted = false;

    int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    int getSelectedColor() {
        return SharedPrefs.getInstance().getSelectedColor();
    }

    void paintThis(TextView textView) {
        if (!isPainted) {
            isPainted = true;
            textView.setTextColor(SharedPrefs.getInstance().getSelectedColor());
        } else
            textView.setTextColor(ContextCompat.getColor(textView.getContext(),
                    R.color.color_b71c1c));
    }

    void paintThis(SearchView.SearchAutoComplete searchViewText) {
        if (!isPainted) {
            isPainted = true;
            searchViewText.setTextColor(SharedPrefs.getInstance().getSelectedColor());
        } else
            searchViewText.setTextColor(ContextCompat.getColor(searchViewText.getContext(),
                    R.color.color_b71c1c));
    }

    void paintThis(SoundAdapter.ViewHolder holder) {
        if (!isPainted) {
            isPainted = true;
            holder.itemView.setBackgroundColor(SharedPrefs.getInstance().getSelectedColor());
        } else
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(),
                    R.color.color_b71c1c));
    }

    void paintThis(FloatingActionButton fab) {
        if (!isPainted) {
            isPainted = true;
            fab.setColorRipple(SharedPrefs.getInstance().getSelectedColor());
        } else
            fab.setColorRipple(R.color.color_b71c1c);
    }

    void paintThis(FloatingActionMenu fab_menu) {
        if (!isPainted) {
            isPainted = true;
            fab_menu.setMenuButtonColorRipple(SharedPrefs.getInstance().getSelectedColor());
        } else
            fab_menu.setMenuButtonColorRipple(R.color.color_b71c1c);
    }


    void initPaypal(Activity activity) {
        String url =
                "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=68KTBN3PE9U46";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

    void initYoutube(Activity activity) {
        String url = "https://www.youtube.com/subscription_center?add_user=TheToNouSou96";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

    void initGithub(Activity activity) {
        String url = "https://github.com/xtonousou/xSoundBoardHD";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

    boolean isGreenMode(Activity activity) {
        boolean mode = false;
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) &&
                ((PowerManager) activity.getSystemService(Context.POWER_SERVICE)).isPowerSaveMode()) {
            mode = true;
        }
        return mode;
    }

    void restartActivity(Activity activity) {
        activity.finish();
        activity.startActivity(activity.getIntent());
    }
}
