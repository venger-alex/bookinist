<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>List of books</title>
  </head>
  <body>
    <h3>List of books</h3>
    <table border="1" width="80%">
    <c:forEach items="${bookList}" var="book">
      <tr>
        <td>${book.getId()}</td>
        <td>${book.getTitle()}</td>
        <td>${book.getDescription()}</td>
      </tr>
    </c:forEach>
    </table>
  </body>
</html>
