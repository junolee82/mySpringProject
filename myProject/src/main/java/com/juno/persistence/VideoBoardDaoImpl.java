package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.SearchCriteria;
import com.juno.domain.VideoBoardVo;

@Repository
public class VideoBoardDaoImpl implements VideoBoardDao<VideoBoardVo> {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.VideoBoardMapper";

	@Override
	public List<VideoBoardVo> list(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listVideo", cri);
	}

	@Override
	public int listCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listVideoCount", cri);
	}

	@Override
	public VideoBoardVo read(int vNo) throws Exception {
		return session.selectOne(namespace + ".read", vNo);
	}

	@Override
	public void create(VideoBoardVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(VideoBoardVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int vNo) throws Exception {
		session.delete(namespace + ".delete", vNo);
	}

	@Override
	public void updateReplyCnt(int vNo, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("vNo", vNo);
		paramMap.put("amount", amount);

		session.update(namespace + ".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(int vNo) throws Exception {
		session.update(namespace + ".updateViewCnt", vNo);
	}

	// Attach
	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(int vNo) throws Exception {
		return session.selectList(namespace + ".getAttach", vNo);
	}

	@Override
	public void deleteAttach(int vNo) throws Exception {
		session.delete(namespace + ".deleteAttach", vNo);
	}

	@Override
	public void replaceAttach(String fullName, int vNo) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("fullName", fullName);
		paramMap.put("vNo", vNo);

		session.update(namespace + ".replaceAttach", paramMap);
	}

	@Override
	public void updateRecommend(int vNo) throws Exception {
		session.update(namespace + ".updateRecommend", vNo);
	}

}
