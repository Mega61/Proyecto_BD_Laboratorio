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
        String nombreUsuario = session.getAttribute("medicoNombre").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("medico.jsp");

        if (req.getParameter("botonperfilm") != null) {

            session = req.getSession();
            session.setAttribute("medico", usuarioIngresado);
            session.setAttribute("medicoNombre", nombreUsuario);
            req.setAttribute("nameM", nombreUsuario);
            String info = Singleton.getInfoMedico(usuarioIngresado);
            req.setAttribute("infomed", info);
            System.out.println("Se ha oprimido perfil");
            rDispatcher = req.getRequestDispatcher("perfilm.jsp");

        }

        if (req.getParameter("botonlogout") != null) {

            System.out.println("Se ha oprimido logout");
            session = req.getSession();
            session.invalidate();
            rDispatcher = req.getRequestDispatcher("login.html");

        }

        int cantidad = Singleton.getCantidadPacientesParaMedico();
        System.out.println(cantidad);

        for (int i = 0; i < cantidad; i++) {

            String botonOrden = "botongenerar" + i;
            String botonResultados = "botonresultadosmed" + i;
            String nameid = "nombre" + i;

            if (req.getParameter(botonOrden) != null) {

                String id = req.getParameter(nameid);
                System.out.println("Se ha oprimido el botón generar " + i + " con ID: " + id);
                String estado = Singleton.getEstadoP(id);
                String nombrePaciente = Singleton.getNombrePaciente(id);
                System.out.println(estado);

                if (estado.equals("ESPERANDO CITA")) {

                    System.out.println("si puede presionar este botón: generar orden de laboratorio");
                    session = req.getSession();
                    session.setAttribute("idPaciente", id);
                    session.setAttribute("idMedico", usuarioIngresado);
                    req.setAttribute("nomMed", nombreUsuario);
                    req.setAttribute("nomPaciente", nombrePaciente);
                    String listaTipos = Singleton.getListaTipos();
                    req.setAttribute("listaTipos", listaTipos);
                    rDispatcher = req.getRequestDispatcher("generarorden.jsp");

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
                    rDispatcher = req.getRequestDispatcher("resultadosPaciente.html");

                } else {
                    System.out.println("El paciente no tiene el estado ESPERANDO RESULTADOS");
                    rDispatcher = req.getRequestDispatcher("medico.jsp");
                }
            }

        }

        rDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
