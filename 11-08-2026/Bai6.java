import javax.swing.*;

public class Bai6 extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, clearButton;
    private JLabel messageLabel;

    public Bai6() {
        setTitle("Bài 6 - Form đăng nhập cơ bản");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Tên đăng nhập:"));
        usernameField = new JTextField(20);
        row1.add(usernameField);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("Mật khẩu:"));
        passwordField = new JPasswordField(20);
        row2.add(passwordField);

        JPanel row3 = new JPanel();
        loginButton = new JButton("Đăng nhập");
        clearButton = new JButton("Xóa");
        messageLabel = new JLabel("");
        row3.add(loginButton);
        row3.add(clearButton);

        JPanel row4 = new JPanel();
        row4.add(messageLabel);

        loginButton.addActionListener(e -> login());
        clearButton.addActionListener(e -> clear());

        panel.add(row1);
        panel.add(row2);
        panel.add(row3);
        panel.add(row4);

        add(panel);
        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Vui lòng nhập tên đăng nhập và mật khẩu!");
            return;
        }

        if (username.equals("admin") && password.equals("123456")) {
            messageLabel.setText("Đăng nhập thành công!");
        } else {
            messageLabel.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
        }
    }

    private void clear() {
        usernameField.setText("");
        passwordField.setText("");
        messageLabel.setText("");
    }

    public static void main(String[] args) {
        new Bai6();
    }
}
