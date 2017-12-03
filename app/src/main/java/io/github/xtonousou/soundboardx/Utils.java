package io.github.xtonousou.soundboardx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

abstract class Utils {

	static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    static int getSelectedColor() {
        return SharedPrefs.getInstance().getSelectedColor();
    }

    static ArrayList<Sound> getSelectedList(Context context, String name) {
        switch (name) {
			default:
				break;
            case "allSounds":
				return SoundStore.getAllSounds(context);
            case "animalSounds":
				return SoundStore.getAnimalsSounds(context);
            case "funnySounds":
				return SoundStore.getFunnySounds(context);
            case "gamesSounds":
				return SoundStore.getGamesSounds(context);
            case "moviesSounds":
				return SoundStore.getMoviesSounds(context);
        }
        return null;
    }

	static void paintThis(TextView textView) {
        textView.setTextColor(SharedPrefs.getInstance().getSelectedColor());
    }

	static void paintThis(SearchView.SearchAutoComplete searchViewText) {
        searchViewText.setTextColor(SharedPrefs.getInstance().getSelectedColor());
    }

	static void paintThis(SoundAdapter.ViewHolder holder) {
        holder.itemView.setBackgroundColor(SharedPrefs.getInstance().getSelectedColor());
    }

	static void paintThis(FloatingActionButton fab) {
		fab.setColorNormal(R.color.colorPrimary);
		fab.setColorRipple(R.color.colorPrimaryDark);
		fab.setColorPressed(SharedPrefs.getInstance().getSelectedColor());
		fab.setShadowColor(R.color.accent);

        fab.setShowShadow(true);
		fab.setShadowColor(R.color.colorPrimaryDark);
	}

	static void paintThis(android.support.v7.widget.Toolbar toolbar) {
        toolbar.setBackgroundColor(SharedPrefs.getInstance().getSelectedColor());
    }


	static void initPaypal(Activity activity) {
        String url =
                "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=68KTBN3PE9U46";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

	static void initYoutube(Activity activity) {
        String url = "https://www.youtube.com/subscription_center?add_user=TheToNouSou96";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

	static void initGithub(Activity activity) {
        String url = "https://github.com/xtonousou/xSoundBoardHD";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

	static boolean isGreenMode(Activity activity) {
        boolean mode = false;
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) &&
                ((PowerManager) activity.getSystemService(Context.POWER_SERVICE)).isPowerSaveMode()) {
            mode = true;
        }
        return mode;
    }

	static void restartActivity(Activity activity) {
        activity.finish();
        activity.startActivity(activity.getIntent());
    }
}
