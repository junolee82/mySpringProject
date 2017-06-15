package com.juno.persistence;

import java.util.List;

import com.juno.domain.Criteria;

public interface ReplyDao<E> {
	public List<E> listReply(int no, Criteria cri) throws Exception;

	public int count(int no) throws Exception;

	public void create(E vo) throws Exception;

	public void update(E vo) throws Exception;

	public void delete(int no) throws Exception;

	public int getNo(int no) throws Exception;

}
