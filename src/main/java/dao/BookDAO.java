package dao;

import dto.OrderDTO;
import entities.Author;
import entities.Book;
import entities.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static List<Author> getAllAuthors(Connection conn) {
        List<Author> resList = new ArrayList<Author>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, name FROM authors ORDER BY name;");
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
            rs = stmt.executeQuery("SELECT id, name FROM genres ORDER BY name;");
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
                    "WHERE book_id = " + Id + " " +
                    "ORDER BY authors.name; ");
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
                    "WHERE book_id = " + Id + " " +
                    "ORDER BY genres.name; ");
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

    public static Author getAuthorById(Connection conn, Integer Id) {
        Author res = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, name FROM authors WHERE id = " + Id + " LIMIT 1;");
            if(rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                res = new Author(id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {rs.close();} catch (SQLException e) { }
            try {stmt.close();} catch (SQLException e) { }
        }

        return res;
    }

    public static Genre getGenreById(Connection conn, Integer Id) {
        Genre res = null;

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, name FROM genres WHERE id = " + Id + " LIMIT 1;");
            if(rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                res = new Genre(id, name);
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
            rs = stmt.executeQuery("SELECT id, title, description, price FROM books ORDER BY title;");
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
                    "INNER JOIN books ON book_id = id " +
                    "ORDER BY title;";

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
                    "INNER JOIN books ON book_id = id " +
                    "ORDER BY title;";

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
                    "INNER JOIN books ON book_id = id " +
                    "ORDER BY title;";

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

    public static Boolean insertAuthor(Connection conn, String name) {
        String sql = "INSERT INTO authors(name) VALUES (?)";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, name);

            pstm.executeUpdate();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        } finally {
            try {pstm.close();} catch (SQLException e) { }
        }

        return true;
    }

    public static Boolean insertGenre(Connection conn, String name) {
        String sql = "INSERT INTO genres(name) VALUES (?)";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, name);

            pstm.executeUpdate();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        } finally {
            try {pstm.close();} catch (SQLException e) { }
        }

        return true;
    }

    public static Boolean updateAuthor(Connection conn, Integer id, String name) {
        String sql = "UPDATE authors SET name=? WHERE id=?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, name);
            pstm.setInt(2, id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        } finally {
            try {pstm.close();} catch (SQLException e) { }
        }

        return true;
    }

    public static Boolean updateGenre(Connection conn, Integer id, String name) {
        String sql = "UPDATE genres SET name=? WHERE id=?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, name);
            pstm.setInt(2, id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        } finally {
            try {pstm.close();} catch (SQLException e) { }
        }

        return true;
    }

    public static Boolean delAuthor(Connection conn, Integer id) {
        String sql = "DELETE FROM authors WHERE id=?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        } finally {
            try {pstm.close();} catch (SQLException e) { }
        }

        return true;
    }

    public static Boolean delGenre(Connection conn, Integer id) {
        String sql = "DELETE FROM genres WHERE id=?";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        } finally {
            try {pstm.close();} catch (SQLException e) { }
        }

        return true;
    }

}
