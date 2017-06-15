package com.juno.service;

import java.util.List;

import com.juno.domain.Criteria;

public interface ReplyService<E> {
	public List<E> listReply(int no, Criteria cri) throws Exception;

	public int count(int no) throws Exception;

	public void addReply(E vo) throws Exception;

	public void modifyReply(E vo) throws Exception;

	public void removeReply(int no) throws Exception;

}
