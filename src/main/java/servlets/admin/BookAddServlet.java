package servlets.admin;

import dao.BookDAO;
import dto.BookDTO;
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
import java.util.Arrays;
import java.util.List;

public class BookAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Add book");

        List<Author> allAuthorsList = BookDAO.getAllAuthors(conn);
        req.setAttribute("allAuthorsList", allAuthorsList);

        List<Genre> allGenresList = BookDAO.getAllGenres(conn);
        req.setAttribute("allGenresList", allGenresList);


        DBUtils.closeConnQuietly(conn);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addBook.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Add book");

        final String title = req.getParameter("title");
        final String description = req.getParameter("description");
        final Double price = Double.parseDouble(req.getParameter("price"));

        BookDTO bookDTO = new BookDTO(title, description, price);

        String[] authorsSelectedId = req.getParameterValues("authors_select");
        if(authorsSelectedId != null && authorsSelectedId.length > 0) {
            bookDTO.authorsId = Arrays.asList(authorsSelectedId);
        }

        String[] genresSelectedId = req.getParameterValues("genres_select");
        if(genresSelectedId != null && genresSelectedId.length > 0) {
            bookDTO.genresId = Arrays.asList(genresSelectedId);
        }

        if(title != null && title.length() > 0) {
            if(BookDAO.insertBook(conn, bookDTO)) {
                req.setAttribute("myMsg", "Book added, add more");
            } else {
                req.setAttribute("myMsg", "Could not add book");
            }
        }

        List<Author> allAuthorsList = BookDAO.getAllAuthors(conn);
        req.setAttribute("allAuthorsList", allAuthorsList);

        List<Genre> allGenresList = BookDAO.getAllGenres(conn);
        req.setAttribute("allGenresList", allGenresList);


        DBUtils.closeConnQuietly(conn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addBook.jsp");
        requestDispatcher.forward(req, resp);

    }

}
