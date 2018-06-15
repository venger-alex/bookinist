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

public class GenreDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Delete genre");

        final Integer genreId = Integer.parseInt(req.getParameter("id"));

        if(BookDAO.delGenre(conn, genreId)) {
            req.setAttribute("myMsg", "Genre with ID = " + genreId + " deleted successfully");
        } else {
            req.setAttribute("myMsg", "Could not delete genre with ID = " + genreId + ", try again later");
        }

        req.setAttribute("id", genreId);

        DBUtils.closeConnQuietly(conn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/delGenre.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
