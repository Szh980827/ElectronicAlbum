package com.example.electronicalbum;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

	public static final String PHOTO_NAME = "photo_name";

	public static final String PHOTO_IMAGE_ID = "photo_image_id";

	private int[] photoesYuandan = new int[]{R.mipmap.yuandan1, R.mipmap.yuandan2, R.mipmap.yuandan3, R.mipmap.yuandan4,
			R.mipmap.yuandan5, R.mipmap.yuandan6, R.mipmap.yuandan7, R.mipmap.yuandan8, R.mipmap.yuandan9, R.mipmap.yuandan10,
			R.mipmap.yuandan11, R.mipmap.yuandan12, R.mipmap.yuandan13, R.mipmap.yuandan14, R.mipmap.yuandan15,
			R.mipmap.yuandan16, R.mipmap.yuandan17, R.mipmap.yuandan18, R.mipmap.yuandan19, R.mipmap.yuandan20};


	private int[] photoesChunqu36 = new int[]{R.mipmap.chu361, R.mipmap.chu362, R.mipmap.chu363, R.mipmap.chu364, R.mipmap.chu365,
			R.mipmap.chu366, R.mipmap.chu367, R.mipmap.chu368};

	private int[] photoesYuanzu = new int[]{R.mipmap.yuanzu1, R.mipmap.yuanzu2,
			R.mipmap.yuanzu5, R.mipmap.yuanzu6, R.mipmap.yuanzu7, R.mipmap.yuanzu8, R.mipmap.yuanzu9, R.mipmap.yuanzu10,
			R.mipmap.yuanzu11, R.mipmap.yuanzu12, R.mipmap.yuanzu13, R.mipmap.yuanzu14, R.mipmap.yuanzu15,
			R.mipmap.yuanzu16, R.mipmap.yuanzu17, R.mipmap.yuanzu3, R.mipmap.yuanzu4};

	private int[] photoesChu103 = new int[]{R.mipmap.chu1031, R.mipmap.chu1032, R.mipmap.chu1033, R.mipmap.chu1034, R.mipmap.chu10314,
			R.mipmap.chu1035, R.mipmap.chu1036, R.mipmap.chu1037, R.mipmap.chu1038, R.mipmap.chu1039, R.mipmap.chu10310,
			R.mipmap.chu10311, R.mipmap.chu10312, R.mipmap.chu10313, R.mipmap.chu10316, R.mipmap.chu10317, R.mipmap.chu10318};

	private int[] photoesQushang = new int[]{R.mipmap.qushanghai1, R.mipmap.qushanghai2, R.mipmap.qushanghai3,
			R.mipmap.qushanghai4, R.mipmap.qushanghai5, R.mipmap.qushanghai6, R.mipmap.qushanghai7,
			R.mipmap.qushanghai8, R.mipmap.qushanghai9, R.mipmap.qushanghai10, R.mipmap.qushanghai11, R.mipmap.qushanghai12};

	private int[] photoesDongfang = new int[]{R.mipmap.dongfang1, R.mipmap.dongfang2, R.mipmap.dongfang3, R.mipmap.dongfang4,
			R.mipmap.dongfang5, R.mipmap.dongfang6, R.mipmap.dongfang7, R.mipmap.dongfang8, R.mipmap.dongfang9, R.mipmap.dongfang10,
			R.mipmap.dongfang11, R.mipmap.dongfang12, R.mipmap.dongfang13, R.mipmap.dongfang14, R.mipmap.dongfang15, R.mipmap.dongfang16,
			R.mipmap.dongfang17, R.mipmap.dongfang18, R.mipmap.dongfang19};

	private int[] photoesHuanle = new int[]{R.mipmap.huanlegu2, R.mipmap.huanlegu3, R.mipmap.huanlegu4, R.mipmap.huanlegu5,
			R.mipmap.huanlegu6, R.mipmap.huanlegu7, R.mipmap.huanlegu8, R.mipmap.huanlegu9, R.mipmap.huanlegu10, R.mipmap.huanlegu11,
			R.mipmap.huanlegu12, R.mipmap.huanlegu13, R.mipmap.huanlegu14, R.mipmap.huanlegu15, R.mipmap.huanlegu16, R.mipmap.huanlegu17,};

	private int[] photoesXingkong = new int[]{R.mipmap.xingkong2, R.mipmap.xingkong3, R.mipmap.xingkong4, R.mipmap.xingkong5,
			R.mipmap.xingkong6, R.mipmap.xingkong7, R.mipmap.xingkong8, R.mipmap.xingkong9, R.mipmap.xingkong10, R.mipmap.xingkong11,
			R.mipmap.xingkong12, R.mipmap.xingkong13, R.mipmap.xingkong14, R.mipmap.xingkong15, R.mipmap.xingkong16, R.mipmap.xingkong17,
			R.mipmap.xingkong18, R.mipmap.xingkong19, R.mipmap.xingkong20};

	private int[] photoesYuyuan = new int[]{R.mipmap.yuyuan1, R.mipmap.yuyuan2, R.mipmap.yuyuan3, R.mipmap.yuyuan4, R.mipmap.yuyuan5, R.mipmap.yuyuan6,
			R.mipmap.yuyuan7, R.mipmap.yuyuan8, R.mipmap.yuyuan9, R.mipmap.yuyuan10, R.mipmap.yuyuan11, R.mipmap.yuyuan12, R.mipmap.yuyuan13, R.mipmap.yuyuan14,
			R.mipmap.yuyuan15, R.mipmap.yuyuan16, R.mipmap.yuyuan17, R.mipmap.yuyuan18,};

	private int[] photoesJinan = new int[]{R.mipmap.jinan12,R.mipmap.jinan1, R.mipmap.jinan3,  R.mipmap.jinan2, R.mipmap.jinan4, R.mipmap.jinan5, R.mipmap.jinan6,
			R.mipmap.jinan7, R.mipmap.jinan8, R.mipmap.jinan9, R.mipmap.jinan10, R.mipmap.jinan11,};

	private int[] photoesJuhui = new int[]{R.mipmap.juhui2, R.mipmap.juhui3, R.mipmap.juhui4, R.mipmap.juhui5,
			R.mipmap.juhui6, R.mipmap.juhui7, R.mipmap.juhui9, R.mipmap.juhui1};

	private String photoesName;
	private List<Photo> photoList = new ArrayList<>();
	private PhotoAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		Intent intent = getIntent();
		photoesName = intent.getStringExtra(PHOTO_NAME);
		int photoImageId = intent.getIntExtra(PHOTO_IMAGE_ID, 0);
		Toolbar toolbar = findViewById(R.id.toolbar1);
		CollapsingToolbarLayout collapsingToolbar =
				findViewById(R.id.collapsing_toolbar);
		ImageView photoImageView = findViewById(R.id.photo_image_view);

		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		collapsingToolbar.setTitle(photoesName);
		Glide.with(this).load(photoImageId).into(photoImageView);
		setPhoto();
		initPhoto();
		RecyclerView recyclerView = findViewById(R.id.recycler_view_photo);
		GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new PhotoAdapter(photoList);
		recyclerView.setAdapter(adapter);
	}


	private Photo[] setPhoto() {
		Photo[] photos = new Photo[]{};
		switch (photoesName) {
			case "2015/12/31-元旦晚会":
				Photo[] photos1 = new Photo[photoesYuandan.length];
				for (int i = 0; i < photos1.length; i++) {
					photos1[i] = new Photo(photoesYuandan[i]);
				}
				photos = photos1;
				break;
			case "2016/03/06":
				Photo[] photos2 = new Photo[photoesChunqu36.length];
				for (int i = 0; i < photos2.length; i++) {
					photos2[i] = new Photo(photoesChunqu36[i]);
				}
				photos = photos2;
				break;
			case "2016/04/13-远足":
				Photo[] photos3 = new Photo[photoesYuanzu.length];
				for (int i = 0; i < photos3.length; i++) {
					photos3[i] = new Photo(photoesYuanzu[i]);
				}
				photos = photos3;
				break;
			case "2016/10/03":
				Photo[] photos4 = new Photo[photoesChu103.length];
				for (int i = 0; i < photos4.length; i++) {
					photos4[i] = new Photo(photoesChu103[i]);
				}
				photos = photos4;
				break;
			case "2017/06/13-去上海":
				Photo[] photos5 = new Photo[photoesQushang.length];
				for (int i = 0; i < photos5.length; i++) {
					photos5[i] = new Photo(photoesQushang[i]);
				}
				photos = photos5;
				break;
			case "2017/06/14-欢乐谷":
				Photo[] photos6 = new Photo[photoesHuanle.length];
				for (int i = 0; i < photos6.length; i++) {
					photos6[i] = new Photo(photoesHuanle[i]);
				}
				photos = photos6;
				break;
			case "2017/06/14-东方明珠":
				Photo[] photos7 = new Photo[photoesDongfang.length];
				for (int i = 0; i < photos7.length; i++) {
					photos7[i] = new Photo(photoesDongfang[i]);
				}
				photos = photos7;
				break;
			case "2017/06/15-星空艺术馆":
				Photo[] photos8 = new Photo[photoesXingkong.length];
				for (int i = 0; i < photos8.length; i++) {
					photos8[i] = new Photo(photoesXingkong[i]);
				}
				photos = photos8;
				break;
			case "2017/06/16-上海豫园":
				Photo[] photos9 = new Photo[photoesYuyuan.length];
				for (int i = 0; i < photos9.length; i++) {
					photos9[i] = new Photo(photoesYuyuan[i]);
				}
				photos = photos9;
				break;
			case "2017/06/17-济南":
				Photo[] photos10 = new Photo[photoesJinan.length];
				for (int i = 0; i < photos10.length; i++) {
					photos10[i] = new Photo(photoesJinan[i]);
				}
				photos = photos10;
				break;
			case "2017/06/18-高二聚会":
				Photo[] photos11 = new Photo[photoesJuhui.length];
				for (int i = 0; i < photos11.length; i++) {
					photos11[i] = new Photo(photoesJuhui[i]);
				}
				photos = photos11;
				break;

		}
		return photos;
	}

	private void initPhoto() {
		Photo[] photos = setPhoto();
		photoList.clear();
		for (int i = 0; i < photos.length; i++) {
			photoList.add(photos[i]);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
