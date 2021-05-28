import java.sql.*;
public final class Koneksi {
    ResultSet rs;
    Statement st;
    Connection con;

    public Koneksi() {
        try {
            //MySql
            String url = "jdbc:mysql://localhost:3306/resto"; //url database
            String user = "root"; //user database
            String pass = ""; //password database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con=DriverManager.getConnection(url, user, pass);
            st = con.createStatement();
            System.out.println("sukses ");
        } catch (SQLException e) {
            System.err.println("koneksi gagal " + e.getMessage()); //perintah menampilkan error pada koneksi
        }
    }

    public ResultSet execQuery(String query) {
        try {
            rs = st.executeQuery(query);
        } catch (Exception e) {
            rs = null;
            e.printStackTrace();
        }
        return rs;
    }

    public void execUpdate(String query) {
        try {
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}