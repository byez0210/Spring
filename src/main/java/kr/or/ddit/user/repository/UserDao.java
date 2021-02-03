package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {
	
	//����� ���̵�� ����� ��ȸ
	UserVo selectUser(String userid);
	
	//����� ��ü ��ȸ
	//��ȯ Ÿ�� �޼ҵ��();
	List<UserVo> selectAllUser();
	
	// ����� ����¡ ��ȸ
	List<UserVo> selectPagingUser(PageVo vo);
	
	// ����� ��ü �� ��ȸ
	int selectAllUserCnt();
	
	// ����� ���� ���� 
	int modifyUser(UserVo userVo);
	
	// ����� �ű� ���
	int registUser(UserVo userVo);
	
	// ����� ���� 
	int deleteUser(String userid);
}
