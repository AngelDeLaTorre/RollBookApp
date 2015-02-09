package com.example.rollbookapp;

import classes.Exam;
import classes.Grade;
import classes.GradesManager;
import classes.SortedDoublyList;
import classes.Student;
import classes.StudentManager;
import classes.Student_Exam;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class ShowStats extends ListActivity{
	
	Button back;
	TextView ExName;
	TextView number;
	TextView min;
	TextView max;
	TextView ave;
	TextView stdv;
	Exam ex;
	int index;
	SortedDoublyList<Student_Exam> list;
	SEAdapter adapter;
	
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_stats);
		ex = (Exam) this.getIntent().getSerializableExtra("exam");
		list = (SortedDoublyList<Student_Exam>) this.getIntent().getSerializableExtra("list"); 
		
		

		
		
		ExName = (TextView) findViewById(R.id.examTitle);
		number = (TextView) findViewById(R.id.numStudentsText2);
		min = (TextView) findViewById(R.id.minGradeT2);
		max = (TextView) findViewById(R.id.maxGradeT2);
		ave = (TextView) findViewById(R.id.avg_text2);
		stdv = (TextView) findViewById(R.id.stdvText2);
		
		
		
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		//int height = display.getHeight();
		Animation  moveRighttoLeft = new TranslateAnimation(width, 0, 0, 0);
		moveRighttoLeft.setDuration(1500);
		AnimationSet animation = new AnimationSet(false);
		animation.addAnimation(moveRighttoLeft);
		ExName.setAnimation(animation);
		number.setAnimation(animation);
		min.setAnimation(animation);
		max.setAnimation(animation);
		ave.setAnimation(animation);
		stdv.setAnimation(animation);
		
		
		
		
		index = this.getIntent().getExtras().getInt("index");
		
 		
		ExName.setText(ex.getName().toString());
		number.setText(Float.toString(ex.getNum()));
		min.setText(Float.toString(ex.getMin()));
		max.setText(Float.toString(ex.getMax()));
		ave.setText(Float.toString(ex.getAve()));
		stdv.setText(Float.toString(ex.getStdv()));
		
		
		
		
		adapter = new SEAdapter(this,this.list);
		setListAdapter(adapter);
		
		
		
		back = (Button) findViewById(R.id.backButton);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				

				finish();
			}
		});
		
		
		
		
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 102, 153)));
		action.setTitle("RollBook App");
		
		
		return true;
	}
}
