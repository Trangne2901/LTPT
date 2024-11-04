package quanLySanPham;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {
    private static String URL = "jdbc:mysql//localhost:3306/quanlysanpham";
    private static String USER = "root";
    private static String PASS = "1234";

    public static Connection connectionJDBC() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("ket noi thanh cong");
        return conn;
    }

    public static void showListProductAndCategory() {
        String sql = "SELECT p.id, p.name, p.price, p.category_id FROM Product p";


    }

}
