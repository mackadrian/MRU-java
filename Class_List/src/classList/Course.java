
/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Jason Heard
Assignment: 1
Due Date: Sept. 22, 2024

Main File: Main.java
Class Name: Course.java
*/

package classList;

public class Course {
	private String courseName;
	private String courseId;
	private String studentId;
	private int studentGrade;
	
	public Course(String courseName, String courseId, String studentId, int studentGrade) {
		this.courseName = courseName;
		this.courseId = courseId;
		this.studentId = studentId;
		this.studentGrade = studentGrade;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public String getCourseId() {
		return courseId;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public int getStudentGrade() {
		return studentGrade;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public void setStudentGrade(int studentGrade) {
		this.studentGrade = studentGrade;
	}
	
	public String toString() {
		return "\nCourse Name:" + courseName +
				"\nCourse Number: " + courseId +
				"\nStudent Id: " + studentId +
				"\nStudent Grade: " + studentGrade;
	}

}
