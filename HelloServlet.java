package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "HelloServlet", 
        urlPatterns = {"/login"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Singleton singleton = Singleton.getSingleton();
        
        /*String correo = "";
        String contrasegna = "";*/
        //Singleton.connectarBD();

        RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");

        if (req.getParameter("botonresultados") != null || req.getParameter("botoningresar") != null){
            
            System.out.println("Se ha oprimido alguno de los botones");
            
        }

        /*if(req.getParameter("botonlogin") != null){
            correo = req.getParameter("user");
            contrasegna = req.getParameter("pass");

            Singleton.insertData(correo, contrasegna);
            //traerData();
        }*/
        
        
        
        rDispatcher.forward(req, resp);

    }

    

    

    
    
}
