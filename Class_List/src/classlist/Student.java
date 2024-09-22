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
 * Class Name: Student.java.
 *
 * Purpose: The class encapsulates a student using the private variables. Each student
 * contains their own name, email and id.
 *
 * @author Mack Bautista
 */
public class Student {
    private String studentName;
    private String studentEmail;
    private String studentId;

    /**
     * A constructor that creates an instance of the class Student.
     */
    public Student(String studentName, String studentEmail, String studentId) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentId = studentId;
    }

    /**
     * Reads the student's name.
     * @return the private variable student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Reads the student's email.
     * @return the private variable student email
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Reads the student's id.
     * @return the private variable student id
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student's name with a specified student name.
     * @param studentName creates the instance of the private variable
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Sets the student's email with a specified student email.
     * @param studentEmail creates the instance of the private variable
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     * Sets the student's id with a specified student email.
     * @param studentId creates the instance of the private variable
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    /**
     * Reads the private variables of the Student.java class.
     * @return the formatted String of the class Student's private variables.
     */
    @Override
    public String toString() {
        return String.format("Student Name: %7s\n", studentName)
                + String.format("Student Email: %2s\n", studentEmail)
                + String.format("Student Id: %4s\n", studentId);
    }

}
