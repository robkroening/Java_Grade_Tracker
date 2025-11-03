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

        School school = new School();

        // 1) Add courses
        school.addCourse("CS101", "Intro to CS", 3);
        school.addCourse("MATH201", "Discrete Math", 4);

        // 2) Add student
        school.addStudent("S001", "Alex Kim");

        // 3) Assign grades (valid 0..100)
        school.assignGrade("S001", "CS101", 92.0);
        school.assignGrade("S001", "MATH201", 84.5);

        // 4) Fetch student and print transcript
        Student s = school.findStudentById("S001");
        if (s != null) {
            s.printTranscript();
        } else {
            System.out.println("Student not found");
        }

    }
}
