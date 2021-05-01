package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterTwoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreCE, parentescoCE, correoCE, telefonoCE, nombre, genero, correo, telefono, sangre = "";
        int edadCE, edad, documento = 0;

        RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");

        if (req.getParameter("-") != null) {

            nombreCE = req.getParameter("-");
            parentescoCE = req.getParameter("-");
            correoCE = req.getParameter("-");
            telefonoCE = req.getParameter("-");
            edadCE = Integer.parseInt(req.getParameter("-"));

            HttpSession session = req.getSession();
            nombre = session.getAttribute("nombreRegistro").toString();
            genero = session.getAttribute("generoRegistro").toString();
            correo = session.getAttribute("correoRegistro").toString();
            telefono = session.getAttribute("telefonoRegistro").toString();
            edad = (Integer)(session.getAttribute("edadRegistro"));
            documento = (Integer)(session.getAttribute("docuementoRegistro"));

            System.out.println("CONTACTO EMERGENCIA: "+nombreCE + " " + parentescoCE + " " + correoCE + " " + telefonoCE + " " + edadCE
                                +"/n REGISTRO 1: "+nombre + " " + genero + " " + correo + " " + telefono + " " + sangre + " " + edad + " "
                                + documento);

            rDispatcher.forward(req, resp);
        }

    }

}
