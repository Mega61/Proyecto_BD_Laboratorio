<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Menú Administración</title>
        <link href="admin.css" rel="stylesheet">
        <link>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

    </head>


    <body>
        <div> <img id="logoh" src="svg/Logo completo.svg ">
            <img id="bannerI" src="svg/Banner indicativo.svg ">
        </div>
        <div class="subh">
            <img id="oficina" src="svg/oficina virtual.svg">
            <form action="admin" method="GET">
                <button class="botlogout" name="logoutAdmin"><img src="svg/Upload.svg"></button>
            </form>
        </div>
        <label class="nomadmin"><img src="svg/Group 49.svg">${usuarioLogeado}</label>
        <div>
            <img id="caradmin" src="svg/CarruselAdmin.svg">
        </div>
        <div>
            <form action="admin" method="GET">

                <button class="botlistapac" name="botonlistapac"><img src="svg/UserG.svg"> <br><hr color="white" size="1px">Lista de <br>Pacientes</button>
                <button class="botlistamed" name="botonlistamed"><img src="svg/Vector.svg"> <br> <br><hr color="white" size="1px">Lista de <br>Médicos</button>
                <button class="botlistaexa" name="botonlistaexa"><img src="svg/FileText.svg"> <br><hr color="white" size="1px">Lista de <br>exámenes</button>
                <button class="botstats" name="botonstats"><img src="svg/stats.svg"> <br><hr color="white" size="1px">Estadísticas <br>Génesis</button>

            </form>

        </div>
        <div class="footer">

        </div>
    </body>

    </html>