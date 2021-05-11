package servlet;

import java.io.IOException;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        //String usuarioIngresado = session.getAttribute("admin").toString();

        RequestDispatcher rDispatcher = req.getRequestDispatcher("listamed.jsp");

        if (req.getParameter("botonagregarmed") != null) {

            rDispatcher = req.getRequestDispatcher("registromed.html");

        }

        if (req.getParameter("buscarmed") != null) {

            String busquedaMed = req.getParameter("busmed");

            String listaMedicos = Singleton.buscarMedico(busquedaMed);

            req.setAttribute("listamed", listaMedicos);
            req.setAttribute("admin", "admin");

        }

        int cantidadMedicos = Singleton.getCantidadMedicos();
        String nombrebot = "";
        String documentoMedico = "";
        for (int i = 0; i < cantidadMedicos; i++) {

            nombrebot = "botoneditarmed"+i;
            documentoMedico = "idmed" + i;

            if(req.getParameter(nombrebot) != null){

                String listaedit = Singleton.getInfoMedico(req.getParameter(documentoMedico));
                req.setAttribute("infomed", listaedit);
                req.setAttribute("usuarioLogeado", "admin");
                session.setAttribute("medico", req.getParameter(documentoMedico));

                rDispatcher = req.getRequestDispatcher("editarm.jsp");
            }
            
        }

        if (req.getParameter("botonvolver")  != null){

            session = req.getSession();
            session.setAttribute("admin", "admin");
            req.setAttribute("admin", "admin");
            String lista = Singleton.getListaMedicos();
            req.setAttribute("listamed", lista);
            rDispatcher = req.getRequestDispatcher("listamed.jsp");

        }

        if (req.getParameter("botonlogout") != null) {

            System.out.println("Se ha oprimido logout");
            session = req.getSession();  
            session.invalidate(); 
            rDispatcher = req.getRequestDispatcher("index.html");

        }

        rDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}