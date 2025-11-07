import java.util.*;
import java.io.*;
import java.nio.file.*;

public class School {
    // attributes
    // private List<Student> students;
    // private List<Course> courses;
    private final HashMap<String, Student> studentsById = new HashMap<>();
    private final HashMap<String, Course> courseByCode = new HashMap<>();

    // methods
    // add a student to the school
    public Student addStudent(String name) {
        // validate input - can't be empty id or name - can't be null
        if (name == null) {
            return null;
        }

        String cleanName = name.trim();

        if (cleanName.isEmpty()) {
            return null;
        }

        // student id generation
        String studentId = generateNewStudentId();

        // check for dupes
        if (studentsById.containsKey(studentId)) {
            return null;
        }

        // add the new student to the hashmap
        Student newStudent = new Student(studentId, cleanName);
        studentsById.put(studentId, newStudent);

        // return true if added false if not
        return newStudent;
    }

    // overloads the current add student method
    // when you need to pass in an ID in loading the student.
    public Student addStudent(String id, String name) {
        // validate input - can't be empty id or name - can't be null
        if (name == null || id == null) {
            return null;
        }

        String cleanName = name.trim();
        String cleanId = id.trim();

        if (cleanName.isEmpty() || cleanId.isEmpty()) {
            return null;
        }

        // // student id generation
        // String studentId = generateNewStudentId();

        // check for dupes
        if (studentsById.containsKey(cleanId)) {
            return null;
        }

        // add the new student to the hashmap
        Student newStudent = new Student(cleanId, cleanName);
        studentsById.put(cleanId, newStudent);

        // return true if added false if not
        return newStudent;
    }

    private String generateNewStudentId() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return id;
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

