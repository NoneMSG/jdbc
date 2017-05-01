package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {

	public static void main(String[] args) {
		Connection conn =null;
		Statement stmt = null;
		try {
			// 1. driver loading
			Class.forName("com.mysql.jdbc.Driver");

			// 2. Connecting
			String url = "jdbc:mysql://localhost:3306/dev?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "dev", "dev");

			// 3. create statement
			stmt = conn.createStatement();
			
			// 4. run SQL
			String sql = "insert into member"+ 
			" values( null," + 
					" 'kick@kick.com'  ,"+ 
					" password('123'),"+
					" '모시모시',"+ 
					" '개발팀',"+ 
					" now())" ;
			
			int count = stmt.executeUpdate(sql);
			System.out.println(count==1);
				
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver is not found");
		} catch (SQLException e) {
			System.out.println("error" + e);
			return ;
		}finally {
			/*자원정리*/
			try { //open의 반대순으로 정리
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
