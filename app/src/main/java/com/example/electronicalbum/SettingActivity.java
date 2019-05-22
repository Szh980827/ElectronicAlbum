package com.example.electronicalbum;

import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
		textView = findViewById(R.id.title_text);
		textView.setText("设置");
		button = findViewById(R.id.title_back);

		SharedPreferences pref = getSharedPreferences("mysetting", MODE_PRIVATE);
		boolean isStart = pref.getBoolean("pref_key_start", false);
		boolean isPlay = pref.getBoolean("pref_key_zidong", false);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
