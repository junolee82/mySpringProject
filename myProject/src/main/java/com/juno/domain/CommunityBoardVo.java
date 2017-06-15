package com.juno.domain;

import java.util.Arrays;
import java.util.Date;

public class CommunityBoardVo {
	private int cNo;
	private String cTitle;
	private String cContent;
	private String writer;
	private Date regDate;
	private int viewCnt;
	private int replyCnt;
	private int recommend;
	private String fullName;

	private String[] files;

	public int getcNo() {
		return cNo;
	}

	public String getcTitle() {
		return cTitle;
	}

	public String getcContent() {
		return cContent;
	}

	public String getWriter() {
		return writer;
	}

	public Date getRegDate() {
		return regDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public int getRecommend() {
		return recommend;
	}

	public String getFullName() {
		return fullName;
	}

	public String[] getFiles() {
		return files;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return String.format(
				"CommunityBoardVo [cNo=%s, cTitle=%s, cContent=%s, writer=%s, regDate=%s, viewCnt=%s, replyCnt=%s, recommend=%s, fullName=%s, files=%s]",
				cNo, cTitle, cContent, writer, regDate, viewCnt, replyCnt, recommend, fullName, Arrays.toString(files));
	}

}
