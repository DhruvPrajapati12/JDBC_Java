package assignment4;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Code2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn;
		int n;
		Scanner sc = new Scanner(System.in);
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
		System.out.println("Connected with database successfully");
		System.out.println(conn);
		
		DatabaseMetaData dbm = conn.getMetaData();
		
		// check if "Result" table is there
		ResultSet tables = dbm.getTables(null, null, "resultData", null);
		if (tables.next()) 
		{
			System.out.println("Table aready exist");
		}
		else
		{
			Statement st1 = conn.createStatement();
			String str = "CREATE TABLE resultData"+
				    "(RollNo int, " +
				    "Name varchar(255)," +
				    "Maths int,"+
				    "Science int,"+
				    "English int,"+
				    "Percentage double )"; 
				
			st1.executeUpdate(str);
			System.out.println("Result table is created");
		}
		
		System.out.print("Enter the number students do you want to add marks in database: ");
		n = sc.nextInt();
		
		if(n != 0)
		{
			while(n != 0)
			{
				System.out.print("Enter the roll no: ");
				int roll = sc.nextInt();
				
				System.out.print("Enter the name: ");
				String name = sc.next();
				
				System.out.print("Enter the marks of maths: ");
				int maths = sc.nextInt();
				
				System.out.print("Enter the marks of science: ");
				int science = sc.nextInt();
				
				System.out.print("Enter the marks of English: ");
				int english = sc.nextInt();
				
				double percentage = (double)(maths + science + english)/3;
				
				PreparedStatement st2 = conn.prepareStatement("insert into resultData(RollNo, Name, Maths, Science, English, Percentage) values(?, ?, ?, ?, ?, ?)");
				st2.setInt(1, roll);
				st2.setString(2, name);
				st2.setInt(3, maths);
				st2.setInt(4, science);
				st2.setInt(5, english);
				st2.setDouble(6, percentage);
				st2.executeUpdate();
				System.out.println("Successfully inserted");
				System.out.println();
				n--;
			}
		}
		
		System.out.print("Enter the rollno for getting marksheet: ");
		int roll = sc.nextInt();
		sc.close();
		
		PreparedStatement st3 = conn.prepareStatement("select * from resultData where RollNo = ?");
		st3.setInt(1, roll);
		ResultSet marksheet = st3.executeQuery();
		
		while(marksheet.next())
		{
			System.out.println("Roll no: " + marksheet.getInt("RollNo"));
			System.out.println("Name: " + marksheet.getString("Name"));
			System.out.println("Maths marks: " + marksheet.getInt("Maths"));
			System.out.println("Science marks: " + marksheet.getInt("Science"));
			System.out.println("English marks: " + marksheet.getInt("English"));
			System.out.println("Percentage: " + marksheet.getDouble("Percentage"));
		}
		conn.close();
	}

}
