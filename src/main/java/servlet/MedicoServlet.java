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

@WebServlet(name = "MedicoServlet", urlPatterns = { "/medico" })

public class MedicoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("medico").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("registro.html");

        }

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("registro.html");

        }

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("registro.html");

        }

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("registro.html");

        }

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            session = req.getSession();
            session.invalidate();
            rDispatcher = req.getRequestDispatcher("login.html");

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
