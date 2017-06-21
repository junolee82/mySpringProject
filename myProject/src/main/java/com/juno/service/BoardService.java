package com.juno.service;

import java.util.List;

import com.juno.domain.SearchCriteria;

public interface BoardService<E> {
	public List<E> listCri(SearchCriteria cri) throws Exception;

	public int listCount(SearchCriteria cri) throws Exception;

	public E read(int no) throws Exception;

	public void register(E vo) throws Exception;

	public void modify(E vo) throws Exception;

	public void remove(int no) throws Exception;
	
	public void recommend(int no) throws Exception;

	public List<String> getAttach(int no) throws Exception;

}
