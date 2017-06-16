package com.juno.service;

import java.util.List;

import com.juno.domain.Criteria;

public interface CommunityReplyService<E> {
	public List<E> listReply(int cNo, Criteria cri) throws Exception;

	public int count(int cNo) throws Exception;

	public void addReply(E vo) throws Exception;

	public void modifyReply(E vo) throws Exception;

	public void removeReply(int rNo) throws Exception;

}
