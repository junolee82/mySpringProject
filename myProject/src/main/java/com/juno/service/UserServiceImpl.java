package com.juno.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.juno.domain.UserVo;
import com.juno.dto.LoginDTO;
import com.juno.persistence.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao dao;

	@Override
	public UserVo login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public void join(UserVo vo) throws Exception {
		dao.join(vo);
	}

	@Override
	public UserVo checkId(String uId) throws Exception {
		return dao.checkId(uId);
	}

}
