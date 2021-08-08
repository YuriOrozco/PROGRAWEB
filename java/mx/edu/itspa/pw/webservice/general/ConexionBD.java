package mx.edu.itspa.pw.webservice.general;
import java.sql.DriverManager;
import java.sql.Connection;                     
import java.sql.SQLException;

public class ConexionBD {
	private static final String HOST="localhost";
    private static final String PORT="3306";
    private static final String USER="root";
    private static final String PASSW="";
    private static final String BD="prueba";
    // AHORA VAMOS A CREAR UNA FUNCION MEJOR
    public static Connection obtenerConexion() {//throws SQLException
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+BD, USER, PASSW);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        return conn;
    }
}