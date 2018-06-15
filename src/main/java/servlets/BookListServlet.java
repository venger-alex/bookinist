package servlets;

import entities.Author;
import entities.Book;
import dao.BookDAO;
import entities.Genre;
import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = DBUtils.getConnection();

        List<Author> authorList = BookDAO.getAllAuthors(conn);
        List<Author> checkedAuthorList = new ArrayList<Author>();
        Map<Author, Boolean> authorFilterMap = new HashMap<Author, Boolean>();
        for (Author author : authorList) {
            String reqParam = req.getParameter("a" + author.getId());
            if(reqParam != null && "on".equals(reqParam)) {
                authorFilterMap.put(author, true);
                checkedAuthorList.add(author);
            }
            else {
                authorFilterMap.put(author, false);
            }
        }
        req.setAttribute("authorList", authorList);
        req.setAttribute("checkedAuthorList", checkedAuthorList);
        req.setAttribute("authorFilterMap", authorFilterMap);

        List<Genre> genreList = BookDAO.getAllGenres(conn);
        List<Genre> checkedGenreList = new ArrayList<Genre>();
        Map<Genre, Boolean> genreFilterMap = new HashMap<Genre, Boolean>();
        for (Genre genre : genreList) {
            String reqParam = req.getParameter("g" + genre.getId());
            if(reqParam != null && "on".equals(reqParam)) {
                genreFilterMap.put(genre, true);
                checkedGenreList.add(genre);
            }
            else {
                genreFilterMap.put(genre, false);
            }
        }
        req.setAttribute("genreList", genreList);
        req.setAttribute("checkedGenreList", checkedGenreList);
        req.setAttribute("genreFilterMap", genreFilterMap);


        List<Book> bookList = null;

        if(checkedAuthorList != null && checkedAuthorList.size() > 0 && (checkedGenreList == null || checkedGenreList.size() == 0)) {
            bookList = BookDAO.filterBooksByAuthors(conn, checkedAuthorList);
        }

        if((checkedAuthorList == null || checkedAuthorList.size() == 0) && checkedGenreList != null && checkedGenreList.size() > 0) {
            bookList = BookDAO.filterBooksByGenres(conn, checkedGenreList);
        }

        if(checkedAuthorList != null && checkedAuthorList.size() > 0 && checkedGenreList != null && checkedGenreList.size() > 0) {
            bookList = BookDAO.filterBooksByAuthorsANDGenres(conn, checkedAuthorList, checkedGenreList);
        }

        if((checkedAuthorList == null || checkedAuthorList.size() == 0) && (checkedGenreList == null || checkedGenreList.size() == 0)) {
            bookList = BookDAO.getAllBooks(conn);
        }

        req.setAttribute("bookList", bookList);

        DBUtils.closeConnQuietly(conn);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/bookList.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
