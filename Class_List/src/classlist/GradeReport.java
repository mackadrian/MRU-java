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
 * Class Name: GradeReport.java.
 *
 * @author Mack Bautista
 *
 */
public class GradeReport {
    private int studentCt;
    private int courseCt;
    private Student studentArray[];
    private Course courseArray[];

    /**
     * A constructor that creates an instace of the class GradeReport.
     */
    public GradeReport(Student studentArray[], Course courseArray[], int studentCt, int courseCt) {
        this.studentArray = studentArray.clone();
        this.courseArray = courseArray.clone();
        this.studentCt = studentCt;
        this.courseCt = courseCt;
    }

    public String getGradesOfCourse() {
        return null;
    }


    public String getGradesOfStudent() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }




}
