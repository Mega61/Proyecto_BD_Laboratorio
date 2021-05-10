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

@WebServlet(name = "AdminServlet", urlPatterns = { "/admin" })

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        String usuarioIngresado = session.getAttribute("admin").toString();
        System.out.println("el usuario es: " + usuarioIngresado);

        // request.setAttribute("Usuario", usuarioIngresado);

        PrintWriter out = resp.getWriter();
        out.println("El usuario es:" + usuarioIngresado);

        RequestDispatcher rDispatcher = req.getRequestDispatcher("admin.html");

        if (req.getParameter("botonlistapac") != null) {

            System.out.println("Se ha oprimido lista pacientes");
            String lista = Singleton.getListaPacientes();
            req.setAttribute("listapac", lista);
            req.setAttribute("admin", usuarioIngresado);
            rDispatcher = req.getRequestDispatcher("listapac.jsp");

        }
        
        if (req.getParameter("botonlistamed") != null) {

            System.out.println("Se ha oprimido lista medicos");
            String lista = Singleton.getListaMedicos();
            req.setAttribute("listamed", lista);
            req.setAttribute("admin", usuarioIngresado);
            rDispatcher = req.getRequestDispatcher("listamed.jsp");

        }

        if (req.getParameter("botonlistaexa") != null) {

            System.out.println("Se ha oprimido lista examenes");
            String lista = Singleton.getHistorialExamenes();
            req.setAttribute("listaexamenes", lista);
            rDispatcher = req.getRequestDispatcher("listaexa.jsp");

        }

        if (req.getParameter("botonvolver")  != null){

            session = req.getSession();
            session.setAttribute("admin", usuarioIngresado);
            req.setAttribute("admin", usuarioIngresado);
            rDispatcher = req.getRequestDispatcher("admin.jsp");

        }

        if (req.getParameter("logoutAdmin") != null) {

            System.out.println("Se ha oprimido logout");
            session = req.getSession();  
            session.invalidate(); 
            rDispatcher = req.getRequestDispatcher("index.html");

        }

        rDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
