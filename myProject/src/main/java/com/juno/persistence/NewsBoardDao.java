package com.juno.persistence;

import java.util.List;

import com.juno.domain.NewsBoardVo;

public interface NewsBoardDao {
	public List<NewsBoardVo> listAll() throws Exception;

	public NewsBoardVo read(int newsNo) throws Exception;

	public void create(NewsBoardVo vo) throws Exception;

	public void update(NewsBoardVo vo) throws Exception;

	public void delete(int newsNo) throws Exception;

}
