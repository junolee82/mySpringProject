package com.juno.domain;

public class SearchCriteria extends Criteria {
	private String searchType;
	private String keyword;

	public String getSearchType() {
		return searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return String.format("SearchCriteria [searchType=%s, keyword=%s]", searchType, keyword);
	}

}
