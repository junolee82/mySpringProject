package com.juno.service;

import java.util.List;

import com.juno.domain.SearchCriteria;

public interface VideoBoardService<E> {
	public List<E> listCri(SearchCriteria cri) throws Exception;

	public int listCount(SearchCriteria cri) throws Exception;

	public E read(int vNo) throws Exception;

	public void register(E vo) throws Exception;

	public void modify(E vo) throws Exception;

	public void remove(int vNo) throws Exception;
	
	public void recommend(int vNo) throws Exception;

	public List<String> getAttach(int vNo) throws Exception;
	
}
