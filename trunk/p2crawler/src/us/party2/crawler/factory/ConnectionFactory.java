package us.party2.crawler.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			//usa
			//return (Connection) DriverManager.getConnection("jdbc:postgresql://209.62.12.130:5432/usa?user=elenice&password=elenice_2011");

			//postos
			return (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/P2Us?user=juliano&password=regor289c");
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}
}
