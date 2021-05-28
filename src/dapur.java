import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class dapur extends JFrame implements ActionListener,MouseListener{
    private final Koneksi con = new Koneksi();
    private ResultSet rs;
    //table
    private final DefaultTableModel dtm = new DefaultTableModel(new Object[]{"Kode Produk", "Nama Produk", "Jenis Produk", "Tipe Produk", "Harga", "Jumlah"}, 1);
    private final JTable jTable1 = new JTable();

    //button
    private final JButton btnInsert = new JButton("Insert");
    private final JButton btnUpdate = new JButton("Update");
    private final JButton btnDelete = new JButton("Delete");

    //label
    private final JLabel judul = new JLabel("Dapur Mahasiswa",JLabel.CENTER);
    private final JLabel kodeProduk = new JLabel("Kode Produk",JLabel.CENTER);
    private final JLabel namaProduk = new JLabel("Nama Produk",JLabel.CENTER);
    private final JLabel jenisProduk = new JLabel("Jenis Produk",JLabel.CENTER);
    private final JLabel tipeProduk = new JLabel("Tipe Produk",JLabel.CENTER);
    private final JLabel harga = new JLabel("Harga",JLabel.CENTER);
    private final JLabel jumlah = new JLabel("Jumlah",JLabel.CENTER);

    //textfield
    private final JTextField txtKode = new JTextField();
    private final JTextField txtNama = new JTextField();
    private final JTextField txtHarga = new JTextField();
    private final JTextField txtJumlah = new JTextField();

    //Combo Box
    private final JComboBox comboJenis = new JComboBox();
    private final JComboBox comboTipe = new JComboBox();

    private final JPanel jPanel1 = new JPanel(new BorderLayout());
    private final JPanel center = new JPanel(new GridLayout(2,1));
    private final JPanel isi = new JPanel(new GridLayout(6,2));
    private final JPanel south = new JPanel(new FlowLayout());
    private final JScrollPane jScrollPane1 = new JScrollPane();

    public dapur(){
        setTitle("(DM) Dapur Mahasiswa");
        jTable1.setModel(dtm);
        jScrollPane1.setViewportView(jTable1);
        judul.setFont(new Font("Tahoma", 0, 18));

        //Item Jenis Produk
        comboJenis.addItem("");
        comboJenis.addItem("Makanan");
        comboJenis.addItem("Minuman");

        //Item Tipe Produk
        comboTipe.addItem("");
        comboTipe.addItem("Indonesia");
        comboTipe.addItem("Western");
        comboTipe.addItem("China");
        comboTipe.addItem("Lain-Lain");

        isi.add(kodeProduk);isi.add(txtKode);
        isi.add(namaProduk);isi.add(txtNama);
        isi.add(jenisProduk);isi.add(comboJenis);
        isi.add(tipeProduk);isi.add(comboTipe);
        isi.add(harga);isi.add(txtHarga);
        isi.add(jumlah);isi.add(txtJumlah);

        center.add(jScrollPane1);
        center.add(isi);
        jPanel1.add(center,BorderLayout.CENTER);
        south.add(btnInsert);
        south.add(btnUpdate);
        south.add(btnDelete);
        jPanel1.add(judul,BorderLayout.NORTH);
        jPanel1.add(south,BorderLayout.SOUTH);

        add(jPanel1);
        refTable();
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        jTable1.addMouseListener(this);
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    private int cekAngka(String x){
        for(int i=0;i<x.length();i++){
            if(x.charAt(i) < '0' || x.charAt(i) > '9'){
                return 0;
            }
        }
        return 1;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnInsert){
            if(txtNama.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Nama Harus Diisi!");
            }else if(comboJenis.getSelectedItem().equals("")){
                JOptionPane.showMessageDialog(null, "Jenis Produk Harus Diisi!");
            }else if(comboTipe.getSelectedItem().equals("")){
                JOptionPane.showMessageDialog(null, "Tipe Produk Harus Diisi!");
            }else if(txtHarga.getText().equals("")|| cekAngka(txtHarga.getText())==0){
                JOptionPane.showMessageDialog(null, "Harga Tidak Boleh Kosong dan Harus Angka!");
            }else if(txtJumlah.getText().equals("")|| cekAngka(txtHarga.getText())==0) {
                JOptionPane.showMessageDialog(null, "Jumlah Tidak Boleh Kosong dan Harus Angka!");
            }else{
                int pilih = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if(pilih == JOptionPane.YES_OPTION){
                    String kode = txtKode.getText();
                    String nama = txtNama.getText();
                    String jenis = (String) comboJenis.getSelectedItem();
                    String tipe = (String) comboTipe.getSelectedItem();
                    String harga = txtHarga.getText();
                    String jumlah = txtJumlah.getText();

                    try{
                        con.execUpdate("insert into produk values('"+kode+"','"+nama+"','"+jenis+"','"+tipe+"','"+harga+"','"+jumlah+"')");
                        refTable();
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null,rs);
                        }else {
                            JOptionPane.showMessageDialog(null, "Sukses Insert Data");
                        }
                    }catch(SQLException E){
                        JOptionPane.showMessageDialog(null, E);
                    }
                }
            }
        }
        if(e.getSource() == btnUpdate){
            if(jTable1.getSelectedRow() != -1){
                String kode = txtKode.getText();
                String nama = txtNama.getText();
                String jenis = (String) comboJenis.getSelectedItem();
                String tipe = (String) comboTipe.getSelectedItem();
                String harga = txtHarga.getText();
                String jumlah= txtJumlah.getText();
                con.execUpdate("Update produk set kodeProduk='"+kode+"', NamaProduk='"+nama+"', JenisProduk="+jenis+", TipeProduk='"+tipe+"', Harga='"+harga+"', Jumlah='"+jumlah+"'  where idProduk='"+jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()+"'");
                refTable();
                JOptionPane.showMessageDialog(null, "Sukses Update Data");
            }else{
                JOptionPane.showMessageDialog(null, "Please choose data!");
            }
        }
        if(e.getSource() == btnDelete){
            if(jTable1.getSelectedRow() != -1){
                String kode = txtKode.getText();
                int pilih = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if(pilih == JOptionPane.YES_OPTION){
                    con.execUpdate("Delete from produk where kodeProduk='"+kode+"'");
                    refTable();
                    JOptionPane.showMessageDialog(null, "Sukses Hapus Data");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Please choose data!");
            }
        }
    }
    public void refTable(){
        int x = dtm.getRowCount();
        for(int i=0;i<x;i++){
           dtm.removeRow(0);
        }
        rs = con.execQuery("SELECT * FROM produk");
        Vector<String> vec = new Vector<String>();
        try {
            while (rs.next()) {
                vec = new Vector<String>();
                vec.add(rs.getString(1));
                vec.add(rs.getString(2));
                vec.add(rs.getString(3));
                vec.add(rs.getString(4));
                vec.add(rs.getString(5));
                vec.add(rs.getString(6));
                dtm.addRow(vec);
            }
        } catch (Exception ex) {}
        txtKode.setText("");
        txtNama.setText("");
        comboJenis.setSelectedItem("");
        comboTipe.setSelectedItem("");
        txtHarga.setText("");
        txtJumlah.setText("");
    }
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==jTable1){
            try{
                txtKode.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                txtNama.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
                comboJenis.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
                comboTipe.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
                txtHarga.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
                txtJumlah.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
            }catch(Exception f){}
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    public static void main(String[] args){
        new dapur();
    }
}