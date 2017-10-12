package dao;

import dbUtil.UserTblUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginDAO {
	//checks if user email address exists in the user table and returns a true boolean if yes, and a false boolean if no
	public static boolean checkUser(String username){
		//establish connection with database
		Connection dBconn = UserTblUtil.getConnection();// This is calling the connection from another class rather than declarting connection here
		boolean userExists=false; // boolean to determine whether user exists or not, default as false
		//String out = "-";
		//Create a Statement Object
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //IMPORTANT LINE, IF YOU DO NOT INCLUDE THIS WITH ANOTHER CATCH STATEMENT YOUR CODE WILL NOT RUN
			Statement st=dBconn.createStatement();
			//construct query to select the row of input email address
			//String sql="select emailAddr from BookUsers where emailAddr='"+username+"'";
			String sql="select emailAddr from BookUsers where emailAddr = '" + username + "'";
			//query string and store in results list
			ResultSet rs=st.executeQuery(sql);
			
			//if anything is stored into results set, then the user exists 
			while (rs.next())
			{
				//out = out + " emailAddr="+rs.getString("emailAddr");
				userExists=true; //set user exists boolean to true
			}	
			if (!userExists)
			System.out.println("Username not found"); //print out that username is not found 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//show errors
			//out = out + " " + e.toString();
		} catch(Exception e2){
			e2.printStackTrace(); //show errors
			//out = out + " " + e2.toString();
		}
		//return out;
		return userExists;

	}
	
	public static boolean checkPW(String username, String password){
		//boolean to store whether password is correct or not
		boolean passwordTrue = false;
		//string to store sql password
		String pass = "";
		//create new connection
		Connection dbConn = UserTblUtil.getConnection();
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //IMPORTANT LINE, IF YOU DO NOT INCLUDE THIS WITH ANOTHER CATCH STATEMENT YOUR CODE WILL NOT RUN
			
		}catch(Exception e2){
			e2.printStackTrace();
		}
		try{
			String sql  = "select password from BookUsers where emailAddr = '" + username + "'";
			Statement st=dbConn.createStatement();
			//create result set for querying password
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				pass = rs.getString("Password");
			}
			//if pass = password, set true to output
			if(pass.equals(password)){
				passwordTrue = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		//return boolean
		return passwordTrue;
		//return pass used for testing ;
	}
	
	public static String getName(String username){
		Connection dbConn = UserTblUtil.getConnection();
		String name = "";
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //IMPORTANT LINE, IF YOU DO NOT INCLUDE THIS WITH ANOTHER CATCH STATEMENT YOUR CODE WILL NOT RUN
		}catch(Exception e2){
			e2.printStackTrace();
		}
		
		try{
			//string to query
			String sql  = "select firstName from BookUsers where emailAddr = '" + username + "'";
			//create result set for querying password
			Statement st=dbConn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				name = rs.getString("firstName");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return name;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//checkPW("ivan_yohuno@hotmail.co.uk", "test");
		//checkUser("ivan_yohuno@hotmail.co.uk");
	}

}
