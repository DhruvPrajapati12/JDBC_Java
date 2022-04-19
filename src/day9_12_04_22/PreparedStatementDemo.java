package day9_12_04_22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
		System.out.println("Connected with the database successfully");
		
		String query = "update studentData set ID = ? where Name = ?";
		
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, 3);
		pst.setString(2, "Het");
		System.out.println("Rows affected: " + pst.executeUpdate());
		
		String query1 = "delete from studentData where Id = ?";
		PreparedStatement pst1 = conn.prepareStatement(query1);
		pst1.setInt(1, 2);
		pst1.executeUpdate();
		
		conn.close();
	}

}