package com.project.phoneBook.repositories;

import org.springframework.stereotype.Repository;

import com.project.phoneBook.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ContactRepositories extends JpaRepository<Contact, Integer> {

}
