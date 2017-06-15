package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.Criteria;
import com.juno.domain.LifestyleReplyVo;

@Repository
public class LifestyleReplyDaoImpl implements ReplyDao<LifestyleReplyVo> {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.LifestyleReplyMapper";

	@Override
	public List<LifestyleReplyVo> listReply(int lNo, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("lNo", lNo);
		paramMap.put("cri", cri);

		return session.selectList(namespace + ".listLifestyleReply", paramMap);
	}

	@Override
	public int count(int lNo) throws Exception {
		return session.selectOne(namespace + ".count", lNo);
	}

	@Override
	public void create(LifestyleReplyVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(LifestyleReplyVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int lNo) throws Exception {
		session.delete(namespace + ".delete", lNo);
	}

	@Override
	public int getNo(int lNo) throws Exception {
		return session.selectOne(namespace + ".getlNo", lNo);
	}

}
