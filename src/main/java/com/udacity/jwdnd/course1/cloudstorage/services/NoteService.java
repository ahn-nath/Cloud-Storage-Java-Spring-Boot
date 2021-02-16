package com.udacity.jwdnd.course1.cloudstorage.services;


import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private List<Note> listNotes;
    private final NoteMapper noteMapper;

    
    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating NoteService bean");
    }

    public void addNote(Note note) {       
        // add message to database (Note object)
        noteMapper.insert(note);
     
    }

    public List<Note> getNotes() {
        return noteMapper.getNotes();
    }
    
    public List<Note> getAllNotes(Integer userId){
        List<Note> notesList = noteMapper.getNotesByUser(userId);
        return notesList;
    }

	public Note getNote(int noteId) {
		Note updateNote = noteMapper.findNote(noteId);
		return updateNote;
	}

	public void updateNote(Note note) {
		// update in database
		noteMapper.updateNote(note);
		
	}

	public void deleteNote(Integer noteId) {
		// delete in database
		noteMapper.delete(noteId);
		
	}
}
