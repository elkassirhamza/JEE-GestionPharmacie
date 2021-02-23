package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


	public class ConnectDb {
	    private static String jdbcURL = "jdbc:postgresql://localhost:5432/pharmacie?useSSL=false";
	    private static String jdbcUsername = "postgres";
	    private static String jdbcPassword = "root";

	    public static Connection getConnection() {
	        java.sql.Connection connection = null;
	        try {
	            Class.forName("org.postgresql.Driver");
	            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	        } catch (SQLException | ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return connection;
	    }
	}



