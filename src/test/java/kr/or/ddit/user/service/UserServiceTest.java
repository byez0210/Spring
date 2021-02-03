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
	// ��ü ����� ��ȸ �׽�Ʈ

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
		assertEquals("����",userVo.getUsernm());
	}
	// ����� ����¡ ��ȸ �׽�Ʈ
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
			UserVo userVo = new UserVo("testUser","�������","dditpass", new Date(), "���߿�_m","������ �߱� �߾ӷ� 76", "4�� ������簳�߿�","34940","brown.png","uuid=generated-filename.png");

			/***When***/
			int updateCnt = userService.modifyUser(userVo);

			/***Then***/
			assertEquals(1, updateCnt);

		}
		
		
		@Test
		public void registUser() {
			/***Given***/
			
			// userid, usernm, pass, reg_dt, alias , addr1, addr2 , zipcode
			UserVo userVo = new UserVo("testUser","�������","dditpass", new Date(), "���߿�_m","������ �߱� �߾ӷ� 76", "4�� ������簳�߿�","34940" ,"brown.png","uuid=generated-filename.png");
			
			/***When***/
			int insertCnt = userService.registUser(userVo);
			
			/***Then***/
			assertEquals(1, insertCnt);
			
		}
		// ���� �׽�Ʈ 
			@Test
			public void deleteUserTest() {
				/***Given***/
				//�ش� �׽�Ʈ�� ����� ���� testUser��� ����ڰ� before�޼ҵ�� ���� ����� �� ����
				String userid = "testUser";

				/***When***/
				int deleteCnt = userService.deleteUser(userid);
				
				
				/***Then***/
				assertEquals(1, deleteCnt);
			}
			
		
}
