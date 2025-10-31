import java.util.*;

public class School {
    // attributes
    // private List<Student> students;
    // private List<Course> courses;
    private final HashMap<String, Student> studentsById = new HashMap<>();
    private final HashMap<String, Course> courseByCode = new HashMap<>();

    // constructor
    // public School() {
    // this.students = new ArrayList<>();
    // this.courses = new ArrayList<>();
    // }

    // methods
    public void addStudent(String studentId, String name) {
        // validate input
        // check for dupes
        // add the new student to the hashmap
        return;
    }

    public void addCourse() {
        // validate input
        // check for dupes
        // add the new course to the course hashmap
    }

    public void removeStudent() {
        // validate input
        // check and see if student exists in the school
        // remove them from the student hashmap
    }

    public void removeCourse() {
        // validate input
        // check and see if course exists in the school
        // remove them if from the course hashmap
    }

    public Student findStudentById(String studentId) {
        // validate input
        // check and see if the student exists in the student hashmap
        // if they do, return the student object you found
        return null;
    }

    public Course findCourseByCode(String courseCode) {
        // validate input
        // check to see if course exists in the school
        // if it does, return the course object you found
        return null;
    }

    public boolean assignGrade(String studentId, String courseCode, double numericScore) {
        // find the student
        // find the course
        // create and attach a grade to the course and assign to the student
        return false;
    }
}
