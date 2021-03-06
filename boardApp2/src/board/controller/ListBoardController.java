package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDBBean;

public class ListBoardController implements Controller {
	private BoardDAO boardDAO;
	
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		List<BoardDBBean> result = boardDAO.listBoard();
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", result);
		mav.setViewName("/board/list");
		return mav;
	}
	/*
	 * ModelAndView mav = new ModelAndView("/board/list");
	 * ModelAndView mav = new ModelAndView("/board/list", "list", result);
	 */

}


















