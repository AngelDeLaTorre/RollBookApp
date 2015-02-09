package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;




public class StudentManager {


	public static SortedDoublyList<Student> listStudents = new SortedDoublyList<Student>();

	public static String direction = "Students.txt";

	public static File  file = new File(direction);



	public static void read(FileInputStream  file) throws FileNotFoundException {

		listStudents = StudentStore.Read(file);

		if (listStudents == null) {

			listStudents = new SortedDoublyList<Student>();

		}

	}


	public static void write(FileOutputStream file) {

		StudentStore.Write(listStudents,file);

	}

	//students
	public static void addStudent(Student student)
	{
		listStudents.add(student);
	}

	public static void editStudent(int index, Student student)
	{
		listStudents.set(index, student);
	}

	public static void removeStudent(Student student)
	{
		listStudents.remove(student);
	}

	public static void removeStudent(int index)
	{
		listStudents.remove(index);
	}


	public static Student getStudent(int index)
	{
		return listStudents.get(index);
	}


	//grades
	public static void addGrade(int index,Grade grade)
	{
		listStudents.get(index).addGrade(grade);
	}

	public static void editGrade(int index1,int index2, Grade grade)
	{
		listStudents.get(index1).getListGrade().set(index2, grade);
	}


	public static void removeGrade(int index1,int index2)
	{
		listStudents.get(index1).getListGrade().remove(index2);
	}


	public static int containAt(Student s){

		for(int i =0;i< listStudents.size();i++)
		{

			if(s.equals(listStudents.get(i)))
			{

				return i;

			}

		}
		return 0;
	}

	public static int containAt(Grade g){

		for(int i =0;i< listStudents.size();i++)
		{
			for(int j =0;j< listStudents.get(i).getListGrade().size();j++)
			{
				if(g.equals(listStudents.get(i).getListGrade().get(j)))
				{

					return j;

				}
			}
			}
			return 0;
		}
	}




