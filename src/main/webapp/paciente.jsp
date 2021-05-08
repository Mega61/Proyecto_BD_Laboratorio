<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">
    <meta name="viewport">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Laboratorio Genesis</title>
    <link href="paciente.css" rel="stylesheet">
    <link>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

</head>


<body>
    <div> <img id="logoh" src="svg/Logo completo.svg ">
        <img id="bannerP" src="svg/Banner indicativoP.svg">
    </div>
    <div class="subhP">
        <form action="paciente" method="GET">
            <button class="botperfil" name="botonperfilpac">Perfil</button>
            <button class="botlogout" name="botonlogoutpac">LogOut</button>
		<label class="nomperfil">${usuarioLogeado}</label>
        </form>
	     <label>${barraestado}</label>
    </div>
    <div>
        <img id="carPac" src="svg/CarruselPac.svg">
    </div>
    <div>
        <form action="paciente" method="GET">

            <button class="botagendar" name="botonagendar">Agendar Cita</button>
            <button class="botsolicitar" name="botonsolicitar">Solicitar Examen (es)</button>
            <button class="botresultados" name="botonresultados">Resultados</button>
        </form>
        <div>
            <img id="footerp" src="svg/Footer vistas.svg">
        </div>
    </div>
</body>

</html>
