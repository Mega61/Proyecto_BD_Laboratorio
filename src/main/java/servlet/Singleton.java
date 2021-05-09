package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.text.Document;

import javassist.compiler.ast.StringL;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.mysql.cj.xdevapi.Result;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;

import org.apache.commons.io.FileUtils;
import org.apache.regexp.recompile;
import org.apache.tomcat.jni.OS;
import org.apache.xml.utils.URI;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;

/*
CREATE TABLE MEDICO (
    id_medico VARCHAR(10) NOT NULL,
    nombre_medico VARCHAR(50) NOT NULL,
    consultorio_medico INT,
    correo_medico VARCHAR(50),
    telefono_medico VARCHAR(10));

ALTER TABLE MEDICO ADD PRIMARY KEY(id_medico);

SHOW CREATE TABLE MEDICO;


CREATE TABLE CONTACTO_EMERGENCIA_PACIENTE 
(id_contacto_emergencia INT NOT NULL AUTO_INCREMENT,
parentesco VARCHAR(25) NOT NULL,
telefono_contacto_emergencia VARCHAR(10) NOT NULL,
edad_contacto_emergencia SMALLINT CHECK(edad_contacto_emergencia > 0),
correo_contacto_emergencia VARCHAR(25),
nombre_contacto_emergencia VARCHAR(25), PRIMARY KEY(id_contacto_emergencia));

CREATE TABLE TIPO_EXAMEN (
    id_tipo_examen INT NOT NULL AUTO_INCREMENT,
    criterios_tipo_examen VARCHAR(25) NOT NULL,
    costo_tipo_examen INT NOT NULL CHECK(costo_tipo_examen > 0),
    muestra_tipo_examen VARCHAR(25) NOT NULL,
    nombre_tipo_examen VARCHAR(25) NOT NULL,
    PRIMARY KEY (id_tipo_examen)
);

CREATE TABLE PACIENTE (
    id_paciente INT NOT NULL,
    id_contacto_emergencia INT NOT NULL,
    correo_paciente VARCHAR(25),
    genero_paciente VARCHAR(10),
    edad_paciente SMALLINT CHECK(edad_paciente > 0),
    nombre_paciente VARCHAR(25) NOT NULL,
    telefono_paciente VARCHAR(10) NOT NULL,
    sangre_paciente VARCHAR(5) NOT NULL,
    estado_paciente VARCHAR(25),
    CONSTRAINT paciente_id_paciente_pk PRIMARY KEY (id_paciente),
    CONSTRAINT paciente_id_contacto_emergencia_fk FOREIGN KEY (id_contacto_emergencia) REFERENCES contacto_emergencia_paciente (id_contacto_emergencia) ON DELETE CASCADE 
);

CREATE TABLE ESPECIALIDAD_MEDICO (
    nombre_especialidad VARCHAR(25) NOT NULL,
    id_medico VARCHAR(10) NOT NULL,
    CONSTRAINT especialidad_nombre_especialidad_pk PRIMARY KEY(nombre_especialidad, id_medico),
    CONSTRAINT especialidad_id_medico_fk FOREIGN KEY (id_medico) REFERENCES medico (id_medico)
);

CREATE TABLE EXAMEN(
    id_examen INT NOT NULL AUTO_INCREMENT,
    id_medico VARCHAR(10) NOT NULL,
    id_paciente INT NOT NULL,
    id_tipo_examen INT NOT NULL,
    diagnostico VARCHAR(500) NOT NULL,
    resultados VARCHAR(500),
    fecha_remision DATE NOT NULL,
    fecha_resultados DATE,
    fecha_realizacion DATE,
    CONSTRAINT examen_id_examen_pk PRIMARY KEY (id_examen),
    CONSTRAINT examen_id_medico_fk FOREIGN KEY (id_medico) REFERENCES medico (id_medico),
    CONSTRAINT examen_id_paciente_fk FOREIGN KEY (id_paciente) REFERENCES paciente (id_paciente),
    CONSTRAINT examen_id_tipo_examen_fk FOREIGN KEY (id_tipo_examen) REFERENCES tipo_examen (id_tipo_examen)
);
*/

public class Singleton {

    private static Singleton instancia;

    private static Connection connSQL = null;

    // LocalHost
    /*
     * private static String userBD = "root"; private static String passBD = "1234";
     * private static String urlBD = "jdbc:mysql://localhost:3306/prueba_local";
     */

    // Heroku

    private static String userBD = "be0469cbf4e9eb";
    private static String passBD = "cdb0227e";
    private static String urlBD = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_fdfec516ca01a53";

    // mysql://be0469cbf4e9eb:cdb0227e@us-cdbr-east-03.cleardb.com/heroku_fdfec516ca01a53?reconnect=true

    static Singleton getSingleton() {
        if (instancia == null) {
            synchronized (Singleton.class) {
                if (instancia == null) {
                    instancia = new Singleton();
                }
            }
        }

        return instancia;
    }

