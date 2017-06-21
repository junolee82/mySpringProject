package com.juno.persistence;

import java.util.List;

import com.juno.domain.SearchCriteria;

public interface BoardDao<E> {
	public List<E> list(SearchCriteria cri) throws Exception;
	
	public int listCount(SearchCriteria cri) throws Exception;
	
	public E read(int no) throws Exception;
	
	public void create(E vo) throws Exception;
	
	public void update(E vo) throws Exception;
	
	public void delete(int no) throws Exception;
	
	// ---------- Transaction ----------//
	// replyCnt
	public void updateReplyCnt(int no, int amount) throws Exception;
	
	// viewCnt
	public void updateViewCnt(int no) throws Exception;
	
	// recommend
	public void updateRecommend(int no) throws Exception;
	
	// attach
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(int no) throws Exception;
	
	public void deleteAttach(int no) throws Exception;
	
	public void replaceAttach(String fullName, int no) throws Exception;	
	
}
