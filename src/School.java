import java.util.*;

public class School {
    // attributes
    // private List<Student> students;
    // private List<Course> courses;
    private final HashMap<String, Student> studentsById = new HashMap<>();
    private final HashMap<String, Course> courseByCode = new HashMap<>();

    // methods
    public boolean addStudent(String studentId, String name) {
        // validate input - can't be empty id or name - can't be null
        if (studentId == null || name == null) {
            return false;
        }

        String cleanId = studentId.trim();
        String cleanName = name.trim();

        if (cleanId.isEmpty() || cleanName.isEmpty()) {
            return false;
        }

        // check for dupes
        if (studentsById.containsKey(cleanId)) {
            return false;
        }

        // add the new student to the hashmap
        Student newStudent = new Student(cleanId, cleanName);
        studentsById.put(cleanId, newStudent);

        // return true if added false if not
        return true;
    }

    // private static final Set<Integer> ALLOWED_CREDIT_HOURS = new
    // HashSet<>(Arrays.asList(0, 1, 2, 3, 4));
    private static final Set<Integer> ALLOWED_CREDIT_HOURS = Set.of(0, 1, 2, 3, 4);

    public boolean addCourse(String courseCode, String courseName, int creditHours) {
        // validate input
        // making sure the course code and course name are not null
        if (courseCode == null || courseName == null) {
            return false;
        }

        // checking to see if credit hours parameter is either 0, 1, 2, 3, or 4,
        if (!(ALLOWED_CREDIT_HOURS.contains(creditHours))) {
            return false;
        }

        String cleanCourseCode = courseCode.trim();
        cleanCourseCode = cleanCourseCode.toUpperCase();
        String cleanCourseName = courseName.trim();

        // making sure course code and name arent empty
        if (cleanCourseCode.isEmpty() || cleanCourseName.isEmpty()) {
            return false;
        }

        // check for dupes
        if (courseByCode.containsKey(cleanCourseCode)) {
            return false;
        }

        // add the new course to the course hashmap
        Course newCourse = new Course(cleanCourseCode, cleanCourseName, creditHours);
        courseByCode.put(cleanCourseCode, newCourse);
        // return true if added false if not
        return true;
    }

    // DON'T NEED THIS YET
    public void removeStudent() {
        // validate input
        // check and see if student exists in the school
        // remove them from the student hashmap
    }

    // DON'T NEED THIS YET
    public void removeCourse() {
        // validate input
        // check and see if course exists in the school
        // remove them if from the course hashmap
    }

    public Student findStudentById(String studentId) {
        // validate input
        if (studentId == null) {
            return null;
        }
        // check and see if the student exists in the student hashmap
        String cleanStudentId = studentId.trim();
        Student student = studentsById.get(cleanStudentId);
        // if they do, return the student object you found
        // if they dont exist, null is returned here
        return student;
    }

    // DONT NEED TO CHECK FOR "" BC IT DOES THIS IN .GET() ALREADY AND ADD STUDENT
    public Course findCourseByCode(String courseCode) {
        // validate input
        // make sure parameter is not null
        if (courseCode == null) {
            return null;
        }

        String cleanCourseCode = courseCode.trim();
        cleanCourseCode = cleanCourseCode.toUpperCase();

        // making sure trimmed string is not empty either.
        if (cleanCourseCode.isEmpty()) {
            return null;
        }

        // make sure courseCode is not empty --> NOT NEEDED
        // if (cleanCourseCode.isEmpty()) {
        // return null;
        // }
        // .get() the course, if it doesnt exist then null return if it does, then the
        // Course returns
        Course newCourse = courseByCode.get(cleanCourseCode);
        // check to see if course exists in the school
        // if it does, return the course object you found
        // if it doesn't it will return null.
        return newCourse;
    }

    public boolean assignGrade(String studentId, String courseCode, double numericScore) {
        // validate score
        if (!(numericScore >= 0 && numericScore <= 100)) {
            return false;
        }
        // extra layer for the score (infinity check)
        if (Double.isNaN(numericScore) || Double.isInfinite(numericScore)) {
            return false;
        }

        // find the student
        Student studentToGrade = this.findStudentById(studentId);
        // find the course
        Course courseToGrade = this.findCourseByCode(courseCode);

        // validate that the student and course exist
        if (studentToGrade == null || courseToGrade == null) {
            return false;
        }

        // create a new grade
        Grade gradeToAssign = new Grade(courseToGrade, numericScore);
        // assign the grade to the student
        studentToGrade.addGrade(gradeToAssign);
        return true;
    }
}
