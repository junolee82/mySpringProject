package com.juno.persistence;

import java.util.List;

import com.juno.domain.SearchCriteria;

public interface VideoBoardDao<E> {
	public List<E> list(SearchCriteria cri) throws Exception;
	
	public int listCount(SearchCriteria cri) throws Exception;
	
	public E read(int vNo) throws Exception;
	
	public void create(E vo) throws Exception;
	
	public void update(E vo) throws Exception;
	
	public void delete(int vNo) throws Exception;
	
	// ---------- Transaction ----------//
	// replyCnt
	public void updateReplyCnt(int vNo, int amount) throws Exception;
	
	// viewCnt
	public void updateViewCnt(int vNo) throws Exception;
	
	// attach
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(int vNo) throws Exception;
	
	public void deleteAttach(int vNo) throws Exception;
	
	public void replaceAttach(String fullName, int vNo) throws Exception;	
	
}
