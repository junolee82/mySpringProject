package com.juno.domain;

import java.util.Arrays;
import java.util.Date;

public class LifestyleBoardVo {
	private int lNo;
	private String lTitle;
	private String lContent;
	private String writer;
	private Date regDate;
	private int viewCnt;
	private int replyCnt;
	private int recommend;
	private String fullName;

	private String[] files;

	public int getlNo() {
		return lNo;
	}

	public String getlTitle() {
		return lTitle;
	}

	public String getlContent() {
		return lContent;
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

	public void setlNo(int lNo) {
		this.lNo = lNo;
	}

	public void setlTitle(String lTitle) {
		this.lTitle = lTitle;
	}

	public void setlContent(String lContent) {
		this.lContent = lContent;
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
				"LifestyleBoardVo [lNo=%s, lTitle=%s, lContent=%s, writer=%s, regDate=%s, viewCnt=%s, replyCnt=%s, recommend=%s, fullName=%s, files=%s]",
				lNo, lTitle, lContent, writer, regDate, viewCnt, replyCnt, recommend, fullName, Arrays.toString(files));
	}

}
