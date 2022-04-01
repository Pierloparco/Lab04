package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	/* Mi serve per collegarmi, andare a lavorare sul Database, MOLTA attenzione a mettere la MIA PASSWORD
	 * dopodichè vado a lavorare sul CorsoDAO...*/
	
	// check user e password
	static private final String jdbcUrl = "jdbc:mysql://localhost/iscritticorsi?user=root&password=Programmazione2022";

	public static Connection getConnection() {

		try {
				Connection connection = DriverManager.getConnection(jdbcUrl);
				return connection;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException("Cannot get a connection " + jdbcUrl, e);
		}
	}

}
