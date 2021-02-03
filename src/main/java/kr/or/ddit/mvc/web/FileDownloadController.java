package kr.or.ddit.mvc.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("file")
@Controller
public class FileDownloadController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/fileDownloadView")
	public String fileDownloadView(String userid , Model model) {
		//1. �ٿ�ε� ������ ��� ==> realfilename
		//2. �ٿ�ε�� ������ ���� �� ==> filename
		//1, 2�� model�� �־��ش� 
		//userid �Ķ��ó�� �����ٰ� ����
		// �Ķ���͸� �̿��Ͽ� �ش� ������� ��������(realfilename,filename)�� ��ȸ�Ͽ� ó��
		
		UserVo userVo = userService.selectUser(userid);
		
		model.addAttribute("realfilename" , userVo.getRealfilename());
		model.addAttribute("filename" , userVo.getFilename());
		
		return "fd";
	}
	@RequestMapping("fileDownload")
	public void fileDownload(HttpServletResponse response, String userid) {
		
		UserVo userVo = userService.selectUser(userid);
		// d:\\upload\\sally.png
		String realfilename = userVo.getRealfilename();
		String filename = userVo.getFilename();
		
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		
		ServletOutputStream sos;
		try {
			sos =response.getOutputStream();
			FileInputStream fis =  new FileInputStream(new File(realfilename));
			byte[] buf = new byte[512];
			while(fis.read(buf) != -1 ){
				sos.write(buf);
			}
			
			fis.close();
			sos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
