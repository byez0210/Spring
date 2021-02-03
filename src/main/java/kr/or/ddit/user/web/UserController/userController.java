package kr.or.ddit.user.web.UserController;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.validator.UserVoValidator;
@RequestMapping("user")
@Controller
public class userController {
	private static final Logger logger = LoggerFactory.getLogger(userController.class);
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("allUser")
	public String allUser(Model model) {
		model.addAttribute("userList", userService.selectAllUser());
		
		return "user/allUser";
	}
	@RequestMapping("allUserTiles")
	public String allUserTiles(Model model) {
		model.addAttribute("userList", userService.selectAllUser());
		
		return "tiles.user.allUser";
	}
	
	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page,
							 @RequestParam(defaultValue = "5") int pageSize,
							 Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes( userService.selectPagingUser(pageVo));
		
		return "user/pagingUser";
	}
//	@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo ,Model model) {
		logger.debug("pageVo : {}", pageVo);
		
		return "user/pagingUser";
	}
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int pageSize,
			Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes( userService.selectPagingUser(pageVo));
		
		//tile-definition�� ����
		return "tiles.user.pagingUser";
	}
	
	@RequestMapping("user")
	public String userController(String userid, Model model) {
		
		model.addAttribute( "user",userService.selectUser(userid));
		
		return"tiles.user.user";
//		return"user/user";
	}
	//����
	@RequestMapping(path ="userModify" ,method=RequestMethod.GET)
	public String  modifyUser(String userid, Model model) {
		model.addAttribute( "user",userService.selectUser(userid));
		
		return "user/userModify";
	}
	//�����Ϸ� 
	@RequestMapping(path ="userModify" ,method=RequestMethod.POST)
	public String  modifyUser2(UserVo userVo, Model model,RedirectAttributes ra ) {
		if( userVo.getRealfilename() == null) {
			userVo.setRealfilename("");
		}
		if( userVo.getFilename() == null) {
			userVo.setFilename("");
		}
		logger.debug("RedirectAttributes ���� �޼���  ");
		int updateCnt = userService.modifyUser(userVo);
		
		if (updateCnt == 1 ) {
			ra.addAttribute("userid", userVo.getUserid());
			
			return "redirect:/user/user";
		}else {
			model.addAttribute("user",userVo);
			return "/user/userModify";
		}
	}
	
	//���
	@RequestMapping(path ="registUser" ,method=RequestMethod.GET)
	public String  registUser() {
	
		return "/user/registUser";
	}
	//��ϿϷ�
	//BindingResult ��ü�� command ��ü �ٷ� �ڿ� ���ڷ� ����ؾ� �Ѵ�
	@RequestMapping(path ="registUser" ,method=RequestMethod.POST)
	public String  registUser2(@Valid UserVo userVo, BindingResult result, Model model,RedirectAttributes ra  ) {
		
//		new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			logger.debug("result has error");
			return "user/registUser";
		}
		
		if( userVo.getRealfilename() == null) {
			userVo.setRealfilename("");
		}
		if( userVo.getFilename() == null) {
			userVo.setFilename("");
		}
		int insertCnt = userService.registUser(userVo);
		
		if (insertCnt == 1 ) {
			ra.addAttribute("userid", userVo.getUserid());
			
			return "redirect:/user/pagingUser";
		}else {
			model.addAttribute("user",userVo);
			return "/user/registUser";
		}
		
	}
	//����
	@RequestMapping(path="deleteUser" ,method=RequestMethod.POST)
	public String deleteUser(String userid, Model model,RedirectAttributes ra) {
		model.addAttribute(userService.deleteUser(userid)); 
		return "redirect:/user/pagingUser";
	}
	
	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		List<String> header = new ArrayList<String>();
		header.add("����� ���̵�");
		header.add("����� �̸�");
		header.add("����� ����");
		
		List<UserVo> userVo = new ArrayList<UserVo>();
		
		model.addAttribute("header",header);
		model.addAttribute("data", userService.selectAllUser());
		
		
		return"userExcelDownloadView";
	}
	// localhost/user/profile
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("image");
		//userid �Ķ���͸� �̿��Ͽ�
		//userService ��ü�� ���� ������� ���� ���� �̸��� ȹ��
		// ���� ������� ������ ������ �о�鿩 resp��ü�� outputstream���� ���� ����
		UserVo userVo=  userService.selectUser(userid);
		
		String  path ="";
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
		}
		else {
			path=userVo.getRealfilename();
		}
		
		logger.debug("path : {}", path);
		
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			while(fis.read(buff) != -1 ) {
				sos.write(buff);
			}
			
			fis.close();
			sos.flush();
			sos.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("fileDownloadView")
	public String  fileDownloadView(String userid, Model model) {
		UserVo userVo = userService.selectUser(userid);
		
		model.addAttribute("realfilename" , userVo.getRealfilename());
		model.addAttribute("filename" , userVo.getFilename());
		
		return "fd";
	}
}
