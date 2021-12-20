package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String connectionString = "jdbc:mysql://gondr.asuscomm.com/yy_10212" + "?useUnicode=true"
				+ "&characterEncoding=utf8" + "&useSSL=false" + "&serverTimezone=Asia/Seoul";
		String userId = "yy_10212";
		String password = "@@p0413jwk";

		Connection con = null;

		try {
			con = DriverManager.getConnection(connectionString, userId, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public static void close(ResultSet rs) {
		try {if(rs != null) rs.close();} catch (Exception e) {}
	}
	
	public static void close(PreparedStatement ps) {
		try {if(ps != null) ps.close();	} catch (Exception e) {}
	}
	
	public static void close(Connection co) {
		try {if(co != null) co.close();	} catch (Exception e) {}
	}
}