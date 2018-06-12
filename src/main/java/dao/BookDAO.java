package dao;

import entities.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static List<Book> getAllBooks(Connection conn) {
        List<Book> resList = new ArrayList<Book>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
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
        }
        return resList;
    }
}
