<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Laboratorio Genesis</title>
        <link href="ordenpac.css" rel="stylesheet">
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
                <img id="bannerM" src="svg/Banner indicativoP.svg">
            </div>
            <div class="subhM">
                <img id="oficina" src="svg/oficina virtual.svg">
                <button class="nomperfil" onmouseover="mostrarbots()">${pacienteNombre} <img id="flecha" src="svg/CaretDown.svg"></button>
                <form>
                    <div id=divinferior hidden=true>
                        <button class="botperfil" name="botonvolver">Volver <img src="svg/User.svg"></button>
                        <button class="botlogout" name="botonlogout">LogOut <img src="svg/Upload.svg"></button>
                    </div>
                </form>
            </div>

            <div class="contenedor">
                <div class="headerGe">
                    <img id="logopac" src="svg/Letras logo.svg">
                    <label class="nombrePac">${nombrePac}</label></div>
                <label class="h3">Ingresar Orden de Laboratorio</label>
                <br>
                <hr color="white" size="1">
                <div class="elingreso">


                    <form action="paciente" method="GET">
                        <label class="numorden">Número de Orden:</label>
                        <input type="text" class="ingnum" name="ingnumorden" required>
                        <button class="genres" name="ingresarorden">Generar Resultados</button>
                    </form>

                </div>
                <div class="footer">
                    <img class="imgfooter" src="svg/FooterOrden.svg">
                </div>

            </div>
            <div class="estadogen">
                <div class="estgen">
                    <img src="svg/Estado Genesis.svg"> ${barraestado}
                </div>
            </div>
        </div>
    </body>

    </html>