package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
	private int credentialId;
	private String stringId;
	private String url;
	private String username;
	private String password;
	private String key;
	private int userId;

	public int getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(int credentialId) {
		this.credentialId = credentialId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// set encrypted password
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
