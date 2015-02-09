package com.example.rollbookapp;

import java.io.FileNotFoundException;

import classes.Grade;

import classes.Student;
import classes.StudentManager;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
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
import android.widget.EditText;

public class AddNewGrade extends Activity{

	Button save;
	EditText name;
	EditText gradeValue;
	Grade grade;
	Student student;
	int index;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_grade);
		
		index= this.getIntent().getExtras().getInt("index");
		name = (EditText) findViewById(R.id.gradeName);
		gradeValue = (EditText) findViewById(R.id.grade);

		student = StudentManager.getStudent(index);
		
		
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		//int height = display.getHeight();
		Animation  moveRighttoLeft = new TranslateAnimation(width, 0, 0, 0);
		moveRighttoLeft.setDuration(1500);
		AnimationSet animation = new AnimationSet(false);
		animation.addAnimation(moveRighttoLeft);
		name.setAnimation(animation);
		gradeValue.setAnimation(animation);
		
		
		save =(Button) findViewById(R.id.saveGrade);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(gradeValue.getText().toString().equals("")){
					grade = new Grade(name.getText().toString(), 0);
				}
				else{
				grade = new Grade(name.getText().toString(), Float.parseFloat(gradeValue.getText().toString()));
				}
				
				
				
				StudentManager.addGrade(index, grade);
				// for debuging purposes
//				for(int i =0; i<1000; i++)
//				{
//				System.out.println(index);
//				System.out.println(student.getName());
//				}
				// Aqui			GradesManager.getStats(StudentManager.listStudents);
				Intent saving = new Intent( AddNewGrade.this , ShowStudent.class);
				saving.putExtra("from", "AddNewGrade");
				saving.putExtra("student",student);
				saving.putExtra("index", index);
				saving.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				try {

					StudentManager.write(openFileOutput(StudentManager.direction, MODE_PRIVATE));

					} catch (FileNotFoundException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

					}
				
				startActivity(saving);

			}
		});

	}
	@SuppressLint("NewApi")
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBar action = getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 102, 153)));
		action.setTitle("RollBook App");
		
		
		return true;
	}
}



