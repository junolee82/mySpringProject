package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.CommunityReplyVo;
import com.juno.domain.Criteria;

@Repository
public class CommunityReplyDaoImpl implements CommunityReplyDao<CommunityReplyVo> {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.CommunityReplyMapper";

	@Override
	public List<CommunityReplyVo> listReply(int cNo, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("cNo", cNo);
		paramMap.put("cri", cri);

		return session.selectList(namespace + ".listCommunityReply", paramMap);
	}

	@Override
	public int count(int cNo) throws Exception {
		return session.selectOne(namespace + ".count", cNo);
	}

	@Override
	public void create(CommunityReplyVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(CommunityReplyVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int rNo) throws Exception {
		session.delete(namespace + ".delete", rNo);
	}

	@Override
	public int getNo(int cNo) throws Exception {
		return session.selectOne(namespace + ".getcNo", cNo);
	}

}
