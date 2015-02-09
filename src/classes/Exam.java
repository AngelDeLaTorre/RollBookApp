package classes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Exam implements Comparable<Exam>, Serializable{

	
	private String name;
	private int num;
	private float min;
	private float max;
	private float ave;
	private float stdv;

	

	public Exam(String name, int num, float min, float max, float ave) {
		
		this.name = name;
		this.num = num;
		this.min = min;
		this.max = max;
		this.ave = ave;
		this.stdv = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getAve() {
		return ave;
	}

	public void setAve(float ave) {
		this.ave = ave;
	}

	public float getStdv() {
		return stdv;
	}

	public void setStdv(float stdv) {
		this.stdv = stdv;
	}

	@Override
	public int compareTo(Exam ex) {
		
		return this.name.compareTo(ex.getName());
	}
	
	
	
	
	
}
