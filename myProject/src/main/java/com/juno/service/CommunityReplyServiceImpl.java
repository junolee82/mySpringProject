package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juno.domain.CommunityBoardVo;
import com.juno.domain.CommunityReplyVo;
import com.juno.domain.Criteria;
import com.juno.persistence.CommunityBoardDao;
import com.juno.persistence.CommunityReplyDao;

@Service
public class CommunityReplyServiceImpl implements CommunityReplyService<CommunityReplyVo> {

	@Inject
	private CommunityReplyDao<CommunityReplyVo> dao;

	@Inject
	private CommunityBoardDao<CommunityBoardVo> cBoardDao;

	@Override
	public List<CommunityReplyVo> listReply(int cNo, Criteria cri) throws Exception {
		return dao.listReply(cNo, cri);
	}

	@Override
	public int count(int cNo) throws Exception {
		return dao.count(cNo);
	}

	@Transactional
	@Override
	public void addReply(CommunityReplyVo vo) throws Exception {

		dao.create(vo);

		cBoardDao.updateReplyCnt(vo.getcNo(), 1);

	}

	@Override
	public void modifyReply(CommunityReplyVo vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeReply(int rNo) throws Exception {

		int cNo = dao.getNo(rNo);
		dao.delete(rNo);

		cBoardDao.updateReplyCnt(cNo, -1);
		
	}

}
