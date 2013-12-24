package jp.game.enneagram;

import static jp.game.enneagram.Constant.*;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AnalyzeActivity extends Activity {
	private static final String TAG = "AnalyzeActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_analyze);
			Intent intent = getIntent();
			int type = intent.getIntExtra(Intent_Type, 0);
			int percent = intent.getIntExtra(Intent_Percent, 0);
			Resources res = getResources();
			String[] types = res.getStringArray(R.array.types);
			String[] descriptions = res.getStringArray(R.array.descriptions);
			String percent_format = res.getString(R.string.percent_format);
			TextView typeTextView = (TextView) findViewById(R.id.typeTextView);
			TextView analyzeTextView = (TextView) findViewById(R.id.analyzeTextView);
			TextView percentTextView = (TextView) findViewById(R.id.percentTextView);
			typeTextView.setText(types[type]);
			analyzeTextView.setText(descriptions[type]);
			percentTextView.setText(String.format(percent_format, percent));
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	public void onClickOkButton(View view) {
		Log.i(TAG, "onClickOkButton");
		try {
			Intent intent = new Intent(this,
					MainActivity.class);
			startActivity(intent);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}
}
