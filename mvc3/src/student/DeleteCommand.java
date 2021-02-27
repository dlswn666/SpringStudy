package student;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements CommandIf{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		StudentDAO dao = new StudentDAO();
		StudentDTO dto = new StudentDTO();
		int res = 0;
		String msg = null, url = null;
		try {
			res = dao.insertStudent(dto);
			if(res > 0) {
				msg = "학생삭제성공!! 학생목록페이지로 이동합니다";
				url = "student.do?commant=list";
			} else {
				msg = "학생삭제실패!! 학생등록페이지로 이동합니다.";
				url = "student.do?command=start";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			msg = "학생등록페이지 실행 중 DB서버 오류 발생!!";
			url = "student.do?command=start";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
