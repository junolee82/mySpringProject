package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.juno.domain.SearchCriteria;
import com.juno.domain.VideoBoardVo;
import com.juno.persistence.VideoBoardDao;

@Service
public class VideoBoardServiceImpl implements VideoBoardService<VideoBoardVo> {

	@Inject
	private VideoBoardDao<VideoBoardVo> dao;

	@Override
	public List<VideoBoardVo> listCri(SearchCriteria cri) throws Exception {
		return dao.list(cri);
	}

	@Override
	public int listCount(SearchCriteria cri) throws Exception {
		return dao.listCount(cri);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public VideoBoardVo read(int vNo) throws Exception {

		dao.updateViewCnt(vNo);

		return dao.read(vNo);
	}

	@Transactional
	@Override
	public void register(VideoBoardVo vo) throws Exception {

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
	public void modify(VideoBoardVo vo) throws Exception {

		dao.update(vo);

		int vNo = vo.getvNo();
		dao.deleteAttach(vNo);

		String[] files = vo.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			dao.replaceAttach(fileName, vNo);
		}

	}

	@Transactional
	@Override
	public void remove(int vNo) throws Exception {

		dao.deleteAttach(vNo);

		dao.delete(vNo);

	}

	@Override
	public List<String> getAttach(int vNo) throws Exception {
		return dao.getAttach(vNo);
	}

	@Override
	public void recommend(int vNo) throws Exception {
		dao.updateRecommend(vNo);
	}

}
