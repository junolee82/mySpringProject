package com.juno.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.UserVo;
import com.juno.dto.LoginDTO;

@Repository
public class UserDaoImpl implements UserDao {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.UserMapper";

	@Override
	public UserVo login(LoginDTO dto) throws Exception {
		return session.selectOne(namespace + ".login", dto);
	}

	@Override
	public void join(UserVo vo) throws Exception {
		session.insert(namespace + ".join", vo);
	}

	@Override
	public UserVo checkId(String uId) throws Exception {
		return session.selectOne(namespace + ".checkId", uId);
	}

}
