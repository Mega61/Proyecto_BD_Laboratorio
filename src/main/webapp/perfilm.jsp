<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Menú de Médico</title>
        <link href="perfilm.css" rel="stylesheet">
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
            <img id="bannerM" src="svg/Banner indicativoM.svg">
        </div>
        <div class="subhM">
            <img id="oficina" src="svg/oficina virtual.svg">
            <button class="nommedico" onmouseover="mostrarbots()">${nameM} <img id="flecha" src="svg/CaretDown.svg"></button>

            <form action="perfilMedico" method="GET">
                <div id=divinferior hidden=true>
                    <button class="botperfil" name="botonvolver">Volver<img src="svg/User.svg"></button>
                    <button class="botlogout" name="botonlogout">LogOut <img src="svg/Upload.svg"></button>
                </div>
            </form>
        </div>
        <div class="contenedor1">
            <div class="hizq">
                <img id="logo1" src="svg/Letras logoM.svg">
                <label class="info">Información</label>
            </div>
            <div>
                <form action="perfilMedico" method="GET">
                    ${infomed}
                </form>


            </div>
        </div>
        <div class="contenedor2">
            <div class="hder">
                <img id="logo2" src="svg/Letras logoM.svg">
                <label class="histo">Historial</label>
            </div>
            <div class="pelicula">
                <input type="date" class="busquedaexa" name="fechabuscada" placeholder="Ingrese la fecha del examen..." required>
                <button class="botbuscar" name="buscarexamenfecha">Buscar</button>
                <label class="nexa">N° Examen</label>
                <label class="fere">Fecha de Remisión</label>
                <hr id="divisor3" color="white" size="1" class="linea">
                <form action="historialm" method="GET">
                    <%-- <div class = "divExamenesMedico">
                <label class="codexa">E12560</label>
                <label class="ferem">10/04/2021</label>
                <button class="verres" >Ver Resultados</button>
                </div> --%>
                        ${examenesmed}
            </div>
            </form>

        </div>
        </div>


        <div>
            <img id="footerM" src="svg/Footer vistas.svg">
        </div>
        </div>
    </body>

    </html>