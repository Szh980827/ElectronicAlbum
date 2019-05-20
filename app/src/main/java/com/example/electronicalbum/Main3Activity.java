package com.example.electronicalbum;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

	private DrawerLayout drawerLayout;

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
	private int[] rawlist = new int[]{R.raw.yanhuo, R.raw.diqiuzhiyan,
			R.raw.haoxianghaoxiang, R.raw.forforever, R.raw.yilei, R.raw.guowang,
			R.raw.wangpai, R.raw.shanhai, R.raw.weiguang, R.raw.xieerpo, R.raw.shijieshangmeiyou,
			R.raw.zuichibi, R.raw.danning, R.raw.shengsuo, R.raw.xiaojiuwo, R.raw.mumachengshi,
			R.raw.xiangwozheyang, R.raw.xiaochou, R.raw.yourname};
	private String rawName[] = {"烟火里的尘埃", "地球之盐", "好想好想+情深深雨蒙蒙",
			"For Forever", "异类", "国王与乞丐", "王牌对王牌", "山海(live)", "微光", "鞋儿破 帽儿破(live)",
			"世界上没有真正的感同身受(live)", "醉赤壁", "丹宁执着", "圣所", "小酒窝", "牧马城市", "像我这样的人(live)",
			"消愁(live)", "你的名字bgm"};
	private int playId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		drawerLayout = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeAsUpIndicator(R.mipmap.touxiang1);
		}
		navigationView.setCheckedItem(R.id.nav_rawlist);
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				switch (menuItem.getItemId()) {
					case R.id.nav_rawlist:
						Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
						intent.putExtra("musicNow", playId + "");
						startActivityForResult(intent, 1);
						break;
					case R.id.nav_friend:
						Toast.makeText(Main3Activity.this, "friend", Toast.LENGTH_SHORT).show();
						break;
				}
				drawerLayout.closeDrawers();
				return true;
			}
		});
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				playId = Integer.parseInt(data.getStringExtra("PLAY_ID"));
				playRawMusic(playId);
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				drawerLayout.openDrawer(GravityCompat.START);
				break;
			default:

		}
		return true;
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
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(this, rawlist[id]);
		mediaPlayer.start();
		Toast.makeText(this, rawName[id], Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		hideFABMenu();

	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return true;
	}
}
