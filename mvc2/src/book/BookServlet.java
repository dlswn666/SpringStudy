package book;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");//넘어온 데이터 한글 처리
		resp.setContentType("text/html; charset=UTF-8"); // 사용자 페이지 한글 처리
		String cmd = req.getParameter("command");
		//세션 객체 생성 
		//세션 객체 생성 시점?
		//초기화를 방지하기 위해서 req에 생성했다?
		HttpSession session = req.getSession();
		List<BookDTO> list = (List)session.getAttribute("cart");
		//session에 list가 생성되지 않았을 때 강제로 생성하게 만드는 코드 
		if(list == null) list = new ArrayList<>();
		
		if(cmd.equals("CHK")) {
			
			Hashtable<String, BookDTO> ht = new Hashtable<>();
			for(BookDTO dto : list) {
				if(ht.containsKey(dto.getTitle())) { // 기존데이터에 있는 자료라면.. 
					BookDTO dto2 = ht.get(dto.getTitle());
					//dto는 현재 ArrayList에서 꺼낸 객체, dto2는 기존에 Hashtable에 저장된 객체 
					dto2.setQty(dto2.getQty()+dto.getQty());
				} else {
					ht.put(dto.getTitle(), dto);
					
				}
			}
			req.setAttribute("order", ht);
			RequestDispatcher view = req.getRequestDispatcher("result.jsp");
			view.forward(req, resp);
		} else {
			if(cmd.equals("ADD")) {
				BookDTO dto = getBook(req);
				list.add(dto);
			} else {
				list.remove(Integer.parseInt(req.getParameter("idx")));
			}
			session.setAttribute("cart", list);
			RequestDispatcher view = req.getRequestDispatcher("bookShop.jsp");
			view.forward(req, resp);
		}

	}
	// DTO에 값을 넣어주는 코드
	protected BookDTO getBook(HttpServletRequest req) {
		String book = req.getParameter("book");
		String qty = req.getParameter("qty");
		
		Scanner scan = new Scanner(book).useDelimiter("\\s*/\\s*");
		BookDTO dto = new BookDTO();
		dto.setTitle(scan.next());
		dto.setAuthor(scan.next());
		dto.setPrice(scan.nextInt());
		dto.setQty(Integer.parseInt(qty));
		
		return dto;
	}
	
	
}

















































