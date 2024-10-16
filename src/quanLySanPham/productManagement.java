package quanLySanPham;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountedCompleter;

public class productManagement {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quanlysanpham";
    private static final String DB_user = "root";
    private static final String DB_pass = "1234";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_user, DB_pass);
        System.out.println("ket noi thanh cong");
        return conn;
    }

    public static void showsProduct() throws SQLException {
        String query = "SELECT *FROM product ";
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                double price = resultSet.getDouble("Price");
                int stock = resultSet.getInt("Stock");
                System.out.println(id + " - " + name + " - " + price + " - " + stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addProduct() {
        String qry = "INSERT INTO product (Name, Price, Stock)" + "VALUES (?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(qry);
            preparedStatement.setString(1, "IPhone 16ProMax");
            preparedStatement.setDouble(2, 4500);
            preparedStatement.setInt(3, 2);
            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                System.out.println("thanh cong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct() throws SQLException {
        String sql = "UPDATE product SET Name=?, Price=?, Stock=? WHERE ID=? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "Iphone 15 promax");
            preparedStatement.setDouble(2, 11000);
            preparedStatement.setInt(3, 4);
            preparedStatement.setInt(4, 1);
            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                System.out.println("thanh cong");
            }
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct() {
        String sql = "DELETE FROM product WHERE ID= ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, 3);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Xoa thanh cong");
            } else {
                System.out.println("Khong thay ban ghi de xoa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<productManagement> searchProductsByName(String name) {
        List<productManagement> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE name LIKE ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = getConnection().prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("ID");
                name = rs.getString("Name");
                double price = rs.getDouble("Price");
                int stock = rs.getInt("Stock");
                System.out.println(id + " - " + name + " - " + price + " - " + stock);
//                products.add(new productManagement(id));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }


    public static void main(String[] args) throws SQLException {
//        addProduct();
//        showsProduct();
//        updateProduct();
//        deleteProduct();
        //Tim kiem theo ten
        List<productManagement> products = searchProductsByName("IPhone");
        for (productManagement product : products) {
            System.out.println(product);
        }
    }


}
