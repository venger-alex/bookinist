package servlets.admin;

import dao.BookDAO;
import entities.Author;
import entities.Book;
import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class AuthorEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Edit author");
        final Integer authorId = Integer.parseInt(req.getParameter("id"));

        Author author = BookDAO.getAuthorById(conn, authorId);

        req.setAttribute("id", author.getId());
        req.setAttribute("name", author.getName());

        DBUtils.closeConnQuietly(conn);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/editAuthor.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Edit author");

        final Integer authorId = Integer.parseInt(req.getParameter("id"));
        final String authorName = req.getParameter("author_name");

        if(authorName != null && authorName.length() > 0) {
            if(BookDAO.updateAuthor(conn, authorId, authorName)) {
                req.setAttribute("myMsg", "Author edited successfully");
            } else {
                req.setAttribute("myMsg", "Could not edit author, try again");
            }
        }

        req.setAttribute("id", authorId);
        req.setAttribute("name", authorName);

        DBUtils.closeConnQuietly(conn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/editAuthor.jsp");
        requestDispatcher.forward(req, resp);

    }

}
