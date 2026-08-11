import javax.swing.*;

public class Bai3 extends JFrame {
    private JTextField fieldA, fieldB;
    private JButton button;
    private JLabel resultLabel;

    public Bai3() {
        setTitle("Bài 3 - Giải phương trình bậc nhất (ax + b = 0)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Hệ số a:"));
        fieldA = new JTextField(15);
        row1.add(fieldA);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("Hệ số b:"));
        fieldB = new JTextField(15);
        row2.add(fieldB);

        JPanel row3 = new JPanel();
        button = new JButton("Giải");
        resultLabel = new JLabel("Kết quả: ");
        row3.add(button);
        row3.add(resultLabel);

        button.addActionListener(e -> solve());

        panel.add(row1);
        panel.add(row2);
        panel.add(row3);

        add(panel);
        setVisible(true);
    }

    private void solve() {
        try {
            double a = Double.parseDouble(fieldA.getText());
            double b = Double.parseDouble(fieldB.getText());

            if (a == 0) {
                if (b == 0) {
                    resultLabel.setText("Kết quả: Phương trình có vô số nghiệm");
                } else {
                    resultLabel.setText("Kết quả: Phương trình vô nghiệm");
                }
            } else {
                double x = -b / a;
                resultLabel.setText(String.format("Kết quả: x = %.2f", x));
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Lỗi: Nhập số không hợp lệ!");
        }
    }

    public static void main(String[] args) {
        new Bai3();
    }
}
