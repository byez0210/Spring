package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.service.UserService;

@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IocTest {

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "userService")
	private UserService userService2;
	
	@Resource(name = "userServiceCons")
	private UserService userServiceCons;
	
	@Resource(name = "userServicePrototype")
	private UserService userServicePrototype;
	
	@Resource(name = "userServicePrototype")
	private UserService userServicePrototype2;
	
	@Resource(name="dbConfig")
	private Dbconfig dbConfig;
	
	//userServiceCons ������ ���� ���������� �����Ǿ����� �׽�Ʈ
	@Test
	public void UserServiceConsTest() {
		/***Given***/
		

		/***When***/
		
		
		/***Then***/
		assertNotNull(userServiceCons);
	}
	
	@Test
	public void beanScopeTest() {
		//������ ������ singleton �������� ���� �ΰ��� ��ü�� ��Ŭ�����κ��� �������Ƿ� �����ؾ���
		//������ �������� singleton������ bean������Ʈ�� �������� �ϳ��� ��ü�� �����ȴ�
		assertNotEquals(userService, userServiceCons);
		
	}
	@Test
	public void beanScopeTest2() {
		//������ ������ ���� ���Թ޾����Ƿ� userService,userService2�� ���� ��ü��
		assertEquals(userService, userService2);
		
	}
	@Test
	public void beanScopePrototypeTest() {
		//������ userServicePrototype ���� ����(scope : prototype)
		assertNotEquals(userServicePrototype, userServicePrototype2);
		
	}
	@Test
	public void PropertyPlaceholderTest() {
		assertNotNull(dbConfig);
		assertEquals("bykim", dbConfig.getUsername());
		assertEquals("java", dbConfig.getPassword());
		assertEquals("oracle.jdbc.driver.OracleDriver",  dbConfig.getDriverClassName());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe",dbConfig.getUrl());
	}
}