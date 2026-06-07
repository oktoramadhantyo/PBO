// 1. Perintah IMPORT harus berada di baris PALING ATAS
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 2. Class Utama yang namanya SAMA PERSIS dengan nama file (Main.java)
public class Kalkulator {
    public static void main(String[] args) {
        // Menjalankan GUI Kalkulator dengan aman
        SwingUtilities.invokeLater(() -> {
            new KalkulatorFrame().setVisible(true);
        });
    }
}

// 3. Class tambahan (Kalkulator) di BAWAH, dan TIDAK BOLEH pakai kata 'public'
class KalkulatorFrame extends JFrame {
    private JTextField tfAngka1, tfAngka2, tfHasil;
    private JButton btnTambah, btnKurang, btnKali, btnBagi, btnReset, btnKeluar;

    public KalkulatorFrame() {
        setTitle("Project Kalkulator Sederhana");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi komponen input dan teks hasil
        tfAngka1 = new JTextField();
        tfAngka2 = new JTextField();
        tfHasil = new JTextField();
        tfHasil.setEditable(false); // Hasil tidak bisa diketik manual oleh user

        // Inisialisasi tombol operasi matematika
        btnTambah = new JButton("+");
        btnKurang = new JButton("-");
        btnKali = new JButton("x");
        btnBagi = new JButton("/");
        btnReset = new JButton("Reset");
        btnKeluar = new JButton("Keluar");

        // Membuat layout form input (3 baris, 2 kolom)
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInput.add(new JLabel("Angka 1:"));
        panelInput.add(tfAngka1);
        panelInput.add(new JLabel("Angka 2:"));
        panelInput.add(tfAngka2);
        panelInput.add(new JLabel("Hasil:"));
        panelInput.add(tfHasil);

        // Membuat layout tombol (2 baris, 3 kolom)
        JPanel panelTombol = new JPanel(new GridLayout(2, 3, 5, 5));
        panelTombol.add(btnTambah);
        panelTombol.add(btnKurang);
        panelTombol.add(btnKali);
        panelTombol.add(btnBagi);
        panelTombol.add(btnReset);
        panelTombol.add(btnKeluar);

        // Menyusun panel ke frame utama menggunakan BorderLayout
        setLayout(new BorderLayout(20, 20));
        add(panelInput, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);

        // --- Logika Event Handling (Action Listener) ---
        btnTambah.addActionListener(e -> hitung('+'));
        btnKurang.addActionListener(e -> hitung('-'));
        btnKali.addActionListener(e -> hitung('*'));
        btnBagi.addActionListener(e -> hitung('/'));

        // Aksi tombol Reset
        btnReset.addActionListener(e -> {
            tfAngka1.setText("");
            tfAngka2.setText("");
            tfHasil.setText("");
            tfAngka1.requestFocus(); // Kembalikan kursor ke inputan pertama
        });

        // Aksi tombol Keluar
        btnKeluar.addActionListener(e -> System.exit(0));
    }

    // Metode bantuan untuk memproses perhitungan matematika
    private void hitung(char operasi) {
        try {
            double a1 = Double.parseDouble(tfAngka1.getText());
            double a2 = Double.parseDouble(tfAngka2.getText());
            double hasil = 0;

            switch (operasi) {
                case '+': hasil = a1 + a2; break;
                case '-': hasil = a1 - a2; break;
                case '*': hasil = a1 * a2; break;
                case '/': 
                    if (a2 == 0) {
                        tfHasil.setText("Error: Div 0");
                        return;
                    }
                    hasil = a1 / a2; 
                    break;
            }
            tfHasil.setText(String.valueOf(hasil));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error Input", JOptionPane.ERROR_MESSAGE);
        }
    }
}
