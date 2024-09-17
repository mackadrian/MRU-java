/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Jason Heard
Assignment: 1
Due Date: Sept. 22, 2024

Main File: Main.java
Class Name: Student.java
*/



package classList;


public class Student {
	private String studentName;
	private String studentEmail;
	private String studentId;
	
	public Student(String studentName, String studentEmail, String studentId) {
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentId = studentId;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public String getStudentEmail() {
		return studentEmail;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	
	public String toString() {
		return "\nStudent Name: " + studentName +
				"\nStudent Email: " + studentEmail +
				"\n Student Id: " + studentId;
		
	}
	
}
