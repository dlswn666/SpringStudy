package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

@Controller
public class BoardController {

	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping("/board_list.do")
	public ModelAndView listBoard() {
		List<BoardDTO> listBoard = boardDAO.listBoard();
		return new ModelAndView("board/list", "list", listBoard);
	}
	
	@RequestMapping(value="/board_write.do", method=RequestMethod.GET)
	public String boardWriteForm() {
		return "board/writeForm";
	}
	
	@RequestMapping(value="/board_write.do", method=RequestMethod.POST)
	public ModelAndView boardWritePro(HttpServletRequest req, 
							@ModelAttribute BoardDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			dto.setFilename("");
		}
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("filename");
		String filename = mf.getOriginalFilename();
		
		if (filename==null || filename.trim().equals("")) {
			dto.setFilename("");
			dto.setFilesize(0);
		}else {
			dto.setFilename(filename);
			HttpSession session = req.getSession();
			String upPath = session.getServletContext().getRealPath("/files");
			File file = new File(upPath, filename);
			try {
				mf.transferTo(file);
				int filesize = (int)file.length();
				System.out.println("filesize = " + filesize);
				dto.setFilesize(filesize);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		dto.setIp(req.getRemoteAddr());
		String msg = null, url = null;
		int res = boardDAO.insertBoard(dto);
		if (res>0) {
			msg = "게시글 등록 성공!! 게시글 목록페이지로 이동합니다.";
			url = "board_list.do";
		}else {
			msg = "게시글 등록 실패!! 게시글 목록페이지로 이동합니다.";
			url = "board_list.do";
		} 
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	
	@RequestMapping("/board_content.do")
	public ModelAndView contentBoard(@RequestParam int num) {
		BoardDTO dto = boardDAO.getBoard(num, "content");
		return new ModelAndView("board/content", "getBoard", dto);
	}
}













