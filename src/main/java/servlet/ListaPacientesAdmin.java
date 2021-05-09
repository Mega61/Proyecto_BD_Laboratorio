package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;

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

        for (int i = 0; i < cantidadPaciente; i++) {

            String botonEliminar = "botoneliminarpac" + i;
            String botonEditar = "botoneditarpac" + i;
            int idPaciente = Integer.parseInt(req.getParameter("idpac" + i));

            if(req.getParameter(botonEditar) != null){

            }

            if(req.getParameter(botonEliminar) != null){

                Singleton.eliminarPaciente(idPaciente);
                listaPac = Singleton.getListaPacientes();
                req.setAttribute("listapac", listaPac);
                req.setAttribute("admin", usuarioIngresado);
                
                rDispatcher = req.getRequestDispatcher("listapac.jsp");
            }

        }

        listaPac = Singleton.getListaPacientes();
        req.setAttribute("listapac", listaPac);
        req.setAttribute("admin", usuarioIngresado);
        rDispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
