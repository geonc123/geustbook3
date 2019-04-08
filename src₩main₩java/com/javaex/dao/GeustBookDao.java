package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GeustBookVo;


public class GeustBookDao {

	private final String url = "jdbc:oracle:thin:@10.37.129.3:1521:xe";


	public List<GeustBookVo> getList() {
		// 0. import java.sql.*;

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		
		List<GeustBookVo> list = new ArrayList<GeustBookVo>();
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query ="select * from gestbook ";

			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();	
			// 4.결과처리
			while(rs.next()) {
				
				int no=rs.getInt("no");
				String id=rs.getString("id");
				String password=rs.getString("password");
				String noticeBoard = rs.getString("notice_board");
				String nowdate = rs.getString("nowdate");
				
				GeustBookVo vo = new GeustBookVo(no,id,password,noticeBoard, nowdate); 
				list.add(vo);
			}
		
		} catch (ClassNotFoundException e)// TODO Auto-generated method stub
		{
			System.out.println("error:드라이버로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
// 5. 자원정리
			try {

				if (rs != null) {

					rs.close();

				}

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}
		}

		return list;
	}
	
	public int insert(GeustBookVo vo) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int count=0;
			
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, "webdb", "webdb");
				// 3. SQL문 준비 / 바인딩 / 실행
				String query ="insert into gestbook " + 
						"    (no, " + 
						"    id, " + 
						"    password, " + 
						"    notice_board, " + 
						"    nowdate ) " + 
						"    values (seq_gestbook_no.nextval, " + 
						"     		 ?, " + 
						"            ?, " + 
						"            ?, " + 
						"              to_char(sysdate, 'yyyy-mm-dd hh24:mi;ss') ) ";
				
				
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getNoticeBoard());
			

				count = pstmt.executeUpdate();
						// 4.결과처리
			

			} catch (ClassNotFoundException e)// TODO Auto-generated method stub
			{
				System.out.println("error:드라이버로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {

	// 5. 자원정리

				try {
					

					if (pstmt != null) {

						pstmt.close();

					}

					if (conn != null) {

						conn.close();

					}

				} catch (SQLException e) {

					System.out.println("error:" + e);
								
				}
			}
		
			return count;
		}

	public int delete(int no , String password){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count=0;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query ="delete from gestbook where no = ? and delete = ?" ;

			
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);

			count = pstmt.executeUpdate();
					// 4.결과처리
		

		} catch (ClassNotFoundException e)// TODO Auto-generated method stub
		{
			System.out.println("error:드라이버로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

// 5. 자원정리

			try {
				

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);
							
			}
		}
	
		return count;
		
	}
	
	public GeustBookVo getVo(int no) {
		// 0. import java.sql.*;
		
		GeustBookVo vo = null;
		
		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문 준비 / 바인딩 / 실행
			String query ="select * from gestbook where no = ?";

			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();	
			// 4.결과처리
			while(rs.next()) {
				
				int no1=rs.getInt("no");
				String id=rs.getString("id");
				String password=rs.getString("password");
				String noticeBoard = rs.getString("notice_board");
				String nowdate = rs.getString("nowdate");
				
				 vo = new GeustBookVo(no1,id,password,noticeBoard, nowdate); 
				
			}
		
		} catch (ClassNotFoundException e)// TODO Auto-generated method stub
		{
			System.out.println("error:드라이버로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
// 5. 자원정리
			try {

				if (rs != null) {

					rs.close();

				}

				if (pstmt != null) {

					pstmt.close();

				}

				if (conn != null) {

					conn.close();

				}

			} catch (SQLException e) {

				System.out.println("error:" + e);

			}
		}

		return vo;
	}
}
