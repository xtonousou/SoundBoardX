package com.xtonousou.soundboard;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.IOException;
import de.greenrobot.event.EventBus;

public class SoundPlayer {

    private MediaPlayer mPlayer;
    private Context mContext;
    private static final String TAG = "SoundPlayer";

    public SoundPlayer(Context context) {
        EventBus.getDefault().register(this);
        this.mContext = context.getApplicationContext();
    }

    public int onEvent(Sound sound) {
        playSound(sound);
        return getDuration();
    }

    /**
     *  Plays sound.
     *  @param sound Sound.
     */
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

    /**
     *  Gets duration of the MediaPlayer.
     *  @return int
     */
    public int getDuration() {
        return mPlayer.getDuration();
    }

    /**
     *  Releases MediaPlayer and unregisters subscriber from EventBus.
     */
    public void release() {
        EventBus.getDefault().unregister(this);
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}