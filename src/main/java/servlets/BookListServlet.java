package servlets;

import entities.Book;
import dao.BookDAO;
import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class BookListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = DBUtils.getConnection();
        List<Book> bookList = BookDAO.getAllBooks(conn);
        DBUtils.closeConnQuietly(conn);
        req.setAttribute("bookList", bookList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/booklist.jsp");
        requestDispatcher.forward(req, resp);

    }
}
