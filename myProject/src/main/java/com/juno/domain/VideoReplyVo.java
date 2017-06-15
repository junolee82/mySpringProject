package com.juno.domain;

import java.util.Date;

public class VideoReplyVo {
	private int rNo;
	private int vNo;
	private String replyText;
	private String replyer;
	private Date regDate;
	private Date updateDate;

	public int getrNo() {
		return rNo;
	}

	public int getvNo() {
		return vNo;
	}

	public String getReplyText() {
		return replyText;
	}

	public String getReplyer() {
		return replyer;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public void setvNo(int vNo) {
		this.vNo = vNo;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return String.format("VideoReplyVo [rNo=%s, vNo=%s, replyText=%s, replyer=%s, regDate=%s, updateDate=%s]", rNo,
				vNo, replyText, replyer, regDate, updateDate);
	}

}
