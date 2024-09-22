/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP2631-001
 * Instructor: Jason Heard
 * Assignment: 1
 * Due Date: Sept. 22, 2024
 */

package classlist;

import java.util.StringTokenizer;

/**
 * Assignment 1 main file.
 *
 * @author Andrew Bloch-Hansen
 */
public class Main {

    private static final int DEFAULT_SIZE = 30;

    /**
     * Main test function for Assignment 1.
     */
    public static void main(String[] args) {
        // Read student.txt line by line and create a student object for each line
        System.out.format("%n");
        System.out.format("student.txt%n");
        System.out.format("-----------%n");

        InStringFile studentReader = new InStringFile("student.txt");
        Student[] studentArray = new Student[DEFAULT_SIZE];
        int studentCt = 0;

        // Use do-while statement to setup student object
        do {

            String studentLine = studentReader.read();
            StringTokenizer tokenizer = new StringTokenizer(studentLine);

            String studentName = tokenizer.nextToken();
            String studentEmail = tokenizer.nextToken();
            String studentNumber = tokenizer.nextToken();

            // Make a student object and copy into the array studentArray
            studentArray[studentCt] = new Student(studentName, studentEmail, studentNumber);

            //System.out.format("%3d %-72s%n", studentCt, studentArray[studentCt].toString());

            studentCt++;

        } while (!studentReader.endOfFile());

        studentReader.close();

        System.out.format("%d student datasets read%n%n", studentCt);

        // Read course.txt line by line and create a course object for each line

        System.out.format("course.txt%n");
        System.out.format("-------------%n");

        InStringFile courseReader = new InStringFile("course.txt");
        Course[] courseArray = new Course[DEFAULT_SIZE];
        int courseCt = 0;

        // Use do-while statement to setup course object
        do {

            String courseLine = courseReader.read();
            StringTokenizer tokenizer = new StringTokenizer(courseLine);

            String courseName = tokenizer.nextToken();
            String courseId = tokenizer.nextToken();
            String studentId = tokenizer.nextToken();
            int studentGrade = Integer.parseInt(tokenizer.nextToken());

            // Make a course object and copy it into the array courseArray
            courseArray[courseCt] = new Course(courseName, courseId, studentId, studentGrade);

            //System.out.format("%3d %-72s%n", courseCt, courseArray[courseCt].toString());

            courseCt++;

        } while (!courseReader.endOfFile());

        courseReader.close();

        System.out.format("%d course datasets read%n", courseCt);

        GradeReport gradeReport = new GradeReport(studentArray, courseArray, studentCt, courseCt);

        // Test 1 --- Testing the GradeReport toString()
        System.out.println("\n----------Test 1: Testing the GradeReport toString()----------");
        System.out.println("Expected Result:");
        System.out.println("Complete Listing of Courses");
        System.out.println("Student Id: 1, Grade: 100, Course: 1026");
        System.out.println("Student Id: 1, Grade: 100, Course: 1027");
        System.out.println("Student Id: 2, Grade: 100, Course: 3357");
        System.out.println("Student Id: 3, Grade: 85, Course: 4457");
        System.out.println("Student Id: 4, Grade: 66, Course: 2210");
        System.out.println("Student Id: 5, Grade: 55, Course: 3340");
        System.out.println("Student Id: 1, Grade: 78, Course: 3342");
        System.out.println("Student Id: 8, Grade: 44, Course: 4442");
        System.out.println("Student Id: 6, Grade: 73, Course: 1026");
        System.out.println("Student Id: 2, Grade: 92, Course: 3340");
        System.out.println("Student Id: 3, Grade: 24, Course: 3342");
        System.out.println("Student Id: 1, Grade: 52, Course: 3357");
        System.out.println("Student Id: 1, Grade: 93, Course: 3340");
        System.out.println("Student Id: 5, Grade: 77, Course: 1027");
        System.out.println("Student Id: 3, Grade: 84, Course: 3340");
        System.out.println("Student Id: 2, Grade: 87, Course: 1026");
        System.out.println("Student Id: 8, Grade: 94, Course: 3357");
        System.out.println("Student Id: 5, Grade: 76, Course: 4442");
        System.out.println("\nActual Result:");
        System.out.format("%s\n", gradeReport.toString());

        // Test 2 --- Testing getStudentName()
        Student student = studentArray[0];
        System.out.println("\n----------Test 2: Testing getStudentName() ----------");
        System.out.println("Expected Result:");
        System.out.println("Student Name: Andrew");
        System.out.println("\nActual Result:");
        System.out.format("Student Name: %s\n", student.getStudentName());

        // Test 3 --- Testing the Student toString()
        System.out.println("\n----------Test 3: Testing the Student toString() ----------");
        System.out.println("Expected Result:");
        System.out.println("Student Name:  Andrew");
        System.out.println("Student Email: ablochha@mtroyal.ca");
        System.out.println("Student Id:    1");
        System.out.println("\nActual Result:");
        System.out.println(student.toString());

        // Test 4 --- Testing setStudentName()
        student.setStudentName("Andrew the Aligator");
        System.out.println("\n----------Test 4: Testing setStudentName()----------");
        System.out.println("Expected Result:");
        System.out.println("Updated Student Name: Andrew the Aligator");
        System.out.println("\nActual Result:");
        System.out.format("Updated Student Name: %s\n", student.getStudentName());

        // Test 5 --- Testing getCourseName()
        Course course = courseArray[0];
        System.out.println("\n----------Test 5: Testing getCourseName()----------");
        System.out.println("Expected Result:");
        System.out.println("Course Name: JavaFundamentals1");
        System.out.println("\nActual Result:");
        System.out.format("Course Name: %s\n",  course.getCourseName());

        // Test 6 --- Testing the Course toString()
        System.out.println("\n----------Test 6: Testing the Course toString() ----------");
        System.out.println("Expected Result:");
        System.out.println("Course Name:   JavaFundamentals1");
        System.out.println("Course Number: 1026");
        System.out.println("Student Id:    1");
        System.out.println("Student Grade: 100");
        System.out.println("\nActual Result:");
        System.out.println(course.toString());

        // Test 7 --- Testing setCourseName()
        course.setCourseName("How to Train Your Dragon");
        System.out.println("\n----------Test 7: Testing setCourseName()----------");
        System.out.println("Expected Result:");
        System.out.println("Updated Course Name: How to Train Your Dragon");
        System.out.println("\nActual Result:");
        System.out.format("Updated Course Name: %s\n", course.getCourseName());

        // Test 8 --- Testing getGradesOfCourse()
        System.out.println("\n----------Test 8: Testing getGradesOfCourse()----------");
        System.out.println("Expected Result:");
        System.out.println("Grades for Course Id: 1026");
        System.out.println("Student Name: Andrew the Aligator, Grade: 100");
        System.out.println("Student Name: Fred, Grade: 73");
        System.out.println("Student Name: Bryan, Grade: 87");
        System.out.println("The Average of the Course: 86.67");
        System.out.println("\nActual Result:");
        System.out.format("%s\n", gradeReport.getGradesOfCourse("1026"));

        // Test 9 --- Testing getGradesOfStudent()
        System.out.println("\n----------Test 9: Testing getGradesOfStudent()----------");
        System.out.println("Expected Result:");
        System.out.println("Grades for Student Id: 1");
        System.out.println("Student Name: Andrew the Aligator, Grade: 100, Course: 1026");
        System.out.println("Student Name: Andrew the Aligator, Grade: 100, Course: 1027");
        System.out.println("Student Name: Andrew the Aligator, Grade: 78, Course: 3342");
        System.out.println("Student Name: Andrew the Aligator, Grade: 52, Course: 3357");
        System.out.println("Student Name: Andrew the Aligator, Grade: 93, Course: 3340");
        System.out.println("The Average of the Student: 84.60");
        System.out.println("\nActual Result:");
        System.out.format("%s\n", gradeReport.getGradesOfStudent("1"));

        // Test 10 --- Testing getGradesOfStudent()
        System.out.println("\n----------Test 10: Testing getGradesOfStudent()----------");
        System.out.println("Expected Result:");
        System.out.println("There are No Grades for Student Id: 7");
        System.out.println("\nActual Result:");
        System.out.format("%s\n", gradeReport.getGradesOfStudent("7"));

    } //end main (Method)

} //end Main (Class)