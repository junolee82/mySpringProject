package com.juno.persistence;

import com.juno.domain.UserVo;
import com.juno.dto.LoginDTO;

public interface UserDao {
	public UserVo login(LoginDTO dto) throws Exception;
	
}
