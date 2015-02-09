package com.example.rollbookapp;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import classes.SortedDoublyList;
import classes.Student_Exam;


public class SEAdapter extends BaseAdapter{

	private SortedDoublyList<Student_Exam> listaExam;
	private LayoutInflater inflater;
	private SortedDoublyList<Student_Exam> listaExamBackup;
	
	
	public SEAdapter(Activity context, SortedDoublyList<Student_Exam> listaExam) {
		super();

		this.listaExam =  new SortedDoublyList<Student_Exam>();
		this.listaExamBackup =new SortedDoublyList<Student_Exam>();
		for(Student_Exam e:listaExam)
		{
			this.listaExam.add(e);
			listaExamBackup.add(e);
		}
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	
	@Override
	public int getCount() {
		
		return listaExam.size();
	}

	@Override
	public Object getItem(int index) {
		
		return listaExam.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Student_Exam exam = listaExam.get(position);
		View view = convertView;
		if(convertView==null)
			view= inflater.inflate(R.layout.only_row, null);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		textView.setText(exam.getName() +"         Score: "+exam.getValue());
		return view;
	}

	
	
	
}
