package com.udacity.jwdnd.course1.cloudstorage.services;


import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import javax.annotation.PostConstruct;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private List<Credential> listCredentials;
    private final CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

  

    
    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating CredentialService bean");
    }

    public void addCredential(Credential credential) { 
    	SecureRandom random = new SecureRandom();
    	byte[] key = new byte[16];
    	random.nextBytes(key);
    	String encodedKey = Base64.getEncoder().encodeToString(key);
    	String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
    	
    	//set values to object
    	credential.setPassword(encryptedPassword);
    	credential.setKey(encodedKey);
    	
        // add credential to database (Credential object)
        credentialMapper.insert(credential);
     
    }

    public List<Credential> getCredentials() {
        return credentialMapper.getCredentials();
    }
    
    public List<Credential> getAllCredentials(Integer userId){
        List<Credential> credentialList = credentialMapper.getCredentialsByUser(userId);
        return credentialList;
    }

	public Credential getCredential(int credentialId) {
		Credential updateCredential = credentialMapper.findCredential(credentialId);
		return updateCredential;
	}

	public void updateCredential(Credential credential) {
		SecureRandom random = new SecureRandom();
    	byte[] key = new byte[16];
    	random.nextBytes(key);
    	String encodedKey = Base64.getEncoder().encodeToString(key);
    	String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
    	
    	//set values to object
    	credential.setPassword(encryptedPassword);
    	
    	credential.setKey(encodedKey);
    	
    	
		
		
		// update in database
		credentialMapper.updateCredential(credential);
		
	}

	public void deleteCredential(Integer noteId) {
		// delete in database
		credentialMapper.delete(noteId);
		
	}
}
