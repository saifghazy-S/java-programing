import java.util.ArrayList;
import java.io.*;

/**
 * نظام إدارة الطلاب (StudentManagementSystem)
 * الكلاس المسؤول عن العمليات البرمجية وحفظ البيانات
 */
public class StudentManagementSystem {
    private ArrayList<Student> students;
    private final String DATA_FILE = "students_data.txt"; // ملف حفظ البيانات

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        loadFromFile(); // تحميل البيانات تلقائياً عند بدء البرنامج
    }

    // الحصول على قائمة الطلاب
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * إضافة طالب جديد للنظام
     */
    public boolean addStudent(int id, String name, String major) {
        if (id <= 0 || name.trim().isEmpty()) return false;
        
        // التحقق من عدم تكرار الرقم
        if (searchStudentById(id) != null) return false;

        students.add(new Student(id, name, major));
        saveToFile(); // حفظ التغييرات
        return true;
    }

    /**
     * حذف طالب من النظام بواسطة رقمه
     */
    public boolean removeStudent(int id) {
        Student student = searchStudentById(id);
        if (student != null) {
            students.remove(student);
            saveToFile();
            return true;
        }
        return false;
    }

    /**
     * البحث عن طالب بواسطة رقمه
     */
    public Student searchStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    /**
     * إضافة مادة دراسية لطالب معين
     */
    public boolean addSubjectToStudent(int studentId, String subjectName, int creditHours, double grade) {
        Student student = searchStudentById(studentId);
        if (student != null && !subjectName.trim().isEmpty() && grade >= 0 && grade <= 100) {
            student.addSubject(new Subject(subjectName, creditHours, grade));
            saveToFile();
            return true;
        }
        return false;
    }

    /**
     * حفظ كافة البيانات في ملف نصي
     */
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Student s : students) {
                // تنسيق: ID,Name,Major|Sub1:Hours:Grade;Sub2:Hours:Grade
                StringBuilder sb = new StringBuilder();
                sb.append(s.getId()).append(",").append(s.getName()).append(",").append(s.getMajor()).append("|");
                
                for (Subject sub : s.getSubjects()) {
                    sb.append(sub.getSubjectName()).append(":").append(sub.getCreditHours()).append(":").append(sub.getGrade()).append(";");
                }
                writer.println(sb.toString());
            }
        } catch (IOException e) {
            System.err.println("خطأ أثناء حفظ البيانات: " + e.getMessage());
        }
    }

    /**
     * تحميل البيانات من الملف النصي عند تشغيل البرنامج
     */
    public void loadFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String[] info = parts[0].split(",");
                
                Student s = new Student(Integer.parseInt(info[0]), info[1], info[2]);
                
                if (parts.length > 1) {
                    String[] subjects = parts[1].split(";");
                    for (String subStr : subjects) {
                        if (subStr.trim().isEmpty()) continue;
                        String[] sParts = subStr.split(":");
                        s.addSubject(new Subject(sParts[0], Integer.parseInt(sParts[1]), Double.parseDouble(sParts[2])));
                    }
                }
                students.add(s);
            }
        } catch (Exception e) {
            System.err.println("خطأ أثناء تحميل البيانات: " + e.getMessage());
        }
    }
}
