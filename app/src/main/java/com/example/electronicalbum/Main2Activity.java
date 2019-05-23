package com.example.electronicalbum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

	private int[] rawlist = new int[]{R.raw.chunfeng,R.raw.yanhuo, R.raw.diqiuzhiyan,
			R.raw.haoxianghaoxiang, R.raw.forforever, R.raw.yilei, R.raw.guowang,
			R.raw.wangpai, R.raw.shanhai, R.raw.weiguang, R.raw.xieerpo, R.raw.shijieshangmeiyou,
			R.raw.zuichibi, R.raw.danning, R.raw.shengsuo, R.raw.xiaojiuwo, R.raw.mumachengshi,
			R.raw.xiangwozheyang, R.raw.xiaochou, R.raw.yourname};

	private String rawName[] = {"春风十里 - 鹿先森乐队","烟火里的尘埃 - 华晨宇", "地球之盐 - 华晨宇",
			"好想好想+情深深雨蒙蒙 - 华晨宇", "For Forever - 华晨宇", "异类 - 华晨宇", "国王与乞丐 - 华晨宇",
			"王牌对王牌(live) - 华晨宇", "山海(live) - 华晨宇", "微光 - 华晨宇", "鞋儿破 帽儿破(live) - 华晨宇",
			"世界上没有真正的感同身受(live) - 王源", "醉赤壁 - 林俊杰", "丹宁执着 - 林俊杰", "圣所 - 林俊杰",
			"小酒窝 - 林俊杰", "牧马城市 - 毛不易", "像我这样的人(live) - 毛不易", "消愁(live) - 毛不易", "你的名字bgm"};

	private int playId;
	private List<RawList> rawLists = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		Toolbar toolbar = findViewById(R.id.toolbar_gequ);
		toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Main2Activity.this.onBackPressed();
			}
		});
		playId = Integer.parseInt(getIntent().getStringExtra("musicNow"));
		initRawList();
		RawlistAdapter adapter = new RawlistAdapter(Main2Activity.this, R.layout.rawlist_item, rawLists);
		ListView listView = findViewById(R.id.lv_rawlist);
		listView.setAdapter(adapter);
		//registerForContextMenu(listView);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position == playId) {
					finish();
				} else {
					Intent intent = new Intent();
					intent.putExtra("PLAY_ID", position + "");
					setResult(RESULT_OK, intent);
					finish();
				}
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.gequ_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
			case R.id.nextplay:
				Toast.makeText(this, "下一首播放", Toast.LENGTH_SHORT).show();
				break;
		}
		return super.onContextItemSelected(item);
	}


	private void initRawList() {
		for (int i = 0; i < rawlist.length; i++) {
			if (playId == i) {
				RawList rawList = new RawList(rawName[i], R.mipmap.music_w);
				rawLists.add(rawList);
			} else {
				RawList rawList = new RawList(rawName[i], R.mipmap.music);
				rawLists.add(rawList);
			}
		}
	}
}
