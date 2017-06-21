package com.juno.persistence;

import java.util.List;

import com.juno.domain.SearchCriteria;

public interface CommunityBoardDao<E> {
	public List<E> list(SearchCriteria cri) throws Exception;

	public int listCount(SearchCriteria cri) throws Exception;

	public E read(int cNo) throws Exception;

	public void create(E vo) throws Exception;

	public void update(E vo) throws Exception;

	public void delete(int cNo) throws Exception;

	// ---------- Transaction ----------//
	// replyCnt
	public void updateReplyCnt(int cNo, int amount) throws Exception;

	// viewCnt
	public void updateViewCnt(int cNo) throws Exception;
	
	// recommend
	public void updateRecommend(int cNo) throws Exception;

	// attach
	public void addAttach(String fullName) throws Exception;

	public List<String> getAttach(int cNo) throws Exception;

	public void deleteAttach(int cNo) throws Exception;

	public void replaceAttach(String fullName, int cNo) throws Exception;

}
