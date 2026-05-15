/**
 * كلاس المادة (Subject)
 * يمثل تفاصيل الكورس الدراسي للطالب
 */
public class Subject {
    private String subjectName; // اسم المادة
    private int creditHours;    // عدد ساعات المادة
    private double grade;       // درجة الطالب في المادة

    /**
     * منشئ الكلاس (Constructor)
     */
    public Subject(String subjectName, int creditHours, double grade) {
        this.subjectName = subjectName;
        this.creditHours = creditHours;
        this.grade = grade;
    }

    // --- الدوال البرمجية (Getters and Setters) ---

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * التحقق مما إذا كانت الدرجة صحيحة (بين 0 و 100)
     */
    public boolean isValidGrade() {
        return grade >= 0 && grade <= 100;
    }

    @Override
    public String toString() {
        return subjectName + " (" + creditHours + " ساعات, درجة: " + grade + ")";
    }
}
