package com.juno.persistence;

import java.util.List;

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
		return null;
	}

	@Override
	public void create(CommunityBoardVo vo) throws Exception {

	}

	@Override
	public void update(CommunityBoardVo vo) throws Exception {

	}

	@Override
	public void delete(int cNo) throws Exception {

	}

	@Override
	public void updateReplyCnt(int cNo, int amount) throws Exception {

	}

	@Override
	public void updateViewCnt(int cNo) throws Exception {

	}

	@Override
	public void addAttach(String fullName) throws Exception {
	}

	@Override
	public List<String> getAttach(int cNo) throws Exception {
		return null;
	}

	@Override
	public void deleteAttach(int cNo) throws Exception {

	}

	@Override
	public void replaceAttach(String fullName, int cNo) throws Exception {

	}

}
