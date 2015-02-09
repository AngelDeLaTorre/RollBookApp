package com.example.rollbookapp;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import classes.Grade;
import classes.SortedDoublyList;


public class GradesAdapter extends BaseAdapter{

	private SortedDoublyList<Grade> listaGrade;
	private LayoutInflater inflater;
	private SortedDoublyList<Grade> listaGradeBackup;
	
	
	public GradesAdapter(Activity context, SortedDoublyList<Grade> listaGrade) {
		super();

		this.listaGrade =  new SortedDoublyList<Grade>();
		this.listaGradeBackup =new SortedDoublyList<Grade>();
		for(Grade g:listaGrade)
		{
			this.listaGrade.add(g);
			listaGradeBackup.add(g);
		}
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	
	@Override
	public int getCount() {
		
		return listaGrade.size();
	}

	@Override
	public Object getItem(int index) {
		
		return listaGrade.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Grade grade = listaGrade.get(position);
		View view = convertView;
		if(convertView==null)
			view= inflater.inflate(R.layout.only_row, null);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		textView.setText(grade.getName() +"        Score:  " + Float.toString(grade.getValue())  );
		return view;
	}

	
	
	
}