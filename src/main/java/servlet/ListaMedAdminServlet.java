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

@WebServlet(name = "ListaMedAdminServlet", urlPatterns = { "/listamedicosadmin" })

public class ListaMedAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("admin").toString();

        RequestDispatcher rDispatcher = req.getRequestDispatcher("admin.html");

        if(req.getParameter("botRegMed") != null){

            rDispatcher = req.getRequestDispatcher("registromed.html");
            
        }

        rDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}