package servlets;

import dao.BookDAO;
import dao.OrderDAO;
import dto.OrderDTO;
import entities.Book;
import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class BookViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("orderMsg", "Order book");

        final String firstName = req.getParameter("first_name");
        final String lastName = req.getParameter("last_name");
        final String address = req.getParameter("address");
        final String quantityString = req.getParameter("quantity");
        final Integer quantity = quantityString == null ? 0 : Integer.parseInt(quantityString);

        final Integer bookId = Integer.parseInt(req.getParameter("id"));

        if(firstName != null && lastName != null && address != null && (quantity > 0)) {
            if(OrderDAO.insertOrder(conn, new OrderDTO(firstName, lastName, address, quantity, bookId))) {
                req.setAttribute("orderMsg", "Order accepted, order more");
            } else {
                req.setAttribute("orderMsg", "Order not accepted, try again");
            }
        }

        Book book = BookDAO.getBookById(conn, bookId);

        DBUtils.closeConnQuietly(conn);

        req.setAttribute("bookId", book.getId());
        req.setAttribute("bookTitle", book.getTitle());
        req.setAttribute("bookDescription", book.getDescription());
        req.setAttribute("bookPrice", book.getPrice());
        req.setAttribute("bookAuthorsList", book.getAuthors());
        req.setAttribute("bookGenresList", book.getGenres());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/book.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
