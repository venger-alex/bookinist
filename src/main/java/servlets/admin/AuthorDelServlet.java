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

public class AuthorDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Delete author");

        final Integer authorId = Integer.parseInt(req.getParameter("id"));

        if(BookDAO.delAuthor(conn, authorId)) {
            req.setAttribute("myMsg", "Author with ID = " + authorId + " deleted successfully");
        } else {
            req.setAttribute("myMsg", "Could not delete author with ID = " + authorId + ", try again later");
        }

        req.setAttribute("id", authorId);

        DBUtils.closeConnQuietly(conn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/delAuthor.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
