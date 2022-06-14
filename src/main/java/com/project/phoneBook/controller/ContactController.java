package com.project.phoneBook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.phoneBook.exception.*;
import com.project.phoneBook.model.Contact;
import com.project.phoneBook.controller.*;
import com.project.phoneBook.repositories.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class ContactController {

	@Autowired
	private ContactRepositories cRepository;
	
	// get all contacts
	@GetMapping("/contacts")
	public List<Contact> getAllContacts(){
		return cRepository.findAll();
	}		
	
	// create Contact rest api
	@PostMapping("/contact")
	public Contact createContact(@RequestBody Contact c) {
		return cRepository.save(c);
	}
	
	// get contact by id rest api
	@GetMapping("/contacts/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer id) {
		Contact c = cRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id :" + id));
		return ResponseEntity.ok(c);
	}
	
	// update contact rest api
	
	@PutMapping("/contacts/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable Integer id, @RequestBody Contact cDetails){
		Contact c = cRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id :" + id));
		
		c.setId(cDetails.getId());
		c.setNumber(cDetails.getNumber());
		c.setName(cDetails.getName());
		
		Contact updatedC = cRepository.save(c);
		return ResponseEntity.ok(updatedC);
	}
	
	// delete contact rest api
	@DeleteMapping("/contacts/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteC(@PathVariable Integer id){
		Contact c = cRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id :" + id));
		
		cRepository.delete(c);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
