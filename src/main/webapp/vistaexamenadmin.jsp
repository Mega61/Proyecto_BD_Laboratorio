<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">
    <meta name="viewport">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Resultados</title>
    <link href="examenpac.css" rel="stylesheet">
    <link>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

</head>


<body>
    <div> <img id="logoh" src="svg/Logo completo.svg ">
        <img id="bannerP" src="svg/Banner indicativo.svg">
    </div>
    <div class="subhP">
        <form>
            <button class="botlogout" name="botonlogout">LogOut</button>
            <label class="nomperfil">${admin}</label>
        </form>
    </div>
    <div class="contenedor1">
        <div class="hder">
            <img id="logo2" src="svg/resgenesis.svg">
            <label class="exa">No Exámen:</label>
            <label class="numexa">${numeroexamen}</label>
        </div>
        <div class="pdf">

            <iframe ${rutaexamen} width="995px" height="638px" style="border: none;">
              
            </iframe>


        </div>

        <div class="footer">

        </div>
</body>

</html>