package com.example.electronicalbum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

	private Button title_back;
	private TextView title_text;

	private int[] rawlist = new int[]{R.raw.yanhuo, R.raw.diqiuzhiyan,
			R.raw.haoxianghaoxiang, R.raw.forforever, R.raw.yilei, R.raw.guowang,
			R.raw.wangpai, R.raw.shanhai, R.raw.weiguang, R.raw.xieerpo, R.raw.shijieshangmeiyou,
			R.raw.zuichibi, R.raw.danning, R.raw.shengsuo, R.raw.xiaojiuwo, R.raw.mumachengshi,
			R.raw.xiangwozheyang, R.raw.xiaochou, R.raw.yourname};
	private String rawName[] = {"烟火里的尘埃", "地球之盐", "好想好想+情深深雨蒙蒙",
			"For Forever", "异类", "国王与乞丐", "王牌对王牌", "山海(live)", "微光", "鞋儿破 帽儿破(live)",
			"世界上没有真正的感同身受(live)", "醉赤壁", "丹宁执着", "圣所", "小酒窝", "牧马城市", "像我这样的人(live)",
			"消愁(live)", "你的名字bgm"};
	private String rawSinger[] = {};
	private int playId;
	private List<RawList> rawLists = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		title_back = findViewById(R.id.title_back);
		title_text = findViewById(R.id.title_text);
		title_text.setText("歌曲列表");
		title_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		playId = Integer.parseInt(getIntent().getStringExtra("musicNow"));
		setString();
		initRawList();
		RawlistAdapter adapter = new RawlistAdapter(Main2Activity.this, R.layout.rawlist_item, rawLists);
		ListView listView = findViewById(R.id.lv_rawlist);
		listView.setAdapter(adapter);
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

	private void setString() {
		for (int i = 0; i < rawName.length; i++) {
			if (i < 10) {
				rawName[i] = rawName[i] + " - 华晨宇";
			} else if (i == 10) {
				rawName[i] = rawName[i] + " - 王源";
			} else if (i > 10 & i < 15) {
				rawName[i] = rawName[i] + " - 林俊杰";
			} else if (i > 14 & i < 18) {
				rawName[i] = rawName[i] + " - 毛不易";
			}
		}
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
