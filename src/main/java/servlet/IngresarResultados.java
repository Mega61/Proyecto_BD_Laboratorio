package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "IngresarResultados", urlPatterns = { "/ingresarResultados" })
public class IngresarResultados extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        int idPaciente = Integer.parseInt(session.getAttribute("idPac").toString());
        String idPacienteS = session.getAttribute("idPac").toString();
        String idMedico = session.getAttribute("medico").toString();
        String nombreMedico = session.getAttribute("medicoNombre").toString();
        int idExamen = Integer.parseInt(session.getAttribute("examen").toString());
        ArrayList<String> tipos = new ArrayList<String>();
        String s

        RequestDispatcher rDispatcher = req.getRequestDispatcher("generarorden.jsp");

        if (req.getParameter("generaror") != null) {

            tipos = Singleton.getTiposExamen(idExamen);

            for (int i = 0; i < tipos.size(); i++) {
                
                str

            }
           
        }

       

        rDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
