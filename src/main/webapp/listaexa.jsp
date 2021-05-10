<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Historial De Resultados</title>
        <link href="listaexa.css" rel="stylesheet">
        <link>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

    </head>


    <body>
        <div> <img id="logoh" src="svg/Logo completo.svg ">
            <img id="bannerP" src="svg/Banner indicativo.svg">
        </div>
        <div class="subhP">
            <img id="oficina" src="svg/oficina virtual.svg">

            <form action="perfilPaciente" method="GET">

                <button class="botvolver" name="botonvolver"><img src="svg/button.svg"></button>
                <button class="botlogout" name="botonlogout"><img src="svg/Upload.svg"></button>

            </form>
        </div>
        <label class="nomadminM"><img src="svg/Group 49.svg"> ${admin} </label>
        <div class="contenedor1">
            <form action = "listaexamenesadmin" method = "GET">
            <div class="hder">
                <img id="logo2" src="svg/Letras logoA.svg">
                <label class="histo">Historial</label>
                <input type="search" class="busquedaexa" name = "idbuscado" placeholder="Ingrese el ID del examen..." required>
                <button class="botbuscar" name = "buscarexamen">Buscar</button>
                <label class="nexa">N° Examen</label>
                <label class="fere">Fecha de Resultados</label>
            </div>
            </form>
            <form action="listaexamenesadmin" method="GET">
                <!-- <div class="exa">
                <hr id="divisor3" color="white" size="1" class="linea">
            
                <label class="codexa">E12560</label>
                <label class="ferem">10/04/2021</label>
                <button class="verres">Ver Resultados</button>
            
            </div> -->
                ${listaexamenes}
            </form>
        </div>

        <div>
            <img id="footerp" src="svg/Footer vistas.svg">
        </div>
    </body>

    </html>