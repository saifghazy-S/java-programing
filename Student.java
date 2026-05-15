import java.util.ArrayList;

/**
 * كلاس الطالب (Student)
 * يحتوي على بيانات الطالب الشخصية وقائمة مواده الدراسية
 */
public class Student {
    private int id;                 // رقم الطالب
    private String name;           // اسم الطالب
    private String major;          // التخصص الدراسي
    private ArrayList<Subject> subjects; // قائمة المواد المسجلة

    /**
     * منشئ الكلاس (Constructor)
     */
    public Student(int id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.subjects = new ArrayList<>();
    }

    // --- الدوال البرمجية (Getters and Setters) ---

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    /**
     * إضافة مادة جديدة لقائمة الطالب
     */
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    /**
     * حذف مادة من قائمة الطالب بواسطة ترتيبها (Index)
     */
    public boolean removeSubject(int index) {
        if (index >= 0 && index < subjects.size()) {
            subjects.remove(index);
            return true;
        }
        return false;
    }

    /**
     * حساب المعدل التراكمي (GPA) للطالب
     * المعادلة: مجموع (الدرجة × الساعات) / مجموع الساعات
     */
    public double calculateGPA() {
        if (subjects.isEmpty()) return 0.0;

        double totalPoints = 0;
        int totalCredits = 0;

        for (Subject subject : subjects) {
            totalPoints += subject.getGrade() * subject.getCreditHours();
            totalCredits += subject.getCreditHours();
        }

        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }

    /**
     * تحويل المعدل الرقمي إلى تقدير حرفي
     */
    public char convertGradeToLetter(double grade) {
        if (grade >= 90) return 'A';
        if (grade >= 80) return 'B';
        if (grade >= 70) return 'C';
        if (grade >= 60) return 'D';
        return 'F';
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
