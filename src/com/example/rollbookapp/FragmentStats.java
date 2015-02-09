package com.example.rollbookapp;

import classes.Exam;
import classes.Grade;
import classes.GradesManager;
import classes.SortedDoublyList;
import classes.Student;
import classes.StudentManager;
import classes.Student_Exam;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class FragmentStats extends ListFragment {

	Button add;
	ExamListAdapter adapter;
	SortedDoublyList<Student_Exam> list;
	EditText search;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View fragmentView = inflater.inflate(R.layout.fragment_stats, container,false);
		//list.add(ex);
		adapter = new ExamListAdapter(this.getActivity(), GradesManager.getStats(StudentManager.listStudents));
		setListAdapter(adapter);





		//animation
		WindowManager wm = (WindowManager) this.getActivity().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		//int height = display.getHeight();
		Animation  moveRighttoLeft = new TranslateAnimation(width, 0, 0, 0);
		moveRighttoLeft.setDuration(1500);
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

				search.setHint("search stats");

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
				adapter.filterExam(arg0.toString());

			}

		});










		add = (Button) fragmentView.findViewById(R.id.add_button_stat);

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent adding = new Intent(v.getContext() , AddNewStudent.class);
				adding.putExtra("from", "FragmentStats");
				adding.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(adding);

			}
		});



		return fragmentView;
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		Intent ourIntent = new Intent(this.getActivity(), ShowStats.class);
		int index = GradesManager.containAt((Exam) l.getAdapter().getItem(position));
		list = new SortedDoublyList<Student_Exam>();
		for(Student s: StudentManager.listStudents)
		{
			for(Grade g: s.getListGrade())
			{

				if(g.getName().equals(GradesManager.listStats.get(index).getName()))
				{
					Student_Exam se = new Student_Exam(s.getName(), g.getValue());
					list.add(se);
				}
			}	
		}

		ourIntent.putExtra("list", list);
		ourIntent.putExtra("exam", GradesManager.listStats.get(index));
		
		ourIntent.putExtra("from", "FragmentStats");
		ourIntent.putExtra("index", index);
		startActivity(ourIntent);

		if(adapter!=null){
			adapter.notifyDataSetChanged();
		}

	}
	@SuppressLint("NewApi")
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getActivity().getMenuInflater().inflate(R.menu.main, menu);
		ActionBar action = this.getActivity().getActionBar();
		action.setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 102, 153)));
		action.setTitle("RollBook App");


		return true;
	}

}
