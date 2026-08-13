import javax.swing.*;

public class Bai2 extends JFrame {
    private JTextField textField1, textField2;
    private JButton button;
    private JLabel resultLabel;

    public Bai2() {
        setTitle("Bài 2 - Tính tổng hai số");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Số thứ nhất:"));
        textField1 = new JTextField(20);
        row1.add(textField1);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("Số thứ hai:"));
        textField2 = new JTextField(15);
        row2.add(textField2);

        JPanel row3 = new JPanel();
        button = new JButton("Tính tổng");
        resultLabel = new JLabel("Kết quả: ");
        row3.add(button);
        row3.add(resultLabel);

        button.addActionListener(e -> {
            try {
                double num1 = Double.parseDouble(textField1.getText());
                double num2 = Double.parseDouble(textField2.getText());
                double sum = num1 + num2;
                resultLabel.setText("Kết quả: " + sum);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Lỗi: Nhập số không hợp lệ!");
            }
        });

        panel.add(row1);
        panel.add(row2);
        panel.add(row3);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Bai2();
    }
}
