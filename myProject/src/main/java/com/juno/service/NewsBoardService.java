package com.juno.service;

import java.util.List;

import com.juno.domain.NewsBoardVo;
import com.juno.domain.SearchCriteria;

public interface NewsBoardService {
	public List<NewsBoardVo> listAll() throws Exception;

	public NewsBoardVo read(int newsNo) throws Exception;

	public void register(NewsBoardVo vo) throws Exception;

	public void modify(NewsBoardVo vo) throws Exception;

	public void remove(int newsNo) throws Exception;

	public List<NewsBoardVo> listNewsCriteria(SearchCriteria cri) throws Exception;

	public int listNewsCount(SearchCriteria cri) throws Exception;

	public void recommend(int newsNo) throws Exception;

	// TitleImg
	public List<String> getAttach(int newsNo) throws Exception;

}
