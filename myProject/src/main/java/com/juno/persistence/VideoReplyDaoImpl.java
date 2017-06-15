package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.Criteria;
import com.juno.domain.VideoReplyVo;

@Repository
public class VideoReplyDaoImpl implements VideoReplyDao<VideoReplyVo> {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.VideoReplyMapper";

	@Override
	public List<VideoReplyVo> listReply(int vNo, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("vNo", vNo);
		paramMap.put("cri", cri);

		return session.selectList(namespace + ".listVideoReply", paramMap);
	}

	@Override
	public int count(int vNo) throws Exception {
		return session.selectOne(namespace + ".count", vNo);
	}

	@Override
	public void create(VideoReplyVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(VideoReplyVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int rNo) throws Exception {
		session.delete(namespace + ".delete", rNo);
	}

	@Override
	public int getNo(int vNo) throws Exception {
		return session.selectOne(namespace + ".getvNo", vNo);
	}

}
