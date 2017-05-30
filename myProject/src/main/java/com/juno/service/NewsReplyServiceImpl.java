package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.juno.domain.Criteria;
import com.juno.domain.NewsReplyVo;
import com.juno.persistence.NewsReplyDao;

@Service
public class NewsReplyServiceImpl implements NewsReplyService {

	@Inject
	private NewsReplyDao dao;

	@Override
	public List<NewsReplyVo> listReply(int newsNo) throws Exception {
		return dao.listAll(newsNo);
	}

	@Override
	public void addReply(NewsReplyVo vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public void modifyReply(NewsReplyVo vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeReply(int rNo) throws Exception {
		dao.delete(rNo);
	}

	@Override
	public List<NewsReplyVo> listReplyPaging(int newsNo, Criteria cri) throws Exception {
		return dao.listPaging(newsNo, cri);
	}

	@Override
	public int count(int newsNo) throws Exception {
		return dao.count(newsNo);
	}

}
