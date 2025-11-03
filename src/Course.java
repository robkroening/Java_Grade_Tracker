public class Course {
    // attributes
    private String courseCode;
    private String courseName;
    private int creditHours;

    // constructor
    public Course(String courseCode, String courseName, int creditHours) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    // methods
    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public double getCreditHours() {
        return creditHours;
    }
}
