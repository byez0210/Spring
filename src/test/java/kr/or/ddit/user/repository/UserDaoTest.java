package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

//eclipse / maven 

//������ ȯ�濡�� junit �ڵ带 ���� ==> junit�ڵ嵵 ������������ ���

public class UserDaoTest extends ModelTestConfig{

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	
	 @Before public void setup() {
	 //initData.sql�� ���� : ���������� �����ϴ� 
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		//populator�� ���� �����ų sql ������ ����
		populator.addScript(new ClassPathResource("/kr/or/ddit/config/db/initData.sql"));
		
		//script ������ �����ϴ� �������߻��� ��� ���̻� �������� �ʰ� 
		populator.setContinueOnError(false);
		
		//populator �� ����
		DatabasePopulatorUtils.execute(populator, dataSource);
		
		
	
	 //�׽�Ʈ���� ����� �ű� ����� �߰� 
		 UserVo userVo = new UserVo("testUser","�׽�Ʈ�����",
	 "testUserPass", new Date(), "���","���� �߱� �߾ӷ� 76","4��","34940"
	,"brown.png","uuid=generated-filename.png"); 
		
	 userDao.registUser(userVo);
	 };
	  
	 //�ű� �Է� �׽�Ʈ�� ���� �׽�Ʈ �������� �Էµ� �����͸� ���� userDao.deleteUser("ddit2"); }
	 
	 @After public void tearDown() { userDao.deleteUser("testUser"); }
	
	//����� ���̵� �̿��Ͽ� Ư�� ����� ��ȸ
	@Test
	public void getUserTest() {
		/***Given***/
		String userid= "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);
		
		/***Then***/
		assertEquals("����",userVo.getUsernm());
	}
	//����� ��ü ��ȸ
	@Test
	public void selectAllUserTest() {
		/***Given***/
		
		/***When***/
		List<UserVo> userList = userDao.selectAllUser();
		
		/***Then***/
		assertEquals(18, userList.size());
	}
	// ����� ����¡ ��ȸ �׽�Ʈ
		@Test
		public void selectPagingUser() {
			/***Given***/
			PageVo  vo = new PageVo(2,5);
			
			/***When***/
			List<UserVo> pageList = userDao.selectPagingUser(vo);
			
			/***Then***/
			assertEquals(5, pageList.size());
		}
		
		// ����� ��ü ��ȸ
		@Test
		public void selectAllUserCnt() {
			/***Given***/
			
			/***When***/
			int userCnt = userDao.selectAllUserCnt();
			
			/***Then***/
			assertEquals(18, userCnt);
		}
		@Test
		public void modifyUserTest() {
			/***Given***/
			
			// userid, usernm, pass, reg_dt, alias , addr1, addr2 , zipcode
			UserVo userVo = new UserVo("ddit2","�������","dditpass", new Date(), "���߿�_m","������ �߱� �߾ӷ� 76", "4�� ������簳�߿�","34940"  ,"brown.png","uuid=generated-filename.png");

			/***When***/
			int updateCnt = userDao.modifyUser(userVo);

			/***Then***/
			assertEquals(1, updateCnt);

		}
		@Test
		public void registUser() {
			/***Given***/
			
			// userid, usernm, pass, reg_dt, alias , addr1, addr2 , zipcode
			UserVo userVo = new UserVo("ddit2","�������","dditpass", new Date(), "���߿�_m","������ �߱� �߾ӷ� 76", "4�� ������簳�߿�","34940" ,"brown.png","uuid=generated-filename.png");
			
			/***When***/
			int insertCnt = userDao.registUser(userVo);
			
			/***Then***/
			assertEquals(1, insertCnt);
			
		}
		// ���� �׽�Ʈ 
		@Test
		public void deleteUserTest() {
			/***Given***/
			//�ش� �׽�Ʈ�� ����� ���� testUser��� ����ڰ� before�޼ҵ�� ���� ����� �� ����
			String userid = "ddit2";

			/***When***/
			int deleteCnt = userDao.deleteUser(userid);
			
			
			/***Then***/
			assertEquals(1, deleteCnt);
		}
		
	
}
