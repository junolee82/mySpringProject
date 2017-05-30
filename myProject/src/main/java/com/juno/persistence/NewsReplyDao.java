package com.juno.persistence;

import java.util.List;

import com.juno.domain.Criteria;
import com.juno.domain.NewsReplyVo;

public interface NewsReplyDao {
	public List<NewsReplyVo> listAll(int newsNo) throws Exception;

	public void create(NewsReplyVo vo) throws Exception;

	public void update(NewsReplyVo vo) throws Exception;

	public void delete(int rNo) throws Exception;

	public List<NewsReplyVo> listPaging(int newsNo, Criteria cri) throws Exception;
	
	public int count(int newsNo) throws Exception;
}
