package com.udacity.jwdnd.course1.cloudstorage.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class NotesController {
    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;
   

	@PostMapping("/notes")
	public String postNote(Authentication auth, Note note, RedirectAttributes redirectAttributes) {
		// get user name of current user and set it to object
		User user = userService.getUser(auth.getName());
		note.setUserId(user.getUserId());
		// get String equivalent of NoteId to check if null
		String stringId = note.getStringId();
		
		//if id is none, add note
		if(stringId.isEmpty()) {
			this.noteService.addNote(note);
			// success
	     	redirectAttributes.addFlashAttribute("successEvent", "Note successfully created!");
		}
		//if we received an id, update
		else{
			note.setNoteId(Integer.parseInt(stringId));
			this.noteService.updateNote(note);
			// success
	     	redirectAttributes.addFlashAttribute("successEvent", "Note successfully updated!");
		}
		
		return "redirect:/home";
	}
	
	@GetMapping("/notes/delete/{id}")
    public String deleteNotes(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        Integer noteId = Integer.parseInt(id);
        noteService.deleteNote(noteId);
        
     // success
     	redirectAttributes.addFlashAttribute("successEvent", "Note successfully deleted!");
        return "redirect:/home";
    }

}
