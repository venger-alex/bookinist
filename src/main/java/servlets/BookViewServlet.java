package servlets;

import dao.BookDAO;
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
        Integer bookId = Integer.parseInt(req.getParameter("id"));

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
