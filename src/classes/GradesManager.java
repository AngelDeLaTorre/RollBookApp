package classes;

public class GradesManager {


	public static SortedDoublyList<Exam> listStats = new SortedDoublyList<Exam>();
		public static SortedDoublyList<Float> listGrades = new SortedDoublyList<Float>();


	public static SortedDoublyList<Exam> getStats(SortedDoublyList<Student> list){

		//boolean Found = false;
		listStats.clear();
		//listGrades.clear();

		for(Student s: list)
		{

			for(Grade g : s.getListGrade())
			{

				boolean Found = false;
				Exam ex = new Exam(g.getName(),1,g.getValue(),g.getValue(),g.getValue());
				if(listStats.isEmpty())
				{
					listStats.add(ex);
				}
				else{

					for(int i=0; i<listStats.size() ; i++)
					{
						if(listStats.get(i).getName().equals(g.getName()))
						{
							//listGrades.add(g);

							listStats.set(i, updateStats(g.getValue(), listStats.get(i)));
							Found=true;

						}

					}

					if(!Found){
						listStats.add(ex);
					}



				}

			}
		}

		return listStats;

	}

	private static Exam updateStats(float value, Exam exam) {

		int number = exam.getNum();
		float min = exam.getMin();
		float max = exam.getMax();
		float ave = exam.getAve();
		float stdv = exam.getStdv();

		exam.setNum(1+number);

		if(min>value)
		{
			exam.setMin(value);
		}

		if(max<value)
		{
			exam.setMax(value);
		}

		exam.setAve((ave*(number) + value)/(number+1));



		exam.setStdv(stdvMethod(StudentManager.listStudents, exam.getName(), exam.getAve()));
		
		
//		float x = (float)(Math.pow(stdv,2)*(exam.getNum()-1)+ ( Math.pow(value-exam.getAve(),2) ) );
//
//		exam.setStdv(2* (float)Math.sqrt(x/(exam.getNum())));

		return exam;


	}


	private static float stdvMethod(SortedDoublyList<Student> listStudents, String exam, float ave) {
		//SortedDoublyList<Float> grades = new SortedDoublyList<Float>();
		listGrades.clear();
		
		for(Student s: listStudents)
		{
			
			for(Grade g: s.getListGrade())
			{
				if(exam.equals(g.getName()))
				{
					listGrades.add(g.getValue());
				}
				
			}
			
		}
		float number=0;
		for(Float f: listGrades)
		{
			number =(float) (number + Math.pow((f-ave), 2));
		}
		
		number = number/listGrades.size();
		
		return (float) Math.sqrt(number);
		
	}

	public static int containAt(Exam x){

		for(int i =0;i< listStats.size();i++)
		{

			if(x.equals(listStats.get(i)))
			{

				return i;

			}

		}
		return 0;
	}


}
