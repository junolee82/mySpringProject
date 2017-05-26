package com.juno.domain;

import java.util.Arrays;
import java.util.Date;

public class NewsBoardVo {
	private int newsNo;
	private String nTitle;
	private String nContent;
	private String writer;
	private Date regDate;
	private int viewCnt;
	private int replyCnt;
	private int recommend;
	private String thumbnailImg;

	private String[] files;

	public int getNewsNo() {
		return newsNo;
	}

	public String getnTitle() {
		return nTitle;
	}

	public String getnContent() {
		return nContent;
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

	public String getThumbnailImg() {
		return thumbnailImg;
	}

	public String[] getFiles() {
		return files;
	}

	public void setNewsNo(int newsNo) {
		this.newsNo = newsNo;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
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

	public void setThumbnailImg(String thumbnailImg) {
		this.thumbnailImg = thumbnailImg;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return String.format(
				"NewsBoardVo [newsNo=%s, nTitle=%s, nContent=%s, writer=%s, regDate=%s, viewCnt=%s, replyCnt=%s, recommend=%s, thumbnailImg=%s, files=%s]",
				newsNo, nTitle, nContent, writer, regDate, viewCnt, replyCnt, recommend, thumbnailImg,
				Arrays.toString(files));
	}

}
