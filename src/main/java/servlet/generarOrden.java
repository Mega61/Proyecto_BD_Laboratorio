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
public class generarOrden extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        int idPaciente = Integer.parseInt(session.getAttribute("idPaciente").toString());
        String idPacienteS = session.getAttribute("idPaciente").toString();
        String idMedico = session.getAttribute("idMedico").toString();
        String nombreMedico = session.getAttribute("nomMedico").toString();

        RequestDispatcher rDispatcher = req.getRequestDispatcher("generarorden.jsp");

        if (req.getParameter("generarOrden") != null) {

            String diagnostico = req.getParameter("diagnosticoGo");
            String tipo0 = req.getParameter("tiposexamenes0");
            String tipo1 = req.getParameter("tiposexamenes1");
            String tipo2 = req.getParameter("tiposexamenes2");
            String tipo3 = req.getParameter("tiposexamenes3");
            String tipo4 = req.getParameter("tiposexamenes4");

            Singleton.crearExamen(idMedico, idPaciente, diagnostico, tipo0, tipo1, tipo2, tipo3, tipo4);
            Singleton.cambiarEstadoP(idPacienteS, "ESPERANDO REALIZACION");
            System.out.println(diagnostico + " " + tipo0 + " " + tipo1 + " " + tipo2 + " " + tipo3 + " " + tipo4);
            session = req.getSession();
            session.setAttribute("medicoNombre", nombreMedico);
            session.setAttribute("medico", idMedico);
            req.setAttribute("nameM", nombreMedico);
            String str = Singleton.getPacientesMed();
            req.setAttribute("listaPacMed", str);
            rDispatcher = req.getRequestDispatcher("medico.jsp");
        }

        if (req.getParameter("botonvolver") != null){

            session = req.getSession();
            session.setAttribute("medicoNombre", nombreMedico);
            session.setAttribute("medico", idMedico);
            req.setAttribute("nameM", nombreMedico);
            String str = Singleton.getPacientesMed();
            req.setAttribute("listaPacMed", str);
            rDispatcher = req.getRequestDispatcher("medico.jsp");

        }

        if (req.getParameter("botonlogout") != null) {

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
