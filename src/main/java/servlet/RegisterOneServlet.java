package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RegisterOneServlet", urlPatterns = { "/registerone" })

public class RegisterOneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre, genero, correo, telefono, sangre = "";
        int edad, documento = 0;

        RequestDispatcher rDispatcher = req.getRequestDispatcher("registroc.html");

        if (req.getParameter("botregisterone") != null) {

            HttpSession session = req.getSession();

            nombre = req.getParameter("nomP");
            genero = req.getParameter("selectgenP");
            correo = req.getParameter("correoP");
            telefono = req.getParameter("telP");
            sangre = req.getParameter("selectSangre");
            edad = Integer.parseInt(req.getParameter("edadP"));
            documento = Integer.parseInt(req.getParameter("docPR"));

            session.setAttribute("nombreRegistro", nombre);
            session.setAttribute("generoRegistro", genero);
            session.setAttribute("correoRegistro", correo);
            session.setAttribute("telefonoRegistro", telefono);
            session.setAttribute("edadRegistro", edad);
            session.setAttribute("docuementoRegistro", documento);
            session.setAttribute("sangreRegistro", sangre);
            

            /*System.out.println(nombre + " " + genero + " " + correo + " " + telefono + " " + sangre + " " + edad + " "
                    + documento);*/

            rDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}