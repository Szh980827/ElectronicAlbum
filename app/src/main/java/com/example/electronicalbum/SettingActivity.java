package com.example.electronicalbum;

import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

	private TextView textView;
	private Button button;

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
