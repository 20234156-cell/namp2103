import javax.swing.*;
import java.awt.*;

public class bai6 extends JFrame {

    private JTextField txtTaiKhoan;
    private JPasswordField txtMatKhau;
    private JComboBox<String> cboLoaiTaiKhoan;
    private JCheckBox chkGhiNho;

    private JButton btnDangNhap;
    private JButton btnThoat;

    public bai6() {
        setTitle("Bài 6 - Form đăng nhập");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitle = new JLabel(
                "FORM ĐĂNG NHẬP",
                SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel lblTaiKhoan = new JLabel("Tài khoản:");
        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        JLabel lblLoai = new JLabel("Loại tài khoản:");

        txtTaiKhoan = new JTextField();
        txtMatKhau = new JPasswordField();

        cboLoaiTaiKhoan = new JComboBox<>(
                new String[] { "Sinh viên", "Giảng viên", "Quản trị viên" });

        chkGhiNho = new JCheckBox("Ghi nhớ đăng nhập");

        btnDangNhap = new JButton("Đăng nhập");
        btnThoat = new JButton("Thoát");

        JPanel pnlInput = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlInput.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        pnlInput.add(lblTaiKhoan);
        pnlInput.add(txtTaiKhoan);

        pnlInput.add(lblMatKhau);
        pnlInput.add(txtMatKhau);

        pnlInput.add(lblLoai);
        pnlInput.add(cboLoaiTaiKhoan);

        pnlInput.add(new JLabel(""));
        pnlInput.add(chkGhiNho);

        JPanel pnlButton = new JPanel();
        pnlButton.add(btnDangNhap);
        pnlButton.add(btnThoat);

        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.add(lblTitle, BorderLayout.NORTH);
        pnlMain.add(pnlInput, BorderLayout.CENTER);
        pnlMain.add(pnlButton, BorderLayout.SOUTH);

        add(pnlMain);

        btnDangNhap.addActionListener(e -> dangNhap());

        btnThoat.addActionListener(e -> System.exit(0));
    }

    private void dangNhap() {
        String taiKhoan = txtTaiKhoan.getText().trim();
        String matKhau = new String(txtMatKhau.getPassword());
        String loaiTaiKhoan = cboLoaiTaiKhoan.getSelectedItem().toString();

        if (taiKhoan.equals("admin") && matKhau.equals("123456")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Đăng nhập thành công!\n"
                            + "Tài khoản: " + taiKhoan + "\n"
                            + "Loại: " + loaiTaiKhoan);

            if (chkGhiNho.isSelected()) {
                System.out.println("Đã chọn ghi nhớ đăng nhập");
            }

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Tài khoản hoặc mật khẩu không đúng!",
                    "Đăng nhập thất bại",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bai6().setVisible(true);
        });
    }
}