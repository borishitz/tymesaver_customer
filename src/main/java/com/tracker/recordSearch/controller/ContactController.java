package com.tracker.recordSearch.controller;


import com.tracker.recordSearch.domain.Contact;
import com.tracker.recordSearch.repository.ContactRepository;
import com.tracker.recordSearch.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepo;

    @GetMapping("/dashboard")
    public String homePage(Model model){
        List<Contact> contacts = contactService.allContacts();
        model.addAttribute("allContacts", contacts);
        return "dashboard/index";
    }

    @GetMapping("/contact/add")
    public String addContact(Model model){
        Contact contact = new Contact();
        model.addAttribute("newContact", contact);
        return "about";
    }

//    TEACHER'S PROFILE
    @GetMapping("/contact/detail/{id}")
    public String showDetialed(@PathVariable("id") Long id, Model model){
        Contact contact = contactRepo.findById(id).get();
        model.addAttribute("contact",contact);
        return "dashboard/contactDetail";
    }

    @GetMapping("/contacts")
    public String getAllContacts(Model model){
        List<Contact> contacts = contactService.allContacts();
        model.addAttribute("contacts", contacts);
        return "FormList";
    }

    @PostMapping("/contact/save")
    public String saveContact(Contact contact){
        contactService.saveContact(contact);
        return "redirect:/";
    }
}
