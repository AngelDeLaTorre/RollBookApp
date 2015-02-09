package com.example.rollbookapp;

import java.io.FileNotFoundException;

import classes.Grade;
import classes.GradesManager;
import classes.Student;
import classes.StudentManager;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditGrade extends Activity{
	
	private Button save,delete;
	private int index;
	private int index2;
	private Grade grade;
	private Student student;
	private TextView name;
	private EditText value;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_grade);
		
		index = this.getIntent().getExtras().getInt("index"); 
		index2 = this.getIntent().getExtras().getInt("index2");
		student = StudentManager.getStudent(index);
		grade = (Grade) this.getIntent().getSerializableExtra("grade");
		//index2 = StudentManager.containAt(grade);
		
		name = (TextView) findViewById(R.id.gradeNameText);
		value = (EditText) findViewById(R.id.grade);
		
		name.setText(grade.getName());
		value.setText(Float.toString(grade.getValue()));
		
		
		save =(Button) findViewById(R.id.saveGradeEdited);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Grade newgrade = new Grade(grade.getName(), Float.parseFloat(value.getText().toString()));
				StudentManager.editGrade(index,index2, newgrade);
	
				Intent saving = new Intent( EditGrade.this , ShowStudent.class);
				saving.putExtra("from", "EditGrade");
				saving.putExtra("student", student);
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
		
		
		
		delete = (Button) findViewById(R.id.deleteGrade);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				StudentManager.removeGrade(index, index2);
				
				Intent deleteing = new Intent( EditGrade.this , ShowStudent.class);
				deleteing.putExtra("from", "EditGrade");
				deleteing.putExtra("student", student);
				deleteing.putExtra("index", index);
				
				startActivity(deleteing);
				
				
				
				
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
