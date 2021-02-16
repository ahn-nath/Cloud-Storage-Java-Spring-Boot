package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
	private Integer fileId;
	private String fileName;
	private String contentType;
	private String fileSize;
	private Integer userId;
	private byte fileData[];

	public File() {
		// empty constructor
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userid) {
		this.userId = userid;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
}