package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

// <bean id="" class=""
// @Repository占쏙옙占쏙옙 占쏙옙占쌕몌옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙 占싱몌옙占쏙옙占쏙옙 class 占싱몌옙占쏙옙占쏙옙 첫占쏙옙占쌘몌옙 占쌀뱄옙占쌘뤄옙 占쏙옙
// 占쏙옙占쌘울옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占싱몌옙占쏙옙占쏙옙 占쏙옙占쏙옙占시댐옙
// UserDaoImpl => userDaoImpl

// UserDao / UserDaoImpl ==> @Resource(name="userDaoImpl")
// UserDaoI / UserDao 	 ==> @Resource(name="userDao")

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;

		/* 이전 버전
		@Override
		public UserVo selectUser(String userid) {
			SqlSession sqlSession =  MybatisUtil.getSqlSession();
			
			UserVo user = sqlSession.selectOne("users.selectUser", userid);
			sqlSession.close();
			
			return user;
			
			return 
		}*/
	@Override
	public UserVo selectUser(String userid) {
		
		// 원래는 데이터 베이스에서 조회를 해야하나, 개발 초기단계라
		// 설정이 완료되지 않은, 현재 확인하려고 하는 기능은 스프링 컨테이너에 초점을 맞추기 위해
		// new 연산자를 통해 생성한 VO객체를 변환 
		
		return template.selectOne("users.selectUser", userid);
	}
	/*
	@Override
	public List<UserVo> selectAllUser() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		// Session : 웹 사이트의 여러 페이지에 걸쳐 사용되는 사용자 정보를 저장하는 방법
		// select : 리턴되는 값의 복수 유무를 판단
		//			1. 단건 : 일반객체를 반환(UserVo)selectOne()
		//			2. 여러건 : List<UserVO> selectList()
		//	insert,update, delete : insert,update, delete 
		
		// 메소드 이름과 실행할 sql id를 동일하게 맞추자(유지보수 - 다른 개발자의 가독성)
		
		List<UserVo> userList = sqlSession.selectList("users.selectAllUser");
		
		//  사용한 자원 반환
		sqlSession.close();
		
		return userList;
	
	}*/
	@Override
	public List<UserVo> selectAllUser() {
		
		return template.selectList("users.selectAllUser");
	}
	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		
		return template.selectList("users.selectPagingUser", vo);
	}
	@Override
	public int selectAllUserCnt() {
		
		return template.selectOne("users.selectAllUserCnt");
	}
	@Override
	public int modifyUser(UserVo userVo) {
		
		return template.update("users.modifyUser", userVo);
	}
	@Override
	public int registUser(UserVo userVo) {
		
		return template.insert("users.registUser", userVo);
	}
	@Override 
	public int deleteUser(String userid) {
		
		return template.delete("users.deleteUser",userid);
	}

}
