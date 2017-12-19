package io.github.xtonousou.soundboardx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;

abstract class Utils {

	private static boolean isExternalStorageReadOnly() {
		return Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState());
	}

	private static boolean isExternalStorageAvailable() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	static String writeSoundOnInternalStorage(Context mContext, String mType, String mFilename, int
			mResourceId) {
		if (isExternalStorageReadOnly()) {
			Toast.makeText(mContext, R.string.storage_ro,
					Toast.LENGTH_SHORT).show();
			return "";
		}

		if (!isExternalStorageAvailable()) {
			Toast.makeText(mContext, R.string.storage_unavailable,
					Toast.LENGTH_SHORT).show();
			return "";
		}

		InputStream inputStream;
		FileOutputStream outputStream;

		String pathName = Environment.getExternalStorageDirectory() + File.separator +
				mContext.getString(R.string.app_name);

		switch (mType) {
			case "ringtone":
				pathName += File.separator + mContext.getString(R.string.ringtones);
				break;
			case "notification":
				pathName += File.separator + mContext.getString(R.string.notifications);
				break;
			case "alarm":
				pathName += File.separator + mContext.getString(R.string.alarms);
				break;
		}

		File directory = new File(pathName);
		if (!directory.exists()) {
			directory.mkdirs();
		}

		pathName += File.separator + mFilename + ".mp3";

		try {
			int length;
			byte[] buffer = new byte[128];

			File file = new File(pathName);
			inputStream = mContext.getResources().openRawResource(mResourceId);
			outputStream = new FileOutputStream(file);

			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}

			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			Toast.makeText(mContext, e.toString(), Toast.LENGTH_LONG).show();
		}

		return pathName;
	}

	static boolean hasPermissions(Context context, String... permissions) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null &&
				permissions != null) {
			for (String permission : permissions) {
				if (ActivityCompat.checkSelfPermission(context, permission) !=
						PackageManager.PERMISSION_GRANTED) {
					return false;
				}
			}
		}
		return true;
	}

	static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

	static String striptease(String string) {
		return Normalizer.normalize(string, Normalizer.Form.NFD)
				.replaceAll("[\' \\p{M}]?", "");
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
		toolbar.setBackgroundColor(toolbar.getResources().getColor(R.color.colorPrimaryDarker));
    }

	static boolean isGreenMode(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
			if (((PowerManager) activity.getSystemService(Context.POWER_SERVICE)).isPowerSaveMode())
				return true;
        return false;
    }

	static void restartActivity(Activity activity) {
        activity.finish();
        activity.startActivity(activity.getIntent());
    }
}
