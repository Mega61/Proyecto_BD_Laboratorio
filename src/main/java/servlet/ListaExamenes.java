package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListaExaAdminServlet", urlPatterns = { "/listaexamenesadmin" })

public class ListaExamenes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        //String usuarioIngresado = session.getAttribute("admin").toString();

        RequestDispatcher rDispatcher = req.getRequestDispatcher(".jsp");

        if (req.getParameter("buscarexamen") != null) {

            int busquedaExamen = Integer.parseInt(req.getParameter("idbuscado"));
            String listaexamenes = Singleton.buscarExamen(busquedaExamen);
            req.setAttribute("listaexamenes", listaexamenes);
            req.setAttribute("admin", "admin");
            rDispatcher = req.getRequestDispatcher("listaexa.jsp");

        }

        int cantidadExamene = Singleton.getCantidadExamenes();
        String nombrebot = "";
        String nomExamen = "";
        int idExamen = 0;
        for (int i = 0; i < cantidadExamene; i++) {

            nombrebot = "verres" + i;
            nomExamen = "idexamen" + i;

            if (req.getParameter(nombrebot) != null) {

                idExamen = Integer.parseInt(req.getParameter(nomExamen));
                rDispatcher = req.getRequestDispatcher("vistaexamenadmin.jsp");
            }

        }

        if (req.getParameter("botonvolver")  != null){

            session = req.getSession();
            session.setAttribute("admin", "admin");
            req.setAttribute("admin", "admin");
            String lista = Singleton.getHistorialExamenes();
            req.setAttribute("listaexamenes", lista);
            rDispatcher = req.getRequestDispatcher("listaexa.jsp");

        }

        if (req.getParameter("botonlogout") != null) {

            System.out.println("Se ha oprimido logout");
            session = req.getSession();  
            session.invalidate(); 
            rDispatcher = req.getRequestDispatcher("index.html");

        }

        String ruta = "src=\"pdf/E"+idExamen+".pdf\"";
        req.setAttribute("admin", "admin");
        req.setAttribute("numeroexamen", idExamen);
        req.setAttribute("rutaexamen", ruta);

        rDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}