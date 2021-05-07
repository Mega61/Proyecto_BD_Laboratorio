<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href="main.css">

</head>
<body>
    <div class="login">
        <div class="login-triangle"></div>
        
        <h2 class="login-header">Log in</h2>

        <label>${usuarioLogeado}</label>
      
        <form class="login-container" action="hello" method="GET">
          <p><input type="email" placeholder="Email" name="user"></p>
          <p><input type="password" placeholder="Password" name="pass"></p>
          <p><input type="submit" value="Log in" name="botonlogin"></p>
        </form>
      </div>
</body>
</html>
