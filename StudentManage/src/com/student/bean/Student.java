package com.student.bean;

import java.util.List;

public class Student extends Users{

	private String no;
	
	private List<Score> score;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public List<Score> getScore() {
		return score;
	}

	public void setScore(List<Score> score) {
		this.score = score;
	}
	
	
}
