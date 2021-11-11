package jhbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StudentDAO {
	Connection conn=null;
	PreparedStatement pstmt;
	//pstmt 는 sql 문을 받는 객체 
	
	final String JDBC_DRIVER="org.h2.Driver";
	final String JDBC_URL="jdbc:h2:tcp://localhost/~/jwbookdb";
	
	//db연결
	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(JDBC_URL,"jwbook","0000");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insert(Student s) {
		open();
		String sql="INSERT INTO studentinfo(NO,NAME,DEPART,MENO)VALUES(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getNo());
			pstmt.setString(2, s.getName());
			pstmt.setString(3, s.getDepart());
			pstmt.setString(4, s.getMemo());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	

}
