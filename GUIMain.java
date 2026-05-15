import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * الكلاس الرئيسي لتشغيل البرنامج (GUIMain)
 */
public class GUIMain {
    public static void main(String[] args) {
        // تحسين مظهر النوافذ لتناسب نظام التشغيل
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // تجاهل الخطأ واستخدام المظهر الافتراضي
        }

        // تشغيل الواجهة الرسومية
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystem system = new StudentManagementSystem();
            
            // فتح النافذة الرئيسية
            StudentFrame frame = new StudentFrame(system);
            frame.setVisible(true);
        });
    }
}
