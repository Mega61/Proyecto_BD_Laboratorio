package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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
        ArrayList<String> idTipos = new ArrayList<String>();
        ArrayList<String> criterios = new ArrayList<String>();
        String str = "";
        String pelicula2 = "";
        int pelicula = 1;
        int pelicula3 = 0;

        RequestDispatcher rDispatcher = req.getRequestDispatcher("medico.jsp");

        if (req.getParameter("generaror") != null) {

            Singleton.connectarBD();
            tipos = Singleton.getTiposExamen(idExamen);
            idTipos = Singleton.getIdTiposExamen(idExamen);
            String tipoActual = "";
            String tipoNuevo = "";

            for (int i = 0; i < tipos.size(); i++) {

                tipoActual = tipos.get(i);
                PreparedStatement statement = null;
                criterios = Singleton.getCriterios(idTipos.get(i), statement);

                if(tipoActual.equals(tipoNuevo) || i == 0){

                    pelicula2 = tipoActual+pelicula;
                    pelicula3 = pelicula-1;
                    str += "--"+pelicula2+"-"+req.getParameter(pelicula2)+"--"+idTipos.get(i)+"\n";
                    Singleton.updateResultadosExamen(req.getParameter(pelicula2), idExamen, idTipos.get(i), criterios.get(pelicula3));
                    pelicula++;

                } else if (i != 0){

                    pelicula = 1;
                    pelicula3 = pelicula-1;
                    pelicula2 = tipoActual+pelicula;
                    str += "--"+pelicula2+"-"+req.getParameter(pelicula2)+"--"+idTipos.get(i)+"\n";
                    Singleton.updateResultadosExamen(req.getParameter(pelicula2), idExamen, idTipos.get(i), criterios.get(pelicula3));
                    pelicula++;

                }
                    
                tipoNuevo = tipos.get(i);

            }
        
           //rDispatcher = req.getRequestDispatcher("medico.jsp");
           System.out.println(str);
           Singleton.cerrarConexion();
        }

       

        //rDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
