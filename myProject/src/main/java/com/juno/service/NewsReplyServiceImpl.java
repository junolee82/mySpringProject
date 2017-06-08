package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juno.domain.Criteria;
import com.juno.domain.NewsReplyVo;
import com.juno.persistence.NewsBoardDao;
import com.juno.persistence.NewsReplyDao;

@Service
public class NewsReplyServiceImpl implements NewsReplyService {

	@Inject
	private NewsReplyDao dao;

	@Inject
	private NewsBoardDao boardDao;

	@Override
	public List<NewsReplyVo> listReply(int newsNo) throws Exception {
		return dao.listAll(newsNo);
	}

	@Transactional
	@Override
	public void addReply(NewsReplyVo vo) throws Exception {
		
		dao.create(vo);
		
		boardDao.updateReplyCnt(vo.getNewsNo(), 1);
		
	}

	@Override
	public void modifyReply(NewsReplyVo vo) throws Exception {
		dao.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(int rNo) throws Exception {
		
		int newsNo = dao.getNewsNo(rNo);		
		dao.delete(rNo);
		
		boardDao.updateReplyCnt(newsNo, -1);
		
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
