<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">
    <meta name="viewport">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Laboratorio Genesis</title>
    <link href="perfilp.css" rel="stylesheet">
    <link>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

</head>


<body>
    <div> <img id="logoh" src="svg/Logo completo.svg ">
        <img id="bannerP" src="svg/Banner indicativoP.svg">
    </div>
    <div class="subhP">
        <form action="perfilPaciente" method = "GET">
            <button class="botlogout" name="botonlogout">LogOut</button>
            <button class="botlogout" name="botonvolver">Volver</button>
            <label class="nomperfil">${usuarioLogeado}</label>
        </form>
    </div>
    <div class="contenedor1">
        <div class="hizq">
            <img id="logo1" src="svg/Letras logo.svg">
            <label class="info">Información</label>
        </div>
        <div>
            ${infopac}
        </div>
    </div>
   <div class="contenedor2">
        <div class="hder">
            <img id="logoce" src="Group 66.svg">

            <div>

                <form>
                    <img class="puntonomce" src="Ellipse 2.svg">
                    <label id="nomce">Nombre:</label>
                    <label id="vnomce">Juan</label>
                    <img class="puntoedadce" src="Ellipse 2.svg">
                    <label id="edadce">Edad:</label>
                    <label id="vedadce">85</label>
                    <img class="puntoparce" src="Ellipse 2.svg">
                    <label id="parce">Parentesco:</label>
                    <label id="vparce">Padre</label>
                    <img class="puntotoolcorreoce" src="Ellipse 2.svg">
                    <img class="toolcorreoce" src="tool.svg">
                    <label id="correoce">Correo</label>
                    <input type="text" id="editcorreoce">
                    <img class="puntotooltelce" src="Ellipse 2.svg">
                    <img class="tooltelce" src="tool.svg">
                    <label id="telce">Confirmar Contraseña</label>
                    <input type="text" id="edittelce">
                    <button class="confirmarce">Confirmar Cambios</button>
                    <br>
                    <br>
                </form>

            </div>
        </div>
    </div>


    <div>
        <img id="footerp" src="svg/Footer vistas.svg">
    </div>
    </div>
</body>

</html>
