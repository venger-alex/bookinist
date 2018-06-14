<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>${bookTitle}</title>
  </head>
  <body>
    <h3>Book: ${bookTitle}</h3>

    <table border="1" cellpadding="5" cellspacing="1">
      <tr>
        <td>ID:</td>
        <td>${bookId}</td>
      </tr>
      <tr>
        <td>Title:</td>
        <td>${bookTitle}</td>
      </tr>
      <tr>
        <td>Description:</td>
        <td>${bookDescription}</td>
      </tr>
      <tr>
        <td>Price:</td>
        <td>${bookPrice}</td>
      </tr>
      <tr>
        <td>Authors:</td>
        <td>
          <c:forEach items="${bookAuthorsList}" var="author">
            ${author.getName()}<br/>
          </c:forEach>
        </td>
      </tr>
      <tr>
        <td>Genres:</td>
        <td>
          <c:forEach items="${bookGenresList}" var="genre">
            ${genre.getName()}<br/>
          </c:forEach>
        </td>
      </tr>
    </table>

  </body>
</html>
