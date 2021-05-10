<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Menú de Médicos</title>
        <link href="medico.css" rel="stylesheet">
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


    <body>
        <div> <img id="logoh" src="svg/Logo completo.svg ">
            <img id="bannerM" src="svg/Banner indicativoM.svg">
        </div>
        <div class="subhM">
            <img id="oficina" src="svg/oficina virtual.svg">
            <button class="nommedico" onmouseover="mostrarbots()">${nameM} <img id="flecha" src="svg/CaretDown.svg"></button>

            <form action="medico" method="GET">
                <div id=divinferior hidden=true>
                    <button class="botperfil" name="botonperfilm">Perfil <img src="svg/User.svg"></button>
                    <button class="botlogout" name="botonlogout">LogOut <img src="svg/Upload.svg"></button>
                </div>

            </form>
        </div>

        <div class="contenedor">

            <div class="hder">
                <img class="h3" src="svg/Letras logo.svg">
                <label class="sol">Solicitudes</label>
            </div>
            <label class="subt">Nombre del Paciente</label>

            <form action="medico" method="GET">
                <%-- <div class="pacienteM">
                <hr color="white" size="1">
                <label class="nombreP">Alvaro Tovar</label>
                <label class="salida">Estado del paciente</label>
                <label class="estado">------</label>
                <button class="generar" name="botongenerar">Generar Orden de laboratorio</button>
                <button class="resultados" name="botonresultadosmed">Resultados de Examen</button>
                </div> --%>
                    <br>
                    <label>${listaPacMed}</label>
            </form>



        </div>
    </body>

    </html>