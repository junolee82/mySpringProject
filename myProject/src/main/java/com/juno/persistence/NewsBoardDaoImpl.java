package com.juno.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.juno.domain.NewsBoardVo;

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

}
