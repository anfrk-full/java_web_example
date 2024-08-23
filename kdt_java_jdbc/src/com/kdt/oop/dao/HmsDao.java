package com.kdt.oop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdt.oop.EmployeeVO;
import com.kdt.oop.PersonVO;
import com.kdt.oop.StudentVO;
import com.kdt.oop.TeacherVO;
import com.kdt.oop.util.HmsType;

public class HmsDao {

	public static final String URL 		= "jdbc:mysql://localhost:3306/test_db?serverTimezone=UTC";
	public static final String DRIVER 	= "com.mysql.cj.jdbc.Driver";
	public static final String USER 	= "root";
	public static final String PASSWORD = "wjsansrk";
	
	public HmsDao() {
		try {
			Class.forName(DRIVER);
			System.out.println("Driver loading OK!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertRow(PersonVO person) {
		int flag = 0;
		PreparedStatement pstmt = null;
		String insertSQL = "insert into tb_hms(TYPE, NAME, AGE, ADDRESS, COMM) values(?,?,?,?,?)";
		Connection conn = null;
		
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(2, person.getName());
			pstmt.setInt(3, person.getAge());
			pstmt.setString(4, person.getAddress());
			switch(person.getType()) {
				case STU :
					pstmt.setString(1, "STU");
					pstmt.setString(5, ((StudentVO)person).getStuId());
					break;
				case TEA :
					pstmt.setString(1, "TEA");
					pstmt.setString(5, ((TeacherVO)person).getSubject());
					break;
				case EMP :
					pstmt.setString(1, "EMP");
					pstmt.setString(5, ((EmployeeVO)person).getDept());
					break;
			}
			
			flag = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) { conn.close(); }
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public List<PersonVO> selectRow() {
		List<PersonVO> 		list = new ArrayList<>();
		PreparedStatement 	pstmt = null;
		ResultSet 			rset = null;
		String selectSQL = "select type, name, age, address, comm from TB_HMS";
		PersonVO person = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = conn.prepareStatement(selectSQL);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				String type = rset.getString(1);
				String name = rset.getString(2);
				int age = rset.getInt(3);
				String address = rset.getString(4);
				String comm = rset.getString(5);
				switch (type) {
					case "STU":
						person = new StudentVO(HmsType.STU, name, age, address, comm);
						break;
				    case "TEA":
				    	person = new TeacherVO(HmsType.TEA, name, age, address, comm);
				    	break;
				    case "EMP":
				    	person = new EmployeeVO(HmsType.EMP, name, age, address, comm);
				    	break;
				  }
				  list.add(person);
				  
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) { conn.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public PersonVO searchRow(String searchname) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String searchSQL = "select TYPE, NAME, AGE, ADDRESS, COMM from TB_HMS where NAME = ? ";
		PersonVO person = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = conn.prepareStatement(searchSQL);
			pstmt.setString(1, searchname);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				
				String type = rset.getString(1);
				String name = rset.getString(2);
				int age = rset.getInt(3);
				String address = rset.getString(4);
				String comm = rset.getString(5);
				
				switch (type) {
				case "STU":
					person = new StudentVO(HmsType.STU, name, age, address, comm);
					break;
			    case "TEA":
			    	person = new TeacherVO(HmsType.TEA, name, age, address, comm);
			    	break;
			    case "EMP":
			    	person = new EmployeeVO(HmsType.EMP, name, age, address, comm);
			    	break;
			  }
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) { conn.close(); }
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return person;
	}
	
	public int removeRow(String name) {
		int flag = 0;
		PreparedStatement pstmt = null;
		String deleteSQL = "delete from TB_HMS where NAME = ?";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setString(1, name);
			flag = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) { conn.close(); }
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
		
	}
	
	public void updateRow(PersonVO person) {
		PreparedStatement pstmt = null;
		String updateSQL = "update TB_HMS set COMM = ? where NAME = ?";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(2, person.getName());
			switch(person.getType()) {
				case STU : 
					pstmt.setString(1, ((StudentVO)person).getStuId());
					break;
				case TEA : 
					pstmt.setString(1, ((TeacherVO)person).getSubject());
					break;
				case EMP : 
					pstmt.setString(1, ((EmployeeVO)person).getDept());
					break;
			}
			pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) { conn.close(); }
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}





















