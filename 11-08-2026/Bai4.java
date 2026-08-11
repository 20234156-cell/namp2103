import javax.swing.*;

public class Bai4 extends JFrame {
    private JTextField field1, field2, field3;
    private JButton button;
    private JLabel resultLabel;

    public Bai4() {
        setTitle("Bài 4 - Kiểm tra và phân loại tam giác");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Cạnh a:"));
        field1 = new JTextField(15);
        row1.add(field1);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("Cạnh b:"));
        field2 = new JTextField(15);
        row2.add(field2);

        JPanel row3 = new JPanel();
        row3.add(new JLabel("Cạnh c:"));
        field3 = new JTextField(15);
        row3.add(field3);

        JPanel row4 = new JPanel();
        button = new JButton("Kiểm tra");
        resultLabel = new JLabel("Kết quả: ");
        row4.add(button);
        row4.add(resultLabel);

        button.addActionListener(e -> checkTriangle());

        panel.add(row1);
        panel.add(row2);
        panel.add(row3);
        panel.add(row4);

        add(panel);
        setVisible(true);
    }

    private void checkTriangle() {
        try {
            double a = Double.parseDouble(field1.getText());
            double b = Double.parseDouble(field2.getText());
            double c = Double.parseDouble(field3.getText());

            if (a <= 0 || b <= 0 || c <= 0) {
                resultLabel.setText("Kết quả: Các cạnh phải lớn hơn 0");
                return;
            }

            if (a + b <= c || a + c <= b || b + c <= a) {
                resultLabel.setText("Kết quả: Không phải tam giác");
                return;
            }

            String type = "";
            if (a == b && b == c) {
                type = "Tam giác đều";
            } else if (a == b || b == c || a == c) {
                type = "Tam giác cân";
            } else if (Math.abs(a*a + b*b - c*c) < 0.001 ||
                       Math.abs(a*a + c*c - b*b) < 0.001 ||
                       Math.abs(b*b + c*c - a*a) < 0.001) {
                type = "Tam giác vuông";
            } else {
                type = "Tam giác thường";
            }

            resultLabel.setText("Kết quả: " + type);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Lỗi: Nhập số không hợp lệ!");
        }
    }

    public static void main(String[] args) {
        new Bai4();
    }
}
