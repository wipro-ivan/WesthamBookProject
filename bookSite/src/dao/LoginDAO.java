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
		//Create a Statement Object
		try {
			Statement st=dBconn.createStatement();
			//construct query to select the row of input email address
			String sql="select * from BookUsers where emailAddr='"+username+"'";
			//query string and store in results list
			ResultSet rs=st.executeQuery(sql);
			
			//if anything is stored into results set, then the user exists 
			while (rs.next())
			{
				userExists=true; //set user exists boolean to true
				//System.out.println("emailAddr="+rs.getString("emailAddr"));
			}	
			if (!userExists)
			System.out.println("Username not found"); //print out that username is not found 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			String sql  = "select password from BookUsers where emailAddr = '" + username + "'";
			
			//create result set for querying password
			ResultSet rs = dbConn.createStatement().executeQuery(sql);
			while(rs.next()){
				pass = rs.getString(1);
			}
			//if pass = password, set true to output
			if(pass.equals(password)){
				passwordTrue = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		//return boolean
		System.out.println(passwordTrue);
		return passwordTrue;
	}
	
	public static String getName(String username){
		Connection dbConn = UserTblUtil.getConnection();
		String name = "";
		try{
			String sql  = "select firstName from BookUsers where emailAddr = '" + username + "'";
			
			//create result set for querying password
			ResultSet rs = dbConn.createStatement().executeQuery(sql);
			while(rs.next()){
				name = rs.getString(1);
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
