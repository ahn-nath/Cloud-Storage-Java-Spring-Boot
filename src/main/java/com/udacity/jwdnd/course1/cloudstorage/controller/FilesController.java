package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.DuplicatedFileException;

@Controller
public class FilesController {
	@Autowired
	FileService fileService;
	@Autowired
	UserService userService;

	 
	@PostMapping("/files")
	public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication auth, RedirectAttributes redirectAttributes) throws IOException {

		try {
			
			// user data 
			User user = userService.getUser(auth.getName());

			
			// check if there's a file to upload
			if (fileUpload.isEmpty()) {
				redirectAttributes.addFlashAttribute("errorEvent",
						"Please choose a file");
				return "redirect:/home";
			}

			// check if name already exist
			if (checkIfDuplicated(fileUpload.getOriginalFilename(), user.getUserId()) == true) {
				throw new DuplicatedFileException();
			}

			// if file is !null and filename is unique, create file
			File file = new File();
			file.setFileData(fileUpload.getBytes());
			file.setUserId(user.getUserId());
			file.setFileSize(String.valueOf(fileUpload.getSize()));
			file.setContentType(fileUpload.getContentType());
			file.setFileName(fileUpload.getOriginalFilename());
			
			// add file to database
			fileService.addFile(file);

			// success
			redirectAttributes.addFlashAttribute("successEvent", "File successfully uploaded!");

		} 
		
		// error
		catch (DuplicatedFileException e) {
			redirectAttributes.addFlashAttribute("errorEvent", "File already exists!");
		}

		return "redirect:/home";
	}

	
	@GetMapping("/files/delete/{id}")
	public String deleteCredentials(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		Integer fileId = Integer.parseInt(id);
		fileService.deleteFile(fileId);
		
		// success
     	redirectAttributes.addFlashAttribute("successEvent", "File successfully deleted!");
		return "redirect:/home";
	}
	
	
	 @GetMapping("/files/download/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id, RedirectAttributes redirectAttributes) {
	    File file = fileService.getFile(Integer.parseInt(id));

	    redirectAttributes.addFlashAttribute("successEvent", "File downloaded!");
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
	        .body(file.getFileData());
	  }
	
	
	private boolean checkIfDuplicated(String filename, Integer userId) {
		String storedName = fileService.getFileByName(filename, userId);

		// if there's an existing file with same name, return true
		if (filename.equals(storedName)) {
			return true;
		}

		return false;
	}

}
