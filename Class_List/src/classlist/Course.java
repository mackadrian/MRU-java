/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP2631-001
 * Instructor: Jason Heard
 * Assignment: 1
 * Due Date: Sept. 22, 2024
 *
 */

package classlist;

/**
 * Class Name: Course.java.
 *
 * Contains the following Course class.
 * A Course contains the name, id, student id, and student grade.
 *
 *
 *
 * @author Mack Bautista
 */
public class Course {
    private String courseName;
    private String courseId;
    private String studentId;
    private int studentGrade;

    /**
     * A constructor that creates an instace of the class Course.
     */
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

	@Override
    public String toString() {
	return String.format("Course Name: %19s\n", courseName) +
	            String.format("Course Number: %s\n", courseId) +
	            String.format("Student Id: %4s\n", studentId) +
	            String.format("Student Grade: %3d\n", studentGrade);
	}

}
