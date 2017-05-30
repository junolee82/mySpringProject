package com.juno.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.juno.domain.NewsReplyVo;
import com.juno.persistence.NewsReplyDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class NewsReplyDaoTest {

	@Inject
	private NewsReplyDao dao;

	private static final Logger logger = LoggerFactory.getLogger(NewsReplyDaoTest.class);

	@Test
	public void listAllTest() throws Exception {
		logger.info(dao.listAll(1).toString());
	}
	
	@Test
	public void insertTest() throws Exception {
		NewsReplyVo vo = new NewsReplyVo();
		vo.setNewsNo(1);
		vo.setReplyText("갓갓..");
		vo.setReplyer("wiz");
		dao.create(vo);
	}
	
	@Test
	public void updateTest() throws Exception {
		NewsReplyVo vo = new NewsReplyVo();
		vo.setrNo(2);
		vo.setReplyText("잘 봤습니다.");
		dao.update(vo);
	}
	
	@Test
	public void deleteTest() throws Exception {
		dao.delete(1);
	}

}
