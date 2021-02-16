package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class CredentialsController {
	@Autowired
	CredentialService credentialService;
	@Autowired
	UserService userService;

	@PostMapping("/credentials")
	public String postCredential(Authentication auth, Credential credential, RedirectAttributes redirectAttributes) {

		// get user name of current user and set it to object
		User user = userService.getUser(auth.getName());
		credential.setUserId(user.getUserId());

		// get String equivalent of NoteId to check if null
		String stringId = credential.getStringId();

		// if id is none, add note
		if (stringId.isEmpty()) {
			this.credentialService.addCredential(credential);
			redirectAttributes.addFlashAttribute("successEvent", "Credential successfully created!");
		}
		// if we received an id, update
		else {
			credential.setCredentialId(Integer.parseInt(stringId));
			this.credentialService.updateCredential(credential);
			redirectAttributes.addFlashAttribute("successEvent", "Credential successfully updated!");
		}

		return "redirect:/home";
	}

	@GetMapping("/credentials/delete/{id}")
	public String deleteCredentials(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		Integer credentialId = Integer.parseInt(id);
		credentialService.deleteCredential(credentialId);
		redirectAttributes.addFlashAttribute("successEvent", "Credential successfully deleted!");
		return "redirect:/home";
	}

}
