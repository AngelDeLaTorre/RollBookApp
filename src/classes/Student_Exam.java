package classes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student_Exam implements Comparable<Student_Exam>, Serializable{

	
	private String name;
	private float value;
	

	

	public Student_Exam(String name,float value) {
		
		this.name = name;
		this.value = value;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	

	

	@Override
	public int compareTo(Student_Exam se) {
		
		return this.name.compareTo(se.getName());
	}
	
	
	
	
	
}
