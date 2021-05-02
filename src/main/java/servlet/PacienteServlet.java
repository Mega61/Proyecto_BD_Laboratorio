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

@WebServlet(name = "PacienteServlet", urlPatterns = { "/paciente" })

public class PacienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("paciente").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("index.html");

        if (req.getParameter("botonagendar") != null) {

            System.out.println("Se ha oprimido agendar");
            rDispatcher = req.getRequestDispatcher("paciente.jsp");

        }
        
        if (req.getParameter("botonsolicitar") != null) {

            System.out.println("Se ha oprimido solicitar");
            rDispatcher = req.getRequestDispatcher("paciente.jsp");

        }

        if (req.getParameter("botonresultados") != null) {

            System.out.println("Se ha oprimido resultados");
            rDispatcher = req.getRequestDispatcher("paciente.jsp");

        }

        if (req.getParameter("botonperfilpac") != null) {

            System.out.println("Se ha oprimido perfil");
            rDispatcher = req.getRequestDispatcher("paciente.jsp");

        }

        if (req.getParameter("botonlogoutpac") != null) {

            System.out.println("Se ha oprimido logout");
            session = req.getSession();  
            session.invalidate(); 
            rDispatcher = req.getRequestDispatcher("login.html");

        }

        rDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

}
