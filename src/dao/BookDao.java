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
import vo.BookVo;

public class BookDao {
	private Connection getConnection() throws SQLException {

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

	public boolean insert(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			// 3. be ready statement 쿼리를 날리지 않고 default값으로 준비 시켜 놓는다.
			
			String temp = "(select no from author where no=1)";
			String sql = "insert into book values(null,?,?,"+temp+")";
			pstmt = conn.prepareStatement(sql);

			// 5. binding
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setLong(2, bookVo.getPrice());
			//pstmt.setLong(3, bookVo.getAuthorNo());

			// 4. run SQL query문은 pstmt 객체 안에 있기때문에 다시 sql문을 주지 않는다.
			int count = pstmt.executeUpdate();
			return count == 1;

		} catch (SQLException e) {
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

	public List<BookVo> getList() {
		List list = new ArrayList<BookVo>();

		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = getConnection();

			// 3. create statement
			stmt = conn.createStatement();

			// 4. run SQL
			String sql = "select no,title,price,author_no from book";
			rs = stmt.executeQuery(sql); // select 쿼리는 excuteQuery, 나머지 쿼리는
											// updateQuery

			// 5. fetch row( row한개씩 가져오기)
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long price = rs.getLong(3);
				Long authorNo = rs.getLong(4);

				BookVo bookVo = new BookVo();
				bookVo.setNo(no);
				bookVo.setTitle(title);
				bookVo.setPrice(price);
				bookVo.setAuthorNo(authorNo);

				list.add(bookVo);
			}
			return list;
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

	}

	public BookVo get(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVo bookVo = null;
		try {
			conn = getConnection();
			// 3. be ready statement 쿼리를 날리지 않고 default값으로 준비 시켜 놓는다.
			String sql = "select no, title, price, author_no from author where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. binding
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				bookVo = new BookVo();

				bookVo.setNo(rs.getLong(1));
				bookVo.setTitle(rs.getString(2));
				bookVo.setPrice(rs.getLong(3));
				bookVo.setAuthorNo(rs.getLong(4));
			}

			return bookVo;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return bookVo;
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

	public BookVo get(String name) {
		return null;
	}

	public boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookVo bookVo = null;
		try {
			conn = getConnection();
			// 3. be ready statement 쿼리를 날리지 않고 default값으로 준비 시켜 놓는다.
			String sql = "delete from book where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. binding
			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();
			return (count == 1);

		} catch (SQLException e) {
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

	public boolean update(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// 3. be ready statement 쿼리를 날리지 않고 default값으로 준비 시켜 놓는다.
			String sql = "update book set no=?, title =?, price=?, authorNo=? where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, bookVo.getNo());
			pstmt.setString(2, bookVo.getTitle());
			pstmt.setLong(3, bookVo.getPrice());
			pstmt.setLong(4, bookVo.getAuthorNo());

			int count = pstmt.executeUpdate();

			return (count == 1);

		} catch (SQLException e) {
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
}
