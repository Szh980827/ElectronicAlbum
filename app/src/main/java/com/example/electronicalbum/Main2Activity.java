package com.example.electronicalbum;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

	private FloatingActionButton fab;
	private Button button1, button2;
	private MediaPlayer mediaPlayer;
	private int rawlist[] = {R.raw.abc, R.raw.yourname, R.raw.one};
	private int cur = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		openRawMusicS(cur);
		fab = findViewById(R.id.fab);
		fab.setOnClickListener(this);
		button1 = findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = findViewById(R.id.button2);
		button2.setOnClickListener(this);
		//自动播放下一曲：成功
		mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				if (cur==rawlist.length-1){
					cur=0;
				}else {
					cur=cur+1;
				}
				openRawMusicS(cur);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.fab:
				final AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				if (am.isMusicActive()) {
					mediaPlayer.pause();
				} else {
					mediaPlayer.start();
				}
				break;
			case R.id.button1:
				if (cur == rawlist.length - 1) {
					cur = 0;
				} else {
					cur = cur + 1;
				}
				openRawMusicS(cur);
				break;

			case R.id.button2:
				if (cur == 0) {
					cur = rawlist.length - 1;
				} else {
					cur = cur - 1;
				}
				openRawMusicS(cur);
				break;
		}
	}

	private void openRawMusicS(int id) {
		if (mediaPlayer!=null){
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(this, rawlist[id]);
		mediaPlayer.start();
	}


}
