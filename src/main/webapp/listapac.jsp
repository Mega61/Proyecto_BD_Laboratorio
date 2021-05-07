<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">
    <meta name="viewport">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Laboratorio Genesis</title>
    <link href="listapac.css" rel="stylesheet">
    <link>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

</head>


<body>
    <div> <img id="logoh" src="Logo completo.svg ">
        <img id="bannerP" src="Banner indicativo.svg">
    </div>
    <div class="subhP">
        <form action="admin" method = "GET">
            <button class="botvolver" name="botonvolver">Volver</button>
            <button class="botlogout" name="logoutAdmin">Logout</button>
            <label> ${admin} </label>
        </form>
    </div>

    <div class="contenedor">
        <label class="h3">Lista de Pacientes</label>

        <br>
        <hr color="white" size="1">
        <div class="pacienteM">
            <form>
                <input type="search" id="busquedapac" name="buspac" placeholder="Escriba el Documento del Paciente..."> </form>
            <hr color="white" size="1" class="linea">
            <form>
                <br>
                <label>${listapac}</label>
            </form>

        </div>

    </div>
</body>

</html>