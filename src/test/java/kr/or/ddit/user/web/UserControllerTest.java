package kr.or.ddit.user.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends WebTestConfig {

	@Test
	public void userControllerTest() throws Exception {
		MvcResult  mvcResult = mockMvc.perform(get("/user/allUser"))
									.andExpect(status().isOk())
									.andExpect(view().name("user/allUser"))
									.andDo(print())
									.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
	
		assertEquals("user/allUser", mav.getViewName());
	}
	@Test
	public void pagingUserTest() throws Exception {
		MvcResult  mvcResult =	mockMvc.perform(get("/user/pagingUser"))
								.andExpect(status().isOk())
								.andExpect(view().name("/user/pagingUser"))
								.andExpect(model().attributeExists("userList"))
								.andExpect(model().attributeExists("pageVo"))
								.andExpect(model().attributeExists("pagination"))
								.andDo(print())
								.andReturn();
		
	}
	@Test
	public void pagingUserTest2() throws Exception {
		MvcResult  mvcResult =	mockMvc.perform(get("/user/pagingUser").param("page", "2"))
								.andExpect(status().isOk())
								.andExpect(view().name("/user/pagingUser"))
								.andExpect(model().attributeExists("userList"))
								.andExpect(model().attributeExists("pageVo"))
								.andExpect(model().attributeExists("pagination"))
								.andDo(print())
								.andReturn();
		
	}
	//사용자 상세 페이지
	@Test
	public void userTest() throws Exception {
		MvcResult  mvcResult =	mockMvc.perform(get("/user/user").param("userid", "aa"))
								.andExpect(status().isOk())
								.andExpect(view().name("/user/user"))
								.andExpect(model().attributeExists("user"))
								.andDo(print())
								.andReturn();
		
	}
	//사용자 등록 요청 페이지 
	@Test
	public void registUserTest2() throws Exception {
		MvcResult  mvcResult =	mockMvc.perform(get("/user/registUser").param("UserVo"))
				.andExpect(status().isOk())
				.andExpect(view().name("/userVo"))
				.andExpect(model().attributeExists("user"))
				.andDo(print())
				.andReturn();
	}
	//사용자 등록 페이지 
	@Test
	public void registUserTest() throws Exception {
		MvcResult  mvcResult =	mockMvc.perform(get("/user/registUser").param("UserVo"))
				.andExpect(status().isOk())
				.andExpect(view().name("/userVo"))
				.andExpect(model().attributeExists("user"))
				.andDo(print())
				.andReturn();
	}
	//사용자 수정 요청 페이지 
	@Test 
	public void modifyUserTest() throws Exception {
		MvcResult  mvcResult =	mockMvc.perform(get("/user/registUser").param("UserVo"))
								.andExpect(status().isOk())
								.andExpect(view().name("/userVo"))
								.andExpect(model().attributeExists("user"))
								.andDo(print())
								.andReturn();
						
	}
	//사용자 수정 페이지 
	@Test 
	public void modifyUserTest2() throws Exception {
		MvcResult  mvcResult =	mockMvc.perform(get("/user/registUser").param("UserVo"))
				.andExpect(status().isOk())
				.andExpect(view().name("/userVo"))
				.andExpect(model().attributeExists("user"))
				.andDo(print())
				.andReturn();
		
	}
	//사용자 삭제 페이지
	@Test 
	public void deleteUserTest() throws Exception {
		MvcResult  mvcResult =	mockMvc.perform(get("/user/registUser").param("UserVo"))
				.andExpect(status().isOk())
				.andExpect(view().name("/userVo"))
				.andExpect(model().attributeExists("user"))
				.andDo(print())
				.andReturn();
		
	}
}
