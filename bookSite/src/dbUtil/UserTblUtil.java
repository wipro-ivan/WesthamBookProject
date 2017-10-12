package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserTblUtil {

	public static Connection getConnection(){
		String status = "Not Connected";
		//details of database
		String uname = "scott";
		String url = "jdbc:oracle:thin:@localhost:1522:orcl5";
		String pwd = "tiger";
		Connection dbConn = null;
			
	   //try to connect to database
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
		}catch(ClassNotFoundException e2){
			e2.printStackTrace();
		}
		
		try{
			dbConn = DriverManager.getConnection(url,uname,pwd);
			status = "Connected";
			System.out.println("Connected");
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		//return dbConn;
		return dbConn;
	}
	
}
