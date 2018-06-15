package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static final String CONN_STRING = "jdbc:postgresql://localhost:5432/bookinist";
    private static final String USER_NAME = "postgres";
    private static final String PASS = "1";

    public static Connection getConnection() {
        try{
            return getConnection(CONN_STRING, USER_NAME, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(String url, String user, String pass) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    public static void closeConnQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackConnQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
