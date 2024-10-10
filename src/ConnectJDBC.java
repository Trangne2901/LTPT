import java.sql.*;

public class ConnectJDBC {
    static final String URLConnection = "jdbc:mysql://localhost:3306/bookstore";
    private static String username = "root";
    private static String password = "1234";
//    URLConnection: URL để kết nối với cơ sở dữ liệu MySQL đang chạy trên localhost (máy cục bộ) và sử dụng database có tên là bookstore.
//    username: Tên tài khoản MySQL là root.
//    password: Mật khẩu MySQL

    // Phương thức để thiết lập kết nối đến cơ sở dữ liệu
    public static Connection connectionDatabase() throws SQLException {
//  DriverManager.getConnection() để mở kết nối, truyền vào URL, tên người dùng và mật khẩu.
        Connection connection = DriverManager.getConnection(URLConnection, username, password);
        System.out.println("Kết nối thành công");
        return connection;
    }

    // Phương thức để lấy và hiển thị dữ liệu
    public void showData() {
        String query = "SELECT * FROM LoaiSach";
        try (Connection connection = connectionDatabase();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String idLoaiSach = rs.getString("LoaiSachID");
                String tenLoaiSach = rs.getString("TenLoaiSach");
                System.out.println(idLoaiSach + " - " + tenLoaiSach);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức để thêm dữ liệu mới
    public void insertData(String loaiSachID, String tenLoaiSach) {
        String query = "INSERT INTO LoaiSach (LoaiSachID, TenLoaiSach) VALUES(?, ?)";
        try (Connection connection = connectionDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, loaiSachID);
            preparedStatement.setString(2, tenLoaiSach);
//            preparedStatement.setString(1, "LS006");
//            preparedStatement.setString(2, "GDCD");

            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                System.out.println("Thêm thành công " + row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Xoa ban ghi
    public void deleteData() {
        String query = "DELETE FROM LoaiSach WHERE LoaiSachID = ?";
        try (Connection connection = connectionDatabase();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, "LS006");

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
//    cap nhat du lieu
    public static void updateData(){
        String query = "UPDATE LoaiSach SET TenLoaiSach =? WHERE LoaiSachID=?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connectionDatabase().prepareStatement(query);
            preparedStatement.setString(1,"Giao Duc Cong Dan");
            preparedStatement.setString(2,"LS006");

            int row = preparedStatement.executeUpdate();
            if(row != 0){
                System.out.println("Cập nhật thành công " + row);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
//        ConnectJDBC demoJDBC = new ConnectJDBC();
//        demoJDBC.showData(); // Lấy và hiển thị dữ liệu
//        demoJDBC.deleteData(); // Xoa
//        // Thêm dữ liệu mới
//        demoJDBC.insertData("LS006", "GDCD");
        updateData();
    }
}
