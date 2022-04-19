package day9_12_04_22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 150334
 *
 */

public class Jdbc_Connect {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
		System.out.println("Connected with database successfully");
		System.out.println(conn.toString());
		
		Statement st = conn.createStatement();
		int result = st.executeUpdate("insert into studentdata(Name, ID, Email) values('Het', 5, 'het@gmail.com')");
		if(result > 0)
		{
			System.out.println("Successfully inserted");
		}
		else
		{
			System.out.println("Unsucessful insertion");
		}
		
		conn.close();
	}

}

//executeQuery() --> for select statement
//executeUpdate() --> for create or modify tables
//execute() -->
