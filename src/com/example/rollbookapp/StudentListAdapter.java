package com.example.rollbookapp;

import classes.SortedDoublyList;
import classes.Student;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class StudentListAdapter extends BaseAdapter{

	private SortedDoublyList<Student> listaStudent;
	private LayoutInflater inflater;
	private SortedDoublyList<Student> listaStudentBackup;
	
	
	public StudentListAdapter(Activity context, SortedDoublyList<Student> listaStudent) {
		super();

		this.listaStudent =  new SortedDoublyList<Student>();
		this.listaStudentBackup =new SortedDoublyList<Student>();
		for(Student s:listaStudent)
		{
			this.listaStudent.add(s);
			listaStudentBackup.add(s);
		}
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	
	@Override
	public int getCount() {
		
		return listaStudent.size();
	}

	@Override
	public Object getItem(int index) {
		
		return listaStudent.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Student student = listaStudent.get(position);
		View view = convertView;
		if(convertView==null)
			view= inflater.inflate(R.layout.only_row, null);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		textView.setText(student.getName()+" "+student.getLast());
		return view;
	}

	public void filterStudent(String substring)

	{

		if(substring==""){

			listaStudent.clear();

			for(Student c: listaStudentBackup){

				listaStudent.add(c);


			}


		}

		else{

			listaStudent.clear();

			for(Student c: listaStudentBackup){

				if(c.getName().contains(substring) || c.getLast().contains(substring))

					listaStudent.add(c);

			}

			//notify ListView to Rebuild

			notifyDataSetChanged();}

	}
	
	
}
