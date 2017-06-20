package com.juno.persistence;

import java.util.List;

import com.juno.domain.MagazineBoardVo;
import com.juno.domain.SearchCriteria;

public interface MagazineBoardDao {
	public List<MagazineBoardVo> listMagazine(SearchCriteria cri) throws Exception;
	
	public int listMagazineCount(SearchCriteria cri) throws Exception;
	
	public MagazineBoardVo read(int mNo) throws Exception;
	
	public void create(MagazineBoardVo vo) throws Exception;
	
	public void update(MagazineBoardVo vo) throws Exception;
	
	public void delete(int mNo) throws Exception;
	
	// ---------- Transaction ----------//
	// replyCnt
	public void updateReplyCnt(int mNo, int amount) throws Exception;
	
	// viewCnt
	public void updateViewCnt(int mNo) throws Exception;
	
	// recommend
	public void updateRecommend(int mNo) throws Exception;
	
	// attach
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(int mNo) throws Exception;
	
	public void deleteAttach(int mNo) throws Exception;
	
	public void replaceAttach(String fullName, int mNo) throws Exception;
		
}
