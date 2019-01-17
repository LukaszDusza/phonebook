package devlab.phonebook.controller;

import devlab.phonebook.model.Contact;
import devlab.phonebook.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public List<Contact> getContacts() {
        return contactService.getContacts();
    }

    @PostMapping("/contacts")
    public void addNew(@RequestBody Contact contact) {
        contactService.addNewContact(contact) ;
    }

    @GetMapping("/contacts/tag")
    public List<Contact> getContactsByTag(@RequestParam String title) {
        return contactService.getContactsByTag(title);
    }


}
