package com.juno.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.juno.domain.NewsBoardVo;
import com.juno.domain.SearchCriteria;
import com.juno.persistence.NewsBoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class NewsBoardDaoTest {
	
	@Inject
	private NewsBoardDao dao;
	
	private static final Logger logger = LoggerFactory.getLogger(NewsBoardDaoTest.class);
	
	@Test
	public void listAllTest() throws Exception {
		logger.info(dao.listAll().toString());
	}
	
	@Test
	public void readTest() throws Exception {
		logger.info(dao.read(1).toString());
	}
	
	@Test
	public void insertTest() throws Exception {
		NewsBoardVo vo = new NewsBoardVo();
		vo.setnTitle("insert test setTitle");
		vo.setnContent("insert test setContent");
		vo.setWriter("이준호");
		dao.create(vo);
	}
	
	@Test
	public void updateTest() throws Exception {
		NewsBoardVo vo = new NewsBoardVo();
		vo.setNewsNo(2);
		vo.setnTitle("insert test setTitle update");
		vo.setnContent("insert test setContent update");
		dao.update(vo);
	}
	
	@Test
	public void deleteTest() throws Exception {
		dao.delete(1);
	}
	
	@Test
	public void listNewsTest() throws Exception {
		SearchCriteria cri = new SearchCriteria();
		logger.info(dao.listNews(cri).toString());
	}
	
}
