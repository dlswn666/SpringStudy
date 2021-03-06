package boardMVC;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProBoardCommand implements BoardCommandIf{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String num = req.getParameter("num");
		String password = req.getParameter("passwd");
		
		BoardDataBean dao = new BoardDataBean();
		int res = 0;
		try {
			res = dao.deleteBoard(Integer.parseInt(num), password);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		String msg = null, url=null;
		if(res > 0) {
			msg = "글삭제 성공! 글목록페이지로 이동합니다";
			url = "list.board";
		} else if(res<0) {
			msg = "비밀번호가 틀렸습니다. 다시 입력해주세요!!";
			url = "writeForm.board?num="+num;
		} else {
			msg = "글삭제 실패! 글보기페이지로 이동합니다.";
			url ="content.board?num="+num;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}
	
	

}




















