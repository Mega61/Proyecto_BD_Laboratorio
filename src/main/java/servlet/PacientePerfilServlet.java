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

@WebServlet(name = "PacientePerfilServlet", urlPatterns = { "/perfil_paciente" })

public class PacientePerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre, genero, correo, telefono, sangre, contrasegna = "";
        int edad, documento = 0;

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("paciente").toString();
        String nombreUsuario = session.getAttribute("pacienteNombre").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("paciente.jsp");

        if (req.getParameter("botcambpac") != null) {

            

            rDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}