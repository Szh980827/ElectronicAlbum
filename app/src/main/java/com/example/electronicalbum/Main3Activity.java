package com.example.electronicalbum;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Animator;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

	private FloatingActionButton fabadd;
	private boolean isAdd = false;
	private RelativeLayout rlAddBill;
	private int[] llId = new int[]{R.id.ll_last, R.id.ll_next, R.id.ll_play};
	private LinearLayout[] ll = new LinearLayout[llId.length];
	private int[] fabId = new int[]{R.id.fab_last, R.id.fab_next, R.id.fab_play};
	private FloatingActionButton[] fab = new FloatingActionButton[fabId.length];

	private AnimatorSet addBillTranslate1;
	private AnimatorSet addBillTranslate2;
	private AnimatorSet addBillTranslate3;

	private MediaPlayer mediaPlayer;
	private int[] rawlist = new int[]{R.raw.yanhuo, R.raw.diqiuzhiyan, R.raw.haoxianghaoxiang, R.raw.forforever, R.raw.yourname, R.raw.yilei, R.raw.guowang};
	private String rawName[] = {"烟火里的尘埃", "地球之盐", "好想好想+情深深雨蒙蒙", "For Forever", "你的名字Bgm", "异类", "国王与乞丐"};
	private int playId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		initView();
		setDefaultValues();
		bindEvents();
		playRawMusic(playId);
		mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				if (playId == rawlist.length - 1) {
					playId = 0;
				} else {
					playId = playId + 1;
				}
				playRawMusic(playId);
			}
		});
	}

	private void initView() {
		fabadd = findViewById(R.id.fab1);
		rlAddBill = findViewById(R.id.rlAddBill);
		for (int i = 0; i < llId.length; i++) {
			ll[i] = findViewById(llId[i]);
		}
		for (int i = 0; i < fabId.length; i++) {
			fab[i] = findViewById(fabId[i]);
		}
	}

	private void setDefaultValues() {
		addBillTranslate1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
		addBillTranslate2 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
		addBillTranslate3 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
	}

	private void bindEvents() {
		fabadd.setOnClickListener(this);
		for (int i = 0; i < fabId.length; i++) {
			fab[i].setOnClickListener(this);
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.fab1:
				fabadd.setImageResource(isAdd ? R.mipmap.add : R.mipmap.add2);
				isAdd = !isAdd;
				rlAddBill.setVisibility(isAdd ? View.VISIBLE : View.GONE);
				if (isAdd) {
					addBillTranslate1.setTarget(ll[0]);
					addBillTranslate1.start();
					addBillTranslate2.setTarget(ll[1]);
					addBillTranslate2.start();
					addBillTranslate3.setTarget(ll[2]);
					addBillTranslate3.start();
				}
				break;
			case R.id.fab_play:
				final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				if (audioManager.isMusicActive()) {
					mediaPlayer.pause();
					Toast.makeText(this, "暂停", Toast.LENGTH_SHORT).show();
				} else {
					mediaPlayer.start();
					Toast.makeText(this, "播放", Toast.LENGTH_SHORT).show();
				}
				hideFABMenu();
				break;
			case R.id.fab_last:
				if (playId == 0) {
					playId = rawlist.length - 1;
				} else {
					playId = playId - 1;
				}
				playRawMusic(playId);
				hideFABMenu();
				break;
			case R.id.fab_next:
				if (playId == rawlist.length - 1) {
					playId = 0;
				} else {
					playId = playId + 1;
				}
				playRawMusic(playId);
				hideFABMenu();
				break;

		}
	}

	private void hideFABMenu() {
		rlAddBill.setVisibility(View.GONE);
		fabadd.setImageResource(R.mipmap.add);
		isAdd = false;
	}

	private void playRawMusic(int id) {
		if (mediaPlayer != null) {
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(this, rawlist[id]);
		mediaPlayer.start();
		Toast.makeText(this, rawName[id], Toast.LENGTH_SHORT).show();
	}
}
