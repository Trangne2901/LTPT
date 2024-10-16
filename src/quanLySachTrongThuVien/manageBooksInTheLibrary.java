package quanLySachTrongThuVien;

import JDBC1.ConnectJDBC;
import quanLySanPham.productManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class manageBooksInTheLibrary {

    private static String URL = "jdbc:mysql://localhost:3306/book";
    private static String user = "root";
    private static String pass = "1234";

    public static Connection connectJDBC() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, user, pass);
        System.out.println("ket noi thanh cong");
        return conn;
    }

    public static void addBook() {
        String query = "INSERT INTO book (title, author, year_published)" + "VALUES(?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connectJDBC().prepareStatement(query);
            preparedStatement.setString(1, "Pride and Prejudice");
            preparedStatement.setString(2, "Jane Austen");
            preparedStatement.setInt(3, 1993);
            int row = preparedStatement.executeUpdate();
            if (row != 0) {
                System.out.println("Them thanh cong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showBook() throws SQLException {
        String sql = "SELECT * FROM book ";
        try (Statement statement = connectJDBC().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String title = resultSet.getString("title");
//                String author = resultSet.getString("author");
//                int year_published = resultSet.getInt("year_published");
//                System.out.println(id +"-" + title +"-"+ author+"-" + year_published);
                System.out.println(resultSet.getInt("id")+" - "+resultSet.getString("title")+" - "
                        +resultSet.getString("author")+" - "+resultSet.getInt("year_published"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateBook(){
        String query = "UPDATE book SET title=?, author=?, year_published=? WHERE id=?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connectJDBC().prepareStatement(query);
            preparedStatement.setString(1,"Số Đỏ");
            preparedStatement.setString(2,"Vu Trong Phung");
            preparedStatement.setInt(3,1936);
            preparedStatement.setInt(4,3);
            int row = preparedStatement.executeUpdate();
            if(row != 0){
                System.out.println("Cap nhat thanh cong");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteBook() {
        String sql = "DELETE FROM product WHERE ID= ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connectJDBC().prepareStatement(sql);
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

    public static List<manageBooksInTheLibrary> searchBookByName(String key){
        List<manageBooksInTheLibrary> Books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE title LIKE ? OR author LIKE ?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connectJDBC().prepareStatement(sql);
            preparedStatement.setString(1,"%" + key +"%");
            preparedStatement.setString(2, "%" + key +"%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int year_published = resultSet.getInt("year_published");
                System.out.println(id +"-" + title +"-"+ author+"-" + year_published);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Books;
    }


    public static void main(String[] args) throws SQLException {
//        addBook();
//        updateBook();
        List<manageBooksInTheLibrary> Books = searchBookByName("hoa");
        for (manageBooksInTheLibrary book : Books) {
            System.out.println(book);
        }
//        showBook();
    }
}

