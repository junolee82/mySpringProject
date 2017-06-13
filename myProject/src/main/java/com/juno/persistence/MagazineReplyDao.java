package com.juno.persistence;

import java.util.List;

import com.juno.domain.Criteria;
import com.juno.domain.MagazineReplyVo;

public interface MagazineReplyDao {
	public List<MagazineReplyVo> listMagazineReply(int mNo, Criteria cri) throws Exception;
	
	public int count(int mNo) throws Exception;
	
	public void create(MagazineReplyVo vo) throws Exception;
	
	public void update(MagazineReplyVo vo) throws Exception;
	
	public void delete(int rNo) throws Exception;
	
	public int getmNo(int mNo) throws Exception;
}
