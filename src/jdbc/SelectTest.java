package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		ResultSet rs=null;
		Connection conn =null;
		Statement stmt = null;
		try {
			// 1. driver loading
			Class.forName("com.mysql.jdbc.Driver");

			// 2. Connecting
			String url = "jdbc:mysql://localhost:3306/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 3. create statement
			stmt = conn.createStatement();
			
			// 4. run SQL
			String sql = "select dept_no, dept_name from departments";
			rs = stmt.executeQuery(sql); //select 쿼리는 excuteQuery, 나머지 쿼리는 updateQuery
			
			// 5. fetch row( row한개씩 가져오기)
			while( rs.next() ){
				String deptNo = rs.getString(1);
				String deptName = rs.getString(2);
				System.out.println(deptNo+", "+deptName);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver is not found");
		} catch (SQLException e) {
			System.out.println("error" + e);
			return ;
		}finally {
			/*자원정리*/
			try { //open의 반대순으로 정리
				if(rs!=null){
					rs.close();
				}
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
