package com.juno.service;

import com.juno.domain.UserVo;
import com.juno.dto.LoginDTO;

public interface UserService {
	public UserVo login(LoginDTO dto) throws Exception;

}
