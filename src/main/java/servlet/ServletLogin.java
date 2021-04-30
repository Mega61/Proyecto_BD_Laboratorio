package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletLogin", urlPatterns = { "/ingresar" })

public class ServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String identificacion = "";
        String contrasegna = "";

        RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");

        if (req.getParameter("botonRegister") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("register.html");

        }

        if (req.getParameter("botoniniciars") != null) {

            identificacion = req.getParameter("docP");
            contrasegna = req.getParameter("conP");

            char prueba = identificacion.charAt(0);

            System.out.println("Se ha oprimido Iniciar Sesión");

            if (identificacion.equals("admin") && contrasegna.equals("1234")) {
                System.out.println("Se ha iniciado sesión como un admin");

                HttpSession sessionAdmin = req.getSession();
                sessionAdmin.setAttribute("admin", identificacion);
                sessionAdmin.setMaxInactiveInterval(30 * 60);
                // Cookie userName = new Cookie("admin", identificacion);
                // userName.setMaxAge(30*60);
                // resp.addCookie(userName);
                resp.sendRedirect("prueba.html");
                // rDispatcher = req.getRequestDispatcher("prueba.html");
            } else if (Character.isDigit(prueba)) {

                System.out.println("Se ha iniciado sesión como un paciente");
                rDispatcher = req.getRequestDispatcher("paciente.html");

            } else {

                System.out.println("Se ha iniciado sesión como un médico");
                rDispatcher = req.getRequestDispatcher("medico.html");

            }

        }

        // rDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("prueba.html");
    }

}