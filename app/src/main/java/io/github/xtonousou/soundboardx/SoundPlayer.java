package io.github.xtonousou.soundboardx;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

<<<<<<< HEAD
class SoundPlayer {

    private MediaPlayer mPlayer;
    private final Context mContext;
    private static final String TAG = "SoundPlayer";

    SoundPlayer(Context context) {
        EventBus.getDefault().register(this);
        this.mContext = context.getApplicationContext();
    }

	@Subscribe(threadMode = ThreadMode.POSTING)
    void onEvent(Sound sound) {
        playSound(sound);
    }

	@Subscribe(threadMode = ThreadMode.BACKGROUND)
    private void playSound(Sound sound) {
        int resource = sound.getResourceId();
        if (mPlayer != null) {
            if (mPlayer.isPlaying())
                mPlayer.stop();
            mPlayer.reset();
            try {
                AssetFileDescriptor afd = mContext.getResources().openRawResourceFd(resource);
                if (afd == null)
                    return;
                mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mPlayer.prepare();
            } catch (IOException | IllegalArgumentException | SecurityException e) {
                Log.e(TAG, e.getMessage());
            }
        } else {
            mPlayer = MediaPlayer.create(mContext, resource);
        }
        mPlayer.setOnPreparedListener(mp -> {
            if (mp == mPlayer) {
                mPlayer.start();
            }
        });
        mPlayer.setOnCompletionListener(mp -> EventBus.getDefault().post("Done"));
    }

	@Subscribe(threadMode = ThreadMode.POSTING)
    void release() {
		EventBus.getDefault().unregister(this);
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
=======
public class SoundPlayer {

	private MediaPlayer mPlayer;
	private Context mContext;

	private static final String TAG = "SoundPlayer";

	public SoundPlayer(Context context) {
		EventBus.getDefault().register(this);
		this.mContext = context.getApplicationContext();
	}

	@Subscribe(threadMode = ThreadMode.POSTING)
	public void onEvent(Sound sound) {
		playSound(sound);
	}

	@Subscribe(threadMode = ThreadMode.BACKGROUND)
	void playSound(Sound sound) {
		int resource = sound.getResourceId();
		if (mPlayer != null) {
			if (mPlayer.isPlaying())
				mPlayer.stop();
			mPlayer.reset();

			try {
				AssetFileDescriptor afd = mContext.getResources().openRawResourceFd(resource);
				if (afd == null)
					return;
				mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
				afd.close();
				mPlayer.prepare();
			} catch (IOException | IllegalArgumentException | SecurityException e) {
				Log.e(TAG, e.getMessage());
			}
		} else {
			mPlayer = MediaPlayer.create(mContext, resource);
		}
		mPlayer.start();
		mPlayer.setOnCompletionListener(mp -> EventBus.getDefault().post("Done"));
	}

	@Subscribe(threadMode = ThreadMode.POSTING)
	void release() {
		EventBus.getDefault().unregister(this);
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
>>>>>>> 992a5e6990f270c8082d761c2c11c8825a5df8ec
}