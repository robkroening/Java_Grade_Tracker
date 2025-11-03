import java.util.ArrayList;
import java.util.List;

public class Student {
    // attributes
    private String studentId;
    private String name;
    // private double gpa;
    private List<Grade> grades;

    // constructor --> initialize attributes
    // You can find another way to auto assign a studentId if you want
    // dont include grades in constructor because those get added later
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    // methods
    // getter studentid
    public String getStudentId() {
        return studentId;
    }

    // getter name
    public String getName() {
        return name;
    }

    // getter grades
    public List<Grade> getGrades() {
        return grades;
    }

    // add a grade to the student
    // lots of validating here
    public boolean addGrade(Grade grade) {

        // things to check against to add grade
        Course passedInCourse = grade.getCourse();
        String passedInCourseCode = passedInCourse.getCourseCode();
        String normalizedPassedInCourseCode = passedInCourseCode.trim();

        // if the course or grade is null for parameter return false
        if (passedInCourse == null || grade == null) {
            return false;
        }
        // if grade score is somehow NaN return false
        if (Double.isNaN(grade.getNumericScore())) {
            return false;
        }

        // loop thru grades array to look at each grade
        for (Grade g : grades) {

            // if no course for current grade iteration
            // go to next loop iteration
            if (g.getCourse() == null) {
                continue;
            }

            // get the current course for the grade
            Course c = g.getCourse();

            // get the current course code for that course
            String existingCourseCode = c.getCourseCode();

            // IMPORTANT!!!
            // if the existing course code is NOT empty
            // AND
            // if the new and existing course codes are equal return false
            if (existingCourseCode != null
                    && existingCourseCode.trim().toUpperCase().equals(normalizedPassedInCourseCode)) {
                return false;
            }
        }

        // after all validation add the grade and return true
        grades.add(grade);
        return true;
    }

    // calculate GPA
    public double calculateGPA() {
        // loop through the entire student grade list
        // translate the grade to the associated GPA point
        // add up all the grades and divide by the total amount
        // viola! there is the student's GPA
        if (this.grades.isEmpty()) {
            return Double.NaN;
        }

        double totalPoints = 0;

        for (Grade aGrade : this.grades) {
            // get the total amount of points
            double score = aGrade.getNumericScore();
            if (score >= 90 && score <= 100) {
                totalPoints = totalPoints + 4.0;
            } else if (score >= 80 && score < 90) {
                totalPoints = totalPoints + 3.0;
            } else if (score >= 70 && score < 80) {
                totalPoints = totalPoints + 2.0;
            } else if (score >= 70 && score < 70) {
                totalPoints = totalPoints + 1.0;
            } else {
                totalPoints = totalPoints + 0.0;
            }
        }

        int numGrades = this.grades.size();

        double calculatedGPA = totalPoints / numGrades;
        // this.gpa = calculatedGPA;
        return calculatedGPA;
    }

    // print transcript
    public void printTranscript() {
        // print course, grade
        // print GPA at the bottom
        System.out.println("Transcript for " + this.name);
        System.out.println("-----------------------------");

        // loop through and print out each course and grade on a new line
        for (int i = 0; i < this.grades.size(); i++) {
            // current grade in iteration
            Grade currentGrade = this.grades.get(i);
            Course currentCourse = currentGrade.getCourse();
            String currentCourseName = currentCourse.getCourseName();
            double numericScore = currentGrade.getNumericScore();
            System.out.println("COURSE: " + currentCourseName + ", GRADE: " + String.valueOf(numericScore));
            System.out.println("-----------------------------");
        }

        // gpa
        double gpa = calculateGPA();
        System.out.println("Your GPA is: " + gpa);
    }

}
