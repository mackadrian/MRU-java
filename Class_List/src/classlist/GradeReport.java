/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP2631-001
 * Instructor: Jason Heard
 * Assignment: 1
 * Due Date: Sept. 22, 2024
 */

package classlist;

import java.text.DecimalFormat;

/**
 * Class Name: GradeReport.java.
 *
 * Purpose: The class holds information about the students and the courses they are in,
 *  using the private variables.
 *
 * @author Mack Bautista
 */
public class GradeReport {
    private int studentCt;
    private int courseCt;
    private Student[] studentArray;
    private Course[] courseArray;

    /**
     * A constructor that creates an instace of the class GradeReport.
     */
    public GradeReport(Student[] studentArray, Course[] courseArray, int studentCt, int courseCt) {
        this.studentArray = studentArray.clone();
        this.courseArray = courseArray.clone();
        this.studentCt = studentCt;
        this.courseCt = courseCt;
    }

    /**
     * Reads the student's names and grades for the course.
     *
     * @param courseId which contains all the students and their grades.
     * @return the student's details in that course and the average grade of the course.
     */
    public String getGradesOfCourse(String courseId) {
        final DecimalFormat df = new DecimalFormat("0.00"); // 2 decimal value average.
        StringBuilder result = new StringBuilder();
        String studentName = null;
        double totalGrades = 0.0;
        int count = 0;


        result.append("Grades for Course Id: ").append(courseId).append("\n");

        for (int i = 0; i < courseCt; i++) {
            Course course = courseArray[i];

            if (course.getCourseId().equals(courseId)) {
                // Searches the student's name corresponding to their student id.
                for (int j = 0; j < studentCt; j++) {
                    if (studentArray[j].getStudentId().equals(course.getStudentId())) {
                        studentName = studentArray[j].getStudentName();
                        break;
                    }
                }

                if (studentName != null) {
                    // Append the student name and grade to the result string.
                    result.append("Student Name: ").append(studentName)
                          .append(", Grade: ").append(course.getStudentGrade())
                          .append("\n");

                    // Add the grade to the total and increment the count.
                    totalGrades += course.getStudentGrade();
                    count++;
                }
            }
        }

        if (count == 0) {
            // If no students were found for the given course ID.
            return "No students found for course ID: " + courseId;
        }

        double averageGrade = totalGrades / count;
        // Calculates the average grade to the result string, formatted to 2 decimal places.
        result.append("The Average of the Course: ").append(df.format(averageGrade));
        return result.toString();
    }

    /**
     * Reads the student's names and grades for the course.
     *
     * @param studentId the name of the student's id that is being accessed.
     * @return the student's details in that course and the average grade of the course.
     */
    public String getGradesOfStudent(String studentId) {
        final DecimalFormat df = new DecimalFormat("0.00"); // 2 decimal playes average
        StringBuilder result = new StringBuilder();
        String studentName = null;

        for (int i = 0; i < studentCt; i++) {
            if (studentArray[i].getStudentId().equals(studentId)) {
                studentName = studentArray[i].getStudentName();
                break;
            }
        }

        // If the student is not found, return a message.
        if (studentName == null) {
            return "Student with ID " + studentId + " not found.";
        }

        result.append("Grades for Student Id: ").append(studentId).append("\n");

        double totalGrades = 0.0;
        int count = 0;
        // Finds the courses and grades of the student.
        for (int i = 0; i < courseCt; i++) {
            Course course = courseArray[i];
            if (course.getStudentId().equals(studentId)) {
                // Append the student name, course ID, and grade to the result string.
                result.append("Student Name: ").append(studentName)
                      .append(", Grade: ").append(course.getStudentGrade())
                      .append(", Course: ").append(course.getCourseId())
                      .append("\n");

                totalGrades += course.getStudentGrade();
                count++;
            }
        }

        if (count > 0) {
            double averageGrade = totalGrades / count;
            // Calculates the average grade to the result string, formatted to 2 decimal places.
            result.append("The Average of the Student: ").append(df.format(averageGrade));
        } else {
            // If no grades instead of calculating the average.
            return "There are No Grades for Student Id: " + studentId;
        }

        return result.toString();
    }

    /**
     * Reads the next line of index of the Course array.
     *
     * @return the Student ID, Grade, and Course of the student.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int studentIndex = 0;
        do {
            // Appends each student's credentials.
            result.append("Student Id: ").append(courseArray[studentIndex].getStudentId())
            .append(", Grade: ").append(courseArray[studentIndex].getStudentGrade())
            .append(", Course: ").append(courseArray[studentIndex].getCourseId())
            .append("\n");
            studentIndex++;
        } while (studentIndex < courseCt);
        return result.toString();
    }




}
