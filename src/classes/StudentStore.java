package classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StudentStore {

	
	
	@SuppressWarnings("unchecked")
	public static SortedDoublyList<Student> Read(FileInputStream file)  {



		SortedDoublyList<Student> list = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;

		try {

			fis = file;
			in = new ObjectInputStream(fis);
			list = (SortedDoublyList<Student>) in.readObject();
			in.close();
		} catch (IOException ex) {

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} 

		return list;
	}



	public static void Write(SortedDoublyList<Student> list, FileOutputStream file) {


		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = file;
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
			fos.close();
			System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}



}
