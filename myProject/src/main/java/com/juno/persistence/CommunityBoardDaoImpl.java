package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.CommunityBoardVo;
import com.juno.domain.SearchCriteria;

@Repository
public class CommunityBoardDaoImpl implements CommunityBoardDao<CommunityBoardVo> {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.CommunityBoardMapper";

	@Override
	public List<CommunityBoardVo> list(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listCommunity", cri);
	}

	@Override
	public int listCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listCommunityCount", cri);
	}

	@Override
	public CommunityBoardVo read(int cNo) throws Exception {
		return session.selectOne(namespace + ".read", cNo);
	}

	@Override
	public void create(CommunityBoardVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(CommunityBoardVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int cNo) throws Exception {
		session.delete(namespace + ".delete", cNo);
	}

	@Override
	public void updateReplyCnt(int cNo, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("cNo", cNo);
		paramMap.put("amount", amount);

		session.update(namespace + ".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(int cNo) throws Exception {
		session.update(namespace + ".updateViewCnt", cNo);
	}

	// Attach
	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(int cNo) throws Exception {
		return session.selectList(namespace + ".getAttach", cNo);
	}

	@Override
	public void deleteAttach(int cNo) throws Exception {
		session.delete(namespace + ".deleteAttach", cNo);
	}

	@Override
	public void replaceAttach(String fullName, int cNo) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("fullName", fullName);
		paramMap.put("cNo", cNo);

		session.update(namespace + ".replaceAttach", paramMap);
	}

}
