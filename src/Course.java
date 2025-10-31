public class Course {
    // attributes
    private String courseCode;
    private String courseName;
    private double creditHours;

    // constructor
    public Course(String courseCode, String courseName, double creditHours) {
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

    // public void setCreditHours(double creditHours) {
    // this.creditHours = creditHours;
    // }

    // public void setCourseName(String courseName) {
    // this.courseName = courseName;
    // }

    // public void setCourseCode(String courseCode) {
    // this.courseCode = courseCode;
    // }
}
