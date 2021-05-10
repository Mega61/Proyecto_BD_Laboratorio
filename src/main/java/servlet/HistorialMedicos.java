package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HistorialMedicos", urlPatterns = { "/historialm" })

public class HistorialMedicos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String usuarioIngresado = session.getAttribute("medico").toString();
        String nombreUsuario = session.getAttribute("medicoNombre").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("historialmed.jsp");

        int iteraciones = Singleton.getCantidadExamenesMed(usuarioIngresado);
        String nomBoton = "";
        String nomHidden = "";
        int idEx = 0;

        if (req.getParameter("buscarexamenfecha") != null) {
            String fechaBuscada = req.getParameter("fechabuscada");

            String listaExamenesBuscados = Singleton.getExamenesMedicoFecha(usuarioIngresado, fechaBuscada);
            req.setAttribute("examenesmed", listaExamenesBuscados);
            String info = Singleton.getInfoMedico(usuarioIngresado);
            req.setAttribute("infomed", info);
            req.setAttribute("nameM", nombreUsuario);
            session.setAttribute("medico", usuarioIngresado);
            session.setAttribute("medicoNombre", nombreUsuario);
            rDispatcher = req.getRequestDispatcher("perfilm.jsp");

        }

        for (int i = 0; i < iteraciones; i++) {

            nomBoton = "verres" + i;
            nomHidden = "idexamen" + i;

            if (req.getParameter(nomBoton) != null) {
                idEx = Integer.parseInt(req.getParameter(nomHidden).toString());
                System.out.println("adentro: " + idEx);
            }
        }

        if (req.getParameter("botonvolver") != null) {

            String info = Singleton.getInfoMedico(usuarioIngresado);
            req.setAttribute("infomed", info);
            String historial = Singleton.getHistorialExamenesMed(usuarioIngresado);
            req.setAttribute("nameM", nombreUsuario);
            req.setAttribute("examenesmed", historial);
            session.setAttribute("medico", usuarioIngresado);
            session.setAttribute("medicoNombre", nombreUsuario);
            rDispatcher = req.getRequestDispatcher("perfilm.jsp");

        }

        if (req.getParameter("botonlogout") != null) {

            System.out.println("Se ha oprimido logout");
            session = req.getSession();
            session.invalidate();
            rDispatcher = req.getRequestDispatcher("login.html");

        }

        System.out.println("afuera: " + idEx);

        String ruta = "src=\"pdf/E" + idEx + ".pdf\"";
        req.setAttribute("nameM", nombreUsuario);
        req.setAttribute("numeroexamen", idEx);
        req.setAttribute("rutaexamen", ruta);

        rDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}