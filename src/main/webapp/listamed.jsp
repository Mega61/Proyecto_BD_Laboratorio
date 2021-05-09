<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
    <!DOCTYPE html>

    <html>

    <head>

        <meta charset="UTF-8">
        <meta name="viewport">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Lista de Médicos</title>
        <link href="listamed.css" rel="stylesheet">
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
            <form action="admin" method="GET">
                <button class="botvolver" name="botonvolver"><img src="svg/button.svg"></button>
                <button class="botlogout" name="logoutAdmin"><img src="svg/Upload.svg"></button>

            </form>
        </div>
        <label class="nomadminM"><img src="svg/Group 49.svg"> ${admin} </label>
        <div class="contenedor">
            <label class="h3">Lista de Médicos</label>
            <form action="listamedicosadmin" method="GET">
            <button class="agregarmed" name="botonagregarmed">Agregar Medicos</button>
            </form>
            <br>
            <hr color="white" size="1">
            <form>
                <input type="search" id="busquedamed" name="busmed" placeholder="Escriba el ID del Médico...">
            </form>
            <label class="nombre">Nombre-Medico</label>
            <label class="doc">Documento</label>
            <label class="con">Consultorio</label>

            <form>
                <%-- <hr color="white" size="1" class="linea">
                <label class="nombreMed">Ana Maria Perez Briceño</label>
                <label class="docmed">1010101010</label>
                <label class="consul">###</label>
                <button class="editarmed" name="botoneditarmed">Editar</button>
                <button class="eliminarmed" name-="botoneliminarmed">Eliminar</button> --%>
                    ${listamed}
            </form>



        </div>
    </body>

    </html>