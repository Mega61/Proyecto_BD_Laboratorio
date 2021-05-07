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

@WebServlet(name = "MedicoPerfilServlet", urlPatterns = { "/perfilMedico" })

public class MedicoPerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String  telefonom, contrasegna, consultorioac, consultorionu  = "";

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("medico").toString();
        String nombreUsuario = session.getAttribute("medicoNombre").toString();
        RequestDispatcher rDispatcher = req.getRequestDispatcher("perfilm.jsp");

        session = req.getSession();
        session.setAttribute("medicoNombre", nombreUsuario);
        session.setAttribute("medico", usuarioIngresado);
        
        consultorionu = req.getParameter("selectconsmed");
        telefonom = req.getParameter("cambiarTelefonoM");
        contrasegna = req.getParameter("cambiarContrasegnaM");
        consultorioac = req.getParameter("consumed");

        if (req.getParameter("botcammed") != null) {
            

            Singleton.connectarBD();

            if (!consultorionu.equals(consultorioac)){

                Singleton.cambiarColumna("medico", "consultorio_medico", consultorionu, "id_medico", usuarioIngresado);

            }

            if (!telefonom.equals("")){

                Singleton.cambiarColumna("medico", "telefono_medico", telefonom, "id_medico", usuarioIngresado);

            }

            if (!contrasegna.equals("")){

                Singleton.cambiarColumna("medico", "contrasegna_medico", contrasegna, "id_medico",usuarioIngresado);

            }

            String info = Singleton.getInfoMedico(usuarioIngresado);
            req.setAttribute("infomed", info);
            Singleton.cerrarConexion();
            rDispatcher =  req.getRequestDispatcher("perfilm.jsp");
            
        }

        if (req.getParameter("botonlogout") != null) {

            System.out.println("Se ha oprimido logout");
            session = req.getSession();  
            session.invalidate(); 
            rDispatcher = req.getRequestDispatcher("login.html");

        }

        if(req.getParameter("botonvolver") != null){

            session = req.getSession();
            session.setAttribute("medicoNombre", nombreUsuario);
            session.setAttribute("medico", usuarioIngresado);
            req.setAttribute("nameM", nombreUsuario);
            rDispatcher = req.getRequestDispatcher("medico.jsp");

        }
    
        rDispatcher.forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}