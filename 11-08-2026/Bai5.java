import javax.swing.*;

public class Bai5 extends JFrame {
    private JTextField fieldN;
    private JButton button;
    private JTextArea resultArea;

    public Bai5() {
        setTitle("Bài 5 - Hiển thị n số Fibonacci");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Nhập n:"));
        fieldN = new JTextField(15);
        row1.add(fieldN);

        JPanel row2 = new JPanel();
        button = new JButton("Hiển thị");
        row2.add(button);

        JPanel row3 = new JPanel();
        resultArea = new JTextArea(10, 35);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        row3.add(scrollPane);

        button.addActionListener(e -> showFibonacci());

        panel.add(row1);
        panel.add(row2);
        panel.add(row3);

        add(panel);
        setVisible(true);
    }

    private void showFibonacci() {
        try {
            int n = Integer.parseInt(fieldN.getText());
            if (n <= 0) {
                resultArea.setText("Vui lòng nhập số nguyên dương!");
                return;
            }

            StringBuilder result = new StringBuilder();
            long a = 0, b = 1;

            for (int i = 0; i < n; i++) {
                result.append(a).append(" ");
                long temp = a + b;
                a = b;
                b = temp;
            }

            resultArea.setText("Dãy Fibonacci " + n + " số đầu tiên:\n" + result.toString());
        } catch (NumberFormatException ex) {
            resultArea.setText("Lỗi: Nhập số nguyên không hợp lệ!");
        }
    }

    public static void main(String[] args) {
        new Bai5();
    }
}
