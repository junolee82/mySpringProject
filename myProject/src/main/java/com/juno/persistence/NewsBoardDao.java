package com.juno.persistence;

import java.util.List;

import com.juno.domain.NewsBoardVo;
import com.juno.domain.SearchCriteria;

public interface NewsBoardDao {
	public List<NewsBoardVo> listAll() throws Exception;

	public NewsBoardVo read(int newsNo) throws Exception;

	public void create(NewsBoardVo vo) throws Exception;

	public void update(NewsBoardVo vo) throws Exception;

	public void delete(int newsNo) throws Exception;

	public List<NewsBoardVo> listNews(SearchCriteria cri) throws Exception;

	public int listNewsCount(SearchCriteria cri) throws Exception;

	// ---------- Transaction ----------//
	// replyCnt
	public void updateReplyCnt(int newsNo, int amount) throws Exception;

	// viewCnt
	public void updateViewCnt(int newsNo) throws Exception;
	
	// upload
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(int newsNo) throws Exception;
	
	public void deleteAttach(int newsNo) throws Exception;
	
	public void replaceAttach(String fullName, int newsNo) throws Exception;

}
