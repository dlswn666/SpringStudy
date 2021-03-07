package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/board_content.do")
	public ModelAndView contentBoard(@RequestParam int num) {
		
		BoardDBBean dto = boardDAO.getBoard(num, "content");
		
		
		return new ModelAndView("/board/content","getBoard", dto);
	}
	
	@RequestMapping(value = "/board_delete.do", method = RequestMethod.GET)
	public String DeleteFormBoard() {
		
		return "/board/deleteForm";
	}
	
	@RequestMapping(value="/board_delete.do", method=RequestMethod.POST)
	public ModelAndView DeleteProBoard(@RequestParam int num, String passwd) {
		int res = boardDAO.deleteBoard(num, passwd);
		
		String msg, url;
		if (res>0) {
			msg = "글 삭제 성고하였습니다";
			url = "board_list.do";
		}else if (res<0) {
			msg = "비밀번호가 틀렸습니다.!!";
			url = "board_delete.do?num=" + num;
		}else {
			msg = "삭제가 실패하였습니다.";
			url = "board_content.do?num=" + num;
		}
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	
	@RequestMapping(value="/board_update.do" ,method=RequestMethod.GET)
	public ModelAndView UpdateFormBoard(@RequestParam int num) {
		BoardDBBean dto = boardDAO.getBoard(num, "update");
		return new ModelAndView("/board/updateForm", "getBoard", dto);
	}
	
	@RequestMapping(value="/board_update.do", method=RequestMethod.POST)
	public ModelAndView UpdateProBoard(@ModelAttribute BoardDBBean dto, BindingResult result) {
		if(result.hasErrors()) {
			dto.setNum(0);
		}
		String msg = null, url = null;
		int res = boardDAO.updateBoard(dto);
		if(res>0) {
			msg = "게시글 업데이트 성공!! 게시글 목록페이지로 이동합니다";
			url = "board_list.do";
		} else {
			msg = "게시글 업데이트 실패!! ";
			url = "board_list.do";
		}
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		mav.addObject("msg", msg);
		mav.addObject("url",url);
		return mav;
	}
	
}




































