package com.example.electronicalbum.zhifu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by SongZhihao on 2019/5/23.
 */
public class DialogUtils {

	public static void showDonateDialog(final Activity activity) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
		dialog.setTitle("提示");
		dialog.setMessage("确认跳转到支付宝?");
		dialog.setCancelable(false);
		dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (RomUtils.checkApkExist(activity, "com.eg.android.AlipayGphone")) {
					donate(activity);
				} else {
					Toast.makeText(activity, "本机未安装支付宝", Toast.LENGTH_SHORT).show();
				}
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		dialog.show();
	}

	private static void donate(Context context) {
		String intentFullUrl = "intent://platformapi/startapp?saId=10000007&" +
				"clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2Ffkx03105dv17dy2iolgpied%3F_s" +  //这里把｛URLcode｝替换成第一步扫描的结果
				"%3Dweb-other&_t=1472443966571#Intent;" +
				"scheme=alipayqr;package=com.eg.android.AlipayGphone;end";
		try {
			Intent intent = Intent.parseUri(intentFullUrl, Intent.URI_INTENT_SCHEME);
			context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
