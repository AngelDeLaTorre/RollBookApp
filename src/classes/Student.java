package classes;

import java.io.Serializable;


public class Student implements Comparable<Student>,Serializable  {
	

	private String name;
	private String last;
	private SortedDoublyList<Grade> listGrade;
	
	public Student(String name, String last) {
		this.name = name;
		this.last = last;
		this.listGrade = new SortedDoublyList<Grade>();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public SortedDoublyList<Grade> getListGrade() {
		return listGrade;
	}
	public void setListGrade(SortedDoublyList<Grade> listGrade) {
		this.listGrade = listGrade;
	}
	
	
	public void addGrade(Grade grade) {
		this.listGrade.add(grade);
		
	}
	
	

	public void deleteGrade(int index) {
		this.listGrade.remove(index);
		
	}
	
	@Override
	public int compareTo(Student student) {
		if(this.name.compareToIgnoreCase(student.name)==0)
		{
			return this.last.compareToIgnoreCase(student.getLast());
		}
		else
		{
			return this.name.compareToIgnoreCase(student.getName());
		}
	}

	

	

	
	
	
	

}
