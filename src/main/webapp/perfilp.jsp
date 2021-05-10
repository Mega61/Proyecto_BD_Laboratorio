<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Perfil Paciente</title>
        <link href="perfilp.css" rel="stylesheet">
        <link>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
        <script>
            function mostrarbots() {
                document.getElementById("divinferior").hidden = false;

            }

            function ocultarbots() {
                document.getElementById("divinferior").hidden = true;

            }
        </script>
    </head>


    <body onclick="ocultarbots()">
        <div> <img id="logoh" src="svg/Logo completo.svg ">
            <img id="bannerP" src="svg/Banner indicativoP.svg">
        </div>
        <div class="subhP">

            <img id="oficina" src="svg/oficina virtual.svg">
            <button class="nomperfil" onmouseover="mostrarbots()">${usuarioLogeado} <img id="flecha" src="svg/CaretDown.svg"></button>
            <form action="perfilPaciente" method="GET">

                <div id=divinferior hidden=true>
                    <button class="botperfil" name="botonvolver">Volver <img src="svg/User.svg"></button>
                    <button class="botlogout" name="botonlogout">LogOut <img src="svg/Upload.svg"></button>
                </div>

            </form>
        </div>
        <form action="perfilPaciente" method="GET">
        ${infopac}
        </form>
        <div>
            <img id="footerp" src="svg/Footer vistas.svg">
        </div>

    </body>

    </html>