package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.juno.domain.CommunityBoardVo;
import com.juno.domain.SearchCriteria;
import com.juno.persistence.CommunityBoardDao;

@Service
public class CommunityBoardServiceImpl implements CommunityBoardService<CommunityBoardVo> {

	@Inject
	private CommunityBoardDao<CommunityBoardVo> dao;

	@Override
	public List<CommunityBoardVo> listCri(SearchCriteria cri) throws Exception {
		return dao.list(cri);
	}

	@Override
	public int listCount(SearchCriteria cri) throws Exception {
		return dao.listCount(cri);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public CommunityBoardVo read(int cNo) throws Exception {

		dao.updateViewCnt(cNo);

		return dao.read(cNo);
	}

	@Transactional
	@Override
	public void register(CommunityBoardVo vo) throws Exception {

		dao.create(vo);

		String[] files = vo.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			dao.addAttach(fileName);
		}

	}

	@Transactional
	@Override
	public void modify(CommunityBoardVo vo) throws Exception {

		dao.update(vo);

		int cNo = vo.getcNo();
		dao.deleteAttach(cNo);

		String[] files = vo.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			dao.replaceAttach(fileName, cNo);
		}

	}

	@Transactional
	@Override
	public void remove(int cNo) throws Exception {

		dao.deleteAttach(cNo);

		dao.delete(cNo);

	}

	@Override
	public List<String> getAttach(int cNo) throws Exception {
		return dao.getAttach(cNo);
	}

}
