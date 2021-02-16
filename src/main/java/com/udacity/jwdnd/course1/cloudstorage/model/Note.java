package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
	private int noteId;
	private String stringId;
	private String noteTitle;
	private String noteDescription;
	private int userId;

	public Note() {
		// empty constructor
	}

	public Note(String noteTitle, String noteDescription) {
		this.noteTitle = noteTitle;
		this.noteDescription = noteDescription;
	}

	public Note(String noteTitle, String noteDescription, Integer userId) {
		this.noteTitle = noteTitle;
		this.noteDescription = noteDescription;
		this.userId = userId;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteDescription() {
		return noteDescription;
	}

	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userid) {
		this.userId = userid;
	}

}
