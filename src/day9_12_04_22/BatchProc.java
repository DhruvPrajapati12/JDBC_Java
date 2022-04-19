package day9_12_04_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchProc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection conn;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
		System.out.println("Connected with the database successfully");
		
		PreparedStatement ps = conn.prepareStatement("insert into studentData values(?, ?, ?)");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.print("Enter id: ");
			String s1 = br.readLine();
			int id = Integer.parseInt(s1);
			
			System.out.print("Enter name: ");
			String name = br.readLine();
			
			System.out.print("Enter email: ");
			String s3 = br.readLine();
			
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.setString(3, s3);
			
			ps.addBatch();
			
			System.out.println("Do you want to add more Y/N?");
			String ans = br.readLine();
			if(ans.equals("n"))
			{
				break;
			}
		}
		ps.executeBatch();
		System.out.println("record successfully added");
	}

}
