<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">
    <meta name="viewport">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Laboratorio Genesis</title>
    <link href="medico.css" rel="stylesheet">
    <link>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

</head>


<body>
    <div> <img id="logoh" src="svg/Logo completo.svg ">
        <img id="bannerM" src="svg/Banner indicativoM.svg">
    </div>
    <div class="subhM">
        <form>
            <button class="botlogout" name="botonlogout">LogOut</button>
            <label class="nommedico" >${nameM}</label>
        </form>
    </div>

    <div class="contenedor">
        <label class="h3">Pacientes solicitantes</label>
        <br>
        <hr color="white" size="1">
        
            <form action="medico" method="GET">
                <div class="pacienteM">
                <hr color="white" size="1">
                <label class="nombreP">Alvaro Tovar</label>
                <label class="salida">Estado del paciente</label>
                <label class="estado">------</label>
                <button class="generar" name="botongenerar">Generar Orden de laboratorio</button>
                <button class="resultados" name="botonresultadosmed">Resultados de Examen</button>
                </div>
                <br>
                <label>${listaPacMed}</label>
            </form>
        


    </div>
</body>

</html>