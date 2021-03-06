<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Menú Paciente</title>
        <link href="paciente.css" rel="stylesheet">
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
        <div>
            <img id="carPac" src="svg/CarruselPac.svg">
        </div>
        <div class="subhP">
            <img id="oficina" src="svg/oficina virtual.svg">
            <button class="nomperfil" onmouseover="mostrarbots()">${usuarioLogeado} <img id="flecha" src="svg/CaretDown.svg"></button>

            <label>${barraestado}</label>
            <form action="paciente" method="GET">
                <div id=divinferior hidden=true>
                    <button class="botperfil" name="botonperfilpac">Perfil <img src="svg/User.svg"></button>
                    <button class="botlogout" name="botonlogoutpac">LogOut <img src="svg/Upload.svg"></button>
                </div>
            </form>

        </div>

        <div>
            <form action="paciente" method="GET">

                <button class="botagendar" name="botonagendar"><img src="svg/Book.svg"> <br><hr color="white" size="1px">Agendar <br>Cita</button>
                <button class="botsolicitar" name="botonsolicitar"><img src="svg/FileText.svg"><br><hr color="white" size="1px">Solicitar<br> Examen(es)</button>
                <button class="botresultados" name="botonresultados"><img src="svg/CarryOut.svg"><br><hr color="white" size="1px">Historial de <br>Resultados</button>
            </form>
            <div>
                <img id="footerp" src="svg/Footer vistas.svg">
            </div>
        </div>
    </body>

    </html>