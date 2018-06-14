package dao;

import entities.Author;
import entities.Book;
import entities.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static List<Author> getAllAuthors(Connection conn) {
        List<Author> resList = new ArrayList<Author>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, name FROM authors;");
            while ( rs.next() ) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                resList.add(new Author(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
        }
        return resList;

    }

    public static List<Genre> getAllGenres(Connection conn) {
        List<Genre> resList = new ArrayList<Genre>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, name FROM genres;");
            while ( rs.next() ) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                resList.add(new Genre(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
        }
        return resList;
    }

    public static List<Author> getBookAuthors(Connection conn, Integer Id) {
        List<Author> res = new ArrayList<Author>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT authors.id, authors.name " +
                    "FROM books_authors " +
                    "INNER JOIN authors " +
                    "ON author_id = id " +
                    "WHERE book_id = " + Id + "; ");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                res.add(new Author(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
        }

        return res;
    }

    public static List<Genre> getBookGenres(Connection conn, Integer Id) {
        List<Genre> res = new ArrayList<Genre>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT genres.id, genres.name " +
                    "FROM books_genres " +
                    "INNER JOIN genres " +
                    "ON genre_id = id " +
                    "WHERE book_id = " + Id + "; ");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                res.add(new Genre(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
        }

        return res;
    }

    public static Book getBookById(Connection conn, Integer Id) {
        Book res = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, title, description, price FROM books WHERE id = " + Id + " LIMIT 1;");
            if(rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                List<Author> bookAuthors = getBookAuthors(conn, Id);
                List<Genre> bookGenres = getBookGenres(conn, Id);
                res = new Book(id, title, description, price, bookAuthors, bookGenres);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
        }

        return res;
    }

    public static List<Book> getAllBooks(Connection conn) {
        List<Book> resList = new ArrayList<Book>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, title, description, price FROM books;");
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                List<Author> bookAuthors = getBookAuthors(conn, id);
                List<Genre> bookGenres = getBookGenres(conn, id);
                resList.add(new Book(id, title, description, price, bookAuthors, bookGenres));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
        }
        return resList;
    }

    public static List<Book> filterBooksByAuthors(Connection conn, List<Author> authors) {
        List<Book> resList = new ArrayList<Book>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();

            String sql = "SELECT id, title, description, price " +
                            "FROM " +
                            "(SELECT DISTINCT book_id FROM books_authors WHERE author_id IN (";
            for (Author author : authors) {
                sql += author.getId() + ",";
            }
            if(authors.size() > 0) {
                sql = new String(sql.substring(0, sql.length()-1));
            }

            sql += ")) AS filtered_books " +
                    "INNER JOIN books ON book_id = id;";

            rs = stmt.executeQuery(sql);

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                List<Author> bookAuthors = getBookAuthors(conn, id);
                List<Genre> bookGenres = getBookGenres(conn, id);
                resList.add(new Book(id, title, description, price, bookAuthors, bookGenres));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
        }
        return resList;
    }

    public static List<Book> filterBooksByGenres(Connection conn, List<Genre> genres) {
        List<Book> resList = new ArrayList<Book>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();

            String sql = "SELECT id, title, description, price " +
                    "FROM " +
                    "(SELECT DISTINCT book_id FROM books_genres WHERE genre_id IN (";
            for (Genre genre : genres) {
                sql += genre.getId() + ",";
            }
            if(genres.size() > 0) {
                sql = new String(sql.substring(0, sql.length()-1));
            }

            sql += ")) AS filtered_books " +
                    "INNER JOIN books ON book_id = id;";

            rs = stmt.executeQuery(sql);

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                List<Author> bookAuthors = getBookAuthors(conn, id);
                List<Genre> bookGenres = getBookGenres(conn, id);
                resList.add(new Book(id, title, description, price, bookAuthors, bookGenres));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
        }
        return resList;
    }

    public static List<Book> filterBooksByAuthorsANDGenres(Connection conn, List<Author> authors, List<Genre> genres) {
        List<Book> resList = new ArrayList<Book>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();

            String sql = "SELECT id, title, description, price " +
                    "FROM " +
                    "(SELECT DISTINCT book_id FROM books_authors WHERE author_id IN (";
            for (Author author : authors) {
                sql += author.getId() + ",";
            }
            if(authors.size() > 0) {
                sql = new String(sql.substring(0, sql.length()-1));
            }

            sql += ") " +
                    "INTERSECT " +
                    "SELECT DISTINCT book_id FROM books_genres WHERE genre_id IN (";
            for (Genre genre : genres) {
                sql += genre.getId() + ",";
            }
            if(genres.size() > 0) {
                sql = new String(sql.substring(0, sql.length()-1));
            }

            sql += ")) AS filtered_books " +
                    "INNER JOIN books ON book_id = id;";

            rs = stmt.executeQuery(sql);

            while ( rs.next() ) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                List<Author> bookAuthors = getBookAuthors(conn, id);
                List<Genre> bookGenres = getBookGenres(conn, id);
                resList.add(new Book(id, title, description, price, bookAuthors, bookGenres));
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
