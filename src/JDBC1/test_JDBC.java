package JDBC1;

import JDBC1.ConnectJDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test_JDBC {

    private static void themBanGhiMoi(){
        ConnectJDBC connectJDBC = new ConnectJDBC();
//        connectJDBC.connectionDatabase();

        String query = "INSERT INTO LoaiSach (LoaiSachID, TenLoaiSach) VALUES(?,?)";
        PreparedStatement preparedStatement = null;
        try {
           preparedStatement = connectJDBC.connectionDatabase().prepareStatement(query);

            preparedStatement.setString(1, "LS006");
            preparedStatement.setString(2, "GDCD");

            int row = preparedStatement.executeUpdate();
            if(row != 0){
                System.out.println("Thêm thành công " + row);
            }

            connectJDBC.connectionDatabase().close();

        }catch( SQLException e ){
            e.printStackTrace();
        }
    }

    private static void xoaBanGhi() {
        ConnectJDBC connectJDBC = new ConnectJDBC();
//        connectJDBC.connectionDatabase();

        String query = "DELETE FROM LoaiSach WHERE LoaiSachID = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connectJDBC.connectionDatabase().prepareStatement(query);
            preparedStatement.setString(1, "LS006");

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa bản ghi thành công!");
            } else {
                System.out.println("Không tìm thấy bản ghi để xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {

        xoaBanGhi();
        themBanGhiMoi();

        }

}
