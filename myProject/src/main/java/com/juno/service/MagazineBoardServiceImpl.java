package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.juno.domain.MagazineBoardVo;
import com.juno.domain.SearchCriteria;
import com.juno.persistence.MagazineBoardDao;

@Service
public class MagazineBoardServiceImpl implements MagazineBoardService {

	@Inject
	private MagazineBoardDao dao;

	@Override
	public List<MagazineBoardVo> listMagazineCri(SearchCriteria cri) throws Exception {
		return dao.listMagazine(cri);
	}

	@Override
	public int listMagazineCount(SearchCriteria cri) throws Exception {
		return dao.listMagazineCount(cri);
	}

	@Override
	public MagazineBoardVo read(int mNo) throws Exception {
		return dao.read(mNo);
	}

	@Override
	public void register(MagazineBoardVo vo) throws Exception {
		dao.create(vo);
		
		String[] files = vo.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			dao.addAttach(fileName);
		}
		
	}

	@Override
	public void modify(MagazineBoardVo vo) throws Exception {

	}

	@Override
	public void remove(int mNo) throws Exception {

	}

	@Override
	public List<String> getAttach(int mNo) throws Exception {
		return dao.getAttach(mNo);
	}

}
