<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Add book</title>
  </head>
  <body>
    <a href="/admin/">Back to list of books</a>

    <h2>${myMsg}</h2>

  <form method="post">
    <table border="0">
      <tr>
        <td>Title:</td>
        <td><input type="text" name="title" /></td>
      </tr>
      <tr>
        <td>Description:</td>
        <td><textarea name="description"></textarea></td>
      </tr>
      <tr>
        <td>Price:</td>
        <td><input type="text" name="price" value="${0.00}"/></td>
      </tr>

      <tr>
        <td>Authors:</td>
        <td>
          <select multiple name="authors_select">
            <c:forEach items="${allAuthorsList}" var="author">
              <option name="${author.getId()}" value="${author.getId()}">${author.getName()}</option>
            </c:forEach>
          </select>
        </td>
      </tr>

      <tr>
        <td>Genres:</td>
        <td>
          <select multiple name="genres_select">
            <c:forEach items="${allGenresList}" var="genre">
                <option name="${genre.getId()}"  value="${genre.getId()}">${genre.getName()}</option>
            </c:forEach>
          </select>
        </td>
      </tr>

      <tr>
        <td colspan ="2" align="right"><input type="submit" value="Add" /></td>
      </tr>
    </table>
  </form>

  <a href="/admin/">Back to list of books</a>

  </body>
</html>
