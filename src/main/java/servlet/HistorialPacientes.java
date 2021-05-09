package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HistorialPacientes", urlPatterns = { "/historialp" })

public class HistorialPacientes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession();

        String usuarioIngresado = session.getAttribute("paciente").toString();
        String nombreUsuario = session.getAttribute("pacienteNombre").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("examenpac.jsp");

        int iteraciones = Singleton.getCantidadExamenesPaciente(usuarioIngresado);
        String nomBoton = "";
        String nomHidden = "";
        int idEx = 0;

        for (int i = 0; i < iteraciones; i++) {
            
            nomBoton = "verres"+i;
            nomHidden = "idexamen"+i;
            
            if(req.getParameter(nomBoton) != null){
                idEx = Integer.parseInt(req.getParameter(nomHidden).toString());
                System.out.println("adentro: " + idEx);
            }
        }

        System.out.println("afuera: " + idEx);

        String ruta = "src=\"pdf/E"+idEx+".pdf\"";
        req.setAttribute("numeroexamen", idEx);
        req.setAttribute("rutaexamen", ruta);
        req.setAttribute("pacienteNombre", nombreUsuario);
        
        rDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

}