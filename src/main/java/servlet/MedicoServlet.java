package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MedicoServlet", urlPatterns = { "/medico" })

public class MedicoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("medico").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("registro.html");

        }

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("registro.html");

        }

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("registro.html");

        }

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            rDispatcher = req.getRequestDispatcher("registro.html");

        }

        if (req.getParameter("-") != null) {

            System.out.println("Se ha oprimido Register");
            session = req.getSession();
            session.invalidate();
            rDispatcher = req.getRequestDispatcher("login.html");

        }

        int cantidad = Singleton.getCantidadPacientesParaMedico();
        System.out.println(cantidad);

        for (int i = 0; i < cantidad; i++) {

            String botonGenerar = "botongenerar" + i;
            String botonResultados = "botonresultadosmed" + i;
            String nameid = "nombre" + i;

            if (req.getParameter(botonGenerar) != null) {

                String id = req.getParameter(nameid);
                System.out.println("Se ha oprimido el botón generar " + i + " con ID: " + id);
                String estado = Singleton.getEstadoP(id);
                System.out.println(estado);
                if (estado.equals("ESPERANDO CITA")) {

                    System.out.println("si puede presionar este botón: generar orden de laboratorio");
                    //rDispatcher = req.getRequestDispatcher("generarOrden.html");

                } else {
                    System.out.println("El paciente no tiene el estado ESPERANDO CITA");
                }

            }

            if (req.getParameter(botonResultados) != null) {

                String id = req.getParameter(nameid);
                System.out.println("Se ha oprimido el botón resultados " + i + " con ID: " + id);
                String estado = Singleton.getEstadoP(id);
                if (estado.equals("ESPERANDO RESULTADOS")) {

                    System.out.println("si puede presionar este botón: Generar Resultados");
                    // rDispatcher = req.getRequestDispatcher("resultadosPaciente.html");

                } else {
                    System.out.println("El paciente no tiene el estado ESPERANDO RESULTADOS");
                }
            }

        }

        //rDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
