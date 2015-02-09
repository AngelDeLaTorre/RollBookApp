package com.example.rollbookapp;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import classes.Exam;
import classes.SortedDoublyList;
import classes.Student;

public class ExamListAdapter extends BaseAdapter{

	private SortedDoublyList<Exam> listaExam;
	private LayoutInflater inflater;
	private SortedDoublyList<Exam> listaExamBackup;


	public ExamListAdapter(Activity context, SortedDoublyList<Exam> listaExam) {
		super();

		this.listaExam =  new SortedDoublyList<Exam>();
		this.listaExamBackup =new SortedDoublyList<Exam>();
		for(Exam e:listaExam)
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
		Exam exam = listaExam.get(position);
		View view = convertView;
		if(convertView==null)
			view= inflater.inflate(R.layout.only_row, null);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		textView.setText(exam.getName());
		return view;
	}

	public void filterExam(String substring)

	{

		if(substring==""){

			listaExam.clear();

			for(Exam c: listaExamBackup){

				listaExam.add(c);


			}


		}

		else{

			listaExam.clear();

			for(Exam c: listaExamBackup){

				if(c.getName().contains(substring) )

					listaExam.add(c);

			}

			//notify ListView to Rebuild

			notifyDataSetChanged();}

	}


}
