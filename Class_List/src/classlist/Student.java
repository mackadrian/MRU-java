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
 * Class Name: Student.java.
 *
 * Contains the following Student class.
 * A Student has a name, email and corresponding Id.
 *
 * @author Mack Bautista
 */
public class Student {
    private String studentName;
    private String studentEmail;
    private String studentId;

    /**
     * A constructor that creates an instace of the class Student.
     */
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


    @Override
    public String toString() {
        return String.format("Student Name: %7s\n", studentName)
                + String.format("Student Email: %2s\n", studentEmail)
                + String.format("Student Id: %4s\n", studentId);
    }

}
