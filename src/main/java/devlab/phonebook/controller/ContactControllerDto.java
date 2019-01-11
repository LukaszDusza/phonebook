package devlab.phonebook.controller;


import devlab.phonebook.dtos.mappers.ContactMapper;
import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.model.Contact;
import devlab.phonebook.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/dto/")
public class ContactControllerDto {

    private ContactService contactService;
    private ContactMapper mapper;

    public ContactControllerDto(ContactService contactService, ContactMapper mapper) {
        this.contactService = contactService;
        this.mapper = mapper;
    }

    @GetMapping("/contacts")
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactService.getContacts();
        List<ContactDto> contactsDto = new ArrayList<>();
        for (Contact c : contacts) {
            contactsDto.add(mapper.map(c));
        }
        return contactsDto;
    }


    @GetMapping("/contacts/{name}")
    public List<ContactDto> getAllContacts(@PathVariable String name) {
        List<Contact> contacts = contactService.getContactsByname(name);
        List<ContactDto> contactsDto = new ArrayList<>();
        for (Contact c : contacts) {
            contactsDto.add(mapper.map(c));
        }
        return contactsDto;
    }

}
