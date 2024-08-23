/*

JDBC (Java DataBase Connectivity)
RDBMS (Relationship DataBase Management System) - Schema(정형화된 데이터)

JDBC 절차 >>>>
try {
	================ORM(Object RelationShip Mapping) : Mybatis, Java Persistence Api(JPA) 
	-- 1, 2번은 한번만
	1. 벤더사에서 제공하는 드라이버(xxxx.jar)를 메모리에 로딩
	2. 커넥션(url, user, password)
	
	-- 3번부터는 메서드 지역에서 구현
	3. SQL구문을 담는 그릇을 준비(PreparedStatement)
	4. 그릇에 SQL 구문을 담아서 전송 (CRUD)
	-- executeQuery() 	- select
	-- executeUpdate() 	- DML(insert, update, delete)
	=======================================================
	5. ResultSet 통한 데이터 핸들링 필요(row 값을 컬럼별로 꺼내서 VO 객체로 만드는 작업)
	6. close() - 외부 리소스를 사용했으므로 닫아주는 작업이 필요
} catch( ) {
	
}

*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kdt.jdbc.board.domain.PostRequestDTO;
import com.kdt.jdbc.board.domain.PostResponseDTO;

public class DatabaseApp {
	public static final String URL 		= "jdbc:mysql://localhost:3306/test_db?serverTimezone=UTC";
	public static final String DRIVER 	= "com.mysql.cj.jdbc.Driver";
	public static final String USER 	= "root";
	public static final String PASSWORD = "wjsansrk";
	
	//DB Connection Declaration
	public static Connection conn;
	
	public static void main(String[] args) {
		// database = test_db
		// driver, url, user = root, password = wjsansrk

		init();
//		System.out.println(">>> 게시물 데이터 생성 ");
//		insertRow();
//		System.out.println(">>> 게시물 전체 조회");
//		List<PostResponseDTO> list = selectRow();
//		list.forEach(System.out::println);
		
//		System.out.println(">>> 게시물 상세 보기");
//		PostResponseDTO response = selectRow(2);
//		System.out.println(response);
//		System.out.println(">>> 사용자로부터 특정 게시물을 삭제하겠다는 요청이 들어왔다면? ");
//		System.out.println(">>> 특정 게시물 삭제");
//		int flag = removeRow(1);
//		System.out.println((flag == 1) ? "게시글 삭제 완료" : "게시글 삭제 실패");
		
		System.out.println(">>> 사용자로부터 특정 게시물의 수정(제목, 본문의 내용등) 요청이 들어왔다면?");
		System.out.println(">>> 특정 게시물 수정");
		PostRequestDTO updateDTO = new PostRequestDTO();
		updateDTO.setId(2); updateDTO.setTitle("목요비데스"); updateDTO.setContent("태풍온다");
		int flag = updateRow(updateDTO);
		System.out.println((flag == 1) ? "게시글 변경 완료" : "게시글 변경 실패");
	}
	
	public static int updateRow(PostRequestDTO request) {
		int flag = 0;
		PreparedStatement pstmt = null;
		String updateSQL = "update tb_post SET title = ?, content = ?, modified_date = now() where id = ?";
		
		try {
			pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, request.getTitle());
			pstmt.setString(2, request.getContent());
			pstmt.setInt(3, request.getId());
			
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
	
	public static int removeRow(int index) {
		int flag = 0;
		PreparedStatement pstmt = null;
		
		String deleteSQL = "delete from tb_post where id = ?";
		
		try {
			pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setInt(1, index);
			flag = pstmt.executeUpdate();	
		} catch (Exception e) {
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
	
	public static PostResponseDTO selectRow(int index) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PostResponseDTO response =  null;
		String selectSQL = "select id, title, content, writer from tb_post where id = ?";
		
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, index);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				response = new PostResponseDTO();
				response.setId(rset.getInt(1));
				response.setTitle(rset.getString(2));
				response.setContent(rset.getString(3));
				response.setWriter(rset.getString(4));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}
	
	public static List<PostResponseDTO> selectRow() {
		
		List<PostResponseDTO> 	list = new ArrayList<>();
		PreparedStatement 		pstmt = null;
		ResultSet 				rset = null;
		String selectSQL = "select id, title, content, writer from tb_post";
		PostResponseDTO response = null;
		try {
			pstmt = conn.prepareStatement(selectSQL);
			rset  = pstmt.executeQuery();
			while(rset.next()) {
				response = new PostResponseDTO();
				response.setId(rset.getInt(1));
				response.setTitle(rset.getString(2));
				response.setContent(rset.getString(3));
				response.setWriter(rset.getString(4));
				list.add(response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static void insertRow() {
		PreparedStatement pstmt = null;
		int flag = 0;
		
		String insertSQL = "insert into tb_post(title, content, writer, notice_yn, delete_yn) values(?,?,?,?,?)";
		PostRequestDTO request = new PostRequestDTO("즐거운 수요미데스", "스크린치러가자", "섭섭해");
		
		
		try {
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, request.getTitle());
			pstmt.setString(2, request.getContent());
			pstmt.setString(3, request.getWriter());
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			flag = pstmt.executeUpdate();
			System.out.println("DML Result >>> " + flag);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) { conn.close();}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void init() {
		try {
			Class.forName(DRIVER);
			System.out.println("Driver loading OK!!");
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB Server Connection OK!!" + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
