<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Consultas</title>
        <link href="consultas.css" rel="stylesheet">
        <link>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

    </head>


    <body>
        <div> <img id="logoh" src="svg/Logo completo.svg ">
            <img id="bannerC" src="svg/Banner indicativo.svg">
        </div>
        <div class="subhC">
            <img id="oficina" src="svg/oficina virtual.svg">
            <form action="admin" method="GET">
                <button class="botvolver" name="botonvolver"><img src="svg/button.svg"></button>
                <button class="botlogout" name="logoutAdmin"><img src="svg/Upload.svg"></button>

            </form>
        </div>
        <div class="consultas">
            <img class="barra" src="svg/consultas.svg">
            <form>
                <button class="botbarra" id="cant">Cantidad</button>
                <button class="botbarra" id="med">Médico</button>
                <button class="botbarra" id="pac">Paciente</button>
                <button class="botbarra" id="exa">Exámenes</button>
                <button class="botbarra" id="cost">Costos</button>
                <button class="botbarra" id="ano">Año</button>
                <button class="botbarra" id="tipo">Tipos</button>
            </form>
        </div>
        <label class="nomadminM"><img src="svg/Group 49.svg"> admin </label>
        <div class="contenedor">
            <div class="hder">
                <img id="subt" src="svg/Group 71.svg">
                <label id="cons">La Consulta</label>
            </div>
            <div class="lasconsultas">
                <form>
                    <%-- <div class="consulta1">
                    <label class="cons1">Cantidad: 6666666666666666666</label>
                </div>
                <div class="consulta2">
                    <label class="cons2">Cantidad: 6666666666666666666</label>
                </div>
                <div class="consulta3">
                    <label class="cons">Cantidad: 6666666666666666666</label>
                </div>
                <div class="consulta1der">
                    <label class="cons1">Cantidad: 6666666666666666666</label>
                </div>
                <div class="consulta2der">
                    <label class="cons2">Cantidad: 6666666666666666666</label>
                </div>
                <div class="consulta3der">
                    <label class="cons">Cantidad: 6666666666666666666</label>
                </div> --%>
                        ${statsiniciales}
                </form>

            </div>


        </div>
    </body>

    </html>