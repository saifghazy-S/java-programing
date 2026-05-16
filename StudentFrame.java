import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

/**
 * النافذة الرئيسية المتطورة (StudentFrame)
 * تحتوي على كافة الأدوات والوظائف المطلوبة مع تصميم عصري
 */
public class StudentFrame extends JFrame {
    private StudentManagementSystem system;
    private DefaultTableModel tableModel;
    private JTable studentTable;
    
    // --- أسماء مطوري البرنامج (يمكنك تعديلها من هنا) ---
    private final String[] DEVELOPERS = {"سيف محمد ممدوح غازي","احمد محمد ثابت","عبد التواب عبد الحميد ","محمد ياسر عبد الستار","" + //
                "عبد الرحمن ذويل","احمد جوده حسنين","عبدالله محمد السيد  "};

    // حقول الإدخال
    private RoundedTextField idField, nameField, majorField, targetIdField, subNameField, gradeField, searchField;
    
    // تسميات الإحصائيات
    private JLabel studentCountLabel, subjectCountLabel, avgGPALabel;
    
    // الألوان المستخدمة في التصميم
    private final Color MAIN_PURPLE = new Color(108, 92, 231);
    private final Color MAIN_BLUE = new Color(0, 184, 212);
    private final Color LIGHT_PURPLE = new Color(162, 155, 254);
    private final Color PANEL_BG = Color.WHITE;

    public StudentFrame(StudentManagementSystem system) {
        this.system = system;
        
        // إعدادات النافذة
        setTitle("نظام إدارة الطلاب الذكي - Smart Student System");
        setSize(1150, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(116, 125, 140));

        // الحاوية الرئيسية مقسومة لنصفين
        JPanel mainContainer = new JPanel(new GridLayout(1, 2, 25, 0));
        mainContainer.setOpaque(false);
        mainContainer.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        mainContainer.add(createRightPanel()); // لوحة الإدخال
        mainContainer.add(createLeftPanel());  // لوحة العرض

        add(mainContainer, BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH); // التذييل (Footer)

        refreshUI(); // تحديث البيانات عند الفتح
    }

    /**
     * إنشاء لوحة إدخال البيانات (الجانب الأيمن)
     */
    private JPanel createRightPanel() {
        RoundedPanel panel = new RoundedPanel(25, PANEL_BG);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel header = new JLabel("➕ إضافة البيانات", SwingConstants.RIGHT);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setForeground(MAIN_PURPLE);
        panel.add(header, BorderLayout.NORTH);

        JPanel forms = new JPanel();
        forms.setOpaque(false);
        forms.setLayout(new BoxLayout(forms, BoxLayout.Y_AXIS));

        // نموذج الطالب
        forms.add(Box.createVerticalStrut(15));
        forms.add(createLabel("رقم الطالب:"));
        idField = new RoundedTextField(12);
        forms.add(idField);

        forms.add(Box.createVerticalStrut(10));
        forms.add(createLabel("اسم الطالب:"));
        nameField = new RoundedTextField(12);
        forms.add(nameField);

        forms.add(Box.createVerticalStrut(10));
        forms.add(createLabel("التخصص:"));
        majorField = new RoundedTextField(12);
        forms.add(majorField);

        forms.add(Box.createVerticalStrut(20));
        RoundedButton addStudentBtn = new RoundedButton("إضافة الطالب", MAIN_PURPLE, LIGHT_PURPLE, 15);
        addStudentBtn.addActionListener(e -> handleAddStudent());
        forms.add(addStudentBtn);

        forms.add(Box.createVerticalStrut(25));
        forms.add(new JSeparator());
        forms.add(Box.createVerticalStrut(20));
        
        // نموذج المادة
        JLabel subHeader = new JLabel("إضافة مادة دراسية", SwingConstants.RIGHT);
        subHeader.setFont(new Font("Arial", Font.BOLD, 18));
        subHeader.setForeground(MAIN_BLUE);
        forms.add(subHeader);

        forms.add(createLabel("رقم الطالب المستهدف:"));
        targetIdField = new RoundedTextField(12);
        forms.add(targetIdField);

        forms.add(createLabel("اسم المادة:"));
        subNameField = new RoundedTextField(12);
        forms.add(subNameField);

        forms.add(createLabel("الدرجة (0-100):"));
        gradeField = new RoundedTextField(12);
        forms.add(gradeField);

        forms.add(Box.createVerticalStrut(20));
        RoundedButton addSubBtn = new RoundedButton("إضافة المادة", MAIN_BLUE, new Color(129, 236, 236), 15);
        addSubBtn.addActionListener(e -> handleAddSubject());
        forms.add(addSubBtn);

        panel.add(forms, BorderLayout.CENTER);
        return panel;
    }

