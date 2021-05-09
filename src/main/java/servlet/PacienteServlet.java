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

@WebServlet(name = "PacienteServlet", urlPatterns = { "/paciente" })

public class PacienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("paciente").toString();
        String nombreUsuario = session.getAttribute("pacienteNombre").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("paciente.jsp");

        if (req.getParameter("botonagendar") != null) {

            Singleton.cambiarEstadoP(usuarioIngresado, "ESPERANDO CITA");
            req.setAttribute("usuarioLogeado", nombreUsuario);
            System.out.println("Se ha oprimido agendar");
            String barraEstado = Singleton.getBarraEstado(usuarioIngresado);
            req.setAttribute("barraestado", barraEstado);
            rDispatcher = req.getRequestDispatcher("paciente.jsp");
            
        }
        
        if (req.getParameter("botonsolicitar") != null) {

            System.out.println("Se ha oprimido solicitar");
            req.setAttribute("usuarioLogeado", nombreUsuario);
            String barraEstado = Singleton.getBarraEstado(usuarioIngresado);
            req.setAttribute("barraestado", barraEstado);
            rDispatcher = req.getRequestDispatcher("ordenpac.jsp");

        }

        if (req.getParameter("botonresultados") != null) {

            System.out.println("Se ha oprimido resultados");
            req.setAttribute("usuarioLogeado", nombreUsuario);
            String barraEstado = Singleton.getBarraEstado(usuarioIngresado);
            req.setAttribute("barraestado", barraEstado);
            req.setAttribute("nombrePac", nombreUsuario);
            rDispatcher = req.getRequestDispatcher("paciente.jsp");

        }

        if (req.getParameter("botonperfilpac") != null) {

            session = req.getSession();
            session.setAttribute("pacienteNombre", nombreUsuario);
            session.setAttribute("paciente", usuarioIngresado);
            req.setAttribute("usuarioLogeado", nombreUsuario);
            System.out.println("Se ha oprimido perfil");
            String info = Singleton.getInfoPacientes(usuarioIngresado);
            req.setAttribute("infopac", info);
            rDispatcher = req.getRequestDispatcher("perfilp.jsp");

        }

        if (req.getParameter("botonlogoutpac") != null) {

            System.out.println("Se ha oprimido logout");
            session = req.getSession();  
            session.invalidate(); 
            rDispatcher = req.getRequestDispatcher("login.html");

        }

        if (req.getParameter("ingresarorden") != null) {

            Singleton.connectarBD();
            int idExImp = Integer.parseInt(req.getParameter("ingnumorden"));
            int idEX = Singleton.getIdExamenPaciente(usuarioIngresado);

            if (idExImp == idEX){

                Singleton.cambiarEstadoP(usuarioIngresado, "ESPERANDO RESULTADOS");
                Singleton.setFechaRealizacion(idEX);
                String barraEstado = Singleton.getBarraEstado(usuarioIngresado);
                req.setAttribute("barraestado", barraEstado);

            }
            Singleton.cerrarConexion();
            session.setAttribute("pacienteNombre", nombreUsuario);
            session.setAttribute("paciente", usuarioIngresado);
            rDispatcher = req.getRequestDispatcher("paciente.jsp");
        }

        if(req.getParameter("botonvolver") != null){

            session = req.getSession();
            session.setAttribute("pacienteNombre", nombreUsuario);
            session.setAttribute("paciente", usuarioIngresado);
            req.setAttribute("usuarioLogeado", nombreUsuario);
            String barraEstado = Singleton.getBarraEstado(usuarioIngresado);
            req.setAttribute("barraestado", barraEstado);
            rDispatcher = req.getRequestDispatcher("paciente.jsp");

        }

        rDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

}
