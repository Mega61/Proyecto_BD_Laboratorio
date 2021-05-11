package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistroMedServlet", urlPatterns = { "/registromed" })

public class RegistroMedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //String usuarioIngresado = session.getAttribute("admin").toString();

        RequestDispatcher rDispatcher = req.getRequestDispatcher("admin.jsp");
        Singleton singleton = Singleton.getSingleton();

        String nombre, genero, correo, telefono, especialidad1, especialidad2, especialidad3, contrasegna ="";
        int edad, consultorio = 0;
        
        if (req.getParameter("botregistermedad") != null){

            nombre = req.getParameter("nomMed");
            edad = Integer.parseInt(req.getParameter("edadMed"));
            genero = req.getParameter("genMed");
            correo = req.getParameter("correoMed");
            telefono = req.getParameter("telMed");
            consultorio = Integer.parseInt(req.getParameter("conMed"));
            especialidad1 = req.getParameter("esp1Med");
            especialidad2 = req.getParameter("esp2Med");
            especialidad3 = req.getParameter("esp3Med");
            contrasegna = req.getParameter("confirmP");
            
            ArrayList<String> especialidades = new ArrayList<String>();
            especialidades.add(especialidad1);
            especialidades.add(especialidad2);
            especialidades.add(especialidad3);

            singleton.ingresarMedico(nombre, consultorio, correo, telefono, genero, edad, contrasegna, especialidades);
            System.out.println(nombre + " " +consultorio +" "+ correo +" "+ telefono +" "+ genero +" "+ edad +" "+ contrasegna +" "+ especialidades);

        }
       
        rDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}