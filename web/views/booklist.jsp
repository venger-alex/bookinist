<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>List of books</title>
  </head>
  <body>
    <h3>List of books</h3>

    <table border="1" cellpadding="5" cellspacing="1">
      <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
        <th>Authors</th>
        <th>Genres</th>
      </tr>
    <c:forEach items="${bookList}" var="book">
      <tr>
        <td><a href="/book?id=${book.getId()}" target="_blank">${book.getId()}</a></td>
        <td><a href="/book?id=${book.getId()}" target="_blank">${book.getTitle()}</a></td>
        <td>${book.getDescription()}</td>
        <td>${book.getPrice()}</td>
        <td>

          <c:forEach items="${book.getAuthors()}" var="author"  varStatus="сounter" >
            <c:if test="${сounter.count > 1}">, </c:if>
            ${author.getName()}
          </c:forEach>

        </td>
        <td>

          <c:forEach items="${book.getGenres()}" var="genre"  varStatus="сounter" >
            <c:if test="${сounter.count > 1}">, </c:if>
            ${genre.getName()}
          </c:forEach>

        </td>
      </tr>
    </c:forEach>
    </table>
  </body>
</html>
