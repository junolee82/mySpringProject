package com.juno.domain;

public class Criteria {
	private int page; // 페이지 번호
	private int perPageNum; // 페이지 당 보여지는 개수

	public Criteria() {
		super();
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}
	
	// method for MyBatis SQL MAPPER - limit #{pageStart}, #{perPageNum}
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}
	
	// method for MyBatis SQL MAPPER - limit #{pageStart}, #{perPageNum}
	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}

	@Override
	public String toString() {
		return String.format("Criteria [page=%s, perPageNum=%s]", page, perPageNum);
	}

}
