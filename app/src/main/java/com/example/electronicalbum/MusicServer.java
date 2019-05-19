package com.example.electronicalbum;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by SongZhihao on 2019/5/19.
 */
public class MusicServer extends Service {
	private MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(this, R.raw.abc);
			mediaPlayer.setLooping(true);
			mediaPlayer.start();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mediaPlayer.stop();
	}
}
