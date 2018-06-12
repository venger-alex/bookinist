package servlets;

import entities.Book;
import dao.BookDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> bookList = BookDAO.getAllBooks();
        req.setAttribute("bookList", bookList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/booklist.jsp");
        requestDispatcher.forward(req, resp);

        
    }
}
