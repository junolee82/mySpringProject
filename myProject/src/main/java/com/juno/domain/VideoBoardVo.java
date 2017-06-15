package com.juno.domain;

import java.util.Arrays;
import java.util.Date;

public class VideoBoardVo {
	private int vNo;
	private String vTitle;
	private String vContent;
	private String writer;
	private Date regDate;
	private int viewCnt;
	private int replyCnt;
	private int recommend;
	private String fullName;

	private String[] files;

	public int getvNo() {
		return vNo;
	}

	public String getvTitle() {
		return vTitle;
	}

	public String getvContent() {
		return vContent;
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

	public void setvNo(int vNo) {
		this.vNo = vNo;
	}

	public void setvTitle(String vTitle) {
		this.vTitle = vTitle;
	}

	public void setvContent(String vContent) {
		this.vContent = vContent;
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
				"VideoBoardVo [vNo=%s, vTitle=%s, vContent=%s, writer=%s, regDate=%s, viewCnt=%s, replyCnt=%s, recommend=%s, fullName=%s, files=%s]",
				vNo, vTitle, vContent, writer, regDate, viewCnt, replyCnt, recommend, fullName, Arrays.toString(files));
	}

}
