package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RegisterTwoServlet", urlPatterns = { "/registertwo" })
public class RegisterTwoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreCE, parentescoCE, correoCE, telefonoCE, nombre, genero, correo, telefono, sangre, estado = "";
        int edadCE, edad, documento = 0;

        RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");

        if (req.getParameter("botregistertwo") != null) {

            nombreCE = req.getParameter("nomCE");
            parentescoCE = req.getParameter("parCE");
            correoCE = req.getParameter("correoCE");
            telefonoCE = req.getParameter("telCE");
            edadCE = Integer.parseInt(req.getParameter("edadCE"));

            HttpSession session = req.getSession();
            nombre = session.getAttribute("nombreRegistro").toString();
            genero = session.getAttribute("generoRegistro").toString();
            correo = session.getAttribute("correoRegistro").toString();
            telefono = session.getAttribute("telefonoRegistro").toString();
            edad = (Integer)(session.getAttribute("edadRegistro"));
            documento = (Integer)(session.getAttribute("docuementoRegistro"));
            sangre = session.getAttribute("sangreRegistro").toString();
            estado = "DEFAULT";
            
            Singleton.ingresarPacienteYcontactoEmergencia(documento, correo, genero, edad, nombre, telefono, sangre, estado, parentescoCE, telefonoCE, edadCE, correoCE, nombreCE);

            System.out.println("CONTACTO EMERGENCIA: "+nombreCE + " " + parentescoCE + " " + correoCE + " " + telefonoCE + " " + edadCE
                                +" \n REGISTRO 1: "+nombre + " " + genero + " " + correo + " " + telefono + " " + sangre + " " + edad + " "
                                + documento);

            rDispatcher.forward(req, resp);
        }

    }

}
