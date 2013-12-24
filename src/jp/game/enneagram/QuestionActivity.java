/* Copyright (C) 2013 M.Nakamura
 *
 * This software is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 2.1 Japan License.
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://creativecommons.org/licenses/by-nc-sa/2.1/jp/legalcode
 */
package jp.game.enneagram;

import static jp.game.enneagram.Constant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

public class QuestionActivity extends Activity {
	private static final String TAG = "MainActivity";
	private List<QuestionItem> mQuestions = new ArrayList<QuestionItem>();
	int mType;
	int mPercent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_question);
			viewQuestion();
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	private void viewQuestion() {
		Log.i(TAG, "viewQuestion");
		try {
			Resources res = getResources();
			String[] questionA = res.getStringArray(R.array.questionA);
			String[] questionB = res.getStringArray(R.array.questionB);
			String[] questionC = res.getStringArray(R.array.questionC);
			String[] questionX = res.getStringArray(R.array.questionX);
			String[] questionY = res.getStringArray(R.array.questionY);
			String[] questionZ = res.getStringArray(R.array.questionZ);
			Random rand = new Random();
			mQuestions.clear();
			mQuestions.add(new QuestionItem(questionA[rand
					.nextInt(questionA.length - 1)], QuestionKind.questionA));
			mQuestions.add(new QuestionItem(questionX[rand
					.nextInt(questionX.length - 1)], QuestionKind.questionX));
			mQuestions.add(new QuestionItem(questionB[rand
					.nextInt(questionB.length - 1)], QuestionKind.questionB));
			mQuestions.add(new QuestionItem(questionY[rand
					.nextInt(questionY.length - 1)], QuestionKind.questionY));
			mQuestions.add(new QuestionItem(questionC[rand
					.nextInt(questionC.length - 1)], QuestionKind.questionC));
			mQuestions.add(new QuestionItem(questionZ[rand
					.nextInt(questionZ.length - 1)], QuestionKind.questionZ));
			mQuestions.add(new QuestionItem(questionA[rand
					.nextInt(questionA.length - 1)], QuestionKind.questionA));
			mQuestions.add(new QuestionItem(questionX[rand
					.nextInt(questionX.length - 1)], QuestionKind.questionX));
			mQuestions.add(new QuestionItem(questionB[rand
					.nextInt(questionB.length - 1)], QuestionKind.questionB));
			mQuestions.add(new QuestionItem(questionY[rand
					.nextInt(questionY.length - 1)], QuestionKind.questionY));
			mQuestions.add(new QuestionItem(questionC[rand
					.nextInt(questionC.length - 1)], QuestionKind.questionC));
			mQuestions.add(new QuestionItem(questionZ[rand
					.nextInt(questionZ.length - 1)], QuestionKind.questionZ));
			mQuestions.add(new QuestionItem(questionA[rand
					.nextInt(questionA.length - 1)], QuestionKind.questionA));
			mQuestions.add(new QuestionItem(questionX[rand
					.nextInt(questionX.length - 1)], QuestionKind.questionX));
			mQuestions.add(new QuestionItem(questionB[rand
					.nextInt(questionB.length - 1)], QuestionKind.questionB));
			mQuestions.add(new QuestionItem(questionY[rand
					.nextInt(questionY.length - 1)], QuestionKind.questionY));
			mQuestions.add(new QuestionItem(questionC[rand
					.nextInt(questionC.length - 1)], QuestionKind.questionC));
			mQuestions.add(new QuestionItem(questionZ[rand
					.nextInt(questionZ.length - 1)], QuestionKind.questionZ));
			QuestionAdapter questionAdapter = new QuestionAdapter(this, 0,
					mQuestions);
			ListView questionListView = (ListView) findViewById(R.id.questionListView);
			questionListView.setAdapter(questionAdapter);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	public enum QuestionKind {
		questionA, questionB, questionC, questionX, questionY, questionZ,
	}

	public class QuestionItem {
		String question;
		QuestionKind questionKind;
		boolean yes;
		boolean unknown;

		public QuestionItem(String question, QuestionKind questionKind) {
			this.question = question;
			this.questionKind = questionKind;
			this.yes = false;
			this.unknown = true;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getQuestion() {
			return this.question;
		}

		public void setQuestionKind(QuestionKind questionKind) {
			this.questionKind = questionKind;
		}

		public QuestionKind getQuestionKind() {
			return this.questionKind;
		}

		public void setYes(boolean yes) {
			this.yes = yes;
		}

		public boolean getYes() {
			return this.yes;
		}

		public void setUnknown(boolean unknown) {
			this.unknown = unknown;
		}

		public boolean getUnknown() {
			return this.unknown;
		}
	}

	private class QuestionAdapter extends ArrayAdapter<QuestionItem> {

		public QuestionAdapter(Context context, int resource,
				List<QuestionItem> objects) {
			super(context, resource, objects);
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			Log.i(TAG, "getView");
			View view = convertView;
			try {
				if (view == null) {
					LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					view = inflater.inflate(R.layout.listview_item, null);
				}
				LinearLayout questionLinearLayout = (LinearLayout) view
						.findViewById(R.id.questionLinearLayout);
				TextView questionTextView = (TextView) questionLinearLayout
						.findViewById(R.id.questionTextView);
				QuestionItem item = getItem(position);
				questionTextView.setText(item.getQuestion());
				RadioButton anserYesRadioButton = (RadioButton) questionLinearLayout
						.findViewById(R.id.anserYesRadioButton);
				anserYesRadioButton
						.setOnCheckedChangeListener(new OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								Log.i(TAG, "onCheckedChanged");
								try {
									mQuestions.get(position).setYes(isChecked);
								} catch (Exception e) {
									ErrorReport.LogException(QuestionActivity.this,
											e);
								}
							}
						});
				RadioButton anserUnknownRadioButton = (RadioButton) questionLinearLayout
						.findViewById(R.id.anserUnknownRadioButton);
				anserUnknownRadioButton.setChecked(mQuestions.get(position)
						.getUnknown());
				anserUnknownRadioButton
						.setOnCheckedChangeListener(new OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								Log.i(TAG, "onCheckedChanged");
								try {
									mQuestions.get(position).setUnknown(
											isChecked);
								} catch (Exception e) {
									ErrorReport.LogException(QuestionActivity.this,
											e);
								}
							}
						});
			} catch (Exception e) {
				ErrorReport.LogException(QuestionActivity.this, e);
			}
			return view;
		}
	}

	private void Analyze() {
		Log.i(TAG, "Analyze");
		try {
			int[] question1 = new int[3];
			int[] question2 = new int[3];
			for (QuestionItem item : mQuestions) {
				switch (item.questionKind) {
				case questionA:
					if (item.yes)
						question1[0] += 4;
					else if (item.unknown)
						question1[0] += 1;
					break;
				case questionB:
					if (item.yes)
						question1[1] += 4;
					else if (item.unknown)
						question1[1] += 1;
					break;
				case questionC:
					if (item.yes)
						question1[2] += 4;
					else if (item.unknown)
						question1[2] += 1;
					break;
				case questionX:
					if (item.yes)
						question2[0] += 4;
					else if (item.unknown)
						question2[0] += 1;
					break;
				case questionY:
					if (item.yes)
						question2[1] += 4;
					else if (item.unknown)
						question2[1] += 1;
					break;
				case questionZ:
					if (item.yes)
						question2[2] += 4;
					else if (item.unknown)
						question2[2] += 1;
					break;
				}
			}
			int type1 = 0;
			int max1 = question1[0];
			int sum1 = 0;
			for (int i = 0; i < question1.length; i++) {
				Log.d(TAG, "question1 " + i + " " + question1[i]);
				sum1 += question1[i];
				if (max1 < question1[i]) {
					max1 = question1[i];
					type1 = i;
				}
			}
			int type2 = 0;
			int max2 = question2[0];
			int sum2 = 0;
			for (int i = 0; i < question2.length; i++) {
				Log.d(TAG, "question2 " + i + " " + question2[i]);
				sum2 += question2[i];
				if (max2 < question2[i]) {
					max2 = question2[i];
					type2 = i;
				}
			}
			Resources res = getResources();
			int[] analyzeTypes = res.getIntArray(R.array.analyzeTypes);
			mType = analyzeTypes[type1 * 3 + type2];
			mPercent = (int) ((1.0 - ((sum1 - max1) / (double) sum1
					* (sum2 - max2) / (double) sum2)) * 100);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}

	public void onClickAnalyzeButton(View view) {
		Log.i(TAG, "onClickAnalyzeButton");
		try {
			Analyze();
			Intent intent = new Intent(this, AnalyzeActivity.class);
			intent.putExtra(Intent_Type, mType);
			intent.putExtra(Intent_Percent, mPercent);
			startActivity(intent);
		} catch (Exception e) {
			ErrorReport.LogException(this, e);
		}
	}
}
