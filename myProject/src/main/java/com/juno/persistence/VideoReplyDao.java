package com.juno.persistence;

import java.util.List;

import com.juno.domain.Criteria;

public interface VideoReplyDao<E> {
	public List<E> listReply(int vNo, Criteria cri) throws Exception;

	public int count(int vNo) throws Exception;

	public void create(E vo) throws Exception;

	public void update(E vo) throws Exception;

	public void delete(int rNo) throws Exception;

	public int getNo(int vNo) throws Exception;
}
