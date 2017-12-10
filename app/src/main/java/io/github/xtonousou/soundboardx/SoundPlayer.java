package io.github.xtonousou.soundboardx;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

public class SoundPlayer {

	private MediaPlayer mPlayer;
	private Context mContext;

	private static final String TAG = "SoundPlayer";

	public SoundPlayer(Context context) {
		subscribeSound(this);
		this.mContext = context.getApplicationContext();
	}

	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void onEvent(Sound sound) {
		playSound(sound);
	}

	@Subscribe(threadMode = ThreadMode.ASYNC)
	void subscribeSound(SoundPlayer soundPlayer) {
		EventBus.getDefault().register(soundPlayer);
	}

	@Subscribe(threadMode = ThreadMode.ASYNC)
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

	@Subscribe(threadMode = ThreadMode.ASYNC)
	void release() {
		EventBus.getDefault().unregister(this);
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
}
