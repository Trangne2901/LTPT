package qunaLyKhachHang;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class managementCustomer {
    private static final String URL = "jdbc:mysql://localhost:3306/quanlykhachhang";
    private static final String user = "root";
    private static final String pass = "1234";

    public static Connection connectionJDBC() throws SQLException {
        java.sql.Connection conn = DriverManager.getConnection(URL, user, pass);
        System.out.println("ket noi thanh cong");
        return conn;
    }
     public static void showCustomer(){
        String sql = "SELECT * FROM customer";
        try(Statement stm = connectionJDBC().createStatement();
            ResultSet rs = stm.executeQuery(sql)){
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                System.out.println(id+ " - "+ name + " - " + email +" - "+ phone);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

     }
     public static void addCustomer(){
        String add = "INSERT INTO customer (name, email, phone )" + "VALUES (?,?,?)";
        PreparedStatement pst = null;
        try{
            pst= connectionJDBC().prepareStatement(add);
            pst.setString(1,"Hoàng Văn E");
            pst.setString(2,"hoangvane@gmail.com");
            pst.setString(3,"0905678901");
            int row = pst.executeUpdate();
            if(row != 0){
                System.out.println("them thanh cong ");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
     }
     public static void updateCustomer(){
        String update = "UPDATE customer SET name=?, email=?, phone=?  WHERE id=?";
        PreparedStatement pst = null;
        try{
            pst = connectionJDBC().prepareStatement(update);
            pst.setString(1, "Hoàng Thị Lan");
            pst.setString(2,"lanhoang@gmail.com");
            pst.setString(3,"0905567890");
            pst.setInt(4,5);
            int row = pst.executeUpdate();
            if(row!=0){
                System.out.println("Sua thanh cong");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
     }
     public static void deleteCustomer(){
        String delete = "DELETE FROM customer WHERE id=?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connectionJDBC().prepareStatement(delete);
            preparedStatement.setString(1,"6");
            int row = preparedStatement.executeUpdate();
            if(row>0){
                System.out.println("Xoa thanh cong");
            }else {
                System.out.println("khong tim thay ban ghi de xoa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
     public static List<managementCustomer> searchCustomerByNameOrEmail(String keyword){
         String sql = "SELECT * FROM Customer WHERE name LIKE ? OR email LIKE ?";
         List<managementCustomer> Customer = new ArrayList<>();
         PreparedStatement pstmt = null;
         try{
             pstmt = connectionJDBC().prepareStatement(sql);
             pstmt.setString(1, "%" + keyword + "%");
             pstmt.setString(2, "%" + keyword + "%");

             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 int id = rs.getInt("id");
                 String name = rs.getString("name");
                 String email = rs.getString("email");
                 String phone = rs.getString("phone");
                 System.out.println(id+ " - "+ name + " - " + email +" - "+ phone);
             }

         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         return Customer;

     }

    public static void main(String[] args) {
//        addCustomer();
//        showCustomer();
//        updateCustomer();
//        deleteCustomer();
        List<managementCustomer> Customer = searchCustomerByNameOrEmail("Lan");
        for (managementCustomer Customers : Customer) {
            System.out.println(Customers);
        }
    }
}
