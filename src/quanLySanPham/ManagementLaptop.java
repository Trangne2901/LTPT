package quanLySanPham;

import java.sql.*;


public class ManagementLaptop {

    static String DB_URL = "jdbc:mysql://localhost:3306/quanlysanpham";
    static String user = "root";
    static String pass = "1234";

    public static Connection connectionJDBC () throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, user, pass);
        System.out.println("Ket noi thanh cong.");
        return conn;
    }
    public static void showLaptop(){
        String sql = "SELECT * FROM laptop";
        try(Statement statement = connectionJDBC().createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                String ram = resultSet.getString("ram");
                String ssd = resultSet.getString("ssd");
                String chipset = resultSet.getString("chipset");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                System.out.println(id + " - " + name + " - "+ ram +" - "+ ssd+" - "
                        +chipset+" - "+price+" - "+stock);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void addLaptop() {
        String qry = "INSERT INTO laptop (name, ram, ssd, chipset, price, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connectionJDBC().prepareStatement(qry)) {
            preparedStatement.setString(1, "MacBook Air M2"); // Fixed product name typo
            preparedStatement.setString(2, "8GB");
            preparedStatement.setString(3, "518GB");          // Fixed storage size
            preparedStatement.setString(4, "Apple M2");       // Fixed chipset typo
            preparedStatement.setDouble(5, 1200);              // Price in numeric form
            preparedStatement.setInt(6, 1);                  // Stock quantity

            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                System.out.println("Thêm thành công laptop mới.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateLaptop(){
        String sql = "UPDATE laptop SET name=? ram = ? ";
    }
    public static void main(String[] args) {
        showLaptop();
        addLaptop();
    }
}
