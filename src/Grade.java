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

    // gets the letter of the grade ie A+
    public GradeLetter getLetter() {
        return GradeLetter.fromScore(this.numericScore);
    }

    // gets points for gpa on 4.0 scale
    public double getGradePoints() {
        return getLetter().getPoints();
    }

    // GradeLetter ENUM HELPER FOR WEIGHTED GRADES AND GPA
    public enum GradeLetter {
        // enum constants
        A_PLUS("A+", 4.0, 97, 100),
        A("A", 4.0, 93, 97),
        A_MINUS("A-", 3.7, 90, 93),
        B_PLUS("B+", 3.3, 87, 90),
        B("B", 3.0, 83, 87),
        B_MINUS("B-", 2.7, 80, 83),
        C_PLUS("C+", 2.3, 77, 80),
        C("C", 2.0, 73, 77),
        C_MINUS("C-", 1.7, 70, 73),
        D_PLUS("D+", 1.3, 67, 70),
        D("D", 1.0, 63, 67),
        D_MINUS("D-", 0.7, 60, 63),
        F("F", 0.0, 0, 60);

        // enum attributes
        private final String label;
        private final double points;
        private final double min;
        private final double max;

        // enum constructor
        GradeLetter(String label, double points, double min, double max) {
            this.label = label;
            this.points = points;
            this.min = min;
            this.max = max;
        }

        // enum methods
        public String getLabel() {
            return label;
        }

        public double getPoints() {
            return points;
        }

        // find which letter the score maps to
        public static GradeLetter fromScore(double score) {
            // loop thru grade letter list and see what score falls into
            for (GradeLetter letter : GradeLetter.values()) {
                if (score >= letter.min && (score < letter.max || letter == A_PLUS)) {
                    return letter;
                }
            }
            return F; // if nothing is returned fall back to F
        }
    }
}
