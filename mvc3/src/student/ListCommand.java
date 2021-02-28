package student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommand implements CommandIf{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		StudentDAO dao = new StudentDAO();
		List<StudentDTO> list = null;
		try {
			list = dao.listStudent();
			req.setAttribute("list", list);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return "list.jsp";
	}

}
