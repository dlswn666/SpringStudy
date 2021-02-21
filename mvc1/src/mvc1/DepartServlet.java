package mvc1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DepartServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 받는 값에 대한 설정을 어떻게 하겠다. 
		req.setCharacterEncoding("EUC-KR");
		String depart = req.getParameter("depart");
		String name = req.getParameter("name");
		//사용자 페이지를 어떻게 설정하겠다!
		resp.setContentType("text/html; charset=EUC-KR");
		
		PrintWriter pw = resp.getWriter();
		
		DepartExpert de = new DepartExpert();
		List<String> advice = de.getAdvice(depart);
		
		req.setAttribute("advice", advice);
		RequestDispatcher view = req.getRequestDispatcher("result.jsp");
		view.forward(req, resp);
		
		
	}
	
	

}
