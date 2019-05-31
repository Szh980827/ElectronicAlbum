package com.example.electronicalbum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SettingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		Toolbar toolbar = findViewById(R.id.toolbar_setting);
		toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				SettingActivity.this.onBackPressed();
			}
		});
	}
}
