<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Edit book</title>
  </head>
  <body>
    <a href="/admin/">Back to list of books</a>

    <h2>${myMsg}</h2>

  <form method="post">
    <table border="0">
      <tr>
        <td>ID:</td>
        <td>${id}</td>
      </tr>
      <tr>
        <td>Title:</td>
        <td><input type="text" name="title" value="${book.getTitle()}"/></td>
      </tr>
      <tr>
        <td>Description:</td>
        <td><textarea name="description">${book.getDescription()}</textarea></td>
      </tr>
      <tr>
        <td>Price:</td>
        <td><input type="text" name="price" value="${book.getPrice()}"/></td>
      </tr>

      <tr>
        <td>Authors:</td>
        <td>
          <select multiple name="authors_select">
            <c:forEach items="${allAuthorsList}" var="author">
              <c:if test="${book.getAuthors().contains(author) == true}">
                <option selected name="${author.getId()}" value="${author.getId()}">${author.getName()}</option>
              </c:if>
              <c:if test="${book.getAuthors().contains(author) == false}">
                <option name="${author.getId()}" value="${author.getId()}">${author.getName()}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>
      </tr>

      <tr>
        <td>Genres:</td>
        <td>
          <select multiple name="genres_select">
            <c:forEach items="${allGenresList}" var="genre">
              <c:if test="${book.getGenres().contains(genre) == true}">
                <option selected name="${genre.getId()}" value="${genre.getId()}">${genre.getName()}</option>
              </c:if>
              <c:if test="${book.getGenres().contains(genre) == false}">
                <option name="${genre.getId()}"  value="${genre.getId()}">${genre.getName()}</option>
              </c:if>
            </c:forEach>
          </select>
        </td>
      </tr>

      <tr>
        <td colspan ="2" align="right"><input type="submit" value="Edit" /></td>
      </tr>
    </table>
  </form>

    <a href="/admin/">Back to list of books</a>

  </body>
</html>
