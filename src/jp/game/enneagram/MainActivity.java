/* Copyright (C) 2013 M.Nakamura
 *
 * This software is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 2.1 Japan License.
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://creativecommons.org/licenses/by-nc-sa/2.1/jp/legalcode
 */
package jp.game.enneagram;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		try {
			// キャッチされない例外により、スレッドが突然終了したときや、
			// このスレッドに対してほかにハンドラが定義されていないときに
			// 呼び出されるデフォルトのハンドラを設定します。
			Thread.setDefaultUncaughtExceptionHandler(new ErrorReport(this));

			setContentView(R.layout.activity_main);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	@Override
	protected void onStart() {
		Log.i(TAG, "onStart");
		super.onStart();
		try {
			// エラーレポートの送信確認ダイアログを表示
			ErrorReport.SendBugReportDialog(this.getApplicationContext());
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	public void onClickOKButton(View view) {
		Log.i(TAG, "onClickOKButton");
		try {
			Intent intent = new Intent(this, QuestionActivity.class);
			startActivity(intent);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}
}
