package com.udacity.jwdnd.course1.cloudstorage.exceptions;

public class DuplicatedFileException extends Exception{

	/**
	 If the system receives a file with an already existing name in the database for another file
	 throw exception
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedFileException() {
		super();
	}

}
