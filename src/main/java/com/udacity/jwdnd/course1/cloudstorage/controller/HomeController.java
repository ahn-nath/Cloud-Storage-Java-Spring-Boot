package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class HomeController {
    @Autowired
    private NoteService noteService;
    
    @Autowired
    private CredentialService credentialService;

    @Autowired
    private FileService fileService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EncryptionService encryptionService;
	
	 @GetMapping("/home")
	    public ModelAndView getHome(Authentication auth, Note note) throws Exception {
		 	User user = userService.getUser(auth.getName());
		 
		 	if(user == null) {
	           return new ModelAndView(("login"));
	        }
	        
	        ModelAndView modelAndView = new ModelAndView("home");
	        modelAndView.addObject("notes", noteService.getAllNotes(user.getUserId()));
	        modelAndView.addObject("credentials", credentialService.getAllCredentials(user.getUserId()));
	        modelAndView.addObject("files", fileService.getAllFiles(user.getUserId()));
	        modelAndView.addObject("encryptionService", encryptionService);
	        
	        return modelAndView;
	    }
	 

}
