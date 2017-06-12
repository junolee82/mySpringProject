package com.juno.service;

import java.util.List;

import com.juno.domain.MagazineBoardVo;
import com.juno.domain.SearchCriteria;

public interface MagazineBoardService {
	public List<MagazineBoardVo> listMagazineCri(SearchCriteria cri) throws Exception;

	public int listMagazineCount(SearchCriteria cri) throws Exception;

	public MagazineBoardVo read(int mNo) throws Exception;

	public void register(MagazineBoardVo vo) throws Exception;

	public void modify(MagazineBoardVo vo) throws Exception;

	public void remove(int mNo) throws Exception;

	public List<String> getAttach(int mNo) throws Exception;

}
