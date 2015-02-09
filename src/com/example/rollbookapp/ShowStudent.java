package com.example.rollbookapp;


import java.io.FileNotFoundException;

import classes.GradesManager;
import classes.Student;
import classes.StudentManager;
import android.app.ActionBar;
import android.app.Activity;
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


public class ShowStudent extends ListActivity {

	private Button edit;
	private Button addGrade;
	private Button done;
	private Button delete;
	private Student student;
	private int index;
	private TextView text;
	private GradesAdapter adapter;
	String key;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_student);



		key= (String) this.getIntent().getSerializableExtra("from");

		index = this.getIntent().getExtras().getInt("index");
		student = StudentManager.getStudent(index);
		adapter = new GradesAdapter(this, StudentManager.getStudent(index).getListGrade());
		setListAdapter(adapter);


		text= (TextView) findViewById(R.id.nameStudent);
		text.setText(student.getName()+" "+student.getLast());



		//animation
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		//int height = display.getHeight();
		Animation  moveRighttoLeft = new TranslateAnimation(width, 0, 0, 0);
		moveRighttoLeft.setDuration(1500);
		AnimationSet animation = new AnimationSet(false);
		animation.addAnimation(moveRighttoLeft);

		text.setAnimation(animation);







		edit= (Button) findViewById(R.id.editButton);
		edit.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {


				Intent edit = new Intent(ShowStudent.this, EditStudent.class);

				edit.putExtra("index", index);

				edit.putExtra("student", student);

				edit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(edit);

			}
		});

		addGrade = (Button) findViewById(R.id.gradeButton);
		addGrade.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent grade = new Intent(ShowStudent.this, AddNewGrade.class);
				grade.putExtra("from", "ShowStudent");
				grade.putExtra("student", student);
				grade.putExtra("index", index);
				grade.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(grade);

			}
		});

		done = (Button) findViewById(R.id.OKButton);
		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {





				Intent back = new Intent(ShowStudent.this, MainActivity.class);
				back.putExtra("from", "ShowStudent");
				back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				try {

					StudentManager.write(openFileOutput(StudentManager.direction, MODE_PRIVATE));

				} catch (FileNotFoundException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}
				startActivity(back);

			}
		});

		delete = (Button) findViewById(R.id.DeleteButton);
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				StudentManager.removeStudent(StudentManager.listStudents.get(index));
				Intent delete = new Intent(ShowStudent.this, MainActivity.class);
				delete.putExtra("from", "ShowStudent");
				delete.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				try {

					StudentManager.write(openFileOutput(StudentManager.direction, MODE_PRIVATE));

				} catch (FileNotFoundException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}
				startActivity(delete);

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
