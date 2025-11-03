public class Grade {
    // attributes
    private Course course;
    private double numericScore;

    // constructor
    public Grade(Course course, double numericScore) {
        this.course = course;
        this.numericScore = numericScore;
    }

    // methods
    public double getNumericScore() {
        return numericScore;
    }

    public Course getCourse() {
        return course;
    }

    // public String getLetterGrade() {
    // return "";
    // }
}
