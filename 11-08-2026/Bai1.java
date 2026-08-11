import javax.swing.*;

public class Bai1 extends JFrame {
    private JTextField textField;
    private JButton button;
    private JLabel label;

    public Bai1() {
        setTitle("Bài 1 - Chào người dùng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Nhập tên:");
        textField = new JTextField(15);
        button = new JButton("Chào");
        label = new JLabel("");

        button.addActionListener(e -> {
            String name = textField.getText().trim();
            if (name.isEmpty()) {
                label.setText("Vui lòng nhập tên!");
            } else {
                label.setText("Xin chào, " + name + "!");
            }
        });

        panel.add(nameLabel);
        panel.add(textField);
        panel.add(button);
        panel.add(label);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Bai1();
    }
}
