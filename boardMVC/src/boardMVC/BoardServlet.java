package boardMVC;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 주소 받아오기
		String reqUri = req.getRequestURI();
		// /현재 프로젝트명
		String upPath = req.getContextPath();
		String cmd = reqUri.substring(upPath.length());
		System.out.println(cmd);
		CommandFactory factory = CommandFactory.getInstace();
		BoardCommandIf cmdIf = factory.createCommand(cmd);
		System.out.println(cmdIf);
		String nextPage = (String)cmdIf.processCommand(req, resp);
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);

	}

}
