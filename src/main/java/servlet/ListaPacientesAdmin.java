package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListaPacientesAdmin", urlPatterns = { "/pacientesadmin" })

public class ListaPacientesAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int cantidadPaciente = Singleton.getCantidadPacientes();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("listapac.jsp");
        HttpSession session = req.getSession();
        String listaPac = "";
        String usuarioIngresado = session.getAttribute("admin").toString();

        if (req.getParameter("buspacb") != null) {

            int busqueda = Integer.parseInt(req.getParameter("buspac"));
            listaPac = Singleton.buscarPaciente(busqueda);

            req.setAttribute("listapac", listaPac);
            req.setAttribute("admin", usuarioIngresado);

            rDispatcher = req.getRequestDispatcher("listapac.jsp");

        }

        for (int i = 0; i < cantidadPaciente; i++) {

            String botonEditar = "botoneditarpac" + i;
            String pelicula = "idpac" + i;
            

            if (req.getParameter(botonEditar) != null) {

                int idPaciente = Integer.parseInt(req.getParameter(pelicula));

                String lista = Singleton.getInfoPacientes(idPaciente + "");
                req.setAttribute("infopac", lista);
                session.setAttribute("paciente", idPaciente);
                req.setAttribute("admin", usuarioIngresado);
                listaPac = Singleton.getListaPacientes();
                req.setAttribute("listapac", listaPac);
                rDispatcher = req.getRequestDispatcher("editarp.jsp");

            }

        }

        
        req.setAttribute("admin", usuarioIngresado);
        rDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
