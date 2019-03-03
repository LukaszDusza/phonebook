package devlab.phonebook.excercise;

import devlab.phonebook.dtos.model.ContactDto;
import devlab.phonebook.model.Contact;
import devlab.phonebook.service.ContactService;

import java.util.List;

public class Example {

    private static ContactService contactService;


    public Example(ContactService contactService) {
        this.contactService = contactService;
    }

    public static List<ContactDto> getContacts() {
        return contactService.getAllDtoContacts();
    }
}
