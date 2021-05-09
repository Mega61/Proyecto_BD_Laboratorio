package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaPacientesAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int cantidadPaciente = Singleton.getCantidadPacientes();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("listapac.jsp");

        for (int i = 0; i < cantidadPaciente; i++) {

            String botonEliminar = "botoneliminarpac" + i;
            String botonEditar = "botoneditarpac" + i;
            int idPaciente = 0;

            if(req.getParameter(botonEditar) != null){

            }


            if(req.getParameter(botonEliminar) != null){

                Singleton.eliminarPaciente(idPaciente);

            }

        }

        
        

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
