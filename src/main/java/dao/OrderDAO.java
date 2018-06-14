package dao;

import dto.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {
    public static Boolean insertOrder(Connection conn, OrderDTO order) {
        String sql = "INSERT INTO orders(first_name, last_name, address, quantity, book_id) VALUES (?,?,?,?,?)";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, order.firstName);
            pstm.setString(2, order.lastName);
            pstm.setString(3, order.address);
            pstm.setInt(4, order.quantity);
            pstm.setInt(5, order.book_id);

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {pstm.close();} catch (SQLException e) { }
        }

        return true;
    }

}
