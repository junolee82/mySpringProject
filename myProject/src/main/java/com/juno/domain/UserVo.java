package com.juno.domain;

import java.util.Date;

public class UserVo {
	private String uId;
	private String uPw;
	private String uName;
	private String uEmail;
	private String uPhone;
	private Date regDate;

	public String getuId() {
		return uId;
	}

	public String getuPw() {
		return uPw;
	}

	public String getuName() {
		return uName;
	}

	public String getuEmail() {
		return uEmail;
	}

	public String getuPhone() {
		return uPhone;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public void setuPw(String uPw) {
		this.uPw = uPw;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return String.format("UserVo [uId=%s, uPw=%s, uName=%s, uEmail=%s, uPhone=%s, regDate=%s]", uId, uPw, uName,
				uEmail, uPhone, regDate);
	}

}
