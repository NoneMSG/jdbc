package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	public static void main(String args[]){
		try {
			
			//1. driver loading
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. Connecting
			String url = "jdbc:mysql://localhost:3306/employees";
			Connection conn = DriverManager.getConnection(url,"hr","hr");
			
			System.out.println("연결 성공!!");
			conn.close();
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver is not found");
		} catch (SQLException e){
			System.out.println("error"+e);
		}
	}
}
