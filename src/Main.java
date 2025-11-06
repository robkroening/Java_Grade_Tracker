import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        School school = new School();
        // main loop
        while (running) {
            // clear terminal before each iteration
            Main.clearScreen();

            // print the menu
            Main.printMenu();

            // user choice
            int userChoice = Main.userChoice(scanner);

            // handle if user choice is NaN
            if (userChoice == -1) {
                System.out.println("Invalid input. Please enter a number (1-7). ");
                Main.waitForEnter(scanner);
                continue;
            }

            // handle user choice boundaries
            if (userChoice < 1 || userChoice > 7) {
                System.out.println("Invalid option. Please press enter to continue.");
                Main.waitForEnter(scanner); // wait for enter press
                continue;
            }

            // perform an action based off user choice
            switch (userChoice) {
                case 1:
                    // add a student to the school
                    System.out.println("Please enter in a student ID for the new student: ");
                    String studentId = scanner.nextLine();
                    System.out.println("Please enter the student's name: ");
                    String name = scanner.nextLine();
                    boolean okStudentAdd = school.addStudent(studentId, name);
                    if (okStudentAdd == true) {
                        System.out.println("Student added!");
                    } else {
                        System.out.println("Could not add the student (invalid or dupe ID).");
                    }
                    waitForEnter(scanner);
                    break;
                case 2:
                    // add a course to the school
                    System.out.println("Please enter in the new course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.println("Please enter in the new course name: ");
                    String courseName = scanner.nextLine();
                    System.out.println("Please enter in the credit hours for the course: ");

                    // make sure credithours is a whole number - if not break out of case
                    Integer creditHours = readIntSafely(scanner);
                    if (creditHours == null) {
                        System.out.println("Credit hours must be a whole number from 0 to 4.");
                        System.out.println("Course was not added.");
                        Main.waitForEnter(scanner);
                        break;
                    }

                    boolean okCourseAdd = school.addCourse(courseCode, courseName, creditHours);
                    if (okCourseAdd == true) {
                        System.out.println("Course Added!");
                    } else {
                        System.out.println("Course could not be added. An input was invalid.");
                    }
                    Main.waitForEnter(scanner);
                    break;
                case 3:
                    // assign a grade to a student
                    System.out.println("Please enter in the student's ID: ");
                    String stuId = scanner.nextLine();
                    System.out.println("Please enter in the course code for the grade: ");
                    String courCode = scanner.nextLine();
                    System.out.println("Please enter in the score: ");

                    // make sure it is a double and dont crash program
                    Double numericScore = Main.readDoubleSafely(scanner);
                    if (numericScore == null) {
                        System.out.println("You must input a decimal or whole number.");
                        System.out.println("Grade was not assigned.");
                        Main.waitForEnter(scanner);
                        break;
                    }

                    boolean okAssignGrade = school.assignGrade(stuId, courCode, numericScore);
                    if (okAssignGrade == true) {
                        System.out.println("Grade was assigned correctly!");
                    } else {
                        System.out.println(
                                "The grade was not assigned. Invalid score, unknown student/course or duplicate grade.");
                    }
                    Main.waitForEnter(scanner);
                    break;
                case 4:
                    // print transcript
                    System.out.println("Please enter in the ID of the student you want the transcript for: ");
                    String stuIdInput = scanner.nextLine().trim();
                    Student student = school.findStudentById(stuIdInput);

                    // when no student is returned
                    if (student == null) {
                        System.out.println("This student is not in the system.");
                    } else {
                        student.printTranscript();
                    }
                    Main.waitForEnter(scanner); // pause until enter is pressed
                    break;
                case 5:
                    // print all students
                    Main.printAllStudents(school);
                    Main.waitForEnter(scanner);
                    break;
                case 6:
                    // print all courses
                    Main.printAllCourses(school);
                    Main.waitForEnter(scanner);
                    break;
                case 7:
                    // exit the program
                    System.out.println("Goodbye!");
                    Main.waitForEnter(scanner);
                    running = false;
                    break;
                default:
                    System.out.println("something went wrong.");
                    Main.waitForEnter(scanner);
                    break;
            }
        }

        // clear the terminal / run console
        Main.clearScreen();

        // close the scanner and exit program
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
        // 5 - Print all Students
        System.out.println("Press (5) to: Print all Students");
        // 6 - Print all Courses
        System.out.println("Press (6) to: Print all Courses");
        // 7 - Exit the Program
        System.out.println("Press (7) to: Exit the Program");
    }

    // take in a user input of the selection they choose
    private static int userChoice(Scanner scanner) {
        try {
            System.out.println("Please choose an action (1, 2, 3, 4, 5, 6, 7): ");
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

    // clear screen helper
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // read integer safely
    // if decimal is inputted it will return null
    private static Integer readIntSafely(Scanner scanner) {
        String rawInput = scanner.nextLine().trim();
        try {
            return Integer.parseInt(rawInput);
        } catch (Exception e) {
            // if not an integer dont throw error but return null
            return null;
        }
    }

    // read double safely
    private static Double readDoubleSafely(Scanner scanner) {
        String rawDoubleInput = scanner.nextLine().trim();
        try {
            return Double.parseDouble(rawDoubleInput);
        } catch (Exception e) {
            // if not a double dont throw error but return null
            return null;
        }
    }

    // print all students in the school
    private static void printAllStudents(School school) {
        ArrayList<Student> allStudentsList = new ArrayList<Student>(school.getAllStudents());
        // System.out.println(allStudentsList); this gives a ArrayList [] of the school
        if (allStudentsList.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        for (Student student : allStudentsList) {
            System.out.println(student.getStudentId() + ": " + student.getName());
        }
    }

    // print all courses in the school
    private static void printAllCourses(School school) {
        ArrayList<Course> allCourses = new ArrayList<Course>(school.getAllCourses());
        if (allCourses.isEmpty()) {
            System.out.println("No Courses found in system.");
            return;
        }

        for (Course course : allCourses) {
            System.out.println(course.getCourseCode() + ": " + course.getCourseName() + " || Credit Hours: "
                    + course.getCreditHours());
        }
    }
}