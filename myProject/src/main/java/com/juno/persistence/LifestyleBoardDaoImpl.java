package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.LifestyleBoardVo;
import com.juno.domain.SearchCriteria;

@Repository
public class LifestyleBoardDaoImpl implements BoardDao<LifestyleBoardVo> {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.LifestyleBoardMapper";

	@Override
	public List<LifestyleBoardVo> list(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listLifestyle", cri);
	}

	@Override
	public int listCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listLifestyleCount", cri);
	}

	@Override
	public LifestyleBoardVo read(int lNo) throws Exception {
		return session.selectOne(namespace + ".read", lNo);
	}

	@Override
	public void create(LifestyleBoardVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(LifestyleBoardVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int lNo) throws Exception {
		session.delete(namespace + ".delete", lNo);
	}

	@Override
	public void updateReplyCnt(int lNo, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("lNo", lNo);
		paramMap.put("amount", amount);

		session.update(namespace + ".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(int lNo) throws Exception {
		session.update(namespace + ".updateViewCnt", lNo);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(int lNo) throws Exception {
		return session.selectList(namespace + ".getAttach", lNo);
	}

	@Override
	public void deleteAttach(int lNo) throws Exception {
		session.delete(namespace + ".deleteAttach", lNo);
	}

	@Override
	public void replaceAttach(String fullName, int lNo) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("fullName", fullName);
		paramMap.put("lNo", lNo);

		session.update(namespace + ".replaceAttach", paramMap);
	}

	@Override
	public void updateRecommend(int lNo) throws Exception {
		session.update(namespace + ".updateRecommend", lNo);
	}

}
