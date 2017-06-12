package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.MagazineBoardVo;
import com.juno.domain.SearchCriteria;

@Repository
public class MagazineBoardDaoImpl implements MagazineBoardDao {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.MagazineBoardMapper";

	@Override
	public List<MagazineBoardVo> listMagazine(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listMagazine", cri);
	}

	@Override
	public int listMagazineCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listMagazineCount", cri);
	}

	@Override
	public MagazineBoardVo read(int mNo) throws Exception {
		return session.selectOne(namespace + ".read", mNo);
	}

	@Override
	public void create(MagazineBoardVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(MagazineBoardVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int mNo) throws Exception {
		session.delete(namespace + "delete", mNo);
	}

	@Override
	public void updateReplyCnt(int mNo, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("mNo", mNo);
		paramMap.put("amount", amount);

		session.update(namespace + "updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(int mNo) throws Exception {
		session.update(namespace + ".updateViewCnt", mNo);
	}

	// Attach
	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(int mNo) throws Exception {
		return session.selectList(namespace + ".getAttach", mNo);
	}

	@Override
	public void deleteAttach(int mNo) throws Exception {
		session.delete(namespace + ".deleteAttach", mNo);
	}

	@Override
	public void replaceAttach(String fullName, int mNo) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("fullName", fullName);
		paramMap.put("mNo", mNo);

		session.update(namespace + ".replaceAttach", paramMap);
	}

}
