package servlet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javassist.compiler.ast.StringL;
import com.itextpdf.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

    private static int medidini = 85632;
    private static int pacceidini = 4120;

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

    public static String crearIdMedico() {
        medidini++;
        String idm = "M" + medidini;
        return idm;
    }

    public static void ingresarPacienteYcontactoEmergencia(int id_p, String correoP, String generoP, int edadP,
            String nombreP, String telefonoP, String sangreP, String estadoP, String contrasegna, String parentesoCE,
            String telefonoCE, int edadCE, String correoCE, String nombreCE) {

        connectarBD();
        pacceidini++;
        int idCe = pacceidini;

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

    public static void ingresarMedico(String nombreM, int consultorioM, String correoM, String telefonoM,
            String generoM, int edadM, String contrasegnaM, ArrayList<String> especialidades) {

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
                String contrasegnap = rs.getString("contrasegna_paciente");
                String nombrecep = rs.getString("nombre_contacto_emergencia");
                String parentescocep = rs.getString("parentesco");
                String telefonocep = rs.getString("telefono_contacto_emergencia");
                String edadcep = rs.getString("edad_contacto_emergencia");
                String correocep = rs.getString("correo_contacto_emergencia");

                infop ="<form action = \"perfilPaciente\" method=\"GET\">\r\n" +    
    "                <img class=\"fotop\" src=\"Group 63.svg\"> >\r\n" + 
    "                <label class=\"entrada\" id=\"nomp\">"+nombrep+"</label>\r\n" + 
    "                <label id=\"docp\">Documento:</label>\r\n" + 
    "                <label id=\"numdoc\">"+idp+"</label>\r\n" + 
    "                <hr id=\"divisor1\">\r\n" + 
    "                <img class=\"puntoedad\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <label id=\"edadp\">Edad:</label>\r\n" + 
    "                <label id=\"vedad\">"+edadp+"</label>\r\n" + 
    "                <img class=\"puntogen\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <label id=\"genp\">Genero:</label>\r\n" + 
    "                <label id=\"vgen\">"+generop+"</label>\r\n" + 
    "                <img class=\"puntosan\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <label id=\"sanp\">Sangre:</label>\r\n" + 
    "                <label id=\"vsan\">"+sangrep+"</label>\r\n" + 
    "                <img class=\"puntoest\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <label id=\"estp\">Estado:</label>\r\n" + 
    "                <label id=\"vest\">"+estadop+"</label>\r\n" + 
    "                <img class=\"puntotoolc\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <img class=\"toolcorreo\" src=\"svg/tool.svg\">\r\n" + 
    "                <label id=\"correop\">Correo</label>\r\n" + 
    "                <input type=\"email\" id=\"editcp\" name=\"cambiarCorreoP\" placeholder=\"" + correop + "\">\r\n" + 
    "                <img class=\"puntotoolt\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <img class=\"tooltel\" src=\"svg/tool.svg\">\r\n" + 
    "                <label id=\"telp\">Telefono</label>\r\n" + 
    "                <input type=\"text\" id=\"edittp\" name=\"cambiarTelefonoP\" placeholder=\"" + telefonop + "\">\r\n" + 
    "                <img class=\"puntotoolcontra\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <img class=\"toolcontra\" src=\"svg/tool.svg\">\r\n" + 
    "                <label id=\"contrap\">Contraseña</label>\r\n" + 
    "                <input type=\"text\" id=\"editcontrap\">\r\n" + 
    "                <img class=\"puntotoolconfirm\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <img class=\"toolconfirm\" src=\"svg/tool.svg\">\r\n" + 
    "                <label id=\"confirmp\">Confirmar Contraseña</label>\r\n" + 
    "                <input type=\"password\" id=\"editconfirmp\" name=\"cambiarContrasegnaP\" placeholder=\"" + contrasegnap + "\">\r\n" + 
    "                <button class=\"confirmar\" name = \"botcambpac\">Confirmar Cambios</button>\r\n" +
    "                <input type = \"hidden\" name =\"idcepp\" value = \"" + idcep + "\">\r\n" + 
    "            </form>\r\n" + 
    "            <div class=\"divisor2\">\r\n" + 
    "                <img id=\"logoce\" src=\"svg/Group 66.svg\">\r\n" + 
    "            </div>\r\n" + 
    "            <form action = \"perfilPaciente\" method=\"GET\">\r\n" + 
    "                <img class=\"puntonomce\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <label id=\"nomce\">Nombre:</label>\r\n" + 
    "                <label id=\"vnomce\">"+nombrecep+"</label>\r\n" + 
    "                <img class=\"puntoedadce\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <label id=\"edadce\">Edad:</label>\r\n" + 
    "                <label id=\"vedadce\">"+edadcep+"</label>\r\n" + 
    "                <img class=\"puntoparce\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <label id=\"parce\">Parentesco:</label>\r\n" + 
    "                <label id=\"vparce\">"+parentescocep+"</label>\r\n" + 
    "                <img class=\"puntotoolcorreoce\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <img class=\"toolcorreoce\" src=\"svg/tool.svg\">\r\n" + 
    "                <label id=\"correoce\">Correo</label>\r\n" + 
    "                <input type=\"text\" id=\"editcorreoce\" name=\"cambiarCorreoCep\" placeholder=\"" + correocep + "\">\r\n" + 
    "                <img class=\"puntotooltelce\" src=\"svg/Ellipse 2.svg\">\r\n" + 
    "                <img class=\"tooltelce\" src=\"svg/tool.svg\">\r\n" + 
    "                <label id=\"telce\">Telefono</label>\r\n" + 
    "                <input type=\"text\" id=\"edittelce\" name=\"cambiarTelefonoCep\" placeholder=\"" + telefonocep+ "\">\r\n" + 
    "                <button class=\"confirmarce\" name = \"botcambpacep\">Confirmar Cambios</button>\r\n" + 
    "                <br>\r\n" + 
    "                <br>\r\n </form>";

            } else {

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return infop;

    }

    public static void cambiarColumna(String nameTabla, String set, String newSet, String idpce, String username) {

        int id = Integer.parseInt(username);
        // connectarBD();
        String cambiarEstadoPac = "UPDATE " + nameTabla + " SET " + set + " = '" + newSet + "' WHERE " + idpce + " = "
                + id;

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

    public static void generarPdf(String nombrePdf, String url) {

        Document documento = new Document();
        String nombreArchivo = nombrePdf + ".pdf";
        String ruta = System.getProperty("user.home") + "/Desktop/Laboratorio Genesis Proyecto BD/Proyecto_BD_Laboratorio/src/main/webapp/pdf/";
        System.out.println(url + nombreArchivo);
        System.out.println(ruta + nombreArchivo);
        

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(url + nombreArchivo));
            documento.open();

            PdfPTable tabla = new PdfPTable(2);
            tabla.addCell("pelicula");
            tabla.addCell("cachucha");
            documento.add(tabla);

            documento.close();
            System.out.println("Pdf creado");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

}