    public static void connectarBD() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("se ha encontrado el driver");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("No se ha encontrado el driver");
        }

        try {
            connSQL = DriverManager.getConnection(urlBD, userBD, passBD);

            if (connSQL != null) {
                System.out.println("Se ha logrado la conexión con la BD");
            } else {
                System.out.println("No se ha logrado la conexión con la BD");
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("Conexión fallida");
        }
    }

    public static void traerData() {

        try {
            String queryTraer = "SELECT * FROM usuarios";

            PreparedStatement statement = null;

            statement = connSQL.prepareStatement(queryTraer);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String correo = rs.getString("correo");
                String contra = rs.getString("contrasegna");

                System.out.println(id + ", " + correo + ", " + contra);
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void insertData(String correo, String password) {

        try {
            String queryInsertar = "INSERT INTO usuarios (correo, contrasegna) VALUES (?,?)";

            PreparedStatement statement = null;

            statement = connSQL.prepareStatement(queryInsertar);
            statement.setString(1, correo);
            statement.setString(2, password);

            statement.executeUpdate();
            System.out.println(correo + " Se ha añadido correctamente");
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void cerrarConexion() {
        try {
            connSQL.close();
            System.out.println("Se ha cerrado al conexión con la BD");
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public String crearIdMedico() {

        String ValorInicial = "M";
        int ValorInicialInt = 0;

        try {
            String buscarViM = "SELECT valor_medico FROM valores_iniciales;";
            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscarViM);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                ValorInicialInt = rs.getInt("valor_medico");
                ValorInicialInt++;
                ValorInicial += ValorInicialInt;
            }

            String updateViM = "UPDATE valores_iniciales SET valor_medico = " + ValorInicialInt + ";";
            connSQL.prepareStatement(updateViM);
            statement.executeUpdate(updateViM);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return ValorInicial;
    }

    public int crearIdContactoE() {

        int ValorInicial = 0;
        String updateViCe = "";

        try {
            String buscarViCe = "SELECT valor_contacto_emergencia FROM valores_iniciales";
            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscarViCe);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                ValorInicial = rs.getInt("valor_contacto_emergencia");
            }

            ValorInicial++;
            updateViCe = "UPDATE valores_iniciales SET valor_contacto_emergencia = " + ValorInicial + ";";
            statement = connSQL.prepareStatement(updateViCe);
            statement.executeUpdate(updateViCe);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ValorInicial;
    }

    public void ingresarPacienteYcontactoEmergencia(int id_p, String correoP, String generoP, int edadP, String nombreP,
            String telefonoP, String sangreP, String estadoP, String contrasegna, String parentesoCE, String telefonoCE,
            int edadCE, String correoCE, String nombreCE) {

        connectarBD();
        int idCe = crearIdContactoE();

        try {
            String queryInsertarContactoEmergencia = "INSERT INTO contacto_emergencia_paciente VALUES (?,?,?,?,?,?)";
            String queryInsertarPaciente = "INSERT INTO paciente VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = null;

            statement = connSQL.prepareStatement(queryInsertarContactoEmergencia);
            statement.setInt(1, idCe);
            statement.setString(2, parentesoCE);
            statement.setString(3, telefonoCE);
            statement.setInt(4, edadCE);
            statement.setString(5, correoCE);
            statement.setString(6, nombreCE);

            statement.executeUpdate();

            statement = connSQL.prepareStatement(queryInsertarPaciente);
            statement.setInt(1, id_p);
            statement.setInt(2, idCe);
            statement.setString(3, correoP);
            statement.setString(4, generoP);
            statement.setInt(5, edadP);
            statement.setString(6, nombreP);
            statement.setString(7, telefonoP);
            statement.setString(8, sangreP);
            statement.setString(9, estadoP);
            statement.setString(10, contrasegna);

            statement.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("No se ha podido ingresar paciente");
        }

        cerrarConexion();

    }

    public void ingresarMedico(String nombreM, int consultorioM, String correoM, String telefonoM, String generoM,
            int edadM, String contrasegnaM, ArrayList<String> especialidades) {

        connectarBD();
        String idMedico = crearIdMedico();

        try {
            String queryInsertarMedico = "INSERT INTO medico VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(queryInsertarMedico);
            statement.setString(1, idMedico);
            statement.setString(2, nombreM);
            statement.setInt(3, consultorioM);
            statement.setString(4, correoM);
            statement.setString(5, telefonoM);
            statement.setString(6, generoM);
            statement.setInt(7, edadM);
            statement.setString(8, contrasegnaM);
            statement.executeUpdate();
            System.out.println("se metieron los 8 strings");

            for (int i = 0; i < especialidades.size(); i++) {
                if (!especialidades.get(i).equals("")) {
                    String queryInsertarEspecialidad = "INSERT INTO especialidad_medico VALUES (?,?)";
                    statement = connSQL.prepareStatement(queryInsertarEspecialidad);
                    statement.setString(1, especialidades.get(i));
                    statement.setString(2, idMedico);
                    statement.executeUpdate();
                    System.out.println("se ha entrado al if");
                }
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        cerrarConexion();

    }

    public static String getPacientesMed() {

        String str = "";

        try {
            String queryPacienteEstado = "SELECT nombre_paciente, estado_paciente, id_paciente FROM paciente WHERE estado_paciente = 'ESPERANDO CITA' OR estado_paciente = 'ESPERANDO RESULTADOS';";
            PreparedStatement statement = null;

            connectarBD();

            statement = connSQL.prepareStatement(queryPacienteEstado);

            ResultSet rs = statement.executeQuery();

            int identificadorBotones = 0;

            while (rs.next()) {
                String nombre = rs.getString("nombre_paciente");
                String estado = rs.getString("estado_paciente");
                int id = rs.getInt("id_paciente");

                str += "<div class=\"pacienteM\">" + "<hr color=\"white\" size=\"1\">" + "<label class=\"nombreP\">"
                        + "<input type = \"hidden\" name =\"nombre" + identificadorBotones + "\" value = \"" + id
                        + "\">" + nombre + "</label>" + "<label class=\"salida\">Estado del paciente</label>"
                        + "<label class=\"estado\">" + estado + "</label>"
                        + "<button class=\"generar\" name=\"botongenerar" + identificadorBotones
                        + "\">Generar Orden de laboratorio</button>"
                        + "<button class=\"resultados\" name=\"botonresultadosmed" + identificadorBotones
                        + "\">Resultados de Examen</button>" + "</div>" + "<br>";

                identificadorBotones++;
            }

            cerrarConexion();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return str;

    }

    public static boolean loginPaciente(String username, String contrasegna) {

        Boolean b = false;
        int id = Integer.parseInt(username);
        connectarBD();

        String buscarPaciente = "SELECT id_paciente, contrasegna_paciente FROM paciente where id_paciente = " + id
                + " AND contrasegna_paciente = '" + contrasegna + "'";
        try {
            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscarPaciente);
            ResultSet rs = statement.executeQuery();

            /*
             * if(rs.getInt("id_paciente") != 0){
             * 
             * }
             */
            if (rs.next()) {
                System.out.println(rs.getInt("id_paciente"));
                System.out.println(rs.getString("contrasegna_paciente"));
                b = true;
            } else {
                System.out.println("Resultset vacio");
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        cerrarConexion();

        return b;
    }

    public static String getNombrePaciente(String username) {

        String str = "";
        int id = Integer.parseInt(username);
        connectarBD();
        String buscarPacienteNombre = "SELECT nombre_paciente FROM paciente WHERE id_paciente = " + id;

        try {

            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscarPacienteNombre);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                str = rs.getString("nombre_paciente");
            } else {
                str = "Usuario invalido";
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        cerrarConexion();

        return str;

    }

    public static void cambiarEstadoP(String username, String nuevoEstado) {

        int id = Integer.parseInt(username);
        connectarBD();
        String cambiarEstadoPac = "UPDATE paciente SET estado_paciente = '" + nuevoEstado + "' WHERE id_paciente = "
                + id;

        try {
            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(cambiarEstadoPac);
            statement.executeUpdate(cambiarEstadoPac);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        cerrarConexion();

    }

    public static int getCantidadPacientesParaMedico() {
        connectarBD();

        String query = "SELECT count(*) FROM paciente WHERE estado_paciente = 'ESPERANDO CITA' OR estado_paciente = 'ESPERANDO RESULTADOS';";
        int cantidadPacientes = 0;

        try {
            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                cantidadPacientes = rs.getInt("count(*)");
            } else {
                System.out.println("No se han encontrado pacientes");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return cantidadPacientes;
    }

    public static String getEstadoP(String username) {

        String str = "";
        int id = Integer.parseInt(username);
        connectarBD();
        String buscarPacienteEstado = "SELECT estado_paciente FROM paciente WHERE id_paciente = " + id;

        try {

            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscarPacienteEstado);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                str = rs.getString("estado_paciente");
            } else {
                str = "Usuario invalido";
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        cerrarConexion();

        return str;

    }

    public static boolean loginMedico(String username, String contrasegna) {

        Boolean b = false;
        String id = username;
        connectarBD();

        String buscarMedico = "SELECT id_medico, contrasegna_medico FROM medico where id_medico = '" + id
                + "' AND contrasegna_medico = '" + contrasegna + "'";
        try {
            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscarMedico);
            ResultSet rs = statement.executeQuery();

            /*
             * if(rs.getInt("id_paciente") != 0){
             * 
             * }
             */
            if (rs.next()) {
                System.out.println(rs.getString("id_medico"));
                System.out.println(rs.getString("contrasegna_medico"));
                b = true;
            } else {
                System.out.println("Resultset vacio medico");
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        cerrarConexion();

        return b;
    }

    public static String getNombreMedico(String username) {

        String str = "";
        String idMedico = username;
        connectarBD();
        String buscarMedicoNombre = "SELECT nombre_medico FROM medico WHERE id_medico = '" + idMedico + "'";

        try {

            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscarMedicoNombre);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                str = rs.getString("nombre_medico");
            } else {
                str = "Usuario invalido";
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        cerrarConexion();

        return str;

    }

    public static String getInfoPacientes(String username) {

        String infop = "";
        int id = Integer.parseInt(username);
        connectarBD();
        String buscarPacienteNombre = "SELECT * FROM paciente p INNER JOIN contacto_emergencia_paciente c WHERE p.id_contacto_emergencia = c.id_contacto_emergencia AND p.id_paciente = "
                + id;

        try {

            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscarPacienteNombre);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int idp = rs.getInt("id_paciente");
                int idcep = rs.getInt("id_contacto_emergencia");
                String correop = rs.getString("correo_paciente");
                String generop = rs.getString("genero_paciente");
                int edadp = rs.getInt("edad_paciente");
                String nombrep = rs.getString("nombre_paciente");
                String telefonop = rs.getString("telefono_paciente");
                String sangrep = rs.getString("sangre_paciente");
                String estadop = rs.getString("estado_paciente");
                String nombrecep = rs.getString("nombre_contacto_emergencia");
                String parentescocep = rs.getString("parentesco");
                String telefonocep = rs.getString("telefono_contacto_emergencia");
                String edadcep = rs.getString("edad_contacto_emergencia");
                String correocep = rs.getString("correo_contacto_emergencia");

                infop = "<div class=\"contenedor1\"><div class=\"hizq\"><img id=\"logo1\" src=\"svg/Letras logo.svg\"> <label class=\"info\">Información</label> </div><div>"
                        + "                <form action = \"perfilPaciente\" method=\"GET\">\r\n"
                        + "                <img class=\"fotop\" src=\"svg/Group 63.svg\"> >\r\n"
                        + "                <label class=\"entrada\" id=\"nomp\">" + nombrep + "</label>\r\n"
                        + "                <label id=\"docp\">Documento:</label>\r\n"
                        + "                <label id=\"numdoc\">" + idp + "</label>\r\n"
                        + "                <hr id=\"divisor1\">\r\n"
                        + "                <img class=\"puntoedad\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <label id=\"edadp\">Edad:</label>\r\n"
                        + "                <label id=\"vedad\">" + edadp + "</label>\r\n"
                        + "                <img class=\"puntogen\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <label id=\"genp\">Genero:</label>\r\n"
                        + "                <label id=\"vgen\">" + generop + "</label>\r\n"
                        + "                <img class=\"puntosan\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <label id=\"sanp\">Sangre:</label>\r\n"
                        + "                <label id=\"vsan\">" + sangrep + "</label>\r\n"
                        + "                <img class=\"puntoest\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <label id=\"estp\">Estado:</label>\r\n"
                        + "                <label id=\"vest\">" + estadop + "</label>\r\n"
                        + "                <img class=\"puntotoolc\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <img class=\"toolcorreo\" src=\"svg/tool.svg\">\r\n"
                        + "                <label id=\"correop\">Correo</label>\r\n"
                        + "                <input type=\"email\" id=\"editcp\" name=\"cambiarCorreoP\" placeholder=\""
                        + correop + "\">\r\n"
                        + "                <img class=\"puntotoolt\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <img class=\"tooltel\" src=\"svg/tool.svg\">\r\n"
                        + "                <label id=\"telp\">Telefono</label>\r\n"
                        + "                <input type=\"text\" id=\"edittp\" name=\"cambiarTelefonoP\" placeholder=\""
                        + telefonop + "\">\r\n"
                        + "                <img class=\"puntotoolcontra\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <img class=\"toolcontra\" src=\"svg/tool.svg\">\r\n"
                        + "                <label id=\"contrap\">Contraseña</label>\r\n"
                        + "                <input type=\"text\" id=\"editcontrap\">\r\n"
                        + "                <img class=\"puntotoolconfirm\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <img class=\"toolconfirm\" src=\"svg/tool.svg\">\r\n"
                        + "                <label id=\"confirmp\">Confirmar Contraseña</label>\r\n"
                        + "                <input type=\"password\" id=\"editconfirmp\" name=\"cambiarContrasegnaP\">\r\n"
                        + "                <button class=\"confirmar\" name = \"botcambpac\">Confirmar Cambios</button>\r\n"
                        + "            </form></div></div>\r\n"
                        + "    <div class = \"contenedor2\">        <div class=\"hder\">\r\n"
                        + "                <img id=\"logoce\" src=\"svg/Group 66.svg\">\r\n"
                        + "            <div><form action = \"perfilPaciente\" method=\"GET\">\r\n"
                        + "                <img class=\"puntonomce\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <label id=\"nomce\">Nombre:</label>\r\n"
                        + "                <label id=\"vnomce\">" + nombrecep + "</label>\r\n"
                        + "                <img class=\"puntoedadce\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <label id=\"edadce\">Edad:</label>\r\n"
                        + "                <label id=\"vedadce\">" + edadcep + "</label>\r\n"
                        + "                <img class=\"puntoparce\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <label id=\"parce\">Parentesco:</label>\r\n"
                        + "                <label id=\"vparce\">" + parentescocep + "</label>\r\n"
                        + "                <img class=\"puntotoolcorreoce\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <img class=\"toolcorreoce\" src=\"svg/tool.svg\">\r\n"
                        + "                <label id=\"correoce\">Correo</label>\r\n"
                        + "                <input type=\"text\" id=\"editcorreoce\" name=\"cambiarCorreoCep\" placeholder=\""
                        + correocep + "\">\r\n"
                        + "                <img class=\"puntotooltelce\" src=\"svg/Ellipse 2.svg\">\r\n"
                        + "                <img class=\"tooltelce\" src=\"svg/tool.svg\">\r\n"
                        + "                <label id=\"telce\">Telefono</label>\r\n"
                        + "                <input type=\"text\" id=\"edittelce\" name=\"cambiarTelefonoCep\" placeholder=\""
                        + telefonocep + "\">\r\n"
                        + "                <input type = \"hidden\" name =\"idcepp\" value = \"" + idcep + "\">\r\n"
                        + "                <button class=\"confirmarce\" name = \"botcambpacep\">Confirmar Cambios</button>\r\n"
                        + "                <br>\r\n" + "                <br>\r\n </form></div></div></div>";

            } else {

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return infop;

    }

    public static String getInfoMedico(String username) {

        String infom = "";
        String nombre = "";
        String id = "";
        String correo = "";
        String genero = "";
        String telefono = "";
        int consultorio = 0;
        int edad = 0;
        connectarBD();
        String buscar = "SELECT * FROM medico m INNER JOIN especialidad_medico e WHERE m.id_medico = e.id_medico and m.id_medico = '"
                + username + "'";

        try {

            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscar);
            ResultSet rs = statement.executeQuery();
            String especialidad = "";
            int clase = 1;
            while (rs.next()) {

                id = rs.getString("id_medico");
                nombre = rs.getString("nombre_medico");
                consultorio = rs.getInt("consultorio_medico");
                correo = rs.getString("correo_medico");
                telefono = rs.getString("telefono_medico");
                genero = rs.getString("genero_medico");
                edad = rs.getInt("edad_medico");

                especialidad += "<label id=\"vesp" + clase + "\">" + rs.getString("nombre_especialidad") + "</label>";
                clase++;

            }

            infom = "<img class=\"fotop\" src=\"svg/Group 63.svg\"> >\r\n"
                    + "                <label class=\"entrada\" id=\"nomM\">" + nombre + "</label>\r\n"
                    + "                <label id=\"docM\">ID:</label>\r\n" + "                <label id=\"numidM\">"
                    + id + "</label>\r\n" + "                <hr id=\"divisor1\">\r\n"
                    + "                <img class=\"puntoedadm\" src=\"svg/Ellipse 2.svg\">\r\n"
                    + "                <label id=\"edadm\">Edad:</label>\r\n" + "                <label id=\"vedadm\">"
                    + edad + "</label>\r\n" + "                <img class=\"puntogenm\" src=\"svg/Ellipse 2.svg\">\r\n"
                    + "                <label id=\"genm\">Genero:</label>\r\n" + "                <label id=\"vgenm\">"
                    + genero + "</label>\r\n" + "\r\n"
                    + "                <label id=\"esp1\">Especialidad(es):</label>\r\n" + "                <label>"
                    + especialidad + "</label>"
                    + "                <img class=\"puntoesp1\" src=\"svg/Ellipse 2.svg\">\r\n" + "\r\n"
                    + "                <img class=\"puntocorreom\" src=\"svg/Ellipse 2.svg\">\r\n"
                    + "                <label id=\"correom\">Correo:</label>\r\n"
                    + "                <label id=\"vcorreom\">" + correo + "</label>\r\n" + "\r\n"
                    + "                <img class=\"puntotoolc\" src=\"svg/Ellipse 2.svg\">\r\n"
                    + "                <img class=\"toolconsul\" src=\"svg/tool.svg\">\r\n"
                    + "                <label id=\"consul\">Consultorio: " + consultorio + "</label>\r\n"
                    // + " <label id=\"consul\">"+consultorio+"</label>\r\n"
                    + "                <select name=\"selectconsmed\" id=\"consultorio\">\r\n" + "\r\n"
                    + "                    <option value=\"" + consultorio + "\" selected>" + consultorio + "</option>"
                    + "                    <option value=\"201\">201</option>\r\n"
                    + "                    <option value=\"202\">202</option>\r\n"
                    + "                    <option value=\"203\">203</option>\r\n"
                    + "                    <option value=\"204\">204</option>\r\n"
                    + "                    <option value=\"205\">205</option>\r\n"
                    + "                    <option value=\"301\">301</option>\r\n"
                    + "                    <option value=\"302\">302</option>\r\n"
                    + "                    <option value=\"303\">303</option>\r\n"
                    + "                    <option value=\"304\">304</option>\r\n"
                    + "                    <option value=\"305\">305</option>\r\n"
                    + "                    <option value=\"401\">401</option>\r\n"
                    + "                    <option value=\"402\">402</option>\r\n"
                    + "                    <option value=\"403\">403</option>\r\n"
                    + "                    <option value=\"404\">404</option>\r\n"
                    + "                    <option value=\"405\">405</option>\r\n"
                    + "                    <option value=\"501\">501</option>\r\n"
                    + "                    <option value=\"502\">502</option>\r\n"
                    + "                    <option value=\"503\">503</option>\r\n"
                    + "                    <option value=\"504\">504</option>\r\n"
                    + "                    <option value=\"505\">505</option>\r\n" + "\r\n"
                    + "                </select>\r\n"
                    + "                <img class=\"puntotooltm\" src=\"svg/Ellipse 2.svg\">\r\n"
                    + "                <img class=\"tooltelm\" src=\"svg/tool.svg\">\r\n"
                    + "                <label id=\"telm\">Telefono:</label>\r\n"
                    + "                <input type=\"text\" id=\"edittm\" name = \"cambiarTelefonoM\" placeholder = \""
                    + telefono + "\">\r\n"
                    + "                <img class=\"puntotoolcontram\" src=\"svg/Ellipse 2.svg\">\r\n"
                    + "                <img class=\"toolcontram\" src=\"svg/tool.svg\">\r\n"
                    + "                <label id=\"contram\">Contraseña:</label>\r\n"
                    + "                <input type=\"text\" id=\"editcontram\">\r\n"
                    + "                <img class=\"puntotoolconfirmm\" src=\"svg/Ellipse 2.svg\">\r\n"
                    + "                <img class=\"toolconfirmm\" src=\"svg/tool.svg\">\r\n"
                    + "                <label id=\"confirmm\">Confirmar Contraseña:</label>\r\n"
                    + "                <input type=\"password\" id=\"editconfirmm\" name = \"cambiarContrasegnaM\">\r\n"
                    + "                <input type = \"hidden\" name =\"consumed\" value = \"" + consultorio + "\">\r\n"
                    + "                <button class=\"confirmar\" name=\"botcammed\">Confirmar Cambios</button>";

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return infom;

    }

    public static void cambiarColumna(String nameTabla, String set, String newSet, String idpce, String username) {

        char s = username.charAt(0);
        if (Character.isDigit(s)) {

            String cambiarEstadoPac = "UPDATE " + nameTabla + " SET " + set + " = '" + newSet + "' WHERE " + idpce
                    + " = " + username;

            try {
                PreparedStatement statement = null;
                statement = connSQL.prepareStatement(cambiarEstadoPac);
                statement.executeUpdate(cambiarEstadoPac);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        } else {
            // connectarBD();
            String cambiarEstadoPac = "UPDATE " + nameTabla + " SET " + set + " = '" + newSet + "' WHERE " + idpce
                    + " = '" + username + "'";

            try {
                PreparedStatement statement = null;
                statement = connSQL.prepareStatement(cambiarEstadoPac);
                statement.executeUpdate(cambiarEstadoPac);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

            // cerrarConexion();
        }

    }

    public static String getListaPacientes() {

        String str = "";

        try {
            String queryPacienteEstado = "SELECT nombre_paciente, estado_paciente, id_paciente FROM paciente";
            PreparedStatement statement = null;

            connectarBD();

            statement = connSQL.prepareStatement(queryPacienteEstado);

            ResultSet rs = statement.executeQuery();

            int contb = 0;

            while (rs.next()) {
                String nombre = rs.getString("nombre_paciente");
                String estado = rs.getString("estado_paciente");
                int id = rs.getInt("id_paciente");

                str += "<br> <div class=\"pacienteM\"><hr color=\"white\" size=\"1\" class=\"linea\"> <label class=\"nombrePac\">" + nombre + "</label>\r\n"
                        + "                <label class=\"docpac\">" + id + "</label>\r\n"
                        + "                <label class=\"estado\">" + estado + "</label>\r\n"
                        + "                <button class=\"editarpac\" name=\"botoneditarpac" + contb
                        + "\">Editar</button>\r\n"
                        + "                <button class=\"eliminarpac\" name=\"botoneliminarpac" + contb
                        + "\">Eliminar</button></div>";

                contb++;
            }

            cerrarConexion();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return str;

    }

    public static String getListaMedicos() {

        String str = "";
        int numerobot = 0;

        try {
            String listamed = "SELECT id_medico, nombre_medico, consultorio_medico FROM medico";
            PreparedStatement statement = null;
            connectarBD();
            statement = connSQL.prepareStatement(listamed);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_medico");
                String nombre = rs.getString("nombre_medico");
                int consultorio = rs.getInt("consultorio_medico");

                str += "<br><div class=\"pacienteM\"><hr color=\"white\" size=\"1\" class=\"linea\"> <label class=\"nombreMed\">"
                        + nombre + "</label>\r\n" + "                <label class=\"docmed\">" + id + "</label>\r\n"
                        + "                <label class=\"consul\">" + consultorio + "</label>\r\n"
                        + "                <button class=\"editarmed\" name=\"botoneditarmed" + numerobot
                        + "\">Editar</button>\r\n"
                        + "                <button class=\"eliminarmed\" name=\"botoneliminarmed" + numerobot
                        + "\">Eliminar</button></div>";

                numerobot++;
            }

            cerrarConexion();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return str;

    }

    public static String getListaTipos() {

        String str = "";
        String getTipos = "SELECT nombre_tipo_prueba, id_tipo_prueba FROM tipo_prueba;";

        connectarBD();
        try {

            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(getTipos);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                str += "<option value=\"" + rs.getString("id_tipo_prueba") + "\">" + rs.getString("nombre_tipo_prueba")
                        + "</option>";
            }

        } catch (Exception e) {
            // TODO: handle exception3
            e.printStackTrace();
        }
        cerrarConexion();
        return str;
    }

    public static int getIdExamen() {

        int ValorInicial = 0;
        String updateViEx = "";

        try {
            String buscarViEx = "SELECT valor_examen FROM valores_iniciales";
            PreparedStatement statement = null;
            statement = connSQL.prepareStatement(buscarViEx);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                ValorInicial = rs.getInt("valor_examen");
            }

            ValorInicial++;
            updateViEx = "UPDATE valores_iniciales SET valor_examen = " + ValorInicial + ";";
            statement = connSQL.prepareStatement(updateViEx);
            statement.executeUpdate(updateViEx);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return ValorInicial;

    }

    public static void crearExamen(String idMedico, int idPaciente, String diagnostico, String tipo0, String tipo1,
            String tipo2, String tipo3, String tipo4) {

        connectarBD();
        String ingresarExamen = "INSERT INTO examen_laboratorio (id_examen, id_medico, id_paciente, diagnostico, fecha_remision) VALUES (?,?,?,?,?)";
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        ArrayList<String> selects = new ArrayList<String>();
        ArrayList<String> criterios = new ArrayList<String>();
        int idEx = getIdExamen();
        try {

            PreparedStatement statement = connSQL.prepareStatement(ingresarExamen);
            statement.setInt(1, idEx);
            statement.setString(2, idMedico);
            statement.setInt(3, idPaciente);
            statement.setString(4, diagnostico);
            statement.setDate(5, date);

            statement.executeUpdate();

            if (tipo0 != null) {
                selects.add(tipo0);
            }
            if (tipo1 != null) {
                selects.add(tipo1);
            }
            if (tipo2 != null) {
                selects.add(tipo2);
            }
            if (tipo3 != null) {
                selects.add(tipo3);
            }
            if (tipo4 != null) {
                selects.add(tipo4);
            }

            for (int i = 0; i < selects.size(); i++) {

                criterios = Singleton.getCriterios(selects.get(i), statement);

                for (int j = 0; j < criterios.size(); j++) {

                    String ingresarTipos = "INSERT INTO examen_lab_tiene_tipo_prueba (id_examen, id_tipo_prueba, criterio_tipo_prueba) VALUES (?,?,?);";
                    statement = connSQL.prepareStatement(ingresarTipos);
                    statement.setInt(1, idEx);
                    statement.setString(2, selects.get(i));
                    statement.setString(3, criterios.get(j));
                    statement.executeUpdate();

                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        cerrarConexion();

    }

    public static ArrayList<String> getCriterios(String idTipo, PreparedStatement statement) {

        ArrayList<String> criterios = new ArrayList<String>();

        try {

            String traerCriterios = "SELECT criterio_tipo_prueba FROM criterios_tipo_prueba WHERE id_tipo_prueba = '"
                    + idTipo + "';";

            statement = connSQL.prepareStatement(traerCriterios);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                criterios.add(rs.getString("criterio_tipo_prueba"));
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return criterios;

    }

    public static String getBarraEstado(String username) {

        String str = "";
        int idexamen = 0;
        String estado = getEstadoP(username);

        try {

            if (estado.equals("ESPERANDO CITA")) {

                str = "<div class=\"estado\">\r\n" + "                       <div class=\"estgen\">\r\n"
                        + "                       <img src=\"svg/Estado Genesis.svg\">\r\n"
                        + "                       </div>\r\n" + "                       <label class=\"feedback\">"
                        + "Se ha mandado su solicitud, espere a que un medico genere su orden" + "</label>\r\n"
                        + "                       </div>";

            }

            if (estado.equals("ESPERANDO REALIZACION")) {

                connectarBD();
                idexamen = getIdExamenPaciente(username);
                
                str = "<div class=\"estado\">\r\n" + "                       <div class=\"estgen\">\r\n"
                        + "                       <img src=\"svg/Estado Genesis.svg\">\r\n"
                        + "                       </div>\r\n" + "                       <label class=\"feedback\">"
                        + "Ya se ha generado su orden, con el número: " + idexamen + "</label>\r\n"
                        + "                       </div>";
                ;

                cerrarConexion();

            }

            if (estado.equals("ESPERANDO RESULTADOS")) {

                str = "<div class=\"estado\">\r\n" + "                       <div class=\"estgen\">\r\n"
                        + "                       <img src=\"svg/Estado Genesis.svg\">\r\n"
                        + "                       </div>\r\n" + "                       <label class=\"feedback\">"
                        + "Ya se ha enviado su orden de laboratorio, esperando a que el doctor ingrese los resultados"
                        + "</label>\r\n" + "                       </div>";

            }

            if (estado.equals("RESULTADOS GENERADOS")) {

                str = "<div class=\"estado\">\r\n" + "                       <div class=\"estgen\">\r\n"
                        + "                       <img src=\"svg/Estado Genesis.svg\">\r\n"
                        + "                       </div>\r\n" + "                       <label class=\"feedback\">"
                        + "Ya se han ingresado sus resultados, consultelos en la pestaña RESULTADOS" + "</label>\r\n"
                        + "                       </div>";

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return str;

    }

    public static int getIdExamenPaciente(String username) {

        

        String getId = "SELECT id_examen FROM examen_laboratorio e INNER JOIN paciente p WHERE p.id_paciente = "
                + username + " AND  p.id_paciente = e.id_paciente AND fecha_resultados is null;";

        int idEx = 0;

        try {

            PreparedStatement statement = connSQL.prepareStatement(getId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                idEx = rs.getInt("id_examen");
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return idEx;

    }

    public static String getListaTiposExamen(int idEx) {

        String fullEx = "SELECT p.nombre_tipo_prueba, e.criterio_tipo_prueba FROM examen_lab_tiene_tipo_prueba e INNER JOIN tipo_prueba p WHERE e.id_tipo_prueba = p.id_tipo_prueba AND e.id_examen = "
                + idEx + ";";
        String str = "";
        String tipoActual, tipoNuevo = "";
        int control = 1;

        try {
            PreparedStatement statement = connSQL.prepareStatement(fullEx);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                tipoActual = rs.getString("nombre_tipo_prueba");

                if (tipoNuevo.equals(tipoActual) || control == 1) {

                    if (control == 1) {

                        str += "<div class=\"elingreso\"><hr color=\"white\" size=\"1\"><label class=\"h4\">"
                                + tipoActual + "</label>";

                    }

                    str += "<label class=\"criterio" + control + "\">" + rs.getString("criterio_tipo_prueba")
                            + "</label>" + "<input type=\"text\" class=\"cri" + control + "\" name = \"" + tipoActual
                            + control + "\" required>";

                    control++;

                } else if (control != 1) {

                    control = 1;
                    str += "</div>";
                    str += "<div class=\"elingreso\"><hr color=\"white\" size=\"1\"><label class=\"h4\">" + tipoActual
                            + "</label>";
                    str += "<label class=\"criterio" + control + "\">" + rs.getString("criterio_tipo_prueba")
                            + "</label>" + "<input type=\"text\" class=\"cri" + control + "\" name = \"" + tipoActual
                            + control + "\" required>";
                    control++;

                }

                tipoNuevo = rs.getString("nombre_tipo_prueba");

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return str;

    }

    public static ArrayList<String> getTiposExamen(int id_examen) {

        ArrayList<String> tipos = new ArrayList<String>();
        String buscarString = "SELECT t.nombre_tipo_prueba FROM examen_lab_tiene_tipo_prueba e INNER JOIN tipo_prueba t "
                + "WHERE e.id_examen = " + id_examen + " AND e.id_tipo_prueba = t.id_tipo_prueba";

        try {

            PreparedStatement statement = connSQL.prepareStatement(buscarString);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tipos.add(rs.getString("nombre_tipo_prueba"));
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return tipos;

    }

    public static ArrayList<String> getIdTiposExamen(int id_examen) {

        ArrayList<String> idTipos = new ArrayList<String>();
        String idString = "SELECT id_tipo_prueba FROM examen_lab_tiene_tipo_prueba WHERE id_examen = " + id_examen;

        try {

            PreparedStatement statement = connSQL.prepareStatement(idString);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                idTipos.add(rs.getString("id_tipo_prueba"));
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return idTipos;

    }

    public static void updateResultadosExamen(String res, int idEx, String tipo, String criterio) {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String updateRes = "";

        try {
            updateRes = "UPDATE examen_lab_tiene_tipo_prueba SET resultado = '" + res + "' WHERE id_examen = " + idEx
                    + " AND id_tipo_prueba = '" + tipo + "' AND criterio_tipo_prueba = '" + criterio + "'";
            PreparedStatement statement = connSQL.prepareStatement(updateRes);
            statement.executeUpdate(updateRes);

            updateRes = "UPDATE examen_laboratorio SET fecha_resultados = '" + date + "' WHERE id_examen = " + idEx;
            statement = connSQL.prepareStatement(updateRes);
            statement.executeUpdate(updateRes);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void setFechaRealizacion(int idEx) {

        connectarBD();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String updateFecha = "";

        try {

            updateFecha = "UPDATE examen_laboratorio SET fecha_realizacion = '" + date + "' WHERE id_examen = " + idEx;
            PreparedStatement statement = connSQL.prepareStatement(updateFecha);
            statement.executeUpdate(updateFecha);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        cerrarConexion();

    }

    public static void generarPdf(String nombrePdf, String url, int idExamen) {

        String rutaPdf = url + nombrePdf + ".pdf";
        String rutaHtml = url + "nuevaPlantilla.html";

        generarHtmlTemplate(url, idExamen );

        try {
            htmlToPdf(rutaHtml, rutaPdf, url);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        /*
         * Document documento = new Document(); Html String nombreArchivo = nombrePdf +
         * ".pdf"; String ruta = System.getProperty("user.home") +
         * "/Desktop/Laboratorio Genesis Proyecto BD/Proyecto_BD_Laboratorio/src/main/webapp/pdf/"
         * ; System.out.println(url + nombreArchivo); System.out.println(ruta +
         * nombreArchivo);
         * 
         * try { PdfWriter.getInstance(documento, new FileOutputStream(url +
         * nombreArchivo)); documento.open();
         * 
         * PdfPTable tabla = new PdfPTable(2); tabla.addCell("pelicula");
         * tabla.addCell("cachucha"); documento.add(tabla);
         * 
         * documento.close(); System.out.println("Pdf creado");
         * 
         * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
         */

    }

    private static org.w3c.dom.Document html5ParseDocument(String inputHtml) throws IOException {

        org.jsoup.nodes.Document doc;
        System.out.println("Conviertiendo HTML.....");
        doc = Jsoup.parse(new File(inputHtml), "UTF-8");
        System.out.println("Conversión lista....");
        return new W3CDom().fromJsoup(doc);
    }

    private static void htmlToPdf(String inputHTML, String outputPDF, String uri) throws IOException {
        org.w3c.dom.Document doc = html5ParseDocument(inputHTML);
        String baseURI = uri;
        // new URI(uri).toString();

        // FileSystems.getDefault().getPath("C:/", "Users/jedaz/Desktop/", "Laboratorio
        // Genesis Proyecto
        // BD/Proyecto_BD_Laboratorio/src/main/webapp/pdf/").toUri().toString();
        // C:\Users\jedaz\Desktop\Laboratorio Genesis Proyecto
        // BD\Proyecto_BD_Laboratorio\src\main\webapp\pdf

        OutputStream out = new FileOutputStream(outputPDF);
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useSVGDrawer(new BatikSVGDrawer());
        builder.withUri(outputPDF);
        builder.toStream(out);
        builder.withW3cDocument(doc, baseURI);
        builder.useUriResolver(new ResolverURI());
        builder.run();
        out.close();
    }

    private static void generarHtmlTemplate(String uri, int idExamen) {

        connectarBD();
        String queryResultados = "SELECT p.nombre_paciente, p.id_paciente, e.id_examen, p.edad_paciente, m.nombre_medico, e.fecha_remision, e.fecha_resultados, tp.nombre_tipo_prueba, c.criterio_tipo_prueba, t.resultado, c.unidades_criterio_tipo FROM examen_laboratorio e INNER JOIN paciente p INNER JOIN examen_lab_tiene_tipo_prueba t INNER JOIN tipo_prueba tp  INNER JOIN criterios_tipo_prueba c INNER JOIN medico m WHERE e.id_examen = "+ idExamen +" AND p.id_paciente = e.id_paciente AND e.id_medico = m.id_medico AND t.id_examen = e.id_examen AND tp.id_tipo_prueba = t.id_tipo_prueba AND c.id_tipo_prueba = t.id_tipo_prueba AND c.criterio_tipo_prueba = t.criterio_tipo_prueba";

        File plantillaHTML = new File(uri + "plantilla.html");
        try {

            PreparedStatement statement = connSQL.prepareStatement(queryResultados);
            ResultSet rs = statement.executeQuery();

            String htmlString = FileUtils.readFileToString(plantillaHTML);
            String nombrePaciente = "";
            String numeroExamen = "";
            String documentoPaciente = "";
            String edadPaciente = "";
            String medicoRemitente = "";
            String fechaRemision = "";
            String fechaResultados = "";
            String divs =   "";
            String unidades = "";

            String tipoActual = "";
            String tipoNuevo = "";
            int control = 1;

            while(rs.next()){
                nombrePaciente = rs.getString("nombre_paciente");
                numeroExamen = rs.getInt("id_examen") + "";
                documentoPaciente = rs.getInt("id_paciente")+"";
                edadPaciente = rs.getInt("edad_paciente")+"";
                medicoRemitente = rs.getString("nombre_medico");
                fechaRemision = rs.getDate("fecha_remision").toString();
                fechaResultados = rs.getDate("fecha_resultados").toString();

                if(rs.getString("unidades_criterio_tipo") == null){
                    unidades = "";
                }else{
                    unidades = rs.getString("unidades_criterio_tipo");
                }

                tipoActual = rs.getString("nombre_tipo_prueba");

                if (tipoNuevo.equals(tipoActual) || control == 1) {

                    if (control == 1) {

                        divs += "<div class=\"elingreso\"><label class=\"tipo\">" + tipoActual + "</label>";

                    }

                    divs += "<label class=\"cri"+control+"\">" + rs.getString("criterio_tipo_prueba") + "</label>" + "<label class=\"res"+control+"\">" + rs.getString("resultado") + "</label>" + "<label class=\"uni"+control+"\">" + unidades + "</label>";

                    control++;

                } else if (control != 1) {

                    control = 1;
                    divs += "</div><br>";
                    divs += "<div class=\"elingreso\"><label class=\"tipo\">" + tipoActual + "</label>";
                    divs += "<label class=\"cri"+control+"\">" + rs.getString("criterio_tipo_prueba") + "</label>" + "<label class=\"res"+control+"\">" + rs.getString("resultado") + "</label>" + "<label class=\"uni"+control+"\">" + unidades + "</label>";
                    control++;

                }

                tipoNuevo = rs.getString("nombre_tipo_prueba");

            }

            
            htmlString = htmlString.replace("$nombrepaciente", nombrePaciente);
            htmlString = htmlString.replace("$numeroexamen", numeroExamen);
            htmlString = htmlString.replace("$documentopaciente", documentoPaciente);
            htmlString = htmlString.replace("$edadpaciente", edadPaciente);
            htmlString = htmlString.replace("$medicoremitente", medicoRemitente);
            htmlString = htmlString.replace("$fecharemision", fechaRemision);
            htmlString = htmlString.replace("$fecharesultados", fechaResultados);
            htmlString = htmlString.replace("$resultados", divs);
            File HtmlNuevo = new File(uri + "nuevaPlantilla.html");
            FileUtils.writeStringToFile(HtmlNuevo, htmlString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        cerrarConexion();

    }

}
