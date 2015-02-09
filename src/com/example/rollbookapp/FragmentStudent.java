package com.example.rollbookapp;







import java.io.FileNotFoundException;

import classes.GradesManager;
import classes.Student;
import classes.StudentManager;
import classes.StudentStore;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class FragmentStudent extends ListFragment {

	Button add;
	EditText search;

	StudentListAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		
		
		
		
		// Inflate the layout for this fragment
		View fragmentView = inflater.inflate(R.layout.fragment_student, container,false);
		//set list view
		
				adapter = new StudentListAdapter(this.getActivity(), StudentManager.listStudents);
				setListAdapter(adapter);
		
				
		
				
				
				//animation
				
				WindowManager wm = (WindowManager) this.getActivity().getSystemService(Context.WINDOW_SERVICE);
				Display display = wm.getDefaultDisplay();
				int width = display.getWidth();
				int height = display.getHeight();
				Animation  moveRighttoLeft = new TranslateAnimation(width, 0, 0, 0);
				//Animation  moveLefttoRight = new TranslateAnimation(0, width, 0, 0);
				moveRighttoLeft.setDuration(1500);
				//moveLefttoRight.setDuration(900);
				AnimationSet animation = new AnimationSet(false);
				animation.addAnimation(moveRighttoLeft);
			

				//Text setting Name
				search = (EditText) fragmentView.findViewById(R.id.search_bar);
				search.setAnimation(animation);

				search.setGravity(Gravity.LEFT);

				//Focus listener para saber cuando se va afiltrar la lista
				search.setOnFocusChangeListener(new OnFocusChangeListener() {           

					@Override

					public void onFocusChange(View v, boolean hasFocus) {

						search.setHint("search student");

					}

				});

				search.addTextChangedListener(new TextWatcher() {

					@Override
					public void afterTextChanged(Editable arg0) {
						// TODO Auto-generated method stub
					}
					@Override
					public void beforeTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub
					}
					@Override
					public void onTextChanged(CharSequence arg0, int arg1, int arg2,
							int arg3) {
						adapter.filterStudent(arg0.toString());
						
					}

				});
		
		
		//View fragmentView = inflater.inflate(R.layout.fragment_student, container,false);
		add = (Button) fragmentView.findViewById(R.id.add_button_student);
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent adding = new Intent(v.getContext() , AddNewStudent.class);
				
				adding.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(adding);
				
			}
		});
		
		
		
		return fragmentView;
	}
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		
		Intent ourIntent = new Intent(this.getActivity(), ShowStudent.class);
		int index = StudentManager.containAt((Student) l.getAdapter().getItem(position));
		ourIntent.putExtra("student", StudentManager.listStudents.get(index));
		//String index = Integer.toString(p);
		ourIntent.putExtra("from", "FragmentStudent");
		ourIntent.putExtra("index", index);
		startActivity(ourIntent);
		
		if(adapter!=null){
			adapter.notifyDataSetChanged();
		}
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getActivity().getMenuInflater().inflate(R.menu.main, menu);
		ActionBar action = this.getActivity().getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 102, 153)));
		action.setTitle("RollBook App");
		
		
		return true;
	}
	
	
}
