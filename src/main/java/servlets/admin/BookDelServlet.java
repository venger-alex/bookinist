package servlets.admin;

import dao.BookDAO;
import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class BookDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Delete book");

        final Integer bookId = Integer.parseInt(req.getParameter("id"));

        if(BookDAO.delBook(conn, bookId)) {
            req.setAttribute("myMsg", "Book with ID = " + bookId + " deleted successfully");
        } else {
            req.setAttribute("myMsg", "Could not delete book with ID = " + bookId);
        }

        req.setAttribute("id", bookId);

        DBUtils.closeConnQuietly(conn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/delBook.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
