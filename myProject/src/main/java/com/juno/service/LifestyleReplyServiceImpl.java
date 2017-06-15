package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juno.domain.Criteria;
import com.juno.domain.LifestyleBoardVo;
import com.juno.domain.LifestyleReplyVo;
import com.juno.persistence.BoardDao;
import com.juno.persistence.ReplyDao;

@Service
public class LifestyleReplyServiceImpl implements ReplyService<LifestyleReplyVo> {

	@Inject
	private ReplyDao<LifestyleReplyVo> dao;

	@Inject
	private BoardDao<LifestyleBoardVo> lBoardDao;

	@Override
	public List<LifestyleReplyVo> listReply(int lNo, Criteria cri) throws Exception {
		return dao.listReply(lNo, cri);
	}

	@Override
	public int count(int lNo) throws Exception {
		return dao.count(lNo);
	}

	@Transactional
	@Override
	public void addReply(LifestyleReplyVo vo) throws Exception {

		dao.create(vo);

		lBoardDao.updateReplyCnt(vo.getlNo(), 1);

	}

	@Override
	public void modifyReply(LifestyleReplyVo vo) throws Exception {
		dao.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(int rNo) throws Exception {

		int lNo = dao.getNo(rNo);
		dao.delete(rNo);

		lBoardDao.updateReplyCnt(lNo, -1);

	}

}