    /**
     * إنشاء لوحة عرض البيانات والبحث (الجانب الأيسر)
     */
    private JPanel createLeftPanel() {
        RoundedPanel panel = new RoundedPanel(25, PANEL_BG);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // قسم البحث
        JPanel top = new JPanel(new BorderLayout(10, 10));
        top.setOpaque(false);
        JLabel searchTitle = new JLabel("🔍 البحث والتحكم", SwingConstants.RIGHT);
        searchTitle.setFont(new Font("Arial", Font.BOLD, 22));
        searchTitle.setForeground(MAIN_BLUE);
        top.add(searchTitle, BorderLayout.NORTH);

        JPanel searchBar = new JPanel(new BorderLayout(8, 0));
        searchBar.setOpaque(false);
        searchField = new RoundedTextField(12);
        searchField.setToolTipText("ابحث باسم الطالب...");
        searchBar.add(searchField, BorderLayout.CENTER);
        
        RoundedButton searchBtn = new RoundedButton("بحث", MAIN_PURPLE, LIGHT_PURPLE, 12);
        searchBtn.addActionListener(e -> refreshUI()); // البحث يعمل عند الضغط
        searchBar.add(searchBtn, BorderLayout.WEST);
        top.add(searchBar, BorderLayout.CENTER);

        panel.add(top, BorderLayout.NORTH);

        // جدول عرض الطلاب
        String[] columns = {"ID", "اسم الطالب", "المواد المسجلة", "المعدل"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(45);
        studentTable.setSelectionBackground(new Color(162, 155, 254, 100));
        
        // تنسيق الأعمدة (توسيع المسافات)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < studentTable.getColumnCount(); i++) {
            studentTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        studentTable.getColumnModel().getColumn(2).setPreferredWidth(250); // مساحة أكبر للمواد

        JScrollPane scroll = new JScrollPane(studentTable);
        scroll.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        scroll.getViewport().setBackground(Color.WHITE);
        panel.add(scroll, BorderLayout.CENTER);

        // أزرار التحكم السفلى
        JPanel controls = new JPanel(new GridLayout(1, 2, 10, 0));
        controls.setOpaque(false);
        RoundedButton deleteBtn = new RoundedButton("🗑️ حذف الطالب المختار", new Color(235, 77, 75), new Color(255, 121, 121), 12);
        deleteBtn.addActionListener(e -> handleDeleteStudent());
        controls.add(deleteBtn);
        
        panel.add(controls, BorderLayout.SOUTH);

        // الإحصائيات (تظهر فوق الجدول)
        JPanel stats = new JPanel(new GridLayout(1, 3, 10, 0));
        stats.setOpaque(false);
        stats.setPreferredSize(new Dimension(400, 90));
        
        studentCountLabel = new JLabel("0", SwingConstants.CENTER);
        subjectCountLabel = new JLabel("0", SwingConstants.CENTER);
        avgGPALabel = new JLabel("0.0", SwingConstants.CENTER);

        stats.add(createStatCard("عدد الطلاب", studentCountLabel, MAIN_PURPLE));
        stats.add(createStatCard("إجمالي المواد", subjectCountLabel, MAIN_BLUE));
        stats.add(createStatCard("متوسط المعدل", avgGPALabel, new Color(162, 155, 254)));
        
        top.add(stats, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * إنشاء التذييل (Footer) مع زر المطورين
     */
    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.setBorder(BorderFactory.createEmptyBorder(0, 25, 10, 25));

        RoundedButton creditsBtn = new RoundedButton("👥 مطوري البرنامج", new Color(45, 52, 54), new Color(99, 110, 114), 10);
        creditsBtn.setPreferredSize(new Dimension(150, 30));
        creditsBtn.addActionListener(e -> showCredits());
        footer.add(creditsBtn, BorderLayout.WEST);

        JLabel copy = new JLabel("جميع الحقوق محفوظة © 2024", SwingConstants.RIGHT);
        copy.setForeground(Color.WHITE);
        footer.add(copy, BorderLayout.EAST);

        return footer;
    }

    // --- الوظائف البرمجية (Logic) ---

    private void handleAddStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String major = majorField.getText();
            
            if (system.addStudent(id, name, major)) {
                idField.setText(""); nameField.setText(""); majorField.setText("");
                refreshUI();
            } else {
                JOptionPane.showMessageDialog(this, "خطأ: الرقم مكرر أو البيانات غير كاملة");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "يرجى إدخال بيانات صحيحة");
        }
    }