        // assign the grade to the student and return the boolean value
        return studentToGrade.addGrade(gradeToAssign);
    }

    // list all students in the school
    // return collection because it gives flexibility for list, set, etc
    public Collection<Student> getAllStudents() {
        return studentsById.values();
    }

    // list all courses in the school
    // return collection because it gives flexibility for list, set, etc
    public Collection<Course> getAllCourses() {
        return courseByCode.values();
    }

    // save data to school system files
    public boolean saveToFiles(String directory) {
        try {
            // make sure folder exists
            Path basePath = Paths.get(directory);
            // if it doesnt exist then it is created
            Files.createDirectories(basePath);
            // save students to students.tsv
            // students.tsv: studentId \t name
            try (BufferedWriter writer = Files.newBufferedWriter(basePath.resolve("students.tsv"))) { // this creates
                                                                                                      // the actual file
                // now we will perform logic for writing in the students.tsv file
                for (Student student : studentsById.values()) {
                    // each student writing format to file
                    writer.write(tsvHelper(student.getStudentId()) + "\t" + tsvHelper(student.getName()));
                    // create a new line after every student is written to the file
                    writer.newLine();
                }
            }
            // save courses
            // courses.tsv: courseCode \t courseName \t creditHours
            try (BufferedWriter courseWriter = Files.newBufferedWriter(basePath.resolve("courses.tsv"))) {
                // logic for each course
                // for each loop iteration
                // write a new line to the courses.tsv file
                for (Course c : courseByCode.values()) {
                    courseWriter.write(tsvHelper(c.getCourseCode()) + "\t" + tsvHelper(c.getCourseName()) + "\t"
                            + c.getCreditHours());
                    courseWriter.newLine();
                }
            }
            // save grades
            // grades.tsv: studentId \t courseCode \t numericScore
            try (BufferedWriter gradeWriter = Files.newBufferedWriter(basePath.resolve("grades.tsv"))) {
                // logic for each grade in for each loop
                for (Student student : studentsById.values()) {
                    // get each grade for the students collection
                    // loop each student
                    // loop again for each grade
                    for (Grade g : student.getGrades()) {
                        // add each grade to grade file
                        gradeWriter.write(tsvHelper(student.getStudentId()) + "\t"
                                + tsvHelper(g.getCourse().getCourseCode()) + "\t"
                                + g.getNumericScore());
                        gradeWriter.newLine();
                    }
                }
            }

            // return true if everything worked
            return true;
        } catch (IOException e) {
            // TODO: handle exception
            return false;
        }
    }

    // load the saved data files
    public boolean loadFromFiles(String directory) {
        try {
            Path basePath = Paths.get(directory);
            // clear in memory hashmaps students and courses
            studentsById.clear();
            courseByCode.clear();

            // courses phase
            Path coursePath = basePath.resolve("courses.tsv");
            if (Files.exists(coursePath)) {
                // try to create a reader for the course file
                try (BufferedReader courseReader = Files.newBufferedReader(coursePath)) {
                    // perform logic on reading the course file and read each line
                    for (String line; (line = courseReader.readLine()) != null;) {
                        if (line.isBlank()) {
                            continue;
                        }

                        // split by TAB, keep any empty trailing columns (-1)
                        String[] p = line.split("\t", -1);
                        if (p.length < 3) {
                            continue;
                        }

                        // pull out the course name, code, and credit hours from the array
                        String courseCode = p[0].trim();
                        String courseName = p[1].trim();
                        Integer creditHours = null;
                        try {
                            creditHours = Integer.parseInt(p[2].trim());
                        } catch (Exception ignored) {
                        }

                        if (creditHours != null) {
                            addCourse(courseCode, courseName, creditHours);
                        }
                    }
                }
            }

            // students phase
            Path studentsPath = basePath.resolve("students.tsv");
            // if the student.tsv file exists do logic
            if (Files.exists(studentsPath)) {
                // create student reader
                try (BufferedReader studentReader = Files.newBufferedReader(studentsPath)) {
                    // make a loop to do logic for each line
                    for (String line; (line = studentReader.readLine()) != null;) {
                        // tell program if the line is blank, keep iterating
                        if (line.isBlank()) {
                            continue; // loops keeps going even if line is blank
                        }
                        // create the array from the line
                        String[] studentLine = line.split("\t", -1);
                        // tell the program if the array for each line is < 3 keep iterating
                        if (studentLine.length < 3) {
                            continue; // continue iterating even if there is missing data on the line
                        }
                        // pull student values out of the array
                        String studentId = studentLine[0].trim();
                        String name = studentLine[1].trim();
                        // add the student to the hashmap
                        addStudent(studentId, name);
                    }
                }
            }

            // grades phase
            // get the grade path + file and check if it exists
            // if it exists, then proceed with logic
            Path gradesPath = basePath.resolve("grades.tsv");
            if (Files.exists(gradesPath)) {
                // create grade reader
                try (BufferedReader gradeReader = Files.newBufferedReader(gradesPath)) {
                    // loop thru each line after reader has been made
                    for (String line; (line = gradeReader.readLine()) != null;) {
                        // tell to keep iterating even if there is missing data
                        if (line.isEmpty()) {
                            continue;
                        }
                        // create array
                        String[] gradeLine = line.split("\t", -1);
                        // continue iteration even if there is blank data
                        if (gradeLine.length < 3) {
                            continue;
                        }
                        // pull out values of array and put into hashmap
                        // studentid, course code, numeric grade
                        String stuId = gradeLine[0].trim();
                        String courCode = gradeLine[1].trim();
                        Double numGrade = null;
                        try {
                            numGrade = Double.parseDouble(gradeLine[2].trim());
                        } catch (Exception ignored) {
                        }
                        if (numGrade != null) {
                            assignGrade(stuId, courCode, numGrade);
                        }
                    }
                }
            }
            // return true if all successful
            return true;
        } catch (IOException e) {
            return false; // if some IO error happened return false
        }
    }

    private static String tsvHelper(String string) {
        // return empty string if nothing is passed in
        if (string == null) {
            return "";
        }
        // replace tabs, returns, and new line characters in the passed in string and
        // trim it
        String cleanedtsv = string.replace("\t", " ").replace("\n", " ").replace("\r", " ").trim();
        return cleanedtsv;
    }
}