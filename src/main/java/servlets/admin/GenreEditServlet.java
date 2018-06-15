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

public class GenreEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Edit genre");
        final Integer genreId = Integer.parseInt(req.getParameter("id"));

        Genre genre = BookDAO.getGenreById(conn, genreId);

        req.setAttribute("id", genre.getId());
        req.setAttribute("name", genre.getName());

        DBUtils.closeConnQuietly(conn);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/editGenre.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Edit genre");

        final Integer genreId = Integer.parseInt(req.getParameter("id"));
        final String genreName = req.getParameter("genre_name");

        if(genreName != null && genreName.length() > 0) {
            if(BookDAO.updateGenre(conn, genreId, genreName)) {
                req.setAttribute("myMsg", "Genre edited successfully");
            } else {
                req.setAttribute("myMsg", "Could not edit genre, try again");
            }
        }

        req.setAttribute("id", genreId);
        req.setAttribute("name", genreName);

        DBUtils.closeConnQuietly(conn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/editGenre.jsp");
        requestDispatcher.forward(req, resp);

    }

}
