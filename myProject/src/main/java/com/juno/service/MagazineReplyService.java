package com.juno.service;

import java.util.List;

import com.juno.domain.Criteria;
import com.juno.domain.MagazineReplyVo;

public interface MagazineReplyService {
	public List<MagazineReplyVo> listMagazineReply(int mNo, Criteria cri) throws Exception;
	
	public int count(int mNo) throws Exception;
	
	public void addReply(MagazineReplyVo vo) throws Exception;
	
	public void modifyReply(MagazineReplyVo vo) throws Exception;
	
	public void removeReply(int rNo) throws Exception;
	
}
