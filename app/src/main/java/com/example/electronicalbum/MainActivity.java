package com.example.electronicalbum;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView textView1, textView2, textView3, rang, daojishi, action;
	Handler handler, handler1, handler2, handlerRang, handlerDaojishi, handlerAction;

	private MediaPlayer mediaPlayer;
	private String talk[] =
			{"hello","hello","hello", "你好啊", "接下来", "让", "我们", "进入", "属于我们", "的", "回忆",
					"准备好了没？", "千万", "别", "眨眼", "3", "2", "1", "Action!","不","不","不",
					"我想","你还需要再等会","你还需要再等会","Wait a moment！","怕","你",
					"还没有准备好","是","不是","有点","迫不及待了呢?", "OK","现在","我们正式","开始！"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ActionBar actionbar = getSupportActionBar();
		if (actionbar != null) {
			actionbar.hide();
		}
		textView1 = findViewById(R.id.textView1);
		textView2 = findViewById(R.id.bie);
		textView3 = findViewById(R.id.zhuibeihaolema);
		rang = findViewById(R.id.rang);
		daojishi = findViewById(R.id.daojishi);
		action = findViewById(R.id.action);
		mediaPlayer = MediaPlayer.create(this,R.raw.def);
		textShan();
	}

	public void textShan() {

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String str = (String) msg.obj;
				textView2.setText("");
				textView3.setText("");
				rang.setText("");
				daojishi.setText("");
				action.setText("");
				textView1.setText(str);
				;
			}
		};
		handler1 = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String str = (String) msg.obj;
				textView1.setText("");
				textView3.setText("");
				rang.setText("");
				daojishi.setText("");
				action.setText("");
				textView2.setText(str);
				;
			}
		};
		handler2 = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String str = (String) msg.obj;
				textView1.setText("");
				textView2.setText("");
				;
				rang.setText("");
				daojishi.setText("");
				action.setText("");
				textView3.setText(str);
			}
		};
		handlerRang = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String str = (String) msg.obj;
				textView1.setText("");
				textView2.setText("");
				;
				rang.setText(str);
				daojishi.setText("");
				action.setText("");
				textView3.setText("");
			}
		};
		handlerDaojishi = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String str = (String) msg.obj;
				textView1.setText("");
				textView2.setText("");
				;
				rang.setText("");
				daojishi.setText(str);
				action.setText("");
				textView3.setText("");
			}
		};
		handlerAction = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String str = (String) msg.obj;
				textView1.setText("");
				textView2.setText("");
				;
				rang.setText("");
				daojishi.setText("");
				action.setText(str);
				textView3.setText("");
			}
		};
		new Thread() {
			@Override
			public void run() {
				mediaPlayer.start();
				for (int i = 0; i < talk.length; i++) {
					try {
						sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (talk[i].equals("准备好了没？") || talk[i].equals("Wait a moment！")) {
						Message msg = new Message();
						msg.obj = talk[i];
						handler2.sendMessage(msg);
					} else if (talk[i].equals("别")) {
						Message msg = new Message();
						msg.obj = talk[i];
						handler1.sendMessage(msg);
					} else if (talk[i].equals("让")) {
						Message msg = new Message();
						msg.obj = talk[i];
						handlerRang.sendMessage(msg);
					} else if (talk[i].equals("Action!") || talk[i].equals("开始！") || talk[i].equals("回忆")) {
						Message msg = new Message();
						msg.obj = talk[i];
						handlerAction.sendMessage(msg);
					} else if (talk[i].equals("3") || talk[i].equals("OK") || talk[i].equals("2") || talk[i].equals("1") || talk[i].equals("不")) {
						Message msg = new Message();
						msg.obj = talk[i];
						handlerDaojishi.sendMessage(msg);
					} else {
						Message msg = new Message();
						msg.obj = talk[i];
						handler.sendMessage(msg);
					}
				}
				Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
				mediaPlayer.release();
				startActivity(intent1);
				finish();
			}
		}.start();
	}
}
