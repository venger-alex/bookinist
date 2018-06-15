<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>List of authors</title>
  </head>
  <body>
    <h2 align="center">List of authors</h2>

    <a href="/admin/addAuthor"><h3>Add author...</h3></a>
    <table border="1" cellpadding="5" cellspacing="1" width="100%">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
    <c:forEach items="${authorList}" var="author">
      <tr>
        <td>${author.getId()}</td>
        <td>${author.getName()}</td>
        <td><a href="/admin/editAuthor?id=${author.getId()}" >Edit...</a> </td>
        <td><a href="/admin/delAuthor?id=${author.getId()}" >Delete...</a> </td>
      </tr>
    </c:forEach>
    </table>


  </body>
</html>
