<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>List of genres</title>
  </head>
  <body>
    <h2 align="center">List of genres</h2>

    <a href="/admin/addGenre"><h3>Add genre...</h3></a>
    <table border="1" cellpadding="5" cellspacing="1" width="100%">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
    <c:forEach items="${genreList}" var="genre">
      <tr>
        <td>${genre.getId()}</td>
        <td>${genre.getName()}</td>
        <td><a href="/admin/editGenre?id=${genre.getId()}" >Edit...</a> </td>
        <td><a href="/admin/delGenre?id=${genre.getId()}" >Delete...</a> </td>
      </tr>
    </c:forEach>
    </table>


  </body>
</html>
