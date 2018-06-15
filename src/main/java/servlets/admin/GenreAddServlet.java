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

public class GenreAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("myMsg", "Add genre");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addGenre.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Add genre");

        final String genreName = req.getParameter("genre_name");
        if(genreName != null && genreName.length() > 0) {
            if(BookDAO.insertGenre(conn, genreName)) {
                req.setAttribute("myMsg", "Genre added, add more");
            } else {
                req.setAttribute("myMsg", "Could not add genre, try again");
            }
        }

        DBUtils.closeConnQuietly(conn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addGenre.jsp");
        requestDispatcher.forward(req, resp);

    }

}
