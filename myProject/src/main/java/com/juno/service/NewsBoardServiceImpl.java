package com.juno.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.juno.domain.NewsBoardVo;
import com.juno.persistence.NewsBoardDao;

@Service
public class NewsBoardServiceImpl implements NewsBoardService{
	
	@Inject
	private NewsBoardDao dao;

	@Override
	public List<NewsBoardVo> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public NewsBoardVo read(int newsNo) throws Exception {
		return dao.read(newsNo);
	}

	@Override
	public void register(NewsBoardVo vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public void update(NewsBoardVo vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void remove(int newsNo) throws Exception {
		dao.delete(newsNo);
	}

}
