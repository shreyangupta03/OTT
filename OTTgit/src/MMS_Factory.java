import java.lang.*;
import java.sql.*;

/*
	Methods to be called in the following order:

	1. activateConnection
	2. 	Any number getDAO calls with any number of database transactions
	3. deactivateConnection
*/
public class MMS_Factory {

	public enum TXN_STATUS {
		COMMIT, ROLLBACK
	};

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/OTTDataBase?characterEncoding=latin1&useConfigs=maxPerformance";
	static final String USER = "root";
	static final String PASS = "Shreyanak1234";
	Connection dbconnection = null;

	// You can add additional DAOs here as needed
	MediaManagementSystem mediaManagementSystem = null;

	boolean activeConnection = false;
	boolean isOwner;

	public MMS_Factory() {
		dbconnection = null;
		activeConnection = false;
		isOwner = true;
	}

	public boolean CurrentUser() {
		return isOwner;
	}

	public void setOwner() {
		isOwner = true;
	}

	public void setUser() {
		isOwner = false;
	}

	public boolean getStatus() {
		return isOwner;
	}

	public void activateConnection() throws Exception {
		if (activeConnection == true)
			throw new Exception("Connection already active");

		System.out.println("Connecting to database...");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbconnection = DriverManager.getConnection(DB_URL, USER, PASS);
			dbconnection.setAutoCommit(false);
			activeConnection = true;

		} catch (ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public MediaManagementSystem getMMS() throws Exception {//user actuve return
		if (activeConnection == false)
			throw new Exception("Connection not activated...");

		if (mediaManagementSystem == null && isOwner)
			mediaManagementSystem = new Owner(dbconnection);
		else
			mediaManagementSystem = new User(dbconnection);

		return mediaManagementSystem;
	}

	public void deactivateConnection(TXN_STATUS txn_status) {
		// Okay to keep deactivating an already deactivated connection
		activeConnection = false;
		if (dbconnection != null) {
			try {
				if (txn_status == TXN_STATUS.COMMIT)
					dbconnection.commit();
				else
					dbconnection.rollback();

				dbconnection.close();
				dbconnection = null;

				// Nullify all DAO objects
				mediaManagementSystem = null;
			} catch (SQLException ex) {
				// handle any errors
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
	}
};
