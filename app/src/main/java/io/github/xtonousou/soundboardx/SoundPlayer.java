package io.github.xtonousou.soundboardx;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

import de.greenrobot.event.EventBus;

class SoundPlayer {

    private MediaPlayer mPlayer;
    private final Context mContext;
    private static final String TAG = "SoundPlayer";

    public SoundPlayer(Context context) {
        EventBus.getDefault().register(this);
        this.mContext = context.getApplicationContext();
    }

    public int onEvent(Sound sound) {
        playSound(sound);
        return getDuration();
    }

    public void playSound(Sound sound) {
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
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mp == mPlayer) {
                    mPlayer.start();
                }
            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                EventBus.getDefault().post("Done");
            }
        });
    }

    public int getDuration() {
        return mPlayer.getDuration();
    }

    public void release() {
        EventBus.getDefault().unregister(this);
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}