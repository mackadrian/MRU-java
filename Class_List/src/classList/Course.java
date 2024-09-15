package classList;

/**
 * Assignment 1 Student class.
 *
 * @author Mack Bautista
 */

public class Course {
	private String courseName;
	private String courseId;
	private String studentId;
	private int studentGrade;
	
	public Course(String courseName, String courseId, String studentId, int studentGrade) {
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
		return "Course Name:" + courseName +
				"\nCourse Number: " + courseId +
				"\nStudent Id: " + studentId +
				"\nStudent Grade: " + studentGrade;
	}

}
