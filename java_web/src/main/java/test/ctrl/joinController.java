package test.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.domain.RequestJoinDTO;
import test.service.JoinService;

@WebServlet("/joinUser")
public class joinController extends HttpServlet {

	private JoinService service;
	public void init() {
		System.out.println("인스턴스 생성시 딱 한번 호출되는 콜백함수입니다.");
		service = new JoinService();
	}
	public void destroy() {
		System.out.println("메모리상에서 allocation 될 때 딱 한번 호출되는 콜백함수입니다.");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println("debug >>> client path : " + uri);
		System.out.println("debug >>> client request method : " + request.getMethod());
		//////
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		RequestJoinDTO params = new RequestJoinDTO();
		params.setId(id); params.setPwd(pwd); params.setName(name); params.setGender(gender);
		
		String msg = service.join(params);
		
		HttpSession session = request.getSession();
		session.setAttribute("joinmsg", msg);
		response.sendRedirect("joinOk.jsp");
	}
}
