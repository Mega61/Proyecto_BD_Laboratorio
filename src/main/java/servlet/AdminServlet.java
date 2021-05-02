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

@WebServlet(name = "AdminServlet", urlPatterns = { "/admin" })

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("admin").toString();
        System.out.println("el usuario es: " + usuarioIngresado);

        // request.setAttribute("Usuario", usuarioIngresado);

        PrintWriter out = resp.getWriter();
        out.println("El usuario es:" + usuarioIngresado);

        RequestDispatcher rDispatcher = req.getRequestDispatcher("admin.html");

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

        rDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
