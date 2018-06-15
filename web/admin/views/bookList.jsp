<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>List of books</title>
    <style type="text/css">
      #btn_filter {width:100%}
    </style>
  </head>
  <body>
    <h2 align="center">List of books</h2>

    <form method="post">
      <table border="0" width="100%" cellpadding="5" cellspacing="1">
        <tr>
          <td><b>Filter by genres:</b></td>
          <td>
            <c:forEach items="${genreList}" var="genre">
              <c:if test="${genreFilterMap.get(genre) == true}">
                <label><input checked type="checkbox" name="g${genre.getId()}" />${genre.getName()}</label>
              </c:if>
              <c:if test="${genreFilterMap.get(genre) == false}">
                <label><input type="checkbox" name="g${genre.getId()}" />${genre.getName()}</label>
              </c:if>
            </c:forEach>
          </td>
        </tr>
        <tr>
          <td><b>Filter by authors:</b></td>
          <td>
            <c:forEach items="${authorList}" var="author">
              <c:if test="${authorFilterMap.get(author) == true}">
                <label><input checked type="checkbox" name="a${author.getId()}" />${author.getName()}</label>
              </c:if>
              <c:if test="${authorFilterMap.get(author) == false}">
                <label><input type="checkbox" name="a${author.getId()}" />${author.getName()}</label>
              </c:if>
            </c:forEach>
          </td>
        </tr>
        <tr align="center">
          <td colspan="2">
            <input type="submit" value="Filter" id="btn_filter" />
          </td>
        </tr>
      </table>
    </form>

    <p align="left">
      <a href="/admin/addBook" style="margin: 10px;"><h3 style="display:inline;">Add book...</h3></a>
      <a href="/admin/authors/" target="_blank" style="margin: 10px;"><h3 style="display:inline;">Add/Edit authors...</h3></a>
      <a href="/admin/genres/" target="_blank" style="margin: 10px;"><h3 style="display:inline;">Add/Edit genres...</h3></a>
    </p>
    <table border="1" cellpadding="5" cellspacing="1" width="100%">
      <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
        <th>Authors</th>
        <th>Genres</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
    <c:forEach items="${bookList}" var="book">
      <tr>
        <td>${book.getId()}</td>
        <td>${book.getTitle()}</td>
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
        <td><a href="/admin/editBook?id=${book.getId()}" >Edit...</a> </td>
        <td><a href="/admin/delBook?id=${book.getId()}" >Delete...</a> </td>

      </tr>
    </c:forEach>
    </table>


  </body>
</html>
