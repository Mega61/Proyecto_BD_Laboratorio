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

        String identificacion, contrasegna = "";
        Singleton singleton = Singleton.getSingleton();

        RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");

        if (req.getParameter("botonRegister") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("registro.html");

        }

        if (req.getParameter("botoniniciars") != null) {

            identificacion = req.getParameter("docP");
            contrasegna = req.getParameter("conP");
            char prueba = identificacion.charAt(0);

            System.out.println("Se ha oprimido Iniciar Sesión");

            if (identificacion.equals("admin") && contrasegna.equals("1234")) {
                System.out.println("Se ha iniciado sesión como un admin");
                System.out.println(identificacion);
                System.out.println(contrasegna);
                HttpSession sessionAdmin = req.getSession();
                sessionAdmin.setAttribute("admin", identificacion);
                sessionAdmin.setMaxInactiveInterval(30 * 60);
                // Cookie userName = new Cookie("admin", identificacion);
                // userName.setMaxAge(30*60);
                // resp.addCookie(userName);

                /*
                 * FormContainer container = new FormContainer(identificacion);
                 * System.out.println(container.getValor());
                 */

                req.setAttribute("usuarioLogeado", identificacion);

                // resp.sendRedirect("prueba.jsp");
                rDispatcher = req.getRequestDispatcher("admin.jsp");
            } else if (Character.isDigit(prueba)) {

                System.out.println("Se ha iniciado sesión como un paciente");
                Boolean pruebaPac = Singleton.loginPaciente(identificacion, contrasegna);

                if (pruebaPac) {
                    HttpSession sessionAdmin = req.getSession();
                    sessionAdmin.setAttribute("paciente", identificacion);
                    String nombreP = Singleton.getNombrePaciente(identificacion);
                    req.setAttribute("usuarioLogeado", nombreP);
                    sessionAdmin.setAttribute("pacienteNombre", nombreP);
                    rDispatcher = req.getRequestDispatcher("paciente.jsp");
                }else{
                    System.out.println("pruebapac es falso");
                }

            } else if (!Character.isDigit(prueba)) {

                System.out.println("Se ha iniciado sesión como un médico");
                HttpSession sessionAdmin = req.getSession();
                sessionAdmin.setAttribute("medico", identificacion);
                String str = Singleton.getPacientesMed();
                req.setAttribute("listaPacMed", str);
                rDispatcher = req.getRequestDispatcher("medico.html");

            } else {

                System.out.println("Credenciales incorrectas");
                rDispatcher = req.getRequestDispatcher("login.html");

            }

        }

        rDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
