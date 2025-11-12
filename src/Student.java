import java.util.ArrayList;
import java.util.List;

public class Student {
    // attributes
    private String studentId;
    private String name;
    private double gpa = 0.0;
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
    // now becomes WEIGHTED
    public boolean calculateGPA() {
        // calculate weighted GPA
        // check if grades are empty, if they are then no GPA to calculate
        // loop thru list of grades, get the points from every associated grade from the
        // enum
        if (this.grades.isEmpty() || this.grades == null) {
            this.gpa = 0.0;
            return false;
        }

        // weighted gpa is total quality points / total credits
        double totalQualityPoints = 0;
        double totalCredits = 0;

        for (Grade aGrade : this.grades) {
            // figure out the score for the grade
            // figure out the credits of the class
            // from this, get the points
            // add points to total points
            // then divide total points by number of grades

            // if theres no grade or no course go to next iteration
            if (aGrade == null || aGrade.getCourse() == null) {
                continue;
            }

            // get the credit hours of the class
            int credits = aGrade.getCourse().getCreditHours();

            // 0 credit classes will not affect GPA -> next iteration
            if (credits <= 0) {
                continue;
            }

            // get the weighted gpa score for a grade
            double points = aGrade.getGradePoints();
            // add to the sum of the quality points
            totalQualityPoints = totalQualityPoints + (credits * points);
            // add to the sum of total credits
            totalCredits = totalCredits + credits;
        }

        // if all the credits were 0 credit hour courses, then gpa is unaffected
        // gpa is 0.0 by policy
        if (totalCredits == 0) {
            this.gpa = 0.0;
        }

        // calculate the weighted GPA
        this.gpa = totalQualityPoints / totalCredits;
        return true;
    }

    // get GPA
    public double getGpa() {
        return gpa;
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
        System.out.println("Your GPA is: " + getGpa());
    }

}
