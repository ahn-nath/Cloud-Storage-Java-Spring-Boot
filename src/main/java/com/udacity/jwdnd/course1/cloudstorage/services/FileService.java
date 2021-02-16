package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;


import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Service
public class FileService {
	private final FileMapper fileMapper;

	public FileService(FileMapper fileMapper) {
		this.fileMapper = fileMapper;
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("Creating FileService bean");
	}

	public void addFile(File file) {
		// add message to database (Note object)
		fileMapper.insert(file);

	}

	public List<File> getFiles() {
		return fileMapper.getFiles();
	}

	public List<File> getAllFiles(Integer userId) {
		List<File> fileList = fileMapper.getFilesByUser(userId);
		return fileList;
	}

	public File getFile(int fileId) {
		File updateFile = fileMapper.findFile(fileId);
		return updateFile;
	}

	public String getFileByName(String filename, Integer userId) {
		String name = fileMapper.findFileByName(filename, userId);
		return name;
	}

	public void deleteFile(Integer fileId) {
		// delete in database
		fileMapper.delete(fileId);

	}

}
