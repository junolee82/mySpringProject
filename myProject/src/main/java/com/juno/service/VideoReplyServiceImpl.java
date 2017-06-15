package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juno.domain.Criteria;
import com.juno.domain.VideoBoardVo;
import com.juno.domain.VideoReplyVo;
import com.juno.persistence.VideoBoardDao;
import com.juno.persistence.VideoReplyDao;

@Service
public class VideoReplyServiceImpl implements VideoReplyService<VideoReplyVo> {

	@Inject
	private VideoReplyDao<VideoReplyVo> dao;

	@Inject
	private VideoBoardDao<VideoBoardVo> vBoardDao;

	@Override
	public List<VideoReplyVo> listReply(int vNo, Criteria cri) throws Exception {
		return dao.listReply(vNo, cri);
	}

	@Override
	public int count(int vNo) throws Exception {
		return dao.count(vNo);
	}

	@Transactional
	@Override
	public void addReply(VideoReplyVo vo) throws Exception {

		dao.create(vo);

		vBoardDao.updateReplyCnt(vo.getvNo(), 1);

	}

	@Override
	public void modifyReply(VideoReplyVo vo) throws Exception {
		dao.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(int rNo) throws Exception {

		int vNo = dao.getNo(rNo);
		dao.delete(rNo);

		vBoardDao.updateReplyCnt(vNo, -1);

	}

}
