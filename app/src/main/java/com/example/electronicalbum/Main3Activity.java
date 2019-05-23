package com.example.electronicalbum;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

	private DrawerLayout drawerLayout;
	private FloatingActionButton fabadd;
	private boolean isAdd = false;
	private RelativeLayout rlAddBill;
	private int[] llId = new int[]{R.id.ll_last, R.id.ll_next, R.id.ll_play, R.id.ll_nowPlay};
	private LinearLayout[] ll = new LinearLayout[llId.length];
	private int[] fabId = new int[]{R.id.fab_last, R.id.fab_next, R.id.fab_play};
	private FloatingActionButton[] fab = new FloatingActionButton[fabId.length];
	private AnimatorSet addBillTranslate1;
	private AnimatorSet addBillTranslate2;
	private AnimatorSet addBillTranslate3;
	private AnimatorSet addBillTranslate4;
	private MediaPlayer mediaPlayer;
	private TextView textView;
	private int[] rawlist = new int[]{R.raw.chunfeng,R.raw.yanhuo, R.raw.diqiuzhiyan,
			R.raw.haoxianghaoxiang, R.raw.forforever, R.raw.yilei, R.raw.guowang,
			R.raw.wangpai, R.raw.shanhai, R.raw.weiguang, R.raw.xieerpo, R.raw.shijieshangmeiyou,
			R.raw.zuichibi, R.raw.danning, R.raw.shengsuo, R.raw.xiaojiuwo, R.raw.mumachengshi,
			R.raw.xiangwozheyang, R.raw.xiaochou, R.raw.yourname};
	private String rawName[] = {"春风十里 - 鹿先森乐队","烟火里的尘埃 - 华晨宇", "地球之盐 - 华晨宇", "好想好想+情深深雨蒙蒙 - 华晨宇",
			"For Forever - 华晨宇", "异类 - 华晨宇", "国王与乞丐 - 华晨宇", "王牌对王牌(live) - 华晨宇", "山海(live) - 华晨宇", "微光 - 华晨宇", "鞋儿破 帽儿破(live) - 华晨宇",
			"世界上没有真正的感同身受(live) - 王源", "醉赤壁 - 林俊杰", "丹宁执着 - 林俊杰", "圣所 - 林俊杰", "小酒窝 - 林俊杰", "牧马城市 - 毛不易", "像我这样的人(live) - 毛不易",
			"消愁(live) - 毛不易", "你的名字bgm"};
	private int playId = 0;

	private Photoes[] photoes = {
			new Photoes("2015/12/31-元旦晚会", R.mipmap.f2015_1231),
			new Photoes("2016/03/06", R.mipmap.f2016_36),
			new Photoes("2016/04/13-远足", R.mipmap.f2016_413),
			new Photoes("2016/10/03", R.mipmap.f2016_10_3),
			new Photoes("2017/06/13-去上海", R.mipmap.f2017_613qushanghai),
			new Photoes("2017/06/14-欢乐谷", R.mipmap.f2017_614huanle),
			new Photoes("2017/06/14-东方明珠", R.mipmap.f2017_614dongfang),
			new Photoes("2017/06/15-星空艺术馆", R.mipmap.f2017_615xingkong),
			new Photoes("2017/06/16-上海豫园", R.mipmap.yuyuan7),
			new Photoes("2017/06/17-济南", R.mipmap.f2017_6_17),
			new Photoes("2017/06/18-高二聚会", R.mipmap.f2017_6_18)
	};
	private List<Photoes> photoesList = new ArrayList<>();
	private PhotoesAdapter adapter;
	private boolean isPlay;
	private FragmentManager manager = getSupportFragmentManager();

	private long firstTime;

	private String playmode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		Log.d("Main3Activity", "onCreate");
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		drawerLayout = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeAsUpIndicator(R.mipmap.touxiang1);
		}
		textView = findViewById(R.id.tv_nowplay);


		/*
		 * NavigationView 侧滑栏单击事件
		 */
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
					case R.id.nav_setting:
						Intent intent1 = new Intent(Main3Activity.this, SettingActivity.class);
						startActivity(intent1);
						break;
					case R.id.nav_about:
						Intent intent2 = new Intent(Main3Activity.this, AboutActivity.class);
						startActivity(intent2);
						break;
				}
				drawerLayout.closeDrawers();
				return true;
			}
		});
		/*
		 * 悬浮按钮 相关方法
		 */
		initView();
		setDefaultValues();
		bindEvents();

		/*
		 * mediaPlayer 媒体播放器
		 */
		/*
		 * 获取设置、数据库信息
		 */
		SharedPreferences pref = getSharedPreferences("mysetting1", MODE_PRIVATE);
		isPlay = pref.getBoolean("pref_key_zidong", true);
		SharedPreferences pre = getSharedPreferences("data", MODE_PRIVATE);
		playId = pre.getInt("playId", 0);

		if (isPlay) {
			playRawMusic(playId);
			showPlayToast(playId);
			savaPlayId();
		} else {
			if (mediaPlayer != null) {
				mediaPlayer.stop();
				mediaPlayer.release();
			}
			mediaPlayer = MediaPlayer.create(this, rawlist[playId]);
		}
		zidongPlayer();

		/*
		 * CardView
		 */
		initPhotoes();
		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new PhotoesAdapter(photoesList);
		recyclerView.setAdapter(adapter);
	}

	/*
	 * CardView 初始化
	 */

	private void initPhotoes() {
		photoesList.clear();
		for (int i = 0; i < photoes.length; i++) {
			photoesList.add(photoes[i]);
		}
	}

	/*
	 * 接收 Main2Activity 返回的 playId
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				playId = Integer.parseInt(data.getStringExtra("PLAY_ID"));
				playRawMusic(playId);
				showPlayToast(playId);
				savaPlayId();
			}
		}
	}

	/*
	 * 侧滑栏
	 */
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

	/*
	 * 悬浮按钮的初始化
	 */
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

	/*
	 * 悬浮按钮的属性动画
	 */
	private void setDefaultValues() {
		addBillTranslate1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
		addBillTranslate2 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
		addBillTranslate3 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
		addBillTranslate4 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.set_animator);
	}

	/*
	 * 悬浮按钮的单击事件初始化
	 */
	private void bindEvents() {
		fabadd.setOnClickListener(this);
		for (int i = 0; i < fabId.length; i++) {
			fab[i].setOnClickListener(this);
		}
	}

	/*
	 * 悬浮按钮的单击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.fab1:
				textView.setText(" " + rawName[playId] + " ");
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
					addBillTranslate4.setTarget(ll[3]);
					addBillTranslate4.start();
				}
				break;
			case R.id.fab_play:
				final AudioManager audioManager1 = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				if (audioManager1.isMusicActive()) {
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
				showPlayToast(playId);
				savaPlayId();
				hideFABMenu();
				break;
			case R.id.fab_next:
				if (playId == rawlist.length - 1) {
					playId = 0;
				} else {
					playId = playId + 1;
				}
				playRawMusic(playId);
				showPlayToast(playId);
				savaPlayId();
				hideFABMenu();
				break;
		}
	}

	private void hideFABMenu() {
		rlAddBill.setVisibility(View.GONE);
		fabadd.setImageResource(R.mipmap.add);
		isAdd = false;
	}

	/*
	 * 媒体播放器 ： 音乐播放器初始化，播放
	 */
	private void playRawMusic(int id) {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		mediaPlayer = MediaPlayer.create(this, rawlist[id]);
		mediaPlayer.start();
	}

	/*
	 * 媒体播放器 ： 通过toast显示当前正在播放的音乐
	 */
	private void showPlayToast(int id) {
		Toast.makeText(this, "正在播放：" + rawName[id], Toast.LENGTH_SHORT).show();
	}

	/*
	 * 自动播放下一曲
	 */
	private void zidongPlayer() {
		mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				SharedPreferences pref1 = getSharedPreferences("mysetting1", MODE_PRIVATE);
				playmode = pref1.getString("pref_key_playmode", "列表循环");
				if (playmode.equals("列表循环")) {
					if (playId == rawlist.length - 1) {
						playId = 0;
					} else {
						playId = playId + 1;
					}
					playRawMusic(playId);
					showPlayToast(playId);
					savaPlayId();
				} else if (playmode.equals("顺序播放")) {
					if (playId == rawlist.length - 1) {
						mediaPlayer.stop();
					} else {
						playId = playId + 1;
						playRawMusic(playId);
						showPlayToast(playId);
						savaPlayId();
					}
				} else if (playmode.equals("单曲循环")) {
					playRawMusic(playId);
					showPlayToast(playId);
					savaPlayId();
				} else if (playmode.equals("随机列表")) {
					int num = new Random().nextInt(rawlist.length);
					if (num == playId) {
						num = new Random().nextInt(rawlist.length);
					}
					playId = num;
					playRawMusic(playId);
					showPlayToast(playId);
					savaPlayId();
				}
			}
		});
		mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return true;
			}
		});
	}

	/*
	 * 保存当前播放歌曲Id到数据库
	 */
	private void savaPlayId() {
		SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
		editor.putInt("playId", playId);
		editor.apply();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		hideFABMenu();
		Log.d("Main3Activity", "onRestart");
	}

	@Override
	protected void onStart() {
		super.onStart();

		Log.d("Main3Activity", "onStart");
		zidongPlayer();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("Main3Activity", "onResume");
		zidongPlayer();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("Main3Activity", "onDestroy");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("Main3Activity", "onStop");
		zidongPlayer();
	}

	/*
	 * 重写安卓返回按钮
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {// 点击了返回按键
			if (manager.getBackStackEntryCount() != 0) {
				manager.popBackStack();
			} else {
				exitApp(2000);// 退出应用
			}
			return true;// 返回true，防止该事件继续向下传播
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 退出应用
	 * 设置第二次点击退出的时间间隔
	 */
	private void exitApp(long timeInterval) {
		// 第一次肯定会进入到if判断里面，然后把firstTime重新赋值当前的系统时间
		// 然后点击第二次的时候，当点击间隔时间小于2s，那么退出应用；反之不退出应用
		if (System.currentTimeMillis() - firstTime >= timeInterval) {
			savaPlayId();
			Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
			firstTime = System.currentTimeMillis();
		} else {
			finish();// 销毁当前activity
			System.exit(0);// 完全退出应用
		}
	}

	/*
	 * 歌曲名添加演唱者
	 */
}
