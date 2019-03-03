package devlab.phonebook.controller;

import devlab.phonebook.model.Contact;
import devlab.phonebook.service.ContactService;
import io.swagger.annotations.ResponseHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping(value = "/contacts")
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
