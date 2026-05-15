import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * مكتبة المكونات الرسومية المخصصة
 * تحتوي على أشكال عصرية (زوايا منحنية، تأثيرات حركية)
 */

// لوحة بخلفية منحنية (Rounded Panel)
class RoundedPanel extends JPanel {
    private int radius;
    private Color backgroundColor;

    public RoundedPanel(int radius, Color bgColor) {
        this.radius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(backgroundColor);
        graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        graphics.dispose();
    }
}

// زر عصري مع تأثير Hover (Rounded Button)
class RoundedButton extends JButton {
    private Color color1;
    private Color color2;
    private int radius;
    private boolean isHovered = false;

    public RoundedButton(String text, Color c1, Color c2, int radius) {
        super(text);
        this.color1 = c1;
        this.color2 = c2;
        this.radius = radius;
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // إضافة تأثير الـ Hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // تغيير اللون عند مرور الماوس
        Color c1 = isHovered ? color1.brighter() : color1;
        Color c2 = isHovered ? color2.brighter() : color2;

        GradientPaint gp = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2);
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        
        g2.dispose();
        super.paintComponent(g);
    }
}

// حقل إدخال نص عصري (Rounded TextField)
class RoundedTextField extends JTextField {
    private int radius;

    public RoundedTextField(int radius) {
        this.radius = radius;
        setOpaque(false);
        setBorder(new EmptyBorder(5, 10, 5, 10));
        setHorizontalAlignment(JTextField.RIGHT); // الكتابة من اليمين
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.setColor(new Color(200, 200, 200));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.dispose();
        super.paintComponent(g);
    }
}
