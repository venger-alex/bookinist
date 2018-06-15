package servlets.admin;

import dao.BookDAO;
import dto.BookDTO;
import entities.Author;
import entities.Book;
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

public class BookEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Edit book");
        final Integer bookId = Integer.parseInt(req.getParameter("id"));

        Book book = BookDAO.getBookById(conn, bookId);

        req.setAttribute("id", book.getId());
        req.setAttribute("book", book);

        List<Author> allAuthorsList = BookDAO.getAllAuthors(conn);
        req.setAttribute("allAuthorsList", allAuthorsList);

        List<Genre> allGenresList = BookDAO.getAllGenres(conn);
        req.setAttribute("allGenresList", allGenresList);

        DBUtils.closeConnQuietly(conn);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/editBook.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = DBUtils.getConnection();
        req.setAttribute("myMsg", "Edit book");

        final Integer bookId = Integer.parseInt(req.getParameter("id"));
        final String title = req.getParameter("title");
        final String description = req.getParameter("description");
        final Double price = Double.parseDouble(req.getParameter("price"));

        BookDTO bookDTO = new BookDTO(bookId, title, description, price);

        String[] authorsSelectedId = req.getParameterValues("authors_select");
        if(authorsSelectedId != null && authorsSelectedId.length > 0) {
            bookDTO.authorsId = Arrays.asList(authorsSelectedId);
        }

        String[] genresSelectedId = req.getParameterValues("genres_select");
        if(genresSelectedId != null && genresSelectedId.length > 0) {
            bookDTO.genresId = Arrays.asList(genresSelectedId);
        }

        if(title != null && title.length() > 0) {
            if(BookDAO.updateBook(conn, bookDTO)) {
                req.setAttribute("myMsg", "Book edited successfully");
            } else {
                req.setAttribute("myMsg", "Could not edit book");
            }
        }

        req.setAttribute("id", bookId);
        req.setAttribute("book", BookDAO.getBookById(conn, bookId));

        List<Author> allAuthorsList = BookDAO.getAllAuthors(conn);
        req.setAttribute("allAuthorsList", allAuthorsList);

        List<Genre> allGenresList = BookDAO.getAllGenres(conn);
        req.setAttribute("allGenresList", allGenresList);

        DBUtils.closeConnQuietly(conn);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/editBook.jsp");
        requestDispatcher.forward(req, resp);

    }

}
