package test;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {
	@RequestMapping("/fileUpload.do")
	public void fileUpload(HttpServletRequest req) {
		// 파일 받기
		// servlet 에서 Multipart를 등록 하지 않으면 classCastError가 나옴. 
		// 형변환이 이루어지지 않기 때문에 
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		MultipartFile mf = mr.getFile("filename");
		//파일 이름을 알기 위해서 
		String filename = mf.getOriginalFilename();
		//파일이 없을 때 메소드 종료
		if(filename == null || filename.trim().equals("")) return;
//		System.out.println("filename = " + filename );
		// 세션 정보 들고오기
		HttpSession session = req.getSession();
		//경로 확인 방법
		String upPath = session.getServletContext().getRealPath("/files");
		System.out.println("upPath = " + upPath);
		
		// 파일 다운 구현
		
		File file = new File(upPath, filename);
		try {
			mf.transferTo(file);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}




















