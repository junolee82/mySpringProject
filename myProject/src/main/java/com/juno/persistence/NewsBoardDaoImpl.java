package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.NewsBoardVo;
import com.juno.domain.SearchCriteria;

@Repository
public class NewsBoardDaoImpl implements NewsBoardDao {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.NewsBoardMapper";

	@Override
	public List<NewsBoardVo> listAll() throws Exception {
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public NewsBoardVo read(int newsNo) throws Exception {
		return session.selectOne(namespace + ".read", newsNo);
	}

	@Override
	public void create(NewsBoardVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(NewsBoardVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int newsNo) throws Exception {
		session.delete(namespace + ".delete", newsNo);
	}

	// news
	@Override
	public List<NewsBoardVo> listNews(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listNews", cri);
	}

	@Override
	public int listNewsCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listNewsCount", cri);
	}

	@Override
	public void updateReplyCnt(int newsNo, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("newsNo", newsNo);
		paramMap.put("amount", amount);

		session.update(namespace + ".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(int newsNo) throws Exception {
		session.update(namespace + ".updateViewCnt", newsNo);
	}
	
	// Attach ---
	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(int newsNo) throws Exception {
		return session.selectList(namespace + ".getAttach", newsNo);
	}

	@Override
	public void deleteAttach(int newsNo) throws Exception {
		session.delete(namespace + ".deleteAttach", newsNo);
	}

	@Override
	public void replaceAttach(String fullName, int newsNo) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("newsNo", newsNo);
		paramMap.put("fullName", fullName);
		
		session.insert(namespace + ".replaceAttach", paramMap);
	}

}
