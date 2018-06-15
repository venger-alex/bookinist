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

public class AuthorAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("myMsg", "Add author");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addAuthor.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Add author");

        final String authorName = req.getParameter("author_name");
        if(authorName != null && authorName.length() > 0) {
            if(BookDAO.insertAuthor(conn, authorName)) {
                req.setAttribute("myMsg", "Author added, add more");
            } else {
                req.setAttribute("myMsg", "Could not add author, try again");
            }
        }

        DBUtils.closeConnQuietly(conn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addAuthor.jsp");
        requestDispatcher.forward(req, resp);

    }

}
