package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.Criteria;
import com.juno.domain.NewsReplyVo;

@Repository
public class NewsReplyDaoimpl implements NewsReplyDao {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.NewsReplyMapper";

	@Override
	public List<NewsReplyVo> listAll(int newsNo) throws Exception {
		return session.selectList(namespace + ".listAll", newsNo);
	}

	@Override
	public void create(NewsReplyVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(NewsReplyVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int rNo) throws Exception {
		session.delete(namespace + ".delete", rNo);
	}

	@Override
	public List<NewsReplyVo> listPaging(int newsNo, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("newsNo", newsNo);
		paramMap.put("cri", cri);
		return session.selectList(namespace + ".listPaging", paramMap);
	}

	@Override
	public int count(int newsNo) throws Exception {
		return session.selectOne(namespace + ".count", newsNo);
	}

}
