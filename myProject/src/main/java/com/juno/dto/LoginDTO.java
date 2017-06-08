package com.juno.dto;

public class LoginDTO {
	private String uId;
	private String uPw;
	// private boolean useCookie;

	public String getuId() {
		return uId;
	}

	public String getuPw() {
		return uPw;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public void setuPw(String uPw) {
		this.uPw = uPw;
	}

	@Override
	public String toString() {
		return String.format("LoginDTO [uId=%s, uPw=%s]", uId, uPw);
	}

}
