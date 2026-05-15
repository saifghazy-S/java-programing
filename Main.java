/**
 * Main Class
 * البرنامج الرئيسي لتشغيل نظام إدارة الطلاب
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  مرحباً في نظام إدارة الطلاب           ║");
        System.out.println("║  Welcome to Student Management System  ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        // إنشاء نظام إدارة الطلاب
        StudentManagementSystem system = new StudentManagementSystem();
        
        // بيانات تجريبية (اختياري)
        System.out.println("جاري تحميل البيانات التجريبية...\n");
        
        // إضافة طلاب تجريبيين
        system.addStudent(101, "أحمد علي", "علوم الحاسوب");
        system.addStudent(102, "سارة محمد", "هندسة البرمجيات");
        system.addStudent(103, "عمر خالد", "تقنية المعلومات");
        
        // إضافة مواد تجريبية
        system.addSubjectToStudent(101, "Java Programming", 3, 92);
        system.addSubjectToStudent(101, "Database Systems", 3, 85);
        system.addSubjectToStudent(101, "Data Structures", 4, 90);
        
        system.addSubjectToStudent(102, "Web Development", 3, 95);
        system.addSubjectToStudent(102, "Mobile Apps", 3, 88);
        
        system.addSubjectToStudent(103, "Network Security", 4, 87);
        
        System.out.println("✅ تم تحميل البيانات التجريبية بنجاح!\n");
        
        // تشغيل البرنامج
        system.run();
    }
}
