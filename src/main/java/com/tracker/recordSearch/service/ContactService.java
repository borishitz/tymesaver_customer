package com.tracker.recordSearch.service;


import com.tracker.recordSearch.domain.Contact;
import com.tracker.recordSearch.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> allContacts(){
        return (List<Contact>) contactRepository.findAll();
    }
}
