package com.example.rollbookapp;


import java.io.FileNotFoundException;

import classes.Grade;
import classes.GradesManager;
import classes.Student;
import classes.StudentManager;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class EditStudent extends ListActivity {

	Button save;
	EditText name;
	EditText last;
	Student student;
	GradesAdapter adapter;
	int index;
	int indexG;
	String key;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_student);


		index =  this.getIntent().getExtras().getInt("index"); 

		student =StudentManager.getStudent(index);

		name= (EditText)findViewById(R.id.firstNameEdit2);
		last= (EditText)findViewById(R.id.lastNameEdit2);

		name.setText(student.getName());
		last.setText(student.getLast());

		adapter = new GradesAdapter(this, student.getListGrade());
		setListAdapter(adapter);

		save =(Button) findViewById(R.id.saveStudentEdit);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Student temp = new Student(name.getText().toString(), last.getText().toString());
				temp.setListGrade(student.getListGrade());

				StudentManager.editStudent(index, temp);

				Intent saving = new Intent( EditStudent.this , ShowStudent.class);
				saving.putExtra("from", "EditStudent");
				saving.putExtra("index",index);
				saving.putExtra("student",StudentManager.getStudent(index));
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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);


		Intent ourIntent = new Intent(EditStudent.this, EditGrade.class);
		indexG = StudentManager.containAt((Grade) l.getAdapter().getItem(position));
		ourIntent.putExtra("grade", student.getListGrade().get(indexG));
		
		ourIntent.putExtra("index2", indexG);
		ourIntent.putExtra("index", index);
		startActivity(ourIntent);

		if(adapter!=null){
			adapter.notifyDataSetChanged();
		}

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



