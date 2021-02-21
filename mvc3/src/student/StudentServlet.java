package student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String cmd = req.getParameter("command");
		PrintWriter pw = resp.getWriter();
		
		if(cmd.equals("insert")) {
			pw.println("학생등록페이지에서 왔네요");
		} else if (cmd.equals("delete")) {
			pw.println("학생삭제페이지에서 왔네요");
		} else if (cmd.equals("find")) {
			pw.println("학생찾기페이지에서 왔네요");
		} else if (cmd.equals("list")) {
			pw.println("학생목록페이지에서 왔네요");
		}
		
		
	}
	
	
	
}
