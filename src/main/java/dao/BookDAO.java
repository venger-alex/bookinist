package dao;

import entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static List<Book> getAllBooks() {
        List<Book> resList = new ArrayList<Book>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String connStr = "jdbc:postgresql://localhost:5432/bookinist";
        String userStr = "postgres";
        String passStr = "1";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            conn = DriverManager.getConnection(connStr, userStr, passStr);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM book;");
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                resList.add(new Book(id, title, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
            try {conn.close(); } catch (SQLException e) { }
        }
        return resList;
    }
}
