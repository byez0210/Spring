package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/TypeConversion.xml")
public class TypeConversionTest {
	private static final Logger logger = LoggerFactory.getLogger(TypeConversionTest.class);

	@Resource(name="user")
	private UserVo user;
	
	@Test
	public void UserTsest() {
//		String age = "5"; 스프링 프렘임 워크가 알아서 적절한 타입을 제공 
		logger.debug("user.getReg_dt() : {} ", user.getReg_dt());
		logger.debug("user.getHire_dt() : {} ", user.getHire_dt());
		logger.debug("user.getPrice() : {} ", user.getPrice());
	}

}
