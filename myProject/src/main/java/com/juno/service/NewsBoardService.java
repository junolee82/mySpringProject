package com.juno.service;

import java.util.List;

import com.juno.domain.NewsBoardVo;

public interface NewsBoardService {
	public List<NewsBoardVo> listAll() throws Exception;

	public NewsBoardVo read(int newsNo) throws Exception;

	public void register(NewsBoardVo vo) throws Exception;

	public void update(NewsBoardVo vo) throws Exception;

	public void remove(int newsNo) throws Exception;

}
