<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Editar Médico</title>
        <link href="editarm.css" rel="stylesheet">
        <link>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

    </head>

    <body>
        <div> <img id="logoh" src="svg/Logo completo.svg ">
            <img id="bannerM" src="svg/Banner indicativo.svg">
        </div>
        <div class="subhM">
            <img id="oficina" src="svg/oficina virtual.svg">

            <form action="listamedicosadmin" method="GET">
                <button class="botvolver" name="botonvolver"><img src="svg/button.svg"></button>
                <button class="botlogout" name="botonlogout"><img src="svg/Upload.svg"></button>
            </form>
        </div>
        <label class="nomadminM"><img src="svg/Group 49.svg"> ${admin} </label>
        <div class="contenedor1">
            <div class="hizq">
                <img id="logo1" src="svg/Letras logoM.svg">
                <label class="info">Información</label>
            </div>
            <div>
                <form action="perfilMedicoEd" method="GET">
                    ${infomed}
                </form>


            </div>
        </div>

        <div>
            <img id="footerM" src="svg/Footer vistas.svg">
        </div>

    </body>

    </html>