package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.juno.domain.LifestyleBoardVo;
import com.juno.domain.SearchCriteria;
import com.juno.persistence.BoardDao;

@Service
public class LifestyleBoardServiceImpl implements BoardService<LifestyleBoardVo> {

	@Inject
	private BoardDao<LifestyleBoardVo> dao;

	@Override
	public List<LifestyleBoardVo> listCri(SearchCriteria cri) throws Exception {
		return dao.list(cri);
	}

	@Override
	public int listCount(SearchCriteria cri) throws Exception {
		return dao.listCount(cri);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public LifestyleBoardVo read(int lNo) throws Exception {

		dao.updateViewCnt(lNo);

		return dao.read(lNo);
	}

	@Transactional
	@Override
	public void register(LifestyleBoardVo vo) throws Exception {

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
	public void modify(LifestyleBoardVo vo) throws Exception {

		dao.update(vo);

		int lNo = vo.getlNo();
		dao.deleteAttach(lNo);

		String[] files = vo.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			dao.replaceAttach(fileName, lNo);
		}

	}

	@Transactional
	@Override
	public void remove(int lNo) throws Exception {

		dao.deleteAttach(lNo);

		dao.delete(lNo);

	}

	@Override
	public List<String> getAttach(int lNo) throws Exception {
		return dao.getAttach(lNo);
	}

}
