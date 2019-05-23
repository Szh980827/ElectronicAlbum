package com.example.electronicalbum;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.electronicalbum.zhifu.DialogUtils;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

	private Toolbar mToolbar;
	private Button mCodeButton,zhifuButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		initView();
	}


	private void initView() {
		mToolbar = findViewById(R.id.about_toolbar);
		mCodeButton = findViewById(R.id.about_bt_code);
		zhifuButton = findViewById(R.id.about_zhifu);
		setSupportActionBar(mToolbar);
		mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AboutActivity.this.onBackPressed();
			}
		});
		mCodeButton.setOnClickListener(this);
		zhifuButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.about_bt_code:
				Intent intent = new Intent();//创建Intent对象
				intent.setAction(Intent.ACTION_VIEW);//为Intent设置动作
				String data = "https://github.com/Szh980827/ElectronicAlbum";//获取编辑框里面的文本内容
				intent.setData(Uri.parse(data));//为Intent设置数据
				startActivity(intent);//将Intent传递给Activity
				break;
			case R.id.about_zhifu:
				DialogUtils.showDonateDialog(this);
				break;

		}
	}
}
