package day9_12_04_22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
		System.out.println("Connected with the database successfully");
	
		Statement st = conn.createStatement();
		ResultSet result = st.executeQuery("select * from studentData");
		
		while(result.next())
		{
			System.out.println("Name: " + result.getString("Name"));
			System.out.println("ID: " + result.getInt("ID"));
			System.out.println("Email: " + result.getString("Email"));
			
			System.out.println();
		}
		conn.close();
	}

}
