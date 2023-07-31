package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class ConnectionPool {
	// DB정보
	private String id = "root";
	private String pw = "1234";
	private String url = "jdbc:mysql://localhost:3306/weather";
	
	// JDBC참조변수
	protected static Connection DBcon = null; // DB연결용 참조변수
	protected PreparedStatement DBprepar = null; // SQL쿼리 전송용 참조변수
	protected ResultSet DBresult = null; // SQL쿼리 결과(SELECT결과) 수신용 참조변수
	ConnectionPool(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loading Success..");
			if(DBcon==null) {
			DBcon = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected..");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
