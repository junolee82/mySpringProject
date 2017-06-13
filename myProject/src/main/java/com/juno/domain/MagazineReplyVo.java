package com.juno.domain;

import java.util.Date;

public class MagazineReplyVo {
	private int rNo;
	private int mNo;
	private String replyText;
	private String replyer;
	private Date regDate;
	private Date updateDate;

	public int getrNo() {
		return rNo;
	}

	public int getmNo() {
		return mNo;
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

	public void setmNo(int mNo) {
		this.mNo = mNo;
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
		return String.format("MagazineReplyVo [rNo=%s, mNo=%s, replyText=%s, replyer=%s, regDate=%s, updateDate=%s]",
				rNo, mNo, replyText, replyer, regDate, updateDate);
	}

}
