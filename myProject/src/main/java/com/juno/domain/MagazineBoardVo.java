package com.juno.domain;

import java.util.Arrays;
import java.util.Date;

public class MagazineBoardVo {
	private int mNo;
	private String mTitle;
	private String mContent;
	private String writer;
	private Date regDate;
	private int viewCnt;
	private int replyCnt;
	private int recommend;
	private String fullName;

	private String[] files;

	public int getMagazineNo() {
		return mNo;
	}

	public String getmTitle() {
		return mTitle;
	}

	public String getmContent() {
		return mContent;
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

	public void setMagazineNo(int mNo) {
		this.mNo = mNo;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public void setmContent(String mContent) {
		this.mContent = mContent;
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
				"MagazineBoardVo [mNo=%s, mTitle=%s, mContent=%s, writer=%s, regDate=%s, viewCnt=%s, replyCnt=%s, recommend=%s, fullName=%s, files=%s]",
				mNo, mTitle, mContent, writer, regDate, viewCnt, replyCnt, recommend, fullName,
				Arrays.toString(files));
	}

}
