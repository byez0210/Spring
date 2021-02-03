package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserServiceTest extends ModelTestConfig {
	
	@Resource(name="userService")
	private UserService userService;
	// 전체 사용자 조회 테스트

		@Test
		public void selectAllUsertest() {
			/***Given***/

			/***When***/
			List<UserVo> userList = userService.selectAllUser();
			
			/***Then***/
			assertEquals(21, userList.size());
		}
	@Test
	public void selectTest() {
		/***Given***/
		String userid= "brown";

		/***When***/
		UserVo userVo = userService.selectUser(userid);
		
		/***Then***/
		assertEquals("브라운",userVo.getUsernm());
	}
	// 사용자 페이징 조회 테스트
		@Test
		public void selectPagingUser() {
			/***Given***/
			PageVo  vo = new PageVo(2,5);
			
			/***When***/
			Map<String, Object>  map = userService.selectPagingUser(vo);
			List<UserVo> userList = (List<UserVo>)map.get("userList");
			int userCnt = (int)map.get("userCnt");
			int pagination = (int) Math.ceil((double)userCnt/vo.getPageSize());
//			System.out.println(pagination);
			
			/***Then***/
			assertEquals(5, pagination);
			assertEquals(77, userCnt);
		}
		
		@Test
		public void modifyUserTest() {
			/***Given***/
			
			// userid, usernm, pass, reg_dt, alias , addr1, addr2 , zipcode
			UserVo userVo = new UserVo("testUser","대덕인재","dditpass", new Date(), "개발원_m","대전시 중구 중앙로 76", "4층 대덕인재개발원","34940","brown.png","uuid=generated-filename.png");

			/***When***/
			int updateCnt = userService.modifyUser(userVo);

			/***Then***/
			assertEquals(1, updateCnt);

		}
		
		
		@Test
		public void registUser() {
			/***Given***/
			
			// userid, usernm, pass, reg_dt, alias , addr1, addr2 , zipcode
			UserVo userVo = new UserVo("testUser","대덕인재","dditpass", new Date(), "개발원_m","대전시 중구 중앙로 76", "4층 대덕인재개발원","34940" ,"brown.png","uuid=generated-filename.png");
			
			/***When***/
			int insertCnt = userService.registUser(userVo);
			
			/***Then***/
			assertEquals(1, insertCnt);
			
		}
		// 삭제 테스트 
			@Test
			public void deleteUserTest() {
				/***Given***/
				//해당 테스트가 실행될 때는 testUser라는 사용자가 before메소드애 의해 등록이 된 상태
				String userid = "testUser";

				/***When***/
				int deleteCnt = userService.deleteUser(userid);
				
				
				/***Then***/
				assertEquals(1, deleteCnt);
			}
			
		
}
