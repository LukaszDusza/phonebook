package devlab.phonebook.controller;


import devlab.phonebook.dtos.mappers.ContactMapper;
import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.model.Contact;
import devlab.phonebook.service.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private List<ContactDto> getAllBooks() {
        List<Contact> books = contactService.getContacts();
        List<ContactDto> booksDto = new ArrayList<>();
        for (Contact b : books) {
            booksDto.add(mapper.map(b));
        }
        return booksDto;
    }
}
