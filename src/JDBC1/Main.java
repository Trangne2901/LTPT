package JDBC1;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String URLConnection = "jdbc:mysql://localhost:3306/qlsv";
        String username = "root";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(URLConnection, username, password);
            System.out.println("Ket noi thanh cong");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String query = "SELECT * FROM sinhvien";
        try (Connection connection = DriverManager.getConnection(URLConnection, username, password)) {
            Statement stm = connection.createStatement(); // m bị nhầm Statement, phải dùng đúng của sql =))
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {  //Di chuyển con trỏ xuống bản ghi kế tiếp
                int id = rs.getInt("MaSV");
                String tensv = rs.getString("HoTen");
                System.out.println(id + " - " + tensv);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
