package quanLyNhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Supplier {

    private static String URL = "jdbc:mysql://localhost:3306/quanlynhacungcap";
    private static String user = "root";
    private static String pass = "1234";

    public static Connection connectJDBC() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, user, pass);
        System.out.println("ket noi thanh cong");
        return conn;
    }

    public static void showSupplier() {
        String sql = "SELECT * FROM supplier";
        try (Statement stm = connectJDBC().createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id")+" - " + rs.getString("name")+" - "
                        + rs.getString("address")+" - " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addSupplier(String name, String address, String phone) {
        String sql = "INSERT INTO Supplier (name, address, phone) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstmt = connectJDBC().prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, phone);
            int row = pstmt.executeUpdate();
            if(row!=0){
                System.out.println("Them thanh cong");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateSupplier(int id, String name, String address, String phone) {
        String sql = "UPDATE Supplier SET name = ?, address = ?, phone = ? WHERE id = ?";

        try {
             PreparedStatement pstmt = connectJDBC().prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, phone);
            pstmt.setInt(4, id);
           int row = pstmt.executeUpdate();
           if(row!=0){
               System.out.println("sua thanh cong");
           }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void deleteSupplier(int id) {
        String sql = "DELETE FROM supplier WHERE id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connectJDBC().prepareStatement(sql);
            pstmt.setInt(1, id);
            int row = pstmt.executeUpdate();
            if (row > 0) {
                System.out.println("Xoa thanh cong");
            } else {
                System.out.println("Khong tim thay du lieu de xoa");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<Supplier> searchSuppliersByNameOrAddress(String keyword) {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM Supplier WHERE name LIKE ? OR address LIKE ?";

        try {
             PreparedStatement pstmt = connectJDBC().prepareStatement(sql);

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id")+" - " + rs.getString("name")+" - "
                        + rs.getString("address")+" - " + rs.getString("phone"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return suppliers;
    }



    public static void main(String[] args) {
//        showSupplier();
//        addSupplier("Công ty Thương Mại ABC", "15A Phố Hòa Bình, Hà Nội", "0945123456");

//        deleteSupplier(1);
//        updateSupplier(1, "Công ty ABC", "123 Street Updated", "987654321");
         List<Supplier> suppliers = searchSuppliersByNameOrAddress("ty");
    for (Supplier supplier : suppliers) {
        System.out.println(supplier);
    }
    }

}


