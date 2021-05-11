<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Laboratorio Genesis</title>
        <link href="ingresores.css" rel="stylesheet">
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
        <div>
            <div>
                <img id="logoh" src="svg/Logo completo.svg ">
                <img id="bannerM" src="svg/Banner indicativoM.svg">
            </div>
            <div class="subhM">
                <img id="oficina" src="svg/oficina virtual.svg">
                <button class="nommedico" onmouseover="mostrarbots()">${nomMed} <img id="flecha" src="svg/CaretDown.svg"></button>
                <form action="generarOrden" method="GET">
                    <div id=divinferior hidden=true>
                        <button class="botperfil" name="botonvolver">Volver <img src="svg/User.svg"></button>
                        <button class="botlogout" name="botonlogout">LogOut <img src="svg/Upload.svg"></button>
                    </div>
                </form>
            </div>

            <div class="contenedor">
                <div class="headerGe">
                    <img id="logopac" src="svg/Letras logo.svg">
                    <label class="nombrepac">${nomPaciente}</label></div>
                <label class="h3">Ingresa Resultados de Examenes</label>
                <br>
                <hr color="white" size="1">
                <form action="ingresarResultados" method="GET">
                    ${listaresultados}
                    <%-- <div class="elingreso">
                    <hr color="white" size="1">
                    <label class="h4">Prueba 1</label>

                        <label class="criterio1">Criterio 1</label>
                        <input type="text" class="cri1">
                        <label class="criterio2">Criterio 2</label>
                        <input type="text" class="cri2">
                        <label class="criterio3">Criterio 3</label>
                        <input type="text" class="cri3">
                        <label class="criterio4">Criterio 4</label>
                        <input type="text" class="cri4">
                
                </div> --%>
                        <button class="genorden" name="generaror">Generar Orden</button>
                </form>
            </div>
        </div>
    </body>

    </html>