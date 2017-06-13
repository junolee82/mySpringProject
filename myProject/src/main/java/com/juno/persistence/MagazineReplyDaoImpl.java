package com.juno.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.Criteria;
import com.juno.domain.MagazineReplyVo;

@Repository
public class MagazineReplyDaoImpl implements MagazineReplyDao {

	@Inject
	private SqlSession session;

	private static String namespace = "com.juno.mappers.MagazineReplyMapper";

	@Override
	public List<MagazineReplyVo> listMagazineReply(int mNo, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("mNo", mNo);
		paramMap.put("cri", cri);

		return session.selectList(namespace + ".listMagazineReply", paramMap);
	}

	@Override
	public int count(int mNo) throws Exception {
		return session.selectOne(namespace + ".count", mNo);
	}

	@Override
	public void create(MagazineReplyVo vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public void update(MagazineReplyVo vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int rNo) throws Exception {
		session.delete(namespace + ".delete", rNo);
	}

	@Override
	public int getmNo(int mNo) throws Exception {
		return session.selectOne(namespace + ".getmNo", mNo);
	}

}
