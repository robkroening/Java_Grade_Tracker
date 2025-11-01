import java.util.ArrayList;
import java.util.List;

public class Student {
    // attributes
    private String studentId;
    private String name;
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

    // add grade --> this is because these are where grades are managed by,
    // the student
    public void addGrade(Grade grade) {
        // no logic in here yet
        // make sure not to add a duplicate grade as well
        grades.add(grade);
    }

    // calculate GPA
    public double calculateGPA() {
        return 0.0;
    }

}
