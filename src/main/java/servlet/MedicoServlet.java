package servlet;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MedicoServlet", urlPatterns = { "/medico" })

public class MedicoServlet extends HttpServlet implements Servlet, Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);         
        if (session == null) {
            response.sendRedirect("index.html"); // No logged-in user found, so redirect to login page.
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
        } else {
            chain.doFilter(req, res);  
        }

    }

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
            String historial = Singleton.getHistorialExamenesMed(usuarioIngresado);
            req.setAttribute("examenesmed", historial);
            System.out.println("Se ha oprimido perfil");
            rDispatcher = req.getRequestDispatcher("perfilm.jsp");

        }

        if (req.getParameter("botonlogout") != null) {

            System.out.println("Se ha oprimido logout");
            session = req.getSession();
            session.invalidate();
            rDispatcher = req.getRequestDispatcher("login.html");
            //doFilter(req, resp, chain);

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
                    session.setAttribute("nomMedico", nombreUsuario);
                    req.setAttribute("nomMed", nombreUsuario);
                    req.setAttribute("nomPaciente", nombrePaciente);
                    String listaTipos = Singleton.getListaTipos();
                    req.setAttribute("listaTipos", listaTipos);
                    rDispatcher = req.getRequestDispatcher("generarorden.jsp");

                } else {
                    String str = Singleton.getPacientesMed();
                    req.setAttribute("listaPacMed", str);
                    req.setAttribute("nameM", nombreUsuario);
                    session.setAttribute("medico", usuarioIngresado);
                    session.setAttribute("medicoNombre", nombreUsuario);
                    System.out.println("El paciente no tiene el estado ESPERANDO CITA");
                    rDispatcher = req.getRequestDispatcher("medico.jsp");
                }

            }

            if (req.getParameter(botonResultados) != null) {

                String id = req.getParameter(nameid);
                String nombrePaciente = Singleton.getNombrePaciente(id);
                System.out.println("Se ha oprimido el botón resultados " + i + " con ID: " + id);
                String estado = Singleton.getEstadoP(id);
                if (estado.equals("ESPERANDO RESULTADOS")) {
                    Singleton.connectarBD();
                    int idEx = Singleton.getIdExamenPaciente(id);
                    String listaResultados = Singleton.getListaTiposExamen(idEx);
                    req.setAttribute("listaresultados", listaResultados);
                    req.setAttribute("nomMed", nombreUsuario);
                    req.setAttribute("nomPaciente", nombrePaciente);
                    session.setAttribute("medico", usuarioIngresado);
                    session.setAttribute("medicoNombre", nombreUsuario);
                    session.setAttribute("idPac", id);
                    session.setAttribute("examen", idEx);
                    System.out.println("si puede presionar este botón: Generar Resultados");
                    Singleton.cerrarConexion();
                    rDispatcher = req.getRequestDispatcher("ingresores.jsp");

                } else {
                    String str = Singleton.getPacientesMed();
                    req.setAttribute("listaPacMed", str);
                    req.setAttribute("nameM", nombreUsuario);
                    session.setAttribute("medico", usuarioIngresado);
                    session.setAttribute("medicoNombre", nombreUsuario);
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

    @Override
    public boolean accept(Object entry) throws IOException {
        // TODO Auto-generated method stub
        return false;
    }

}
