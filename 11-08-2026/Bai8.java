import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Bai8 extends JFrame {
    private JTextField maSVField, tenField, diemField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton, deleteButton, clearButton;

    public Bai8() {
        setTitle("Bài 8 - Quản lý sinh viên bằng JTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));

        inputPanel.add(new JLabel("Mã SV:"));
        maSVField = new JTextField();
        inputPanel.add(maSVField);

        inputPanel.add(new JLabel("Tên:"));
        tenField = new JTextField();
        inputPanel.add(tenField);

        inputPanel.add(new JLabel("Điểm:"));
        diemField = new JTextField();
        inputPanel.add(diemField);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Thêm");
        deleteButton = new JButton("Xóa");
        clearButton = new JButton("Xóa sạch");

        addButton.addActionListener(e -> addStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearFields());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        inputPanel.add(buttonPanel);

        String[] columns = {"Mã SV", "Tên", "Điểm"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void addStudent() {
        String maSV = maSVField.getText().trim();
        String ten = tenField.getText().trim();
        String diem = diemField.getText().trim();

        if (maSV.isEmpty() || ten.isEmpty() || diem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double score = Double.parseDouble(diem);
            if (score < 0 || score > 10) {
                JOptionPane.showMessageDialog(this, "Điểm phải từ 0 đến 10!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            tableModel.addRow(new Object[]{maSV, ten, String.format("%.2f", score)});
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Điểm phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tableModel.removeRow(selectedRow);
    }

    private void clearFields() {
        maSVField.setText("");
        tenField.setText("");
        diemField.setText("");
    }

    public static void main(String[] args) {
        new Bai8();
    }
}
