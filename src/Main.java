import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // adds a new student
        // add a course
        // give student a grade for course
        // view students report card
        // see their gpa
        // storing --->
        // many students
        // many courses
        // which student took which course and what they scored

        // classes -------->
        // Student.java
        // Course.java
        // Grade.java
        // School.java

        // remember each method should only do ONE thing like printing OR returning a
        // value

        // School school = new School();
        // System.out.println(school.addStudent("S001 ", "Alice"));
        // System.out.println(school.addStudent(" S001", "Alice"));
        // System.out.println(school.findStudentById("S001") != null);
        // System.out.println(school.findStudentById("S999") == null);

        // school.assignGrade("S001", null, 0);
        // school.assignGrade("S001", null, 0);

        // School school = new School();

        // // 1) Add courses
        // school.addCourse("CS101", "Intro to CS", 3);
        // school.addCourse("MATH201", "Discrete Math", 4);

        // // 2) Add student
        // school.addStudent("S001", "Alex Kim");

        // // 3) Assign grades (valid 0..100)
        // school.assignGrade("S001", "CS101", 92.0);
        // school.assignGrade("S001", "MATH201", 84.5);

        // // 4) Fetch student and print transcript
        // Student s = school.findStudentById("S001");
        // if (s != null) {
        // s.printTranscript();
        // } else {
        // System.out.println("Student not found");
        // }

        // MAIN PROGRAM LOOP:
        // TODO:
        // show menu
        // take user input for which action is performed (and do error handling)
        // perform that action
        // return back to the menu
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        School school = new School();
        while (running) {
            // clear terminal before each iteration
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // print the menu
            Main.printMenu();

            int userChoice = Main.userChoice(scanner);
            // handle choice boundaries
            if (userChoice < 1 || userChoice > 5) {
                System.out.println("Invalid option. Please press enter to continue.");
                scanner.nextLine();
                continue;
            }

            // perform an action
            switch (userChoice) {

                case 1:
                    // add a student to the school
                    System.out.println("Please enter in a student ID for the new student: ");
                    String studentId = scanner.nextLine();
                    System.out.println("Please enter the student's name: ");
                    String name = scanner.nextLine();
                    school.addStudent(studentId, name);
                    break;

                case 2:
                    // add a course to the school
                    System.out.println("Please enter in the new course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.println("Please enter in the new course name: ");
                    String courseName = scanner.nextLine();
                    System.out.println("Please enter in the credit hours for the course: ");
                    int creditHours = Integer.parseInt(scanner.nextLine());
                    school.addCourse(courseCode, courseName, creditHours);
                    break;

                case 3:
                    // assign a grade to a student
                    System.out.println("Please enter in the student's ID: ");
                    String stuId = scanner.nextLine();
                    System.out.println("Please enter in the course code for the grade: ");
                    String courCode = scanner.nextLine();
                    System.out.println("Please enter in the score: ");
                    double numericScore = Double.parseDouble(scanner.nextLine());
                    school.assignGrade(stuId, courCode, numericScore);
                    break;

                case 4:
                    // print transcript
                    // find the student you want to print the transcript for
                    // print the transcript for this student
                    System.out.println("Please enter in the ID of the student you want the transcript for: ");
                    String stuIdInput = scanner.nextLine().trim();
                    Student student = school.findStudentById(stuIdInput);

                    if (student == null) {
                        System.out.println("This student is not in the system.");
                        System.out.println("Press enter to continue.");
                        Main.waitForEnter(scanner); // pause until enter is pressed
                        break;

                    }

                    student.printTranscript();
                    System.out.println("Press enter to continue.");
                    Main.waitForEnter(scanner); // pause until enter is pressed
                    break;

                case 5:
                    // exit the program
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("something went wrong.");
                    break;
            }
        }

        // clear the terminal / run console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // close the scanner
        scanner.close();
    }

    // show the menu options
    private static void printMenu() {
        System.out.println("Please select what you'd like to do: ");
        // 1 - Add Student
        System.out.println("Press (1) to: Add a Student");
        // 2 - Add Course
        System.out.println("Press (2) to: Add a Course");
        // 3 - Assign a grade
        System.out.println("Press (3) to: Assign a Grade");
        // 4 - Print Transcript
        System.out.println("Press (4) to: Print Transcript");
        // 5 - Exit the Program
        System.out.println("Press (5) to: Exit the Program");
    }

    // take in a user input of the selection they choose
    private static int userChoice(Scanner scanner) {
        try {
            System.out.println("Please choose an action (1, 2, 3, 4, 5): ");
            int decision = Integer.parseInt(scanner.nextLine());
            return decision;
        } catch (Exception e) {
            return -1;
        }
    }

    // wait for enter helper function
    private static void waitForEnter(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
