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

//스프링 환경에서 junit 코드를 실행 ==> junit코드도 스프링빈으로 등록

public class UserDaoTest extends ModelTestConfig{

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	
	 @Before public void setup() {
	 //initData.sql을 실행 : 스프링에서 제동하는 
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		//populator를 통해 실행시킬 sql 파일을 지정
		populator.addScript(new ClassPathResource("/kr/or/ddit/config/db/initData.sql"));
		
		//script 파일을 실행하다 에러가발생할 경우 더이상 진행하지 않게 
		populator.setContinueOnError(false);
		
		//populator 를 실행
		DatabasePopulatorUtils.execute(populator, dataSource);
		
		
	
	 //테스트에서 사용할 신규 사용자 추가 
		 UserVo userVo = new UserVo("testUser","테스트사용자",
	 "testUserPass", new Date(), "대덕","대전 중구 중앙로 76","4층","34940"
	,"brown.png","uuid=generated-filename.png"); 
		
	 userDao.registUser(userVo);
	 };
	  
	 //신규 입력 테스트를 위해 테스트 과정에서 입력된 테이터를 삭제 userDao.deleteUser("ddit2"); }
	 
	 @After public void tearDown() { userDao.deleteUser("testUser"); }
	
	//사용자 아이디를 이용하여 특정 사용자 조회
	@Test
	public void getUserTest() {
		/***Given***/
		String userid= "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);
		
		/***Then***/
		assertEquals("브라운",userVo.getUsernm());
	}
	//사용자 전체 조회
	@Test
	public void selectAllUserTest() {
		/***Given***/
		
		/***When***/
		List<UserVo> userList = userDao.selectAllUser();
		
		/***Then***/
		assertEquals(18, userList.size());
	}
	// 사용자 페이징 조회 테스트
		@Test
		public void selectPagingUser() {
			/***Given***/
			PageVo  vo = new PageVo(2,5);
			
			/***When***/
			List<UserVo> pageList = userDao.selectPagingUser(vo);
			
			/***Then***/
			assertEquals(5, pageList.size());
		}
		
		// 사용자 전체 조회
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
			UserVo userVo = new UserVo("ddit2","대덕인재","dditpass", new Date(), "개발원_m","대전시 중구 중앙로 76", "4층 대덕인재개발원","34940"  ,"brown.png","uuid=generated-filename.png");

			/***When***/
			int updateCnt = userDao.modifyUser(userVo);

			/***Then***/
			assertEquals(1, updateCnt);

		}
		@Test
		public void registUser() {
			/***Given***/
			
			// userid, usernm, pass, reg_dt, alias , addr1, addr2 , zipcode
			UserVo userVo = new UserVo("ddit2","대덕인재","dditpass", new Date(), "개발원_m","대전시 중구 중앙로 76", "4층 대덕인재개발원","34940" ,"brown.png","uuid=generated-filename.png");
			
			/***When***/
			int insertCnt = userDao.registUser(userVo);
			
			/***Then***/
			assertEquals(1, insertCnt);
			
		}
		// 삭제 테스트 
		@Test
		public void deleteUserTest() {
			/***Given***/
			//해당 테스트가 실행될 때는 testUser라는 사용자가 before메소드애 의해 등록이 된 상태
			String userid = "ddit2";

			/***When***/
			int deleteCnt = userDao.deleteUser(userid);
			
			
			/***Then***/
			assertEquals(1, deleteCnt);
		}
		
	
}
