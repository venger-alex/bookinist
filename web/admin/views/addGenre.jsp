<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Add genre</title>
  </head>
  <body>
    <a href="/admin/genres/">Back to list of genres</a>

    <h2>${myMsg}</h2>

  <form method="post">
    <table border="0">
      <tr>
        <td>Name:</td>
        <td><input type="text" name="genre_name" /></td>
      </tr>
      <tr>
        <td colspan ="2" align="right"><input type="submit" value="Add" /></td>
      </tr>
    </table>
  </form>

  <a href="/admin/genres/">Back to list of genres</a>

  </body>
</html>
