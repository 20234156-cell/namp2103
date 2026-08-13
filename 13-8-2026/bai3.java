import javax.swing.*;
import java.awt.*;

public class bai3 extends JFrame {

    private JTextField txtA;
    private JTextField txtB;
    private JTextField txtKetQua;

    private JButton btnGiai;
    private JButton btnXoa;
    private JButton btnThoat;

    public bai3() {
        setTitle("Bài 3 - Giải phương trình bậc nhất");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel(
                "GIẢI PHƯƠNG TRÌNH BẬC NHẤT ax + b = 0",
                SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblA = new JLabel("Nhập a:");
        JLabel lblB = new JLabel("Nhập b:");
        JLabel lblKetQua = new JLabel("Kết quả:");

        txtA = new JTextField();
        txtB = new JTextField();
        txtKetQua = new JTextField();

        txtKetQua.setEditable(false);

        btnGiai = new JButton("Giải");
        btnXoa = new JButton("Xóa");
        btnThoat = new JButton("Thoát");

        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 10, 10));

        pnlInput.add(lblA);
        pnlInput.add(txtA);

        pnlInput.add(lblB);
        pnlInput.add(txtB);

        pnlInput.add(lblKetQua);
        pnlInput.add(txtKetQua);

        JPanel pnlButton = new JPanel();

        pnlButton.add(btnGiai);
        pnlButton.add(btnXoa);
        pnlButton.add(btnThoat);

        JPanel pnlMain = new JPanel(new BorderLayout(10, 10));
        pnlMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlMain.add(lblTitle, BorderLayout.NORTH);
        pnlMain.add(pnlInput, BorderLayout.CENTER);
        pnlMain.add(pnlButton, BorderLayout.SOUTH);

        add(pnlMain);

        btnGiai.addActionListener(e -> giaiPhuongTrinh());

        btnXoa.addActionListener(e -> {
            txtA.setText("");
            txtB.setText("");
            txtKetQua.setText("");
            txtA.requestFocus();
        });

        btnThoat.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có muốn thoát không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void giaiPhuongTrinh() {
        try {
            String aText = txtA.getText().trim();
            String bText = txtB.getText().trim();

            if (aText.isEmpty() || bText.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Vui lòng nhập đầy đủ a và b!",
                        "Lỗi nhập liệu",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double a = Double.parseDouble(aText);
            double b = Double.parseDouble(bText);

            if (a != 0) {
                double x = -b / a;

                txtKetQua.setText("Phương trình có nghiệm x = " + x);
            } else if (b == 0) {
                txtKetQua.setText("Phương trình có vô số nghiệm");
            } else {
                txtKetQua.setText("Phương trình vô nghiệm");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "a và b phải là số!",
                    "Lỗi nhập liệu",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bai3().setVisible(true);
        });
    }
}