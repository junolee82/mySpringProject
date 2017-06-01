package com.juno.domain;

import org.springframework.web.multipart.MultipartFile;

public class FileBean {
	private String attach_path;
	private MultipartFile upload;
	private String filename;
	private String CKEditorFuncNum;

	public String getAttach_path() {
		return attach_path;
	}

	public MultipartFile getUpload() {
		return upload;
	}

	public String getFilename() {
		return filename;
	}

	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	public void setAttach_path(String attach_path) {
		this.attach_path = attach_path;
	}

	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}

	@Override
	public String toString() {
		return String.format("FileBean [attach_path=%s, upload=%s, filename=%s, CKEditorFuncNum=%s]", attach_path,
				upload, filename, CKEditorFuncNum);
	}

}
