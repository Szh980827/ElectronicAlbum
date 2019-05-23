package com.example.electronicalbum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class HelloActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_hello);
		final ActionBar actionbar = getSupportActionBar();
		if (actionbar != null) {
			actionbar.hide();
		}

		Thread myThread = new Thread() {
			@Override
			public void run() {
				try {
					sleep(2000);
					SharedPreferences pref = getSharedPreferences("mysetting1", MODE_PRIVATE);
					boolean isStart = pref.getBoolean("pref_key_start", true);
					if (isStart) {
						Intent intent = new Intent(HelloActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					} else {
						Intent intent1 = new Intent(HelloActivity.this, Main3Activity.class);
						startActivity(intent1);
						finish();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		myThread.start();
	}
}
