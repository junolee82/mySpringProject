package com.juno.persistence;

import java.util.List;

import com.juno.domain.Criteria;

public interface CommunityReplyDao<E> {
	public List<E> listReply(int cNo, Criteria cri) throws Exception;

	public int count(int cNo) throws Exception;

	public void create(E vo) throws Exception;

	public void update(E vo) throws Exception;

	public void delete(int rNo) throws Exception;

	public int getNo(int cNo) throws Exception;

}
