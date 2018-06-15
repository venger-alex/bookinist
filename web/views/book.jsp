<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>${bookTitle}</title>
  </head>
  <body>
    <h2>Book: ${bookTitle}</h2>

    <table border="1" cellpadding="5" cellspacing="1">
      <tr>
        <td><b>ID:</b></td>
        <td>${bookId}</td>
      </tr>
      <tr>
        <td><b>Title:</b></td>
        <td>${bookTitle}</td>
      </tr>
      <tr>
        <td><b>Description:</b></td>
        <td>${bookDescription}</td>
      </tr>
      <tr>
        <td><b>Price:</b></td>
        <td>${bookPrice}</td>
      </tr>
      <tr>
        <td><b>Authors:</b></td>
        <td>
          <c:forEach items="${bookAuthorsList}" var="author">
            ${author.getName()}<br/>
          </c:forEach>
        </td>
      </tr>
      <tr>
        <td><b>Genres:</b></td>
        <td>
          <c:forEach items="${bookGenresList}" var="genre">
            ${genre.getName()}<br/>
          </c:forEach>
        </td>
      </tr>
    </table>

  <form method="post">
    <h3>${orderMsg}</h3>
    <table border="0">
      <tr>
        <td>First name:</td>
        <td><input type="text" name="first_name" /></td>
      </tr>
      <tr>
        <td>Last name:</td>
        <td><input type="text" name="last_name" /></td>
      </tr>
      <tr>
        <td>Address:</td>
        <td><input type="text" name="address" /></td>
      </tr>
      <tr>
        <td>Quantity:</td>
        <td><input type="text" name="quantity" /></td>
      </tr>
      <tr>
        <td colspan ="2" align="right"><input type="submit" value="Order" /></td>
      </tr>
    </table>
  </form>

  </body>
</html>
