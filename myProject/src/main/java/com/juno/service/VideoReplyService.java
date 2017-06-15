package com.juno.service;

import java.util.List;

import com.juno.domain.Criteria;

public interface VideoReplyService<E> {
	public List<E> listReply(int vNo, Criteria cri) throws Exception;

	public int count(int vNo) throws Exception;

	public void addReply(E vo) throws Exception;

	public void modifyReply(E vo) throws Exception;

	public void removeReply(int rNo) throws Exception;

}
