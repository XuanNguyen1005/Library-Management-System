package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtTitle, txtAuthor;

    public LibraryView() {
        setTitle("Quan Ly Thu Vien - Nhom 9");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Giao diện chính
        setLayout(new BorderLayout());

        // Panel nhập liệu phía trên
        JPanel panelInput = new JPanel(new GridLayout(2, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Thong tin sach"));
        
        panelInput.add(new JLabel("Tieu de sach:"));
        txtTitle = new JTextField();
        panelInput.add(txtTitle);

        panelInput.add(new JLabel("Tac gia:"));
        txtAuthor = new JTextField();
        panelInput.add(txtAuthor);

        add(panelInput, BorderLayout.NORTH);

        // Bảng hiển thị dữ liệu
        String[] columns = {"ID", "Tieu de", "Tac gia", "Trang thai"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel nút bấm phía dưới
        JPanel panelButtons = new JPanel();
        JButton btnAdd = new JButton("Them Sach");
        JButton btnSave = new JButton("Luu JSON");
        panelButtons.add(btnAdd);
        panelButtons.add(btnSave);

        add(panelButtons, BorderLayout.SOUTH);

        // Xử lý sự kiện nút Thêm Sách
        btnAdd.addActionListener(e -> {
            String title = txtTitle.getText().trim();
            String author = txtAuthor.getText().trim();
            if (!title.isEmpty() && !author.isEmpty()) {
                String id = "B" + (tableModel.getRowCount() + 1);
                tableModel.addRow(new Object[]{id, title, author, "Co san"});
                txtTitle.setText("");
                txtAuthor.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Vui long nhap day du thong tin sach!");
            }
        });

        // Xử lý sự kiện nút Lưu JSON
        btnSave.addActionListener(e -> {
            try (java.io.FileWriter writer = new java.io.FileWriter("library.json")) {
                com.google.gson.Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
                JOptionPane.showMessageDialog(this, "Luu file library.json thanh cong!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Loi khi luu file!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibraryView().setVisible(true);
        });
    }
}