package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PacientePerfilServlet", urlPatterns = { "/perfilPaciente" })

public class PacientePerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String correop, telefonop, contrasegnap, correocep, telefonocep, idcep = "";

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("paciente").toString();
        String nombreUsuario = session.getAttribute("pacienteNombre").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("perfilp.jsp");

        if (req.getParameter("botcambpac") != null) {
            
            session = req.getSession();
            session.setAttribute("pacienteNombre", nombreUsuario);
            session.setAttribute("paciente", usuarioIngresado);
            correop = req.getParameter("cambiarCorreoP");
            telefonop = req.getParameter("cambiarTelefonoP");
            contrasegnap = req.getParameter("cambiarContrasegnaP");
            correocep = req.getParameter("cambiarCorreoCep");
            telefonocep = req.getParameter("cambiarTelefonoCep");
            idcep = req.getParameter("idcepp");

            Singleton.connectarBD();

            if (!correop.equals("")){

                Singleton.cambiarColumna("paciente", "correo_paciente", correop, "id_paciente",usuarioIngresado);

            }

            if (!telefonop.equals("")){

                Singleton.cambiarColumna("paciente", "telefono_paciente", telefonop, "id_paciente",usuarioIngresado);

            }

            if (!contrasegnap.equals("")){

                Singleton.cambiarColumna("paciente", "contrasegna_paciente", contrasegnap, "id_paciente",usuarioIngresado);

            }

            if (!correocep.equals("")){

                Singleton.cambiarColumna("contacto_emergencia_paciente", "correo_contacto_emergencia", correocep, "id_contacto_emergencia",idcep);

            }

            if (!telefonocep.equals("")){

                Singleton.cambiarColumna("contacto_emergencia_paciente", "telefono_contacto_emergencia", telefonocep, "id_contacto_emergencia",idcep);

            }

            String info = Singleton.getInfoPacientes(usuarioIngresado);
            req.setAttribute("infopac", info);
            Singleton.cerrarConexion();
            rDispatcher =  req.getRequestDispatcher("perfilp.jsp");
            
        }

        if (req.getParameter("botcambpacep") != null){

            

        }
    
        rDispatcher.forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}