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

import java.text.Normalizer;
import java.util.ArrayList;

abstract class Utils {

	static String striptease(String string) {
		return Normalizer.normalize(string, Normalizer.Form.NFD)
				.replaceAll("[\'| |\\p{M}]?", "");
	}

	static void normalize(SoundAdapter adapter, Context context) {
		switch (SharedPrefs.getInstance().getSelectedCategory()) {
			case 1:
				adapter.showAllSounds(context);
				break;
			case 2:
				adapter.showFunnySounds(context);
				break;
			case 3:
				adapter.showGamesSounds(context);
				break;
			case 4:
				adapter.showMoviesSounds(context);
				break;
			case 5:
				adapter.showMoviesSounds(context);
				break;
		}
	}

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
		fab.setColorRipple(SharedPrefs.getInstance().getSelectedColor());
	}

	static void paintThis(android.support.v7.widget.Toolbar toolbar) {
        //toolbar.setBackgroundColor(SharedPrefs.getInstance().getSelectedColor());
		toolbar.setBackgroundColor(toolbar.getResources().getColor(R.color.colorPrimaryDarker));
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
        String url = "https://github.com/xtonousou/soundboardx";
        Intent intent = new Intent(Intent.ACTION_VIEW).setData((Uri.parse(url)));
        activity.startActivity(intent);
    }

	static boolean isGreenMode(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			if (((PowerManager) activity.getSystemService(Context.POWER_SERVICE)).isPowerSaveMode()) {
				return true;
			}
        }
        return false;
    }

	static void restartActivity(Activity activity) {
        activity.finish();
        activity.startActivity(activity.getIntent());
    }
}
