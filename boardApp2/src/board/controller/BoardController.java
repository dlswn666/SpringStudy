package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping("/board_list.do")
	public String listBoard(HttpServletRequest req) {
	List<BoardDBBean> result = boardDAO.listBoard();	
		req.setAttribute("list", result);
		return "/board/list";
	}
	@RequestMapping(value = "/board_write.do", method=RequestMethod.GET)
	public String writeFormBoard() {
		return "/board/writeForm";
	}
	@RequestMapping(value = "/board_write.do" , method = RequestMethod.POST)
	public ModelAndView writeProBoard(HttpServletRequest req, BoardDBBean dto, BindingResult result) {
		if(result.hasErrors()) {
			dto.setNum(0);
		}
		dto.setIp(req.getRemoteAddr());
		String msg = null, url = null;
		int res = boardDAO.insertBoard(dto);
		if(res>0) {
			msg = "게시글 등록 성공!! 게시글 목록페이지로 이동합니다";
			url = "board_list.do";
		} else {
			msg = "게시글 등록 실패!! ";
			url = "board_list.do";
		}
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		mav.addObject("msg", msg);
		mav.addObject("url",url);
		return mav;
	}
}




































