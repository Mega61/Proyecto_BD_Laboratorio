<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">
    <meta name="viewport">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Laboratorio Genesis</title>
    <link href="generarorden.css" rel="stylesheet">
    <link>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
    <script>
        function mostrarbots() {
            document.getElementById("tipos1").hidden = false;
            document.getElementById("tipos2").hidden = false;
            document.getElementById("tipos3").hidden = false;
            document.getElementById("tipos4").hidden = false;
        }
    </script>
</head>


<body>
    <div>
        <div>
            <img id="logoh" src="Logo completo.svg ">
            <img id="bannerM" src="Banner indicativoM.svg">
        </div>
        <div class="subhM">
            <form>
                <button class="botlogout" name="botonlogout">LogOut</button>
            </form>
        </div>

       
        <div class="contenedor">
            <div class="headerGe">
                <img id="logopac" src="Letras logo.svg">
                <label class="nombrepac">Alvaro</label></div>
            <label class="h3">Generar Orden de laboratorio</label>
            <br>
            <hr color="white" size="1">
            <div class="pacienteM">
                <label class="h4">Diagnóstico del paciente</label>
                <hr color="white" width="672" size=" 1 ">
                <form>
                    <textarea id="diagnostico" name=" textarea " rows="10 " cols="50 " placeholder="Diagnóstico... "></textarea>
                </form>

            </div>
            <label class="h5">Escoger los examenes que se debe realizar el paciente</label>
            <button class="agregarexamenes" id="botagregar" onclick="mostrarbots()">+</button>
            <hr id="linea3" color="white" width="672" size="1">
            <select name="tiposexamenes" id="tipos" required>
            <option value="" disabled selected>Tipo de examen correspondiente</option>
            <option value="ex1">ex1</option>
            <option value="ex2">ex2</option>
            <option value="ex3">ex3</option>
            <option value="ex4">ex4</option>
            <option value="ex5">ex5</option>
            <option value="ex6">ex6</option>
            <option value="ex7">ex7</option>
            <option value="ex8">ex8</option>
            
          </select>
            <select name="tiposexamenes" id="tipos1" hidden="true">
            <option value="" disabled selected>Tipo de examen correspondiente</option>
            <option value="ex1">ex1</option>
            <option value="ex2">ex2</option>
            <option value="ex3">ex3</option>
            <option value="ex4">ex4</option>
            <option value="ex5">ex5</option>
            <option value="ex6">ex6</option>
            <option value="ex7">ex7</option>
            <option value="ex8">ex8</option>
            
          </select>
            <select name="tiposexamenes" id="tipos2" hidden="true">
            <option value="" disabled selected>Tipo de examen correspondiente</option>
            <option value="ex1">ex1</option>
            <option value="ex2">ex2</option>
            <option value="ex3">ex3</option>
            <option value="ex4">ex4</option>
            <option value="ex5">ex5</option>
            <option value="ex6">ex6</option>
            <option value="ex7">ex7</option>
            <option value="ex8">ex8</option>
            
          </select>
            <select name="tiposexamenes" id="tipos3" hidden="true">
            <option value="" disabled selected>Tipo de examen correspondiente</option>
            <option value="ex1">ex1</option>
            <option value="ex2">ex2</option>
            <option value="ex3">ex3</option>
            <option value="ex4">ex4</option>
            <option value="ex5">ex5</option>
            <option value="ex6">ex6</option>
            <option value="ex7">ex7</option>
            <option value="ex8">ex8</option>
            
          </select>
            <select name="tiposexamenes" id="tipos4" hidden="true">
            <option value="" disabled selected>Tipo de examen correspondiente</option>
            <option value="ex1">ex1</option>
            <option value="ex2">ex2</option>
            <option value="ex3">ex3</option>
            <option value="ex4">ex4</option>
            <option value="ex5">ex5</option>
            <option value="ex6">ex6</option>
            <option value="ex7">ex7</option>
            <option value="ex8">ex8</option>
            
          </select>
            <button class="genorden">Generar Orden</button>
            <div class="footer">

            </div>
        </div>
    </div>
</body>

</html>