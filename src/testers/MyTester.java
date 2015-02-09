package testers;


import classes.Grade;
import classes.SortedDoublyList;
import classes.Student;
import classes.StudentManager;

public class MyTester {

	
	public static <E> void main(String[] args) 
	{
		Student s = new Student("Angel", "De La Torre" );
		Grade g = new Grade("Ex1",  100);
		
		SortedDoublyList<Grade> list = new  SortedDoublyList<Grade>();
		list.add(g);
		s.setListGrade(list);
		StudentManager.listStudents.add(s);
		
		
		System.out.println(StudentManager.listStudents.get(0).getName()+" "+StudentManager.listStudents.get(0).getLast()+" "
				+StudentManager.listStudents.get(0).getListGrade().get(0).getName()+" "
				+StudentManager.listStudents.get(0).getListGrade().get(0).getValue());
		
		
		
//		SortedDoublyList<String> list = new SortedDoublyList<>();
//		list.add("Xavi");
//		list.add("Angel");
//		list.add("Rada");
//		list.add("Xavi");
//		list.add("Angel");
//		list.add("Donald");
//		
//		for( String s: list)
//		{
//			System.out.println(s);
//			
//		}
//		System.out.println("size: "+list.size());
//		
//		System.out.println(list.firstIndex("Donald"));
//		System.out.println(list.lastIndex("Donald"));
		
	}

}
