package com.juno.persistence;

import com.juno.domain.UserVo;
import com.juno.dto.LoginDTO;

public interface UserDao {
	public UserVo login(LoginDTO dto) throws Exception;
	
	public void join(UserVo vo) throws Exception;
	
	public UserVo checkId(String uId) throws Exception;
	
}
