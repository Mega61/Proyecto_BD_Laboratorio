package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GenerarOrdenServlet", urlPatterns = { "/generarOrden" })
public class generarOrden extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        String idPaciente = session.getAttribute("idPaciente").toString();
        String idMedico = session.getAttribute("idMedico").toString();

        RequestDispatcher rDispatcher = req.getRequestDispatcher("generarorden.jsp");

        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    
}
