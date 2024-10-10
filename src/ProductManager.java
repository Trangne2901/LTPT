import javax.xml.transform.Result;
import java.sql.*;

public class ProductManager {
    public static void main(String[] args) throws SQLException {
//        ProductManager manager = new ProductManager();
//        manager.showDataProduct();
//        showDataProduct();
        updateProduct();
        showProductById(3);
//        deleteProductByID();
    }
    private static final String URL = "jdbc:mysql://localhost:3306/product_manager_database";
    private static String username = "root";
    private static String password = "1234";

    public static Connection connectionDatabase() throws SQLException {
        //  DriverManager.getConnection() để mở kết nối, truyền vào URL, tên người dùng và mật khẩu.

        Connection connection = DriverManager.getConnection(URL, username, password);
        System.out.println("Kết nối thành công");
        return connection;
    }
    public static void showDataProduct(){
        String query = "SELECT * FROM product";
        try (Statement stm = connectionDatabase().createStatement();
             ResultSet rs  = stm.executeQuery(query) ){
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
               double price = rs.getDouble("Price");
                String detail = rs.getString("Detail");
                String manufacturer = rs.getString("Manufacturer");
                int number = rs.getInt("number");
                String status = rs.getString("Status");
                System.out.println(id + " - " + name + " - " + price + " - "
                        + detail + " - " + manufacturer +" - " + number + " - "
                        + " - " + status );
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        
    }
    // Phương thức để truy vấn bản ghi theo ID
    public static void showProductById(int id) {
        String query = "SELECT * FROM product WHERE id = ?";
        try (Connection connection = connectionDatabase();
             PreparedStatement pre = connection.prepareStatement(query)) {
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ID");
                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                System.out.println(id + " - " + name + " - " + price );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//    public static void updateProduct() throws SQLException{
//        String query = "UPDATE product SET detail=? WHERE id =?";
//        PreparedStatement preparedStatement = null;
//        try{
//            preparedStatement= connectionDatabase().prepareStatement(query);
//            preparedStatement .setString(1,"Du lieu moi");
//            preparedStatement .setString(2,"1");
//
//            int row = preparedStatement.executeUpdate();
//            if(row != 0){
//                System.out.println("Cập nhật thành công " + row);
//            }
//
//            //Đóng kết nối
//            connectionDatabase().close();
//
//        }catch( SQLException e ){
//            e.printStackTrace();
//        }
//    }
    public static void updateProduct() throws SQLException{
        String query = "UPDATE product SET manufacturer =?, price =? WHERE id =?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement= connectionDatabase().prepareStatement(query);
            preparedStatement .setString(1,"SamSung");
            preparedStatement .setString(2,"150000");
            preparedStatement .setString(3,"3");

            int row = preparedStatement.executeUpdate();
            if(row != 0){
                System.out.println("Cập nhật thành công " + row);
            }

            //Đóng kết nối
            connectionDatabase().close();

        }catch( SQLException e ){
            e.printStackTrace();
        }
    }
    //Xoa ban ghi
    public static void deleteProductByID() {
        String query = "DELETE FROM LoaiSach WHERE LoaiSachID = ?";
        try (Connection connection = connectionDatabase();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, "4");

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa bản ghi thành công!");
            } else {
                System.out.println("Không tìm thấy bản ghi để xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
