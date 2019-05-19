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

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

	private FloatingActionButton fab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.yourname);
		mediaPlayer.start();
		final AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (am.isMusicActive()){
					mediaPlayer.pause();
				}else {
					mediaPlayer.start();
				}
			}
		});

	}
}
