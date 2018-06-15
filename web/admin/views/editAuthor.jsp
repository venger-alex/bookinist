<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Edit author</title>
  </head>
  <body>
    <a href="/admin/authors/">Back to list of authors</a>

    <h2>${myMsg}</h2>

  <form method="post">
    <table border="0">
      <tr>
        <td>ID:</td>
        <td>${id}</td>
      </tr>
      <tr>
        <td>Name:</td>
        <td><input type="text" name="author_name" value="${name}"/></td>
      </tr>
      <tr>
        <td colspan ="2" align="right"><input type="submit" value="Edit" /></td>
      </tr>
    </table>
  </form>

    <a href="/admin/authors/">Back to list of authors</a>

  </body>
</html>
