package com.juno.service;

import java.util.List;

import com.juno.domain.Criteria;
import com.juno.domain.NewsReplyVo;

public interface NewsReplyService {
	public List<NewsReplyVo> listReply(int newsNo) throws Exception;

	public void addReply(NewsReplyVo vo) throws Exception;

	public void modifyReply(NewsReplyVo vo) throws Exception;

	public void removeReply(int rNo) throws Exception;

	public List<NewsReplyVo> listReplyPaging(int newsNo, Criteria cri) throws Exception;

	public int count(int newsNo) throws Exception;

}
