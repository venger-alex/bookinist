package servlets.admin;

import dao.BookDAO;
import entities.Author;
import entities.Genre;
import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class GenreListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = DBUtils.getConnection();

        List<Genre> genreList = BookDAO.getAllGenres(conn);
        req.setAttribute("genreList", genreList);

        DBUtils.closeConnQuietly(conn);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/genreList.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
