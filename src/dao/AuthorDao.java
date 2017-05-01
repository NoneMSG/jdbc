package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.AuthorVo;

//함수이름들은 CURD 기능에 맞도록 작성
public class AuthorDao {
	
	private Connection getConnection() throws SQLException{
	
		Connection conn = null;
		
		try {
			// 1. driver loading
			Class.forName("com.mysql.jdbc.Driver");

			// 2. Connecting
			String url = "jdbc:mysql://localhost:3306/dev?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "dev", "dev");
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver is not found");
		}
		return conn;
	}

	public boolean insert(AuthorVo authorVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn= getConnection();
			// 3. be ready statement 쿼리를 날리지 않고 default값으로 준비 시켜 놓는다.
			String sql = "insert into author values(null,?,?)";
			pstmt = conn.prepareStatement(sql);

			// 5. binding
			pstmt.setString(1, authorVo.getName());
			pstmt.setString(2, authorVo.getBio());

			// 4. run SQL query문은 pstmt 객체 안에 있기때문에 다시 sql문을 주지 않는다.
			int count = pstmt.executeUpdate();

			return count == 1;
		}  catch (SQLException e) {
			System.out.println("error" + e);
			return false;
		} finally {
			/* 자원정리 */
			try { // open의 반대순으로 정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<AuthorVo> getList() {
		List list = new ArrayList<AuthorVo>();

		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			
			conn=getConnection();
			// 3. create statement
			stmt = conn.createStatement();

			// 4. run SQL
			String sql = "select no, name from author";
			rs = stmt.executeQuery(sql); // select 쿼리는 excuteQuery, 나머지 쿼리는
											// updateQuery

			// 5. fetch row( row한개씩 가져오기)
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String bio = rs.getString(3);

				AuthorVo authorVo = new AuthorVo();
				authorVo.setNo(no);
				authorVo.setName(name);
				authorVo.setBio(bio);

			}
		} catch (SQLException e) {
			System.out.println("error" + e);
			return list;
		} finally {
			/* 자원정리 */
			try { // open의 반대순으로 정리
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public AuthorVo get(Long no) {
		return null;
	}

	public AuthorVo get(String name) {
		return null;
	}

	public boolean delete(Long no) {

		return false;
	}

	public boolean update(AuthorVo authorVo) {

		return false;
	}
}
