package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juno.domain.Criteria;
import com.juno.domain.MagazineReplyVo;
import com.juno.persistence.MagazineBoardDao;
import com.juno.persistence.MagazineReplyDao;

@Service
public class MagazineReplyServiceImpl implements MagazineReplyService {

	@Inject
	private MagazineReplyDao dao;

	@Inject
	private MagazineBoardDao mBoardDao;

	@Override
	public List<MagazineReplyVo> listMagazineReply(int mNo, Criteria cri) throws Exception {
		return dao.listMagazineReply(mNo, cri);
	}

	@Override
	public int count(int mNo) throws Exception {
		return dao.count(mNo);
	}

	@Transactional
	@Override
	public void addReply(MagazineReplyVo vo) throws Exception {

		dao.create(vo);

		mBoardDao.updateReplyCnt(vo.getmNo(), 1);

	}

	@Override
	public void modifyReply(MagazineReplyVo vo) throws Exception {
		dao.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(int rNo) throws Exception {

		int mNo = dao.getmNo(rNo);
		dao.delete(rNo);

		mBoardDao.updateReplyCnt(mNo, -1);
		
	}

}
