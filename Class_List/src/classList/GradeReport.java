/*
Name: Mack Bautista
Email: mbaut981@mtroyal.ca
Course: COMP2631-001
Instructor: Jason Heard
Assignment: 1
Due Date: Sept. 22, 2024

Main File: Main.java
Class Name: GradeReport.java
*/

package classList;

public class GradeReport {
	private int studentCt;
	private int courseCt;
	private Student studentArray[];
	private Course courseArray[];
	
	public GradeReport(Student studentArray[], Course courseArray[], int studentCt, int courseCt) {
		this.studentArray = studentArray.clone();
		this.courseArray = courseArray.clone();
		this.studentCt = studentCt;
		this.courseCt = courseCt;
	}
	
	public String getGradesOfCourse() {
		
	}
	
	public String getGradesOfStudent() {
	}
	
	public String toString() {
		
	}
	
	
	

}
