package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.juno.domain.NewsBoardVo;
import com.juno.domain.SearchCriteria;
import com.juno.persistence.NewsBoardDao;

@Service
public class NewsBoardServiceImpl implements NewsBoardService {

	@Inject
	private NewsBoardDao dao;

	@Override
	public List<NewsBoardVo> listAll() throws Exception {
		return dao.listAll();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public NewsBoardVo read(int newsNo) throws Exception {
		
		dao.updateViewCnt(newsNo);
		
		return dao.read(newsNo);
	}
	
	@Transactional
	@Override
	public void register(NewsBoardVo vo) throws Exception {
		
		dao.create(vo);
		
		String[] files = vo.getFiles();
		
		if(files == null) {
			return;
		}
		
		for(String fileName : files) {
			dao.addAttach(fileName);
		}
		
	}
	
	@Transactional
	@Override
	public void modify(NewsBoardVo vo) throws Exception {
		
		dao.update(vo);
		
		int newsNo = vo.getNewsNo();
		dao.deleteAttach(newsNo);
		
		String[] files = vo.getFiles();
		
		if(files == null) {
			return;
		}
		
		for(String fileName : files) {
			dao.replaceAttach(fileName, newsNo);
		}
		
	}
	
	@Transactional
	@Override
	public void remove(int newsNo) throws Exception {
		
		dao.deleteAttach(newsNo);
		
		dao.delete(newsNo);
		
	}

	@Override
	public List<NewsBoardVo> listNewsCriteria(SearchCriteria cri) throws Exception {
		return dao.listNews(cri);
	}

	@Override
	public int listNewsCount(SearchCriteria cri) throws Exception {
		return dao.listNewsCount(cri);
	}

	@Override
	public List<String> getAttach(int newsNo) throws Exception {
		return dao.getAttach(newsNo);
	}

	@Override
	public void recommend(int newsNo) throws Exception {
		dao.updateRecommend(newsNo);
	}

}
