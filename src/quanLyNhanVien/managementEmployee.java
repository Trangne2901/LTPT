package quanLyNhanVien;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class managementEmployee {
    private static String URL = "jdbc:mysql://localhost:3306/quanlynhanvien";
    private static String user = "root";
    private static String pass = "1234";

    public static Connection connectJDBC() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, user, pass);
        System.out.println("ket noi thanh cong");
        return conn;
    }

    public static void showEmployee() {
        String sql = "SELECT * FROM employee";
        try (Statement stm = connectJDBC().createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");
                System.out.println(id + " - " + name + " - " + department + " - " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addEmployee(String name, String department, Double salary) {
        String sql = "INSERT INTO Employee (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = connectJDBC().prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setDouble(3, salary);
            int row = pstmt.executeUpdate();
            if (row != 0) {
                System.out.println("Them thanh cong " + row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateEmployee(int id, String name, String department, double salary) {
        String sql = "UPDATE Employee SET name = ?, department = ?, salary = ? WHERE id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connectJDBC().prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setDouble(3, salary);
            pstmt.setInt(4, id);
            int row = pstmt.executeUpdate();
            if (row != 0) {
                System.out.println("Sua thanh cong");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteEmployee(int id) {
        String sql = "DELETE FROM Employee WHERE id = ?";
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

    public static List<managementEmployee> searchEmployeesByNameOrDepartment(String keyword) {
        List<managementEmployee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE name LIKE ? OR department LIKE ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connectJDBC().prepareStatement(sql);

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");
                System.out.println(id + " - " + name + " - " + department + " - " + salary);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }


    public static void main(String[] args) {
//        showEmployee();
//        addEmployee("Alice", "IT", 75000000.0);

//        addEmployee("HuyenTrang", "IT", 50000000.0);
//        updateEmployee(1, "Nguyễn Văn Hùng", "Kinh Doanh", new BigDecimal("1500000"));
//        deleteEmployee(6);
        List<managementEmployee> employees = searchEmployeesByNameOrDepartment("IT");
        for (managementEmployee employee : employees) {
            System.out.println(employee);
        }
    }

}
