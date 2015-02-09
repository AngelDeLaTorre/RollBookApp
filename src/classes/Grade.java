package classes;

import java.io.Serializable;


public  class Grade implements Comparable<Grade>,Serializable{
	
	private String name;
	private float value;
	
	public Grade(String name, float value) {
		this.name = name;
		this.value = value;
		if(name==null)
		{
			this.name="Magical Exam";
		}
		
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

	public void setValue(float value) {
		this.value = value;
	}

	

	@Override
	public int compareTo(Grade grade) {
	
		return this.name.compareToIgnoreCase(grade.getName());
	}


}
