package ch10;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//dao를 구현한 model
public class NewsDAO {
	final String JDBC_DRIVER="org.h2.Driver";
	final String JDBC_URL="jdbc:h2:tcp://localhost/~/jwbookdb";
	
	//db 연결을 하는 open메소드 
	public Connection open() {
		Connection conn=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(JDBC_URL,"jwbook","0000");
		} catch (Exception e) {
			e.printStackTrace();
			//만약 에러면 conn은 null을 반환 
		}
		return conn;
	}
	//뉴스를 더하는 메소드 
	public void addNews(News n) throws Exception{
		Connection conn=open();
		
		String sql="insert into news(title,img,date,content) values(?,?,CURRENT_TIMESTAMP(),?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getCotent());
			pstmt.executeUpdate();
		
	}
	
	//뉴스 기사 목록을 가져오는 메소드(db에 저장된 목록을 가져오는 메소드 )
	public List<News> getAll() throws Exception{
		Connection conn=open();
		List<News> newsList =new ArrayList<>();
		String sql="select aid, title,PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss')as cdate from news";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		
		while(rs.next()) {
			News n=new News();
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setDate(rs.getString("cdate"));
			newsList.add(n);
		}
		return newsList;
	}
	
	//클릭했을때 특정기사 보여주는 메소드 
	public News getNews(int aid) throws SQLException{
		Connection conn=open();
		News n =new News();
		//단 하나의 뉴스만 가져오기에 조건이 붙는다. 
		String sql="select aid, title,PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss')as cdate from news where aid=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		n.setAid(rs.getInt("aid"));
		n.setTitle(rs.getString("title"));
		n.setImg(rs.getString("img"));
		n.setDate(rs.getString("cdate"));
		n.setCotent(rs.getString("content"));
		pstmt.executeQuery();
		return n;
	}
	
	// 뉴스 삭제를 위한 메소드 
	public void delNews(int aid) throws SQLException{
		Connection conn=open();
		String sql="delete from news where aid=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, aid);
		if(pstmt.executeUpdate()==0) {
			throw new SQLException("DB에러");
		}
	}
}
