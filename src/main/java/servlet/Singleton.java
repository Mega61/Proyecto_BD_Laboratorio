package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Singleton {

    private static Singleton instancia;

    private static Connection connSQL = null;
    private static PreparedStatement statement = null;

        //LocalHost
    /*private static String userBD = "root";
    private static String passBD = "1234";
    private static String urlBD = "jdbc:mysql://localhost:3306/prueba_local";
    */

    //Heroku

    private static String userBD = "be0469cbf4e9eb";
    private static String passBD = "cdb0227e";
    private static String urlBD = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_fdfec516ca01a53";

    //mysql://be0469cbf4e9eb:cdb0227e@us-cdbr-east-03.cleardb.com/heroku_fdfec516ca01a53?reconnect=true

    static Singleton getSingleton(){
        if(instancia == null){
            synchronized(Singleton.class){
                if(instancia == null){
                    instancia = new Singleton();
                }
            }
        }

        return instancia;
    }
    
    public static void connectarBD(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("se ha encontrado el driver");
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            System.out.println("No se ha encontrado el driver");
        }

        try {
            connSQL = DriverManager.getConnection(urlBD, userBD, passBD);

            if(connSQL != null){
                System.out.println("Se ha logrado la conexión con la BD");
            }else{
                System.out.println("No se ha logrado la conexión con la BD");
            }
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
            System.out.println("Conexión fallida");
        }
    }

    public static void traerData(){

        try {
            String queryTraer = "SELECT * FROM usuarios";

            statement = connSQL.prepareStatement(queryTraer);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String correo = rs.getString("correo");
                String contra = rs.getString("contrasegna");

                System.out.println(id + ", " + correo + ", " + contra);
            }
            
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void insertData(String correo, String password){

        try {
            String queryInsertar = "INSERT INTO usuarios (correo, contrasegna) VALUES (?,?)";

            statement = connSQL.prepareStatement(queryInsertar);
            statement.setString(1, correo);
            statement.setString(2, password);


            statement.executeUpdate();
            System.out.println(correo + " Se ha añadido correctamente");
        } catch (SQLException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
