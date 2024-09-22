/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP2631-001
 * Instructor: Jason Heard
 * Assignment: 1
 * Due Date: Sept. 22, 2024
 */

package classlist;

/**
 * Class Name: Course.java.
 *
 * Purpose: The class encapsulates a student taking a course using the private variables. Each
 * course contains their own name, id, and the student id and grade that belongs in that course.
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

    /**
     *  Reads the course name.
     * @return the private variable course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Reads the course id.
     * @return the private variable course id
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Reads the student's id belonging in that course.
     * @return the private variable student's id
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Reads the student's grade belonging in that course.
     * @return the private variable student's grade
     */
    public int getStudentGrade() {
        return studentGrade;
    }

    /**
     * Sets the course name with a specified course name.
     * @param courseName creates the instance of the private variable
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Sets the course id with a specified course id.
     * @param courseId creates the instance of the private variable
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * Sets the student id with a specified student id.
     * @param studentId creates the instance of the private variable
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Sets the student grade with a specified student grade.
     * @param studentGrade creates the instance of the private variable
     */
    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }

    /**
     * Reads the private variables of Course.java class.
     * @return the formatted String of the class Course's private variables
     */
    @Override
    public String toString() {
        return String.format("Course Name: %19s\n", courseName) +
                String.format("Course Number: %s\n", courseId) +
                String.format("Student Id: %4s\n", studentId) +
                String.format("Student Grade: %3d\n", studentGrade);
    }

}