    private void handleAddSubject() {
        try {
            int id = Integer.parseInt(targetIdField.getText());
            String name = subNameField.getText();
            double grade = Double.parseDouble(gradeField.getText());
            
            if (system.addSubjectToStudent(id, name, 3, grade)) {
                targetIdField.setText(""); subNameField.setText(""); gradeField.setText("");
                refreshUI();
            } else {
                JOptionPane.showMessageDialog(this, "خطأ: تأكد من رقم الطالب والدرجة (0-100)");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "يرجى إدخال بيانات صحيحة");
        }
    }

    private void handleDeleteStudent() {
        int row = studentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "يرجى اختيار طالب من الجدول أولاً");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        if (system.removeStudent(id)) refreshUI();
    }

    private void showCredits() {
        StringBuilder sb = new StringBuilder("فريق التطوير:\n\n");
        for (String dev : DEVELOPERS) sb.append("- ").append(dev).append("\n");
        JOptionPane.showMessageDialog(this, sb.toString(), "عن البرنامج", JOptionPane.INFORMATION_MESSAGE);
    }

    private void refreshUI() {
        tableModel.setRowCount(0);
        String filter = searchField != null ? searchField.getText().toLowerCase() : "";
        ArrayList<Student> students = system.getStudents();
        
        double sumGPA = 0;
        int totalSubs = 0;
        int count = 0;

        for (Student s : students) {
            // تصفية النتائج بناءً على البحث
            if (!filter.isEmpty() && !s.getName().toLowerCase().contains(filter)) continue;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.getSubjects().size(); i++) {
                sb.append(s.getSubjects().get(i).getSubjectName());
                if (i < s.getSubjects().size() - 1) sb.append(" | "); // مسافة أكبر بين المواد
            }
            
            tableModel.addRow(new Object[]{s.getId(), s.getName(), sb.toString(), String.format("%.2f", s.calculateGPA())});
            
            sumGPA += s.calculateGPA();
            totalSubs += s.getSubjects().size();
            count++;
        }

        studentCountLabel.setText(String.valueOf(count));
        subjectCountLabel.setText(String.valueOf(totalSubs));
        avgGPALabel.setText(String.format("%.2f", count > 0 ? sumGPA / count : 0.0));
    }

    // دوال مساعدة للواجهة
    private JPanel createStatCard(String title, JLabel valLabel, Color color) {
        RoundedPanel card = new RoundedPanel(15, color);
        card.setLayout(new BorderLayout());
        JLabel l1 = new JLabel(title, SwingConstants.CENTER);
        l1.setForeground(Color.WHITE);
        valLabel.setFont(new Font("Arial", Font.BOLD, 20));
        valLabel.setForeground(Color.WHITE);
        card.add(valLabel, BorderLayout.CENTER);
        card.add(l1, BorderLayout.SOUTH);
        return card;
    }

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text, SwingConstants.RIGHT);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        l.setAlignmentX(Component.RIGHT_ALIGNMENT);
        return l;
    }
}
